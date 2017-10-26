package com.geocraft.electrics.constants;

import common.geocraft.untiltools.SDCardManager;

/**
 * 作者  zhouqin
 * 时间 2016/6/5.
 */
public class ConstPath {
	public static String getAssetsZipFileName() {
		return "YPGT.zip";
	}

	public static String getAppDataFolder() {
		return SDCardManager.GetSDCardPath() + "YPGT/";
	}

	public static String getTaskRootFolder() {
		return getAppDataFolder() + "任务/";
	}

	public static String getTemplateRootFolder() {
		return getAppDataFolder() + "模板/";
	}

	public static String getLogPath() {
		return getAppDataFolder() + "Log/";
	}

	public static String getImportPath() {
		return getAppDataFolder() + "导入/";
	}

	public static String getSystemPath() {
		return getAppDataFolder() + "系统/";
	}
	public static String getIconPath() {
		return getSystemPath() + "图标/";
	}

	public static String getLogFileName() {
		return "YPGT.txt";
	}

	public static String getLogFilePathName() {
		return getLogPath() + getLogFileName();
	}

	public static String getRegisterPath() {
		return getSystemPath()+"register.txt";
	}
}
