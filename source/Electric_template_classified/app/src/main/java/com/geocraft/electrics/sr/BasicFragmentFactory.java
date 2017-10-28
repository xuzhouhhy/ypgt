package com.geocraft.electrics.sr;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.sr.fragment.HWGBasicFragment_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_Base;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_Base_;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 */
@EBean
public class BasicFragmentFactory {
    //    private List<FragmentDatasetOption> mHwgFragments = new ArrayList<FragmentDatasetOption>();//环网柜
//    private List<FragmentDatasetOption> mKBSFragments = new ArrayList<FragmentDatasetOption>();//开闭所
//    private List<FragmentDatasetOption> mFJXFragments = new ArrayList<FragmentDatasetOption>();//分接箱
//    private List<FragmentDatasetOption> mDLFJXFragments = new ArrayList<FragmentDatasetOption>();//电缆分接箱
//    private List<FragmentDatasetOption> mXSBDZFragments = new ArrayList<FragmentDatasetOption>();//箱式变电站
//    private List<FragmentDatasetOption> mZSBYQFragments = new ArrayList<FragmentDatasetOption>();//柱上变压器
    //    private List<FragmentDatasetOption> mTowerFragments = new ArrayList<FragmentDatasetOption>();//杆号自带
//    private List<FragmentDatasetOption> mWellFragments = new ArrayList<FragmentDatasetOption>();//工井自带

    private final String KEY_GY_JKXLTZXX_BASE = "GY_JKXLTZXX_BASE";
    private final String KEY_GY_HYGTZXX_BASE = "GY_HYGTZXX_BASE";


    private List<FragmentDatasetOption> mJKXLFragments = new ArrayList<FragmentDatasetOption>();//架空线路
    private List<FragmentDatasetOption> mDLXLFragments = new ArrayList<FragmentDatasetOption>();//电缆线路

    private List<FragmentDatasetOption> mDYXLFragments = new ArrayList<FragmentDatasetOption>();//电源线路

    public FragmentDatasetOption getHWGFragment() {
        String datasetName = Enum.GY_HYGTZXX;
        BusinessFragment fragment = new HWGBasicFragment_();
        String HWG = ElectricApplication_.getInstance().
                getApplicationContext().getResources().getString(R.string.tower_hwg);
        FragmentDatasetOption fragmentDatasetOption = new FragmentDatasetOption(KEY_GY_HYGTZXX_BASE, HWG,
                datasetName, fragment);
        return fragmentDatasetOption;
    }

    public FragmentDatasetOption getGY_JTXL_Base() {
        String datasetName = Enum.GY_JKXLTZXX;
        GY_JTXL_Base fragment = new GY_JTXL_Base_();
        String fragmentName = ElectricApplication_.getInstance().
                getApplicationContext().getResources().getString(R.string.well_jk_base);
        FragmentDatasetOption fragmentDatasetOption = new FragmentDatasetOption(KEY_GY_JKXLTZXX_BASE,
                fragmentName, datasetName, fragment);
        return fragmentDatasetOption;
    }

    public List<FragmentDatasetOption> getJKFramentItems() {
        if (null != mJKXLFragments && mJKXLFragments.size() > 0) {
            return mJKXLFragments;
        }
        mJKXLFragments.add(getHWGFragment());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getHWGFragment());
        return mJKXLFragments;
    }

    public List<FragmentDatasetOption> getDLFramentItems() {
        if (null != mDLXLFragments && mDLXLFragments.size() > 0) {
            return mDLXLFragments;
        }
        return mDLXLFragments;
    }

    public List<FragmentDatasetOption> getDYFramentItems() {
        if (null != mDYXLFragments && mDYXLFragments.size() > 0) {
            return mDYXLFragments;
        }
        return mDYXLFragments;
    }

    /**
     * @param fragmentNameKey
     * @return
     */
    public boolean isFragmentChecked(String fragmentNameKey) {
        if (null == mJKXLFragments && mJKXLFragments.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 可选项fragment与对应的dataset配置关系
     */
    public static class FragmentDatasetOption {
        private BusinessFragment mFragment;
        private String mFramentName;
        private String mDatasetName;
        private String mFramentNameKey;

        public FragmentDatasetOption(String mFramentNameKey, String framentName, String datasetName,
                                     BusinessFragment fragment) {
            mFramentNameKey = mFramentNameKey;
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
    }

}
