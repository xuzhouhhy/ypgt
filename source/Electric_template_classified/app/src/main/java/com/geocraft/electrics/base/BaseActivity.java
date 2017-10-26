package com.geocraft.electrics.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.geocraft.electrics.app.ElectricApplication_;
import com.huace.log.crashlog.CrashManager;
import com.huace.log.logger.L;

/**
 * Created by hanpengfei on 2016/4/15.
 */
public abstract class BaseActivity extends AppCompatActivity {
	/**
	 * Show navigation home button by default.
	 * Register activity to crash logger in order to build activity stack when crash happened.
	 */
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Register Activity into log manager stack.
		//CrashManager.getManager(getApplicationContext()).registerActivity(this);
		showHomeButton();

		ElectricApplication_.getInstance().addActivity(this);
	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		CrashManager.getManager(getApplicationContext()).unregisterActivity(this);
		ElectricApplication_.getInstance().removeActivity();
		super.onDestroy();

	}

	void showHomeButton() {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayUseLogoEnabled(false);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}

	public void hideHomeButton() {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(false);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		this.setRequestedOrientation(newConfig.orientation);
	}

	/**
	 * Override this method in order to avoid of the situation that if user quick click at a view,
	 * click event will dispatch many times.
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		try {
			if (ev.getAction() == MotionEvent.ACTION_DOWN) {
				if (isFastDoubleClick()) {
					return true;
				}
			}
			return super.dispatchTouchEvent(ev);
		} catch (Exception ignore) {
			L.printException(ignore);
		}
		return true;
	}

	private static long lastClickTime;
	private final static long DOUBLE_CLICK_INTERVAL = 300;

	private static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (timeD > DOUBLE_CLICK_INTERVAL) {
			lastClickTime = time;
			return false;
		} else {
			return true;
		}
	}


}
