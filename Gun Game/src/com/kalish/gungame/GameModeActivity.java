package com.kalish.gungame;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GameModeActivity extends MapActivity {
	
	private MapView map;

	public void onCreate(Bundle savedInstanceState) {
		//Initial setup
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_layout);
		
		//Find and setup up Map
		map = (MapView) this.findViewById(R.id.mapview);
		map.setBuiltInZoomControls(true);
		MapController mapController = map.getController();
		
		
		//Add overlay
		List<Overlay> mapOverlays = map.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.enemy);
		EnemyItemizedOverlay itemizedOverlay = new EnemyItemizedOverlay(drawable, this);
		float lat = 38.93027f;
		float lon = -77.194111f;
		GeoPoint point1 = new GeoPoint((int)(lat * 1E6),(int)(lon * 1E6));
		mapController.setZoom(14);
		mapController.setCenter(point1);
		OverlayItem overlayItem1 = new OverlayItem(point1, "Test1", "test1");
		itemizedOverlay.addOverlay(overlayItem1);
		mapOverlays.add(itemizedOverlay);
		
		//Switch to camera button

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
