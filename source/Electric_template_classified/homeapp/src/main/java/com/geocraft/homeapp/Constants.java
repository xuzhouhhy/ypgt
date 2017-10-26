package com.geocraft.homeapp;

import common.geocraft.untiltools.SDCardManager;

/**
 * 作者  zhouqin
 * 时间 2016/6/5.
 */
public class Constants {
	public static String getAssetsZipFileName() {
		return "all.zip";
	}

	public static String getAppDataFolder() {
		return SDCardManager.GetSDCardPath() + "/all/";
	}

	public static String apk1 = "YPGT.apk";

	public static String apk2 = "RouteInspection.apk";

}
