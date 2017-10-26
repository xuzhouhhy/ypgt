package com.geocraft.electrics.ui.controller;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
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
import com.geocraft.electrics.ui.activity.CommonListActivity_;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity;
import com.geocraft.electrics.ui.activity.RecordActivity_;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;
import com.scaninput.ScanInputService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.FileUtils;
import common.geocraft.untiltools.T;

/**
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EBean
public class DeviceShowListController extends BaseController {

    @Bean
    TaskManager mTaskManager;
    @Bean
    DbManager mDbManager;
    LT40ScanReceiver mReceiver = new LT40ScanReceiver();
    //小类,同时也是当前Activity的标题
    private String mSecondType;
    //大类
    private String mFirstType;
    private String mQueryValue;
    private int mParentKey = -1;
    private List<DataSet> mDataSets = new ArrayList<>();
    private List<CollectTypeItem> mCollectTypeList = new ArrayList<>();

    public String getSecondType() {
        return mSecondType;
    }

    public void setSecondType(String secondType) {
        mSecondType = secondType;
    }

    public String getFirstType() {
        return mFirstType;
    }

    public void setFirstType(String firstType) {
        mFirstType = firstType;
    }

    public List<DataSet> getItems() {
        return mDataSets;
    }

    public void initIntentData(Context context) {

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

    public void initScanFilter(Context context) {
        IntentFilter scanIntentFilter = new IntentFilter();
        scanIntentFilter.addAction(ScanInputService.HARDWARE_SCAN);
        context.registerReceiver(mReceiver, scanIntentFilter);
    }

    public void unregisterScanReceiver(Context context) {
        context.unregisterReceiver(mReceiver);
    }

    public void doSomethingInThread(final String keyWord) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (mDataSets != null) {
                    mDataSets.clear();
                }
                DataSet dataSet = mTaskManager.getDataSource().getDataSetByName(mFirstType, mSecondType);
                mDataSets = mDbManager.queryByKeyword(dataSet, dataSet.First, keyWord, true);
            }
        }).run();

    }

    public void openChildDataSetActivity(Context context, int position) {

        Intent intent = new Intent(context, CommonListActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, mFirstType);
        intent.putExtra(Constants.INTENT_DATA_SET_NAME, mSecondType);
        intent.putExtra(Constants.INTENT_DATA_SET_KEY, mDataSets.get(position).PrimaryKey);
        ((DeviceShowListActivity) context).startActivityForResult(intent, ConstRequestCode
                .REQUEST_CODE_EDIT_PARENT_DATA_SET);
    }

    public void setQueryValue(String queryValue) {
        mQueryValue = queryValue;
    }

    public void openRecordActivityToChange(Context context, int position) {

        // 从已有的项中加载Record,传递为false，不是创建一条记录
        Intent intent = new Intent(context, RecordActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, mFirstType);
        intent.putExtra(Constants.INTENT_DATA_SET_NAME, mSecondType);
        intent.putExtra(Constants.INTENT_DATA_IS_CREATE_RECORD, false);
        intent.putExtra(Constants.INTENT_DATA_SET_KEY, mDataSets.get(position).PrimaryKey);

        ((DeviceShowListActivity) context).startActivityForResult(
                intent, ConstRequestCode.REQUEST_CODE_OPEN_RECORDACTIVITY);
    }

    public void openRecordActivityToAdd(Context context) {
        Intent intent = new Intent(context, RecordActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, mFirstType);
        intent.putExtra(Constants.INTENT_DATA_SET_NAME, mSecondType);
        intent.putExtra(Constants.INTENT_DATA_IS_CREATE_RECORD, true);
        intent.putExtra(Constants.INTENT_DATA_SEARCH_FIELD_VALUE, mQueryValue);
        intent.putExtra(Constants.INTENT_DATA_SET_PARENT_KEY, mParentKey);
        ((DeviceShowListActivity) context).startActivityForResult(
                intent, ConstRequestCode.REQUEST_CODE_OPEN_RECORDACTIVITY);
    }

    public boolean isPassedSecondType(Context context) {
        return ((DeviceShowListActivity) context).getIntent().hasExtra(
                Constants.INTENT_DATA_SET_NAME);
    }

    public String getTitleFromIntent(Context context) {
        return ((DeviceShowListActivity) context).getIntent().getStringExtra(
                Constants.INTENT_DATA_SET_NAME);
    }

    public boolean isPassedFirstType(Context context) {
        return ((DeviceShowListActivity) context).getIntent().hasExtra(
                Constants.INTENT_DATA_SET_GROUP_NAME);
    }

    public String getFirstTypeFromIntent(Context context) {
        return ((DeviceShowListActivity) context).getIntent().getStringExtra(
                Constants.INTENT_DATA_SET_GROUP_NAME);
    }

    public String getDataSetAlias() {
        try {
            DataSet dataSet = mTaskManager.getDataSource().getDataSetByName(mFirstType, mSecondType);
            return dataSet != null ? dataSet.Alias : "";
        } catch (Exception e) {
            L.printException(e);
            return "";
        }
    }

    public void initDataFromDB() {
        DataSet dataSet = mTaskManager.getDataSource().getDataSetByName(mFirstType, mSecondType);
        if (mQueryValue.isEmpty()) {
            L.i("queryAll");
            mDataSets = mDbManager.queryAll(dataSet, true);
        } else {
            L.i("queryByCondition");
            L.i("queryByCondition" + dataSet.SearchField + mQueryValue);
            mDataSets = mDbManager.queryByCondition(dataSet, dataSet.SearchField, mQueryValue, true);
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
                                ((DeviceShowListActivity) context).refreshListView(position);
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

    public String getQueryValueFromIntent(Context context) {
        return ((DeviceShowListActivity) context).getIntent().getStringExtra(Constants.INTENT_DATA_SET_QUERY_VALUE);
    }

    public boolean isPassedQueryValue(Context context) {
        return ((DeviceShowListActivity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_QUERY_VALUE);
    }

    public boolean isPassedParentKey(Context context) {
        return ((DeviceShowListActivity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_PARENT_KEY);
    }

    public int getParentKeyFromIntent(Context context) {
        return ((DeviceShowListActivity) context).getIntent().getIntExtra(Constants.INTENT_DATA_SET_PARENT_KEY, -1);
    }

    public void setParentKey(int parentKey) {
        mParentKey = parentKey;
    }

    public boolean deleteTargetRecord(DataSet dataSet) {
        //if()
        return false;
    }

    public void refreshList(List<DataSet> search) {
        clearDataSetList();

        mDataSets.addAll(search);
    }

    public void scanSelect(String barCode, Context context) {
        List<DataSet> search;
        if (ElectricApplication_.getInstance().getList().get(ElectricApplication_.getInstance().getList().size() - 1) == context) {
            if (Enum.DATA_SET_NAME_JLX_J.equals(getSecondType())) {

                search = searchByBarCode(barCode, Enum.CALCULATEBOX_FIELD_JLXTMBH);
                if (search.size() == 0) {
                    T.showShort(context, context.getString(R.string.jlx_not_found));
                } else {
                    refreshList(search);
                    ((DeviceShowListActivity) context).getAdapter().notifyDataSetChanged();
                }
            } else if (Enum.DATA_SET_NAME_ZDSB.equals(getSecondType())) {
                search = searchByBarCode(barCode, Enum.TERMINAL_FIELD_ZCBH);
                if (search.size() == 0) {
                    T.showShort(context, context.getString(R.string.zdsb_not_found));
                } else {
                    refreshList(search);
                    ((DeviceShowListActivity) context).getAdapter().notifyDataSetChanged();
                }
            } else if (Enum.DATA_SET_NAME_JLXYDNBDGX.equals(getSecondType())) {
                search = searchByBarCode(barCode, Enum.METER_FIELD__DBTMBH);
                if (search.size() == 0) {
                    T.showShort(context, context.getString(R.string.dnb_not_found));
                } else {
                    refreshList(search);
                    ((DeviceShowListActivity) context).getAdapter().notifyDataSetChanged();
                }

            } else if (Enum.DATA_SET_NAME_DYKHDA.equals(getSecondType())) {
                search = searchByBarCode(barCode, Enum.LOWVOLTAGEUSER_FIELD_JLXTXM);
                L.i(search.size() + "");
                List<DataSet> search2 = new ArrayList<>();
                search2 = searchByBarCode(barCode, Enum.LOWVOLTAGEUSER_FIELD_DNBTXM);
                if (search.size() == 0) {
                    if (search2.size() == 0) {
                        T.showShort(context, context.getString(R.string.dykhda_not_found));
                    } else {
                        refreshList(search2);
                        ((DeviceShowListActivity) context).getAdapter().notifyDataSetChanged();
                    }
                } else {
                    refreshList(search);
                    ((DeviceShowListActivity) context).getAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    public void initCollectTypeList() {
        mCollectTypeList.clear();
        DataSource dataSource = mTaskManager.getDataSource();
        if (dataSource != null) {
            List<DataSetGroup> dataSetGroupList = dataSource.DataSourceGroups;
            for (int i = 0; i < dataSetGroupList.size(); i++) {
                DataSetGroup dataSetGroupTemp = dataSetGroupList.get(i);
                if (dataSetGroupTemp == null) {
                    continue;
                }
                if (!dataSetGroupList.get(i).IsVisible) {
                    continue;
                }
                CollectTypeItem temp = new CollectTypeItem();
                temp.mItemAlias = dataSetGroupTemp.Alias;
                temp.mItemName = dataSetGroupTemp.Name;
                temp.mIsGoToDeatail = dataSetGroupTemp.isShowInDeviceList();
                temp.mIsSystemItem = false;
                mCollectTypeList.add(temp);
            }
        }
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

    public class LT40ScanReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            L.i("onReceive");
            if (ScanInputService.HARDWARE_SCAN.equals(intent.getAction())) {
                String text = intent.getStringExtra(ScanInputService.TEXT);
                scanSelect(text, context);
            }
        }
    }
}
