package com.kalish.gungame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MenuActivity extends Activity {
	
	private Button login;
	private EditText username;
	private EditText pw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.login = (Button) this.findViewById(R.id.submit);
        this.username =  (EditText) this.findViewById(R.id.username);
        this.pw = (EditText) this.findViewById(R.id.pw);
        login.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intentCool = new Intent(getBaseContext(), MainActivity.class);
				startActivity(intentCool);
			}
		}); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
    
    
}
