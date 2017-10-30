package com.geocraft.electrics.sr.fragment;


import android.view.View;

import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 杆桩基础信息
 */
@EFragment(R.layout.fragment_business)
public class WellBaseInfoFragment extends WellBaseFragment {

    @Override
    public boolean logicCheck() {
        return isDataValid(mDataSet);
    }

    public boolean isDataValid(DataSet dataSet) {
        if (null == mLinearLayout) {
            return false;
        }
        List<View> childViewGroup = new ArrayList<>();
        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            View viewTemp = mLinearLayout.getChildAt(i);
            childViewGroup.add(viewTemp);
        }
        List<FieldInfo> fieldInfoList = dataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            for (View view : childViewGroup) {
                if (view.getTag() == null) {
                    continue;
                }
                if (!isDataValid(view, fieldInfo)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDataValid(View view, FieldInfo fieldInfo) {
        if (view.getTag().toString().equalsIgnoreCase(fieldInfo.Alias)) {
            switch (((DataInterActionInterface) view).getControlerType()) {
                case PropertyDictionay.OperateCode.Type_BaseText: {
                    if (fieldInfo.Name.equals(Enum.GYCJ_LINE_F_GH)) {
                        String wellName = ((BusinessEditText) view).getControlValue();
                        if (null == wellName || wellName.isEmpty()) {
                            T.showShort(mActivity,
                                    mActivity.getString(R.string.well_name_empty));
                            return false;
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
        return true;
    }
}
