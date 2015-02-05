package com.Dominospizzadb.dominospizzadb;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity{

	private static final long SPLASH_DELAY = 500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.splash);

		TimerTask mTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent().setClass(
						SplashActivity.this, Dominospizzadb.class);
				startActivity(mIntent);
				finish();
			}
		};

		Timer timer = new Timer();
		timer.schedule(mTask, SPLASH_DELAY);
	}

}
