package com.geocraft.electrics.sr.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.entity.DataSource;
import com.geocraft.electrics.entity.PhotoRules;
import com.geocraft.electrics.factory.DeleteDataSetFactory;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.sr.LineFactory;
import com.geocraft.electrics.sr.activity.AddChildLineActivity;
import com.geocraft.electrics.sr.activity.AddChildLineActivity_;
import com.geocraft.electrics.sr.activity.TowerShowListActivity;
import com.geocraft.electrics.sr.activity.WellActivity_;
import com.geocraft.electrics.sr.event.LineElementRefreshEvent;
import com.geocraft.electrics.ui.activity.RecordActivity_;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.FileUtils;
import common.geocraft.untiltools.T;

/**
 * 杆塔列表界面
 */
@EBean
public class TowerShowListController extends BaseController {

    private static String TAG = TowerShowListController.class.getSimpleName();

    @Bean
    TaskManager mTaskManager;
    @Bean
    DbManager mDbManager;
    DataSetGroup dataSetGroup;
    private List<DataSet> mDataSets = new ArrayList<>();
    /**
     * 线路ID
     */
    private String mLineId;

    public List<DataSet> getItems() {
        return mDataSets;
    }

    public void initCollectTypeList() {
        DataSource dataSource = mTaskManager.getDataSource();
        if (null == dataSource) {
            return;
        }
        List<DataSetGroup> dataSetGroupList = dataSource.DataSourceGroups;
        for (int i = 0; i < dataSetGroupList.size(); i++) {
            DataSetGroup dataSetGroupTemp = dataSetGroupList.get(i);
            if (dataSetGroupTemp == null) {
                continue;
            }
            if (!dataSetGroupList.get(i).IsVisible) {
                continue;
            }
            if (dataSetGroupTemp.Name.equals(Enum.GYCJ)) {
                dataSetGroup = dataSetGroupTemp;
                break;
            }
        }
    }

    public void initDataFromDB() {
        if (null == dataSetGroup) {
            return;
        }
        mDataSets.clear();
        for (DataSet dataset : dataSetGroup.DataSets) {
            if (!LineFactory.oneOfLineDataset(dataset)) {
                continue;
            }
            List<DataSet> dataSets = mDbManager.queryByCondition(dataset,
                    Enum.GY_JKXLTZXX_FIELD_LINEID, mLineId, true);
            mDataSets.addAll(dataSets);
        }
    }

    /**
     * 获取上级activity传递来的intent值
     */
    public void initIntentData(Context context) {
        String lineId = ((Activity) context).getIntent().getStringExtra(
                Constants.INTENT_DATA_LINE_NAMES);
        if (null == lineId || lineId.isEmpty()) {
            Log.e(TAG, "line id null or empty");
            return;
        }
        mLineId = lineId.trim();
    }

    public boolean hasChildDataSets() {
        return mDataSets.get(0).DataSets.size() == 0 ? false : true;
    }

    public List<DataSet> searchByBarCode(String barCode, String CompareFieldName) {
        List<DataSet> search = new ArrayList<>();
        for (int i = 0; i < mDataSets.size(); i++) {
            DataSet temp = mDataSets.get(i);
            if (barCode.trim().equals(temp.GetFieldValueByName(CompareFieldName))) {
                search.add(temp);
            }
        }
        return search;
    }

