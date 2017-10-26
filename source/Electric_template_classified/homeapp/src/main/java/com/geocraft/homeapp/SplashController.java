package com.geocraft.homeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import org.androidannotations.annotations.EBean;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@EBean
public class SplashController {
	public void splash(Context context) {
		Intent intent = new Intent(context,
				MainActivity_.class);
		context.startActivity(intent);
		((Activity) context).finish();
	}

}
