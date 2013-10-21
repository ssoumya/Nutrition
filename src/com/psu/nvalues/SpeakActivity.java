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
import android.os.SystemClock;



public class SpeakActivity extends Activity {

    private static SpeechKit _speechKit;
    private Vocalizer _vocalizer;
    private Object _lastTtsContext = null;    
    private String recog_result;
    private Handler _handler = null;
    private final Recognizer.Listener _listener;
    private Recognizer _currentRecognizer;

    
    public SpeakActivity()
    {
    	super();
    _listener = createListener();
    _currentRecognizer = null;
    
    }
    // Allow other activities to access the SpeechKit instance.
    static SpeechKit getSpeechKit()
    {
        return _speechKit;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.menu);
        
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
        
        _currentRecognizer = getSpeechKit().createRecognizer(Recognizer.RecognizerType.Dictation, Recognizer.EndOfSpeechDetection.Long, "en_US", _listener, _handler);
        _currentRecognizer.start();
        
	    //Intent menu_intent = new Intent(this, MenuActivity.class);
	    //this.startActivity(menu_intent);	
		//finish();


}//end of on create bundle


public void ProcessRecog()
{
	 
	if(recog_result.length() == 0)
	{
		//_vocalizer = getSpeechKit().createVocalizerWithLanguage("en_US", vocalizerListener, new Handler());                    
		//_lastTtsContext = new Object();
		_vocalizer.speakString("Sorry I did not recognize what you said" , _lastTtsContext);

	    Intent menu_intent = new Intent(this, MenuActivity.class);
	    this.startActivity(menu_intent);	
		//finish();

	}
	else
	{
		if(recog_result.equalsIgnoreCase("help") )
		{		        
			Intent help_intent = new Intent(this, HelpActivity.class);
	        this.startActivity(help_intent);		
	        //finish();

			
		}
		else if(recog_result.equalsIgnoreCase("exit"))
		{
	    	Intent intent = new Intent(Intent.ACTION_MAIN);
	    	intent.addCategory(Intent.CATEGORY_HOME);
	    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	startActivity(intent);	
	    	System.exit(0);
		}	

		else if(recog_result.equalsIgnoreCase("Carrot") || recog_result.equalsIgnoreCase("Carrots"))
		{
	        Intent carrot_intent = new Intent(this, CarrotActivity.class);
	        this.startActivity(carrot_intent);		
			//finish();

		}
		else if(recog_result.equalsIgnoreCase("Cake") || recog_result.equalsIgnoreCase("cheese cake") || recog_result.equalsIgnoreCase("cheesecake") )
		{

	        Intent cheesecake_intent = new Intent(this, CheesecakeActivity.class);
	        this.startActivity(cheesecake_intent);	
			//finish();

		}
		
		else if(recog_result.equalsIgnoreCase("Burger") || recog_result.equalsIgnoreCase("Quesadilla Burger"))
		{

		       Intent burger_intent = new Intent(this, BurgerActivity.class);
		       this.startActivity(burger_intent);	
				//finish();

		}
		
		else if(recog_result.equalsIgnoreCase("Eggplant") || recog_result.equalsIgnoreCase("Parmigiana Eggplant"))
		{

	        Intent eggplant_intent = new Intent(this, EggplantActivity.class);
	        this.startActivity(eggplant_intent);		
			//finish();

		}	

		else if(recog_result.equalsIgnoreCase("Strawberry") || recog_result.equalsIgnoreCase("Strawberries"))
		{

	        Intent strawberry_intent = new Intent(this, StrawberryActivity.class);
	        this.startActivity(strawberry_intent);		
			//finish();

		}	
		
		else if(recog_result.equalsIgnoreCase("Tangerine") || recog_result.equalsIgnoreCase("Tangerines"))
		{

	        Intent tangerine_intent = new Intent(this, TangerineActivity.class);
	        this.startActivity(tangerine_intent);	
			//finish();

		}	
		else
		{
			//_vocalizer = getSpeechKit().createVocalizerWithLanguage("en_US", vocalizerListener, new Handler());                    
			//_lastTtsContext = new Object();
			_vocalizer.speakString("Sorry that food item is not in the list" , _lastTtsContext);	
	        Intent menu_intent = new Intent(this, MenuActivity.class);
	        this.startActivity(menu_intent);	

		}

	}

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


public Recognizer.Listener createListener()
{
    return new Recognizer.Listener()
    {            
        @Override
        public void onRecordingBegin(Recognizer recognizer) 
        {
            
            // Create a repeating task to update the audio level
            Runnable r = new Runnable()
            {
                public void run()
                {
 
                        _handler.postDelayed(this, 500);
                }
            };
            r.run();
        }

        @Override
        public void onRecordingDone(Recognizer recognizer) 
        {
        }

        @Override
        public void onError(Recognizer recognizer, SpeechError error) 
        {
        	if (recognizer != _currentRecognizer) return;
            //_currentRecognizer = null;

            // Display the error + suggestion in the edit box
            //String detail = error.getErrorDetail();
            String suggestion = error.getSuggestion();
            
            if (suggestion == null) suggestion = "Error connecting to speech server";
        	_vocalizer = getSpeechKit().createVocalizerWithLanguage("en_US", vocalizerListener, new Handler());                    
            _lastTtsContext = new Object();
    		_vocalizer.speakString("Sorry I did not recognize what you said" , _lastTtsContext);

            //finish();
}

        @Override
        public void onResults(Recognizer recognizer, Recognition results) {
           // _currentRecognizer = null;
            int count = results.getResultCount();
            Recognition.Result [] rs = new Recognition.Result[count];
            for (int i = 0; i < count; i++)
            {
                rs[i] = results.getResult(i);
            }
        	_vocalizer = getSpeechKit().createVocalizerWithLanguage("en_US", vocalizerListener, new Handler());                    
            _lastTtsContext = new Object();
            recog_result = rs[0].getText();
            if(recog_result.length() != 0)
            	_vocalizer.speakString("You said " + rs[0].getText() , _lastTtsContext);
            //SystemClock.sleep(500);
            ProcessRecog();

            
        }
    };
}

@Override
public void onDestroy() {
    super.onDestroy();

    if (_currentRecognizer !=  null)
    {
        _currentRecognizer.cancel();
        _currentRecognizer = null;
    }
    
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