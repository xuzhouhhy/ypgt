package com.geocraft.electrics.event;

/**
 * Created by Administrator on 2016/6/7.
 */
public class RefreshPhotoAdapterEventArgs {

	private String mCurrentDataSetName = "";

	public RefreshPhotoAdapterEventArgs(String dataSetName) {
		this.mCurrentDataSetName = dataSetName;
	}

	public String getCurrentDataSetName() {
		return this.mCurrentDataSetName;
	}
}
