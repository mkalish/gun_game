package com.kalish.gungame;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.app.TabActivity;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.menu);
		Resources resources = getResources();
		TabHost tabHost = getTabHost();
		
		Intent intentCool = new Intent().setClass(this, CoolActivity.class);
		TabSpec tabSpecCool = tabHost.newTabSpec("Cool")
				.setIndicator(resources.getString(R.string.games))
				.setContent(intentCool);
		
		Intent intentLame = new Intent().setClass(this, LameActivity.class);
		TabSpec tabSpecLame = tabHost.newTabSpec("Lame")
				.setIndicator(resources.getString(R.string.accout))
				.setContent(intentLame);
		
		tabHost.addTab(tabSpecCool);
		tabHost.addTab(tabSpecLame);
		tabHost.setCurrentTab(2);
	}
}
