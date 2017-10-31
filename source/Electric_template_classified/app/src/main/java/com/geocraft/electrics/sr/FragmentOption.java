package com.geocraft.electrics.sr;

import com.geocraft.electrics.base.BusinessFragment;

/**
 * 可选项fragment配置类
 */

public class FragmentOption {
    private BusinessFragment mFragment;
    private String mFramentName;
    private String mDatasetName;
    private String mNameKey;
    private boolean mIsChecked;
    private String mParentNameKey = "";

    public FragmentOption(String framentNameKey, String framentName,
                          String datasetName, BusinessFragment fragment) {
        mNameKey = framentNameKey;
        mFramentName = framentName;
        mDatasetName = datasetName;
        mFragment = fragment;
    }

    public BusinessFragment getFragment() {
        return mFragment;
    }

    public void setFragment(BusinessFragment fragment) {
        mFragment = fragment;
    }

    public String getFramentName() {
        return mFramentName;
    }

    public void setFramentName(String framentName) {
        mFramentName = framentName;
    }

    public String getDatasetName() {
        return mDatasetName;
    }

    public void setDatasetName(String datasetName) {
        mDatasetName = datasetName;
    }

    public String getNameKey() {
        return mNameKey;
    }

    public void setNameKey(String nameKey) {
        mNameKey = nameKey;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }

    public String getParentNameKey() {
        return mParentNameKey;
    }

    public void setParentNameKey(String parentNameKey) {
        mParentNameKey = parentNameKey;
    }
}
