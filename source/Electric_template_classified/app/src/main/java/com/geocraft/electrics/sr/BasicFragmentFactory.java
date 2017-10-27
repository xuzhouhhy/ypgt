package com.geocraft.electrics.sr;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.sr.fragment.HWGBasicFragment_;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 */
@EBean
public class BasicFragmentFactory {
    //    private List<DataFragment> mHwgFragments = new ArrayList<DataFragment>();//环网柜
//    private List<DataFragment> mKBSFragments = new ArrayList<DataFragment>();//开闭所
//    private List<DataFragment> mFJXFragments = new ArrayList<DataFragment>();//分接箱
//    private List<DataFragment> mDLFJXFragments = new ArrayList<DataFragment>();//电缆分接箱
//    private List<DataFragment> mXSBDZFragments = new ArrayList<DataFragment>();//箱式变电站
//    private List<DataFragment> mZSBYQFragments = new ArrayList<DataFragment>();//柱上变压器
    //    private List<DataFragment> mTowerFragments = new ArrayList<DataFragment>();//杆号自带
//    private List<DataFragment> mWellFragments = new ArrayList<DataFragment>();//工井自带


    private List<DataFragment> mJKXLFragments = new ArrayList<DataFragment>();//架空线路
    private List<DataFragment> mDLXLFragments = new ArrayList<DataFragment>();//电缆线路

    private List<DataFragment> mDYXLFragments = new ArrayList<DataFragment>();//电源线路

    public DataFragment getHWGFragment() {
        String datasetName = "GY_HYGTZXX";
        BusinessFragment fragment = new HWGBasicFragment_();
        String HWG = ElectricApplication_.getInstance().
                getApplicationContext().getResources().getString(R.string.tower_hwg);
        DataFragment dataFragment = new DataFragment(HWG, datasetName, fragment);
        return dataFragment;
    }

    public List<DataFragment> getJKFramentItems() {
        if (null != mJKXLFragments && mJKXLFragments.size() > 0) {
            return mJKXLFragments;
        }
        mJKXLFragments.add(getHWGFragment());
        return mJKXLFragments;
    }

    public List<DataFragment> getDLFramentItems() {
        if (null != mDLXLFragments && mDLXLFragments.size() > 0) {
            return mDLXLFragments;
        }
        return mDLXLFragments;
    }

    public List<DataFragment> getDYFramentItems() {
        if (null != mDYXLFragments && mDYXLFragments.size() > 0) {
            return mDYXLFragments;
        }
        return mDYXLFragments;
    }

    public static class DataFragment {
        String mFramentName;
        String mDatasetName;
        BusinessFragment mFragment;

        public DataFragment(String framentName, String datasetName, BusinessFragment fragment) {
            mFramentName = framentName;
            mDatasetName = datasetName;
            mFragment = fragment;
        }
    }

}
