package com.kalish.gungame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LameActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView textview = new TextView(this);
		textview.setText("This is lame");
		setContentView(textview);
	}
}
