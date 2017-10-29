package com.geocraft.electrics.sr;

import android.content.res.Resources;

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
 * 可选项frament管理
 */
@EBean
public class BasicFragmentFactory {
    private final static String NAME_KEY_MARK = "&";
    private final String KEY_GY_JKXLTZXX_BASE = "GY_JKXLTZXX_BASE";
    private final String KEY_GY_HYGTZXX_BASE = "GY_HYGTZXX_BASE";
    private List<FragmentOption> mJKXLFragments = new ArrayList<FragmentOption>();//架空线路
    private List<FragmentOption> mDLXLFragments = new ArrayList<FragmentOption>();//电缆线路
    private Resources mResources = ElectricApplication_.getInstance().
            getApplicationContext().getResources();

    public void initFragments(WellType wellType, DataSet dataset) {
        if (wellType == WellType.JK) {
            initJKFramentDtatas(dataset);
        } else if (wellType == WellType.DL) {
            initDLFramentDtatas(dataset);
        }
    }

    public List<FragmentOption> getJKFramentItems() {
        if (null != mJKXLFragments && mJKXLFragments.size() > 0) {
            return mJKXLFragments;
        }
        mJKXLFragments.add(getGY_JTXL_Base());
        mJKXLFragments.add(getHWGFragment());
        // TODO: 2017/10/29 完善

        for (int i = 0; i < mJKXLFragments.size(); i++) {
            FragmentOption option = mJKXLFragments.get(i);
            option.setDatasetName(Enum.GY_JKXLTZXX);
        }
        return mJKXLFragments;
    }

    public List<FragmentOption> getDLFramentItems() {
        if (null != mDLXLFragments && mDLXLFragments.size() > 0) {
            return mDLXLFragments;
        }

        for (int i = 0; i < mDLXLFragments.size(); i++) {
            FragmentOption option = mDLXLFragments.get(i);
            option.setDatasetName(Enum.GY_DLXLTZXX);
        }
        return mDLXLFragments;
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
                                             List<FragmentOption> fragmentOptions) {
        if (null == checkedFragmentkeyValue || checkedFragmentkeyValue.isEmpty()) {
            return;
        }
        String[] keyArry = checkedFragmentkeyValue.split(NAME_KEY_MARK);
        if (null == keyArry) {
            return;
        }
        for (int i = 0; i < fragmentOptions.size(); i++) {
            FragmentOption option = fragmentOptions.get(i);
            String fragmentNameKey = option.getFramentNameKey();
            for (String key : keyArry) {
                if (key.equals(fragmentNameKey)) {
                    option.setChecked(true);
                }
            }
        }
    }

    // =======================================fragment 生成============================

    private FragmentOption getGY_JTXL_Base() {
        GY_JTXL_Base fragment = new GY_JTXL_Base_();
        String fragmentName = mResources.getString(R.string.well_jk_base);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JKXLTZXX_BASE,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getHWGFragment() {
        BusinessFragment fragment = new HWGBasicFragment_();
        String fragmentName = mResources.getString(R.string.tower_hwg);
        FragmentOption fragmentOption = new FragmentOption(
                KEY_GY_HYGTZXX_BASE, fragmentName, "", fragment);
        return fragmentOption;
    }

}
