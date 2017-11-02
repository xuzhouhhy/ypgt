package com.geocraft.electrics.sr.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.ui.view.DataValidityInfoView;
import com.geocraft.electrics.ui.view.DataValidityInfoView_;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessConcatSpinner;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制器
 * Created by zhongshibu02 on 2017/11/1.
 */
@EBean
public class AddChildLineController {

    @Bean
    TaskManager mTaskManager;
    @Bean
    DbManager mDbManager;
    private DataSet mCurrentDataSet;

    private List<Pair<String, Integer>> mIntervalName;
    /**
     * 父线路id
     */
    private int mLineId;

    /**
     * 基桩id
     */
    private int mWellId;

    /**
     * 基桩type
     */
    private String mWellType;

    /**
     * 选择的间隔id
     */
    private int mIntervalId;

    public void initData(Intent intent) {
        mCurrentDataSet = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
                Enum.DATA_SET_NAME_LINE);
        mLineId = intent.getIntExtra(Constants.INTENT_DATA_LINE_ID, -1);
        mWellId = intent.getIntExtra(Constants.INTENT_DATA_WELL_ID, -1);
        mWellType = intent.getStringExtra(Constants.INTENT_DATA_WELL_TYPE);
        mIntervalName = queryIntervalNameList();
    }

    private List<Pair<String, Integer>> queryIntervalNameList() {
        List<Pair<String, Integer>> nameId = new ArrayList<>();
        if (mLineId < 0 || mWellId < 0 || null == mWellType || mWellType.isEmpty()) {
            return nameId;
        }
        //只有电缆线路型基桩才可以查询间隔
        if (!mWellType.equals("2")) {
            return nameId;
        }
        //电缆表
        DataSet dlDs = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
                Enum.GY_DLXLTZXX);
        dlDs.PrimaryKey = mWellId;
        //查询对应基桩id的电缆
        DataSet dataSet = mDbManager.queryByPrimaryKey(dlDs, true);
        //获取查询到基桩的间隔点
        String intervalId = dataSet.GetFieldValueByName(Enum.DLJ_JGD);
        String[] ids = intervalId.split("&");
        if (0 == ids.length) {
            return nameId;
        }
        //间隔表
        DataSet jgDs = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
                Enum.spacer);
        for (String id : ids) {
            try {
                jgDs.PrimaryKey = Integer.valueOf(id);
            } catch (NumberFormatException e) {
                L.e(e, "F_SpacerIds field of GY_DLXLTZXX table has wrong format");
                continue;
            }
            DataSet intervalDs = mDbManager.queryByPrimaryKey(jgDs, true);
            if (null != intervalDs) {
                String name = intervalDs.GetFieldValueByName(Enum.JGNB_JGM);
                Pair<String, Integer> pair = new Pair<>(name, intervalDs.PrimaryKey);
                nameId.add(pair);
            }
        }
        return nameId;
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

    /**
     * 记录线路
     *
     * @return 是否记录成功
     */
    public Boolean saveRecord() {
        if (mCurrentDataSet == null) {
            return false;
        }
        mCurrentDataSet.SetFiledValueByName(Enum.LINE_PARENT_ID, String.valueOf(mLineId));
        mCurrentDataSet.SetFiledValueByName(Enum.LINE_WELL_ID, String.valueOf(mWellId));
        mCurrentDataSet.SetFiledValueByName(Enum.LINE_SPACER_ID, String.valueOf(mIntervalId));
        mCurrentDataSet.SetFiledValueByName(Enum.LINE_JZTYPE, String.valueOf(mWellType));
        int key = mDbManager.insert(mCurrentDataSet);
        if (key >= 0) {
            mCurrentDataSet.PrimaryKey = key;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 给spinner初始化数据
     *
     * @param f_linezcsx spinner控件
     */
    public void initView(BusinessConcatSpinner f_linezcsx, LinearLayout intervalLl) {
        intervalLl.setVisibility(mWellType.equals(Enum.DLJ_TYPE) ? View.VISIBLE : View.GONE);
        List<FieldInfo> fieldInfoList = mCurrentDataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            String tag = f_linezcsx.getTag().toString();
            if (null == tag || tag.isEmpty()) {
                return;
            }
            if (tag.equalsIgnoreCase(fieldInfo.Alias)) {
                f_linezcsx.setControlValue(fieldInfo, fieldInfo.Default);
                f_linezcsx.setData(mCurrentDataSet, fieldInfo);
            }
        }
    }

    public List<Pair<String, Integer>> getIntervalName() {
        return mIntervalName;
    }

    public void setIntervalId(int mIntervalId) {
        this.mIntervalId = mIntervalId;
    }

}
