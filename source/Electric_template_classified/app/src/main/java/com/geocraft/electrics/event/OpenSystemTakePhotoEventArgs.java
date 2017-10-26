package com.geocraft.electrics.event;

import java.io.File;

/**
 * Created by Administrator on 2016/6/7.
 */
public class OpenSystemTakePhotoEventArgs {

	File mFile;
	private String mCurrentDataSetName = "";

	public OpenSystemTakePhotoEventArgs(File file, String dataSetName) {
		this.mFile = file;
		this.mCurrentDataSetName = dataSetName;
	}

	public File getFile() {
		return mFile;
	}

	public String getCurrentDataSetName() {
		return this.mCurrentDataSetName;
	}
}
