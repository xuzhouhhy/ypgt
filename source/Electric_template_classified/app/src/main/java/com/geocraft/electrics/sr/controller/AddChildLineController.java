package com.geocraft.electrics.sr.controller;

import android.app.AlertDialog;
import android.content.Context;

import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.ui.view.DataValidityInfoView;
import com.geocraft.electrics.ui.view.DataValidityInfoView_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.FileUtils;

/**
 * Created by zhongshibu02 on 2017/11/1.
 */
@EBean
public class AddChildLineController {

    @Bean
    TaskManager mTaskManager;
    @Bean
    DbManager mDbManager;
    private DataSet mCurrentDataSet;

    public void initData() {
        mCurrentDataSet = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
                Enum.DATA_SET_NAME_LINE);
    }

    public DataSet getDataSet() {
        return mCurrentDataSet;
    }

    public boolean checkDataValidity(Context context) {
        List<String> illegalFieldList = new ArrayList<>();
        List<String> illegalPhotoList = new ArrayList<>();
        illegalFieldList.clear();
        illegalPhotoList.clear();
        List<FieldInfo> fieldInfoList = mCurrentDataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfoTemp = fieldInfoList.get(i);
            if (fieldInfoTemp == null) {
                continue;
            }
            if (fieldInfoTemp.IsRequired && fieldInfoTemp.Value.length() <= 0) {
                illegalFieldList.add(fieldInfoTemp.Alias);
            }
        }
        if (illegalFieldList.size() > 0 || illegalPhotoList.size() > 0) {
            DataValidityInfoView validityInfoView = DataValidityInfoView_.build(context);
            validityInfoView.setFieldErrorInfo(illegalFieldList);
            validityInfoView.setPhotoErrorInfo(illegalPhotoList);
            new AlertDialog.Builder(context)
                    .setIcon(R.mipmap.ic_warning)
                    .setTitle(R.string.dlg_warning)
                    .setView(validityInfoView)
                    .show();
            return false;
        }
        return true;
    }
}
