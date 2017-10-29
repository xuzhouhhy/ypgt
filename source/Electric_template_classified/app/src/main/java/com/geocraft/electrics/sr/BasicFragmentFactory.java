package com.geocraft.electrics.sr;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
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
    private final static String NAME_KEY_MARK = "&";
    private final String KEY_GY_JKXLTZXX_BASE = "GY_JKXLTZXX_BASE";
    private final String KEY_GY_HYGTZXX_BASE = "GY_HYGTZXX_BASE";
    private List<FragmentDatasetOption> mJKXLFragments = new ArrayList<FragmentDatasetOption>();//架空线路
    private List<FragmentDatasetOption> mDLXLFragments = new ArrayList<FragmentDatasetOption>();//电缆线路

    public void initFragments(WellType wellType, DataSet dataset) {
        if (wellType == WellType.JK) {
            initJKFramentDtatas(dataset);
        } else if (wellType == WellType.DL) {
            initDLFramentDtatas(dataset);
        }
    }

    private void initJKFramentDtatas(DataSet dataset) {
        getJKFramentItems();
        String checkedFragmentkeyValue = dataset.
                GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_COLLECTOBJECT);
        initCheckedFragmentkeyValue(checkedFragmentkeyValue, mJKXLFragments);
    }

    private void initDLFramentDtatas(DataSet dataset) {
        getDLFramentItems();
        String checkedFragmentkeyValue = dataset.
                GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_COLLECTOBJECT);
        initCheckedFragmentkeyValue(checkedFragmentkeyValue, mDLXLFragments);
    }

    private void initCheckedFragmentkeyValue(String checkedFragmentkeyValue,
                                             List<FragmentDatasetOption> fragmentDatasetOptions) {
        if (null == checkedFragmentkeyValue || checkedFragmentkeyValue.isEmpty()) {
            return;
        }
        String[] keyArry = checkedFragmentkeyValue.split(NAME_KEY_MARK);
        if (null == keyArry) {
            return;
        }
        for (int i = 0; i < fragmentDatasetOptions.size(); i++) {
            FragmentDatasetOption option = fragmentDatasetOptions.get(i);
            String fragmentNameKey = option.getFramentNameKey();
            for (String key : keyArry) {
                if (key.equals(fragmentNameKey)) {
                    option.setChecked(true);
                }
            }
        }
    }

    public FragmentDatasetOption getHWGFragment() {
        BusinessFragment fragment = new HWGBasicFragment_();
        String HWG = ElectricApplication_.getInstance().
                getApplicationContext().getResources().getString(R.string.tower_hwg);
        FragmentDatasetOption fragmentDatasetOption =
                new FragmentDatasetOption(KEY_GY_HYGTZXX_BASE, HWG,
                        "", fragment);
        return fragmentDatasetOption;
    }

    public FragmentDatasetOption getGY_JTXL_Base() {
        GY_JTXL_Base fragment = new GY_JTXL_Base_();
        String fragmentName = ElectricApplication_.getInstance().
                getApplicationContext().getResources().getString(R.string.well_jk_base);
        FragmentDatasetOption fragmentDatasetOption =
                new FragmentDatasetOption(KEY_GY_JKXLTZXX_BASE,
                        fragmentName, "", fragment);
        return fragmentDatasetOption;
    }

    public List<FragmentDatasetOption> getJKFramentItems() {
        if (null != mJKXLFragments && mJKXLFragments.size() > 0) {
            return mJKXLFragments;
        }
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getHWGFragment());
        // TODO: 2017/10/29 完善

        for (int i = 0; i < mJKXLFragments.size(); i++) {
            FragmentDatasetOption option = mJKXLFragments.get(i);
            option.setDatasetName(Enum.GY_JKXLTZXX);
        }
        return mJKXLFragments;
    }

    public List<FragmentDatasetOption> getDLFramentItems() {
        if (null != mDLXLFragments && mDLXLFragments.size() > 0) {
            return mDLXLFragments;
        }

        for (int i = 0; i < mDLXLFragments.size(); i++) {
            FragmentDatasetOption option = mDLXLFragments.get(i);
            option.setDatasetName(Enum.GY_DLFJXTZXX);
        }
        return mDLXLFragments;
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
        private boolean mIsChecked;

        public FragmentDatasetOption(String framentNameKey, String framentName,
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
    }

}
