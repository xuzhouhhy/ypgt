package com.geocraft.electrics.entity;

import java.util.ArrayList;

/**
 * Created by hanpengfei on 2016/6/4.
 */
public class DataSetGroup {
    public String Name;
    public String Alias;
    public String Mark;
    public boolean IsVisible;
    private boolean mIsShowInDeviceList;//在三级数据显示界面显示

    public ArrayList<DataSet> DataSets = new ArrayList<DataSet>();

    public DataSetGroup() {
        Name = "";
        Alias = "";
        Mark = "";
        IsVisible = true;
        mIsShowInDeviceList = false;
    }

    public boolean isShowInDeviceList() {
        return mIsShowInDeviceList;
    }

    public void setmIsShowInDeviceList(boolean mIsShowInDeviceList) {
        this.mIsShowInDeviceList = mIsShowInDeviceList;
    }
}