    public void doSomethingInThread(final String keyWord) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mDataSets != null) {
                    mDataSets.clear();
                }
                for (DataSet dataset : dataSetGroup.DataSets) {
                    if (!LineFactory.oneOfLineDataset(dataset)) {
                        continue;
                    }
                    List<DataSet> dataSets = mDbManager.queryByKeywordAndPrimaryKey(dataset,
                            Enum.GY_JKXLTZXX_FIELD_LINEID, mLineId, dataset.First, keyWord, true);
                    mDataSets.addAll(dataSets);
                }
                ElectricApplication.BUS.post(new LineElementRefreshEvent());
            }
        }).run();
    }

    /**
     * 打开新建杆塔、地井、电源点界面
     */
    public void openRecordActivityToAdd(Context context) {
        String lineId = ((Activity) context).getIntent().getStringExtra(
                Constants.INTENT_DATA_LINE_NAMES);
        if (null == lineId || lineId.isEmpty()) {
            return;
        }
        Intent intent = new Intent(context, WellActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_LINE_ID, lineId);
        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, Enum.GYCJ);
        ((TowerShowListActivity) context).startActivityForResult(
                intent, ConstRequestCode.REQUEST_CODE_OPEN_RECORDACTIVITY);
    }

    /**
     * 打开编辑线路子元素界面
     *
     * @param position 子元素位置
     */
    public void openRecordActivityToChange(Context context, int position) {
        DataSet dataSet = mDataSets.get(position);
        Intent intent = new Intent(context, WellActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_WELL_TYPE, dataSet.
                GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_GZlX));
        intent.putExtra(Constants.INTENT_DATA_LINE_ID, mLineId);
        intent.putExtra(Constants.INTENT_DATA_WELL_ID, String.valueOf(dataSet.PrimaryKey));
        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, Enum.GYCJ);
        ((TowerShowListActivity) context).startActivityForResult(
                intent, ConstRequestCode.REQUEST_CODE_OPEN_RECORDACTIVITY);
    }

    public String getDataSetAlias() {
        try {
            return "";
        } catch (Exception e) {
            L.printException(e);
            return "";
        }
    }


    public void showRemoveTargetRecordDialog(final Context context, final int position) {
        new AlertDialog.Builder(context)
                .setIcon(R.mipmap.ic_tip)
                .setTitle(context.getString(R.string.remove_record))
                .setMessage(context.getString(R.string.confirm_delete_record))
                .setNegativeButton(R.string.dlg_no, null)
                .setPositiveButton(R.string.dlg_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            DataSet dataSet = mDataSets.get(position);
                            DeleteDataSetFactory deleteDataSetFactory = new DeleteDataSetFactory();
                            //先删除照片
                            //deleteAllPhotoFiles(dataSet);
                            if (deleteDataSetFactory.deleteAllDataSet(dataSet)) {
                                mDataSets.remove(position);
                                ((TowerShowListActivity) context).refreshListView(position);
                                T.showShort(context, R.string.remove_record_succeed);
                            } else {
                                T.showShort(context, R.string.remove_record_failure);
                            }
                        } catch (Exception e) {
                            L.printException(e);
                            T.showLong(context, R.string.remove_failed);
                        }
                    }
                }).show();
    }

    public void clearDataSetList() {
        mDataSets.clear();
    }

    private void deletePhotoFile(DataSet dataSet) {
        if (dataSet != null) {
            List<PhotoRules> photoRulesList = dataSet.PhotoRules;
            for (int i = 0; i < photoRulesList.size(); i++) {
                if (photoRulesList.get(i) == null) {
                    continue;
                }
                String photoPath = getPhotoPath(photoRulesList.get(i), dataSet);
                if (FileUtils.existFile(photoPath)) {
                    FileUtils.deleteFile(photoPath);
                }
            }
        }
    }

    private void deleteAllPhotoFiles(DataSet dataSet) {
        if (dataSet != null) {
            List<PhotoRules> photoRulesList = dataSet.PhotoRules;
            for (int i = 0; i < photoRulesList.size(); i++) {
                if (photoRulesList.get(i) == null) {
                    continue;
                }
                String photoPath = getPhotoPath(photoRulesList.get(i), dataSet);
                if (FileUtils.existFile(photoPath)) {
                    FileUtils.deleteFile(photoPath);
                }
            }
            if (dataSet.DataSets.size() > 0) {
                for (int i = 0; i < dataSet.DataSets.size(); i++) {
                    //搜索相关子数据集
                    DataSet dataSetTmp = dataSet.DataSets.get(i);
                    List<DataSet> listTmp = mDbManager.queryByCondition(dataSetTmp,
                            dataSetTmp.SearchField, dataSet.GetFieldValueByName(
                                    dataSetTmp.ValueField), true);
                    for (DataSet data : listTmp) {
                        deleteAllPhotoFiles(data);
                    }
                }
            }
        }
    }

    private String getPhotoPath(PhotoRules photoRules, DataSet dataSet) {
        if (photoRules == null) {
            return "";
        }
        String[] photoRuleArray = photoRules.Rules.split(",");
        String photoPrefix = "";
        for (int i = 0; i < photoRuleArray.length; i++) {
            photoPrefix += dataSet.GetFieldValueByName(photoRuleArray[i]) + "_";
        }
        String photoName = "";
        if (photoRules.Type.equals(Constants.TEXT_PHOTO_SUFFIX)) {
            if (photoPrefix.contains("_")) {
                photoPrefix = photoPrefix.substring(0, photoPrefix.lastIndexOf('_'));
            }
            photoName = photoPrefix + Constants.PHOTO_SUFFIX;
        } else {
            photoName = photoPrefix + photoRules.Type + Constants.PHOTO_SUFFIX;
        }
        String taskPath = ConstPath.getTaskRootFolder() + mTaskManager.getTaskInfo().getTaskName();
        String photoPath = taskPath + File.separator + Constants.TASK_PHOTO_FOLDER;
        photoPath = Utils.getPhotoDir(photoPath, photoRules, dataSet);
        return photoPath + photoName;
    }


    public void refreshList(List<DataSet> search) {
        clearDataSetList();
        mDataSets.addAll(search);
    }

    /**
     * 根据基桩新建子线路
     */
    public void onAddLineByWell(Context context, int position) {
        DataSet dataSet = mDataSets.get(position);
        int wellId = dataSet.PrimaryKey;
        String type = dataSet.GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_GZlX);
        Intent intent = new Intent(context, AddChildLineActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_LINE_ID, mLineId);
        intent.putExtra(Constants.INTENT_DATA_WELL_ID, wellId);
        intent.putExtra(Constants.INTENT_DATA_WELL_TYPE, type);
        ((TowerShowListActivity) context).startActivityForResult(
                intent, ConstRequestCode.REQUEST_CODE_OPEN_ADD_CHILD_LINE);
    }

    public static class CollectType {
        public String mItemAlias;
        public String mItemName;
        public boolean mIsSystemItem;
        public boolean mIsGoToDeatail;
    }

}
