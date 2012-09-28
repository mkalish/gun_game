package com.kalish.gungame;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ShootingActivity extends Activity{

	private SurfaceView preview = null;
	private SurfaceHolder previewHolder = null;
	private Camera camera = null;
	private boolean inPreview = false;
	private boolean cameraConfigured = false;
	private int numOfBullets = 7;
	private ArrayList<ImageView> images = new ArrayList<ImageView>();
	private LinearLayout bullets;
	private Button shootSomething;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shooting);
		
		bullets = (LinearLayout) findViewById(R.id.bullets);
		for(int i=0; i<=numOfBullets; i++) {
			addBullet();
		}
		
		preview = (SurfaceView) findViewById(R.id.preview);
		previewHolder = preview.getHolder();
		previewHolder.addCallback(surfaceCallback);
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		shootSomething =  (Button) findViewById(R.id.bam);
		shootSomething.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Log.i("Shoot Button", "Something shot");
				bullets.removeViewAt(0);
			}
		});
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.map:
			Intent game = new Intent(getBaseContext(), GameModeActivity.class);
			startActivity(game);
			return true;
		default:
			return true;
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shooting_menu, menu);
        return true;
    }
	
	private void addBullet() {
			ImageView img = new ImageView(this);
			img.setImageResource(R.drawable.bullet);
			images.add(img);
			bullets.addView(img);
	}

	public void onResume() {
		super.onResume();
		camera = Camera.open();
		startPreview();
	}
	
	public void onPause() {
		if(inPreview) {
			camera.stopPreview();
		}
		
		camera.release();
		camera = null;
		inPreview = false;
		
		super.onPause();
	}
	
	private Camera.Size getBestPreviewSize(int width, int height, Camera.Parameters parameters){
		Camera.Size result = null;
		
		for(Camera.Size size: parameters.getSupportedPreviewSizes()) {
			if(size.width<=width && size.height<=height) {
				if(result==null) {
					result = size;
				}
				else {
					int resultArea = result.width*result.height;
					int newArea = size.width*size.height;
					if(newArea>resultArea) {
						result = size;
					}
				}
			}
		}
		return result;
 	}
	
	private void initPreview(int width, int height) {
		if(camera!=null && previewHolder.getSurface()!=null) {
			try {
				camera.setPreviewDisplay(previewHolder);
			}
			catch(Throwable t) {
				Log.e("PreviewDemo-surfaceCallback","Exception in setPrevieDisplay", t);
				Toast
					.makeText(ShootingActivity.this, t.getMessage(), Toast.LENGTH_LONG)
					.show();
			}
			
			if(!cameraConfigured) {
				Camera.Parameters parameters = camera.getParameters();
				Camera.Size size = getBestPreviewSize(width, height, parameters);
				
				if(size!=null) {
					parameters.setPreviewSize(size.width, size.height);
					camera.setParameters(parameters);
					cameraConfigured = true;
				}
			}
		}
	}
	
	private void startPreview() {
		if(cameraConfigured && camera!=null) {
			camera.startPreview();
			Log.i("Camera Activity", "Camera preview called");
			inPreview = true;
		}
	}
	
	SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
		
		public void surfaceDestroyed(SurfaceHolder holder) {
			
		}
		
		public void surfaceCreated(SurfaceHolder holder) {
			
		}
		
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			initPreview(width, height);
			startPreview();
		}
	};
}
