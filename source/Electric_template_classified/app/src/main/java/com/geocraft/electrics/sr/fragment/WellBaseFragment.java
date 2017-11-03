package com.geocraft.electrics.sr.fragment;

import android.app.AlertDialog;
import android.view.View;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.sr.activity.WellActivity;
import com.geocraft.electrics.sr.controller.SrPhotoManagerController;
import com.geocraft.electrics.ui.controller.PhotoManagerController;
import com.geocraft.electrics.ui.view.DataValidityInfoView;
import com.geocraft.electrics.ui.view.DataValidityInfoView_;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.FileUtils;

/**
 * 井号采集fragment基类
 */
@EFragment(R.layout.fragment_business)
public class WellBaseFragment extends BusinessFragment {
    protected WellActivity mActivity;

    @Override
    protected void init() {
        mActivity = ((WellActivity) this.getActivity());
        mIsNew = mActivity.getController().isCreateRecord();
        mDataSet = mActivity.getController().getCurrentDataSet();
        super.initData(mIsNew, mDataSet);
    }


    public boolean checkDataValidity(List<SrPhotoManagerController.PhotoItemInfo> taskPhotoList) {
        if (null == mLinearLayout) {
            return false;
        }
        List<String> illegalFieldList = new ArrayList<>();
        List<String> illegalPhotoList = new ArrayList<>();
        illegalFieldList.clear();
        illegalPhotoList.clear();
        List<FieldInfo> fieldInfoList = mDataSet.FieldInfos;
        List<View> childViewGroup = new ArrayList<>();
        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            View viewTemp = mLinearLayout.getChildAt(i);
            childViewGroup.add(viewTemp);
        }
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            for (View view : childViewGroup) {
                if (view.getTag() == null) {
                    continue;
                }
                if (view.getTag().toString().equalsIgnoreCase(fieldInfo.Alias)) {
                    if (fieldInfo.IsRequired && fieldInfo.Value.length() <= 0) {
                        illegalFieldList.add(fieldInfo.Alias);
                    }
                    break;
                }
            }
        }
        for (int j = 0; j < taskPhotoList.size(); j++) {
            if (!FileUtils.existFile(taskPhotoList.get(j).photoPath)) {
                illegalPhotoList.add(taskPhotoList.get(j).mPhotoType);
            }
        }
        if (illegalFieldList.size() > 0 || illegalPhotoList.size() > 0) {
            DataValidityInfoView validityInfoView = DataValidityInfoView_.build(mActivity);
            validityInfoView.setFieldErrorInfo(illegalFieldList);
            validityInfoView.setPhotoErrorInfo(illegalPhotoList);
            new AlertDialog.Builder(mActivity)
                    .setIcon(R.mipmap.ic_warning)
                    .setTitle(R.string.dlg_warning)
                    .setView(validityInfoView)
                    .show();
            return false;
        }
        return true;
    }
}

