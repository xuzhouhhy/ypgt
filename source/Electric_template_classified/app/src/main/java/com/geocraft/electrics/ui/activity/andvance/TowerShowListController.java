package com.geocraft.electrics.ui.activity.andvance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.entity.DataSource;
import com.geocraft.electrics.entity.PhotoRules;
import com.geocraft.electrics.factory.DeleteDataSetFactory;
import com.geocraft.electrics.manager.TaskManager;
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
 */
@EBean
public class TowerShowListController extends BaseController {

    @Bean
    TaskManager mTaskManager;
    @Bean
    DbManager mDbManager;
    DataSetGroup dataSetGroup;
    private List<DataSet> mDataSets = new ArrayList<>();
    private List<CollectTypeItem> mCollectTypeList = new ArrayList<>();
    private String mQueryValue;
    private String mFirstType;
    private String mSecondType;

    public List<DataSet> getItems() {
        return mDataSets;
    }

    public void initCollectTypeList() {
        mCollectTypeList.clear();
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
        for (DataSet dataset : dataSetGroup.DataSets) {
            if (dataset.isShowInDeviceList()) {
                continue;
            }
            DataSet dataSet = mTaskManager.getDataSource().getDataSetByName(mFirstType, mSecondType);
            dataSet.SearchField = Enum.GYCJ_LINE_F_GH;
            if (mQueryValue.isEmpty()) {
                mDataSets = mDbManager.queryAll(dataSet, true);
            } else {
                mDataSets = mDbManager.queryByCondition(dataSet, dataSet.SearchField, mQueryValue, true);
            }
        }
    }

    public void initIntentData(Context context) {
        mQueryValue =  ((TowerShowListActivity) context).getIntent().getStringExtra(Constants.INTENT_DATA_SET_QUERY_VALUE);

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
            }
        }).run();

    }

    public void openChildDataSetActivity(Context context, int position) {

    }

    public void setQueryValue(String queryValue) {
        mQueryValue = queryValue;
    }

    public void openRecordActivityToChange(Context context, int position) {

    }

    public void openRecordActivityToAdd(Context context) {
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
                    List<DataSet> listTmp = mDbManager.queryByCondition(dataSetTmp, dataSetTmp.SearchField, dataSet.GetFieldValueByName(dataSetTmp.ValueField), true);
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

    public CollectTypeItem getCollectType() {
        if (null == mCollectTypeList || mCollectTypeList.size() == 0) {
            return null;
        }
        return mCollectTypeList.get(0);//todo 默认返回第一个
    }

    public static class CollectTypeItem {
        public String mItemAlias;
        public String mItemName;
        public boolean mIsSystemItem;
        public boolean mIsGoToDeatail;
    }

}
