package com.geocraft.electrics.sr;

import com.geocraft.electrics.sr.fragment.WellBaseFragment;

/**
 * 可选项fragment配置类
 */

public class FragmentOption {
    private WellBaseFragment mFragment;
    private String mFramentName;
    private String mDatasetName;
    private String mNameKey;
    private boolean mIsChecked;
    private String mParentNameKey = "";

    public FragmentOption(String framentNameKey, String framentName,
                          String datasetName, WellBaseFragment fragment) {
        mNameKey = framentNameKey;
        mFramentName = framentName;
        mDatasetName = datasetName;
        mFragment = fragment;
    }

    public WellBaseFragment getFragment() {
        return mFragment;
    }

    public void setFragment(WellBaseFragment fragment) {
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
