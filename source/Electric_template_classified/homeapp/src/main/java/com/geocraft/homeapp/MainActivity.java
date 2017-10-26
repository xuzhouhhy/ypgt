package com.geocraft.homeapp;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.io.File;
import java.io.IOException;

import common.geocraft.untiltools.T;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

	@Bean
	MainController mController;


	@AfterViews
	void init(){
		UnZipAssetsAsyncTask mAsyncTask = new UnZipAssetsAsyncTask(MainActivity.this,mController);
		mAsyncTask.execute(mController);
	}
	@Click
	void btnAppYPGT(){
		try {
			mController.openYPGT(MainActivity.this);
		} catch (ActivityNotFoundException e) {
			mController.installApkTest(MainActivity.this,Constants.apk1);
		}
	}
	@Click
	void btnRouteInspection(){
		try {
			mController.openRouteInspection(MainActivity.this);
		} catch (ActivityNotFoundException e) {
			mController.installApkTest(MainActivity.this,Constants.apk2);
		}
	}
	@Override
	public void onBackPressed()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.app_tip);
		builder.setIcon(R.mipmap.ic_tip);
		builder.setMessage(R.string.app_alert_exit_application);
		builder.setNegativeButton(R.string.app_no, null);
		builder.setInverseBackgroundForced(true);
		builder.setPositiveButton(R.string.app_yes, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				if(which == DialogInterface.BUTTON1)
				{
					// 退出应用程序
					android.os.Process.killProcess(android.os.Process.myPid());
				}
				else
				{
					return;
				}
			}
		}).show();
	}




}
