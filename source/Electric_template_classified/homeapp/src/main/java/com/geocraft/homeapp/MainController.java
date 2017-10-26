package com.geocraft.homeapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.huace.log.logger.L;

import org.androidannotations.annotations.EBean;

import java.io.File;

/**
 * 作者  zhouqin
 * 时间 2016/6/13.
 */
@EBean
public class MainController {
	/**
	 * get version code of app
	 */
	public  int getVersionCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			L.e(e.getMessage());
		}
		return verCode;
	}
	public void openYPGT(Context context){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		ComponentName componentName
				= new ComponentName("com.geocraft.electrics", "com.geocraft.electrics.ui.activity.SplashActivity_");
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo("com.geocraft.electrics", 0);
			if(info.versionCode != getVersionCode(context))
			{
				installApkTest(context,Constants.apk1);
			}
		}
		catch(PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		intent.setComponent(componentName);
		context.startActivity(intent);
	}
	public void openRouteInspection(Context context){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		ComponentName componentName
				= new ComponentName("com.example.datacollect", "com.example.datacollect.MainActivity");
		intent.setComponent(componentName);
		context.startActivity(intent);
	}

	public void installApkTest(Context context,String appFullName) {
		String fileName = Constants.getAppDataFolder()+appFullName;
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(new File(fileName)),"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	public boolean verifyFileExists(){
		File apk1 = new File(Constants.getAppDataFolder()+Constants.apk1);
		File apk2 = new File(Constants.getAppDataFolder()+Constants.apk2);
		if (apk1.exists()&&apk2.exists()){
			return true;
		}
		else {
			return false;
		}

	}
}
