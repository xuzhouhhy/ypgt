package com.geocraft.homeapp;

import android.app.Activity;
import android.os.Handler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@EActivity(R.layout.acitivity_splash)
public class SplashActivity extends Activity {

	@Bean
	SplashController mController;

	@AfterViews
	void init() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mController.splash(SplashActivity.this);
			}
		}, 2000);
	}
}
