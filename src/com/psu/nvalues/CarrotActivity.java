package com.psu.nvalues;



import com.psu.nvalues.AppInfo;
import com.nuance.nmdp.speechkit.Recognizer;
import com.nuance.nmdp.speechkit.Recognition;
import com.nuance.nmdp.speechkit.SpeechError;

import com.nuance.nmdp.speechkit.Prompt;
import com.nuance.nmdp.speechkit.SpeechKit;
import com.nuance.nmdp.speechkit.Vocalizer;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.Button;

public class CarrotActivity extends Activity implements OnClickListener{
	Button btn_menu;
	Button btn_exit;

    private static SpeechKit _speechKit;
    private Vocalizer _vocalizer;
    private Object _lastTtsContext = null;    
    
    // Allow other activities to access the SpeechKit instance.
    static SpeechKit getSpeechKit()
    {
        return _speechKit;
    }	
    
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrot);
        
        setVolumeControlStream(AudioManager.STREAM_MUSIC); // So that the 'Media Volume' applies to this activity
        
        // If this Activity is being recreated due to a config change (e.g. 
        // screen rotation), check for the saved SpeechKit instance.
        _speechKit = (SpeechKit)getLastNonConfigurationInstance();
        if (_speechKit == null)
        {
            _speechKit = SpeechKit.initialize(getApplication().getApplicationContext(), AppInfo.SpeechKitAppId, AppInfo.SpeechKitServer, AppInfo.SpeechKitPort, AppInfo.SpeechKitSsl, AppInfo.SpeechKitApplicationKey);
            _speechKit.connect();
            // TODO: Keep an eye out for audio prompts not working on the Droid 2 or other 2.2 devices.
            Prompt beep = _speechKit.defineAudioPrompt(R.raw.beep);
            _speechKit.setDefaultRecognizerPrompts(beep, Prompt.vibration(100), null, null);
        }
        
        
        btn_menu= (Button)findViewById(R.id.btn_carrot_menu);
        btn_menu.setOnClickListener(this);
        
        
        
        btn_exit= (Button)findViewById(R.id.btn_carrot_exit);
        btn_exit.setOnClickListener(this);        
        
        String str_nutrition = "Carrots of Serving Size 1 cup or 128g contain," +
        		"Total Calories             52," +
        		"Total Fat                    3," +
        		"Carbohydrate           12g," +
        		"Protein                 4g," +
        		"Vitamin A                428%," +
        		"Protein   abc             4g," +
        		"Vitamin abc                428%," +
        		"Vitamin C              13% "; 
        
        _vocalizer = getSpeechKit().createVocalizerWithLanguage("en_US", vocalizerListener, new Handler());        
        
        _lastTtsContext = new Object();

        _vocalizer.speakString(str_nutrition, _lastTtsContext);	      
               
    }	
    

    // Create Vocalizer listener
    public Vocalizer.Listener vocalizerListener = new Vocalizer.Listener()
     {
         @Override
         public void onSpeakingBegin(Vocalizer vocalizer, String text, Object context) {
            // updateCurrentText("Playing text: \"" + text + "\"", Color.GREEN, false);
         }

         @Override
         public void onSpeakingDone(Vocalizer vocalizer,
                 String text, SpeechError error, Object context) 
         {

         }
     };
public void onClick(View v) {
    
    switch(v.getId()){

    case R.id.btn_carrot_menu: 
    	_vocalizer.cancel();  
    	_vocalizer = null;
    	finish();
        Intent menu_intent = new Intent(this, MenuActivity.class);
        this.startActivity(menu_intent);
        
         break;

    case R.id.btn_carrot_exit: 
    	_vocalizer.cancel();  
    	_vocalizer = null;	
    	finish();
    	Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent);
    	System.exit(0);
        
         break;
    }
}


@Override
protected void onDestroy() {
    super.onDestroy();
    if (_vocalizer != null)
    {
        _vocalizer.cancel();
        _vocalizer = null;
    }
    
    if (_speechKit != null)
    {
        _speechKit.release();
        _speechKit = null;
    }
}

@Override
public Object onRetainNonConfigurationInstance()
{
    // Save the SpeechKit instance, because we know the Activity will be
    // immediately recreated.
    SpeechKit sk = _speechKit;
    _speechKit = null; // Prevent onDestroy() from releasing SpeechKit
    return sk;
}
}
