package com.psu.nvalues;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity implements OnClickListener{
	
	Button btn_speak;
	Button btn_help;
	Button btn_exit;
	
	Button btn_strawberry;
	Button btn_burger;
	Button btn_tangerine;
	Button btn_eggplant;
	Button btn_cheesecake;
	Button btn_carrot;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        btn_speak = (Button)findViewById(R.id.btn_speak);
        btn_speak.setOnClickListener(this);
        
        btn_help= (Button)findViewById(R.id.btn_help);
        btn_help.setOnClickListener(this);
        
        btn_exit= (Button)findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);
        
        btn_strawberry= (Button)findViewById(R.id.btn_strawberry);
        btn_strawberry.setOnClickListener(this);
        
        btn_burger= (Button)findViewById(R.id.btn_burger);
        btn_burger.setOnClickListener(this);
        
        btn_tangerine= (Button)findViewById(R.id.btn_tangerine);
        btn_tangerine.setOnClickListener(this);
        
        btn_eggplant= (Button)findViewById(R.id.btn_eggplant);
        btn_eggplant.setOnClickListener(this);
        
        btn_cheesecake= (Button)findViewById(R.id.btn_cheesecake);
        btn_cheesecake.setOnClickListener(this);
        
        btn_carrot= (Button)findViewById(R.id.btn_carrot);
        btn_carrot.setOnClickListener(this);
           
    }
	


public void onClick(View v) {
    
    switch(v.getId()){

    case R.id.btn_speak: 

    	//NutritionalValuesActivity._currentRecognizer.start();
        Intent speak_intent = new Intent(this, SpeakActivity.class);
        this.startActivity(speak_intent);
        
         break;

    case R.id.btn_help: 

        Intent help_intent = new Intent(this, HelpActivity.class);
        this.startActivity(help_intent);
        
         break;
         

    case R.id.btn_exit: 
    	/*Intent main_page_intent = new Intent(this, NutritionalValuesActivity.class);
        this.startActivity(main_page_intent);*/
    	Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent);
    	System.exit(0);
         break;

    case R.id.btn_strawberry: 
        Intent strawberry_intent = new Intent(this, StrawberryActivity.class);
        this.startActivity(strawberry_intent);

         break;

    case R.id.btn_burger: 
        Intent  burger_intent = new Intent(this, BurgerActivity.class);
        this.startActivity(burger_intent);
         break;

    case R.id.btn_tangerine: 
        Intent tangerine_intent = new Intent(this, TangerineActivity.class);
        this.startActivity(tangerine_intent);
         break;

    case R.id.btn_eggplant: 
        Intent eggplant_intent = new Intent(this, EggplantActivity.class);
        this.startActivity(eggplant_intent);
         break;

    case R.id.btn_cheesecake: 
        Intent cheesecake_intent = new Intent(this, CheesecakeActivity.class);
        this.startActivity(cheesecake_intent);
         break;
         

    case R.id.btn_carrot: 
        Intent carrot_intent = new Intent(this, CarrotActivity.class);
        this.startActivity(carrot_intent);
         break;
         
    }

}

}
