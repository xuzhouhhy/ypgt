package com.geocraft.electrics.ui.activity.andvance;

import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.advance.HWGBasicFragment;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.advance.HWGBasicFragment_;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 */
@EBean
public class BasicFragmentFactory {
    private List<DataFragment> mTowerFragments = new ArrayList<DataFragment>();//杆号自带
    private List<DataFragment> mWellFragments = new ArrayList<DataFragment>();//工井自带
    private List<DataFragment> mJKXLFragments = new ArrayList<DataFragment>();//架空线路
    private List<DataFragment> mDLXLFragments = new ArrayList<DataFragment>();//电缆线路
    private List<DataFragment> mHwgFragments = new ArrayList<DataFragment>();//环网柜
    private List<DataFragment> mKBSFragments = new ArrayList<DataFragment>();//开闭所
    private List<DataFragment> mFJXFragments = new ArrayList<DataFragment>();//分接箱
    private List<DataFragment> mDLFJXFragments = new ArrayList<DataFragment>();//电缆分接箱
    private List<DataFragment> mXSBDZFragments = new ArrayList<DataFragment>();//箱式变电站
    private List<DataFragment> mZSBYQFragments = new ArrayList<DataFragment>();//柱上变压器

    public List<DataFragment> getTowerFragments(String datasetName) {
        if (null == mTowerFragments || mTowerFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mTowerFragments.add(dataFragment);
        }
        return mTowerFragments;
    }

    public List<DataFragment> getWellFragments(String datasetName) {
        if (null == mWellFragments || mWellFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mWellFragments.add(dataFragment);
        }
        return mWellFragments;
    }

    public List<DataFragment> getJKXLFragments(String datasetName) {
        if (null == mJKXLFragments || mJKXLFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mJKXLFragments.add(dataFragment);
        }
        return mJKXLFragments;
    }

    public List<DataFragment> getHWGFragments(String datasetName) {
        if (null == mHwgFragments || mHwgFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mHwgFragments.add(dataFragment);
        }
        return mHwgFragments;
    }

    public List<DataFragment> getDLXLFragments(String datasetName) {
        if (null == mDLXLFragments || mDLXLFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mDLXLFragments.add(dataFragment);
        }
        return mDLXLFragments;
    }

    public List<DataFragment> getKBSFragments(String datasetName) {
        if (null == mKBSFragments || mKBSFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mKBSFragments.add(dataFragment);
        }
        return mKBSFragments;
    }

    public List<DataFragment> geFJXFragments(String datasetName) {
        if (null == mFJXFragments || mFJXFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mFJXFragments.add(dataFragment);
        }
        return mFJXFragments;
    }

    public List<DataFragment> geDLFJXFragments(String datasetName) {
        if (null == mDLFJXFragments || mDLFJXFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mDLFJXFragments.add(dataFragment);
        }
        return mDLFJXFragments;
    }

    public List<DataFragment> geXSBDZFragments(String datasetName) {
        if (null == mXSBDZFragments || mXSBDZFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mXSBDZFragments.add(dataFragment);
        }
        return mXSBDZFragments;
    }

    public List<DataFragment> geZSBYQFragments(String datasetName) {
        if (null == mZSBYQFragments || mZSBYQFragments.size() == 0) {
            HWGBasicFragment fragment = new HWGBasicFragment_();
            DataFragment dataFragment = new DataFragment(datasetName, fragment, true);
            mZSBYQFragments.add(dataFragment);
        }
        return mZSBYQFragments;
    }

    public static class DataFragment {
        String datasetName;
        BusinessFragment businessFragment;
        boolean isLast;

        public DataFragment(String datasetName, BusinessFragment businessFragment,
                            boolean isLast) {
            this.datasetName = datasetName;
            this.businessFragment = businessFragment;
            this.isLast = isLast;
        }
    }

}
