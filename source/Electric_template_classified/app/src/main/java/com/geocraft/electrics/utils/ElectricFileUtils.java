package com.geocraft.electrics.utils;

import java.io.File;

/**
 * Created by Administrator on 2016/6/5.
 */
public class ElectricFileUtils {

	/**
	 * 获取文件后缀名
	 */
	public static String getFileExt(File file) {
		return getFileExt(file.getName());
	}

	/**
	 * 获取文件后缀名
	 */
	public static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public static String getFileName(String fileName)
	{
		int start=fileName.lastIndexOf("/");
		int end=fileName.lastIndexOf(".");
		if(start!=-1 && end!=-1)
		{
			return fileName.substring(start+1,end);
		}
		else
		{
			return "";
		}
	}
}
