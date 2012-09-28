package com.kalish.gungame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CoolActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Button button = new Button(this);
		button.setText("Enter the game");
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent game = new Intent(getBaseContext(), ShootingActivity.class);
				startActivity(game);
			}
		});
		setContentView(button);
	}
}
