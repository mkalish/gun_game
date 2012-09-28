package com.kalish.gungame;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class EnemyItemizedOverlay extends ItemizedOverlay {

	
	private ArrayList<OverlayItem> enemies = new ArrayList<OverlayItem>();
	Context mContext;
	
	public EnemyItemizedOverlay(Drawable enemyMarker) {
		super(boundCenterBottom(enemyMarker));
	}
	
	public EnemyItemizedOverlay(Drawable enemyMarker, Context context) {
		super(boundCenterBottom(enemyMarker));
		mContext = context;
	}
	
	@Override
	protected boolean onTap(int index) {
		OverlayItem item =  enemies.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}
	
	public void addOverlay(OverlayItem overlay){
		enemies.add(overlay);
		populate();
	}
	
	
	
	@Override
	protected OverlayItem createItem(int i) {
		return enemies.get(i);
	}

	@Override
	public int size() {
		return enemies.size();
	}

}
