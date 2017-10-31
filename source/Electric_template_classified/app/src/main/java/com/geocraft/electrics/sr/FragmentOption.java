package com.geocraft.electrics.sr;

import com.geocraft.electrics.base.BusinessFragment;

/**
 * 可选项fragment配置类
 */

public class FragmentOption {
    private BusinessFragment mFragment;
    private String mFramentName;
    private String mDatasetName;
    private String mFramentNameKey;
    private boolean mIsChecked;
    private String mParentNameKey = "";

    public FragmentOption(String framentNameKey, String framentName,
                          String datasetName, BusinessFragment fragment) {
        mFramentNameKey = framentNameKey;
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

    public String getFramentNameKey() {
        return mFramentNameKey;
    }

    public void setFramentNameKey(String framentNameKey) {
        mFramentNameKey = framentNameKey;
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
