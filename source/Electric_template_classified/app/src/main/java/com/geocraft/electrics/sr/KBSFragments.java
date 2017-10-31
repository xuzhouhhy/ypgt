package com.geocraft.electrics.sr;

import android.content.res.Resources;

import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 可选项frament管理
 */
@EBean
public class KBSFragments {
    public final static String KEY_GY_KBS_Base = "KEY_GY_KBS_Base";
    //KBS

    private List<FragmentOption> mKFragments = new ArrayList<FragmentOption>();//开闭所
    private Resources mResources = ElectricApplication_.getInstance().
            getApplicationContext().getResources();


    //二级
    public List<FragmentOption> getSecondLevelItems_KBS() {
        List<FragmentOption> fragmentOptions = new ArrayList<FragmentOption>();
        //fragmentOptions.add(getGY_DLXL_HWG());
        int count = fragmentOptions.size();
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                continue;
            }
            fragmentOptions.get(i).setParentNameKey(KEY_GY_KBS_Base);
        }
        return fragmentOptions;
    }

    public List<FragmentOption> getKBSFramentItems() {
        if (null != mKFragments && mKFragments.size() > 0) {
            return mKFragments;
        }
        //mDLXLFragments.add(getGY_DLXL_Base());
        mKFragments.addAll(getSecondLevelItems_KBS());

        for (int i = 0; i < mKFragments.size(); i++) {
            FragmentOption option = mKFragments.get(i);
            option.setDatasetName(Enum.GY_DLXLTZXX);
            if (CommonFragment.isBaseFragment(option.getNameKey())) {
                option.setChecked(true);
            }
        }
        return mKFragments;
    }


    public void initKBSFramentDtatas(DataSet dataset) {
        getKBSFramentItems();
        String checkedFragmentkeyValue = dataset.
                GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_COLLECTOBJECT);
        CommonFragment.initCheckedFragmentkeyValue(checkedFragmentkeyValue, mKFragments);
    }


    // =======================================fragment 生成============================


}
