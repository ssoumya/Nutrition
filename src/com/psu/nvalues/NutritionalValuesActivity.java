package com.psu.nvalues;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NutritionalValuesActivity extends Activity {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        
        
    }
    public void OnClickNutrition(View view)
    {
    	Intent IntentMenu = new Intent();
    	IntentMenu.setClass(this, MenuActivity.class);
    	startActivity(IntentMenu);
    }
    

}


