package com.geocraft.electrics.ui.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.entity.DataSource;
import com.geocraft.electrics.event.NotRegisteredTempUseEvent;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.task.LoadDefaultTaskAsyncTask;
import com.geocraft.electrics.ui.activity.CollectTypeActivity;
import com.geocraft.electrics.ui.activity.CommonListActivity_;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity_;
import com.geocraft.electrics.ui.activity.ImportActivity_;
import com.geocraft.electrics.ui.activity.SystemSettingActivity_;
import com.geocraft.electrics.ui.activity.TaskManageActivity_;
import com.geocraft.electrics.utils.ElectricBitmapUtils;
import com.geocraft.electrics.utils.PinYinUtil;
import com.geocraft.electrics.utils.SPUtils;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.T;
import common.geocraft.untiltools.Tools;

/**
 * Created by Administrator on 2016/6/4.
 */
@EBean
public class CollectTypeController extends BaseController {

    List<CollectTypeItem> mCollectTypeList = new ArrayList<>();

    @Bean
    TaskManager mTaskManager;

    private static Bitmap getItemBitmap(String mark) {
        String iconParentPath = ConstPath.getIconPath();
        String iconPath = iconParentPath + mark;
        return ElectricBitmapUtils.getItemBitmap(iconPath);
    }

    public boolean getSPDefaultTaskMode(Context context) {
        return (Boolean) SPUtils.get(context, Constants.SP_KEY_IS_DEFAULT_TASK_MODE, true);
    }

    public boolean verifyPermission(Context context) {
        DialogInterface.OnClickListener OnCloseListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        };
        if (!ElectricApplication_.getInstance().mUsePermitted) {
            new AlertDialog.Builder(context)
                    .setIcon(R.mipmap.ic_warning)
                    .setTitle(R.string.dlg_warning)
                    .setCancelable(false)
                    .setMessage(context.getString(R.string.msg_use_declined))
                    .setPositiveButton(R.string.dlg_yes, OnCloseListener)
                    .show();
            return false;
        } else {
            return true;
        }
    }

    @AfterInject
    void init() {
    }

    private void dealWithDefaultTaskModeNoSyns(Context context) {
        if (getSPDefaultTaskMode(context)) {
            openOrCreateDefaultTask();
        } else {
            initCollectTypeList();
            ((CollectTypeActivity) context).getAdapter().notifyDataSetChanged();
            openTaskManagerActivity(context);
        }
    }

    private void dealWithDefaultTaskMode(Context context) {
        if (getSPDefaultTaskMode(context)) {
            LoadDefaultTaskAsyncTask loadDefaultTaskAsyncTask = new LoadDefaultTaskAsyncTask
                    (context, CollectTypeController.this);
            loadDefaultTaskAsyncTask.execute(CollectTypeController.this);
        } else {
            initCollectTypeList();
            ((CollectTypeActivity) context).getAdapter().notifyDataSetChanged();
            openTaskManagerActivity(context);
        }
    }

    public void showRegisterHint(final Context context) {
        if (ElectricApplication_.getInstance().mIsOpenRegister) {
            if (!ElectricApplication_.getInstance().mIsRegistered) {
                DialogInterface.OnClickListener onPositiveListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ElectricApplication.BUS.post(new NotRegisteredTempUseEvent());
                        //dealWithDefaultTaskMode(context);
                        dealWithDefaultTaskModeNoSyns(context);
                    }
                };
                new AlertDialog.Builder(context)
                        .setIcon(R.mipmap.ic_warning)
                        .setTitle(R.string.dlg_warning)
                        .setCancelable(false)
                        .setMessage(context.getString(R.string.msg_not_registered_just_for_temp_use))
                        .setPositiveButton(R.string.dlg_yes, onPositiveListener)
                        .show();
            } else {
                //dealWithDefaultTaskMode(context);
                dealWithDefaultTaskModeNoSyns(context);
            }
        } else {
            if (verifyPermission(context)) {
                //dealWithDefaultTaskMode(context);
                dealWithDefaultTaskModeNoSyns(context);
            }
        }
    }

    public List<CollectTypeItem> getCollectTypeList() {
        return mCollectTypeList;
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
                temp.mBitmap = getItemBitmap(dataSetGroupTemp.Mark);
                temp.mItemAlias = dataSetGroupTemp.Alias;
                temp.mItemName = dataSetGroupTemp.Name;
                temp.mIsGoToDeatail = dataSetGroupTemp.isShowInDeviceList();
                temp.mIsSystemItem = false;
                mCollectTypeList.add(temp);
            }
        }
        //add system item
        setSystemMenuItem(mCollectTypeList);
    }

    public void setActionBatTitle(Context context) {
        if (mTaskManager.getTaskInfo() != null) {
            ((CollectTypeActivity) context).setTitle(context.getString(R.string.app_name)
                    + " -- " + mTaskManager.getTaskInfo().getTaskName());
        } else {
            ((CollectTypeActivity) context).setTitle(context.getString(R.string.app_name));
        }
    }

    private void setSystemMenuItem(List<CollectTypeItem> list) {
        Context context = ElectricApplication_.getInstance().getApplicationContext();
        //add task Manager
        CollectTypeItem taskManagerItem = new CollectTypeItem();
        taskManagerItem.mBitmap = Tools.getBitmap(context, R.mipmap.ic_menu_project);
        taskManagerItem.mItemAlias = context.getString(R.string.task_manage);
        taskManagerItem.mIsSystemItem = true;
        if (!getSPDefaultTaskMode(context)) {
            list.add(taskManagerItem);
        }

        //add import data
        CollectTypeItem importDataItem = new CollectTypeItem();
        importDataItem.mBitmap = Tools.getBitmap(context, R.mipmap.ic_menu_import);
        importDataItem.mItemAlias = context.getString(R.string.import_data);
        importDataItem.mIsSystemItem = true;
        list.add(importDataItem);
        //add export data
        CollectTypeItem exportDataItem = new CollectTypeItem();
        exportDataItem.mBitmap = Tools.getBitmap(context, R.mipmap.ic_menu_export);
        exportDataItem.mItemAlias = context.getString(R.string.export_data);
        exportDataItem.mIsSystemItem = true;
        list.add(exportDataItem);
        //add system setting
        CollectTypeItem systemSettingItem = new CollectTypeItem();
        systemSettingItem.mBitmap = Tools.getBitmap(context, R.mipmap.ic_menu_setting);
        systemSettingItem.mItemAlias = context.getString(R.string.system_setting);
        systemSettingItem.mIsSystemItem = true;
        list.add(systemSettingItem);
    }

    public void openTargetActivity(Context context, int position) {
        CollectTypeItem collectTypeItemTemp = mCollectTypeList.get(position);
        if (collectTypeItemTemp != null) {
            if (collectTypeItemTemp.mIsSystemItem) {
                openSystemItemActivity(context, collectTypeItemTemp.mItemAlias);
            } else if (collectTypeItemTemp.mIsGoToDeatail) {
                openDeviceShowListActivity(context, collectTypeItemTemp);
            } else {
                openCommonListActivity(context, collectTypeItemTemp);
            }
        } else {
            T.showShort(context, R.string.unable_open_target_activity_tip);
        }
    }

    private void openCommonListActivity(Context context, CollectTypeItem collectTypeItem) {
        Intent intent = new Intent(context, CommonListActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, collectTypeItem.mItemName);
        intent.putExtra(Constants.INTENT_DATA_GROUP_TO_DATA_NAME, collectTypeItem.mIsGoToDeatail);
        context.startActivity(intent);
    }

    public void openDeviceShowListActivity(Context context, CollectTypeItem collectTypeItem) {
        DataSet dataSet = geDataSetIsDataList(collectTypeItem);
        if (dataSet == null) {
            return;
        }
        String firstType = PinYinUtil.getFirstSpell(collectTypeItem.mItemName);
        String secondTypeName = dataSet.Name;
        Intent intent = new Intent(context, DeviceShowListActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_SET_QUERY_VALUE, "");
        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, firstType);
        intent.putExtra(Constants.INTENT_DATA_SET_NAME, secondTypeName);
        context.startActivity(intent);
    }

    private DataSet geDataSetIsDataList(CollectTypeItem collectTypeItem) {
        DataSource dataSource = mTaskManager.getDataSource();
        if (null == dataSource) {
            return null;
        }
        DataSetGroup dataSetGroup = dataSource.getGroupByName(collectTypeItem.mItemName);
        if (dataSetGroup == null) {
            return null;
        }
        DataSet dataSet_datalist = null;
        for (int i = 0; i < dataSetGroup.DataSets.size(); i++) {
            DataSet dataSet = dataSetGroup.DataSets.get(i);
            if (dataSet == null) {
                continue;
            }
            if (dataSet.isShowInDeviceList()) {
                dataSet_datalist = dataSet;
                break;
            }
        }
        return dataSet_datalist;
    }


    private void openSystemItemActivity(Context context, String itemName) {
        if (itemName.equalsIgnoreCase(context.getString(R.string.task_manage))) {
            openTaskManagerActivity(context);
        } else if (itemName.equalsIgnoreCase(context.getString(R.string.system_setting))) {
            openSystemSettingActivity(context);
        } else if (itemName.equalsIgnoreCase(context.getString(R.string.import_data))) {
            openImportDataActivity(context);
        } else if (itemName.equalsIgnoreCase(context.getString(R.string.export_data))) {
            openExportDataActivity(context);
        } else {
            T.showShort(context, R.string.unable_open_target_activity_tip);
        }
    }

    public void openTaskManagerActivityOnInit(Context context) {
        if (mTaskManager.getDataSource() == null) {
            openTaskManagerActivity(context);
        }
    }

    public void onResume(Context context) {

    }

    public void openTaskManagerActivity(Context context) {
        Intent intent = new Intent(context, TaskManageActivity_.class);
        ((CollectTypeActivity) context).startActivityForResult(
                intent, ConstRequestCode.REQUEST_CODE_OPEN_TASK_MANAGER);
    }

    private void openImportDataActivity(Context context) {
        if (mTaskManager.getDataSource() != null) {
            Intent intent = new Intent(context, ImportActivity_.class);
            context.startActivity(intent);
        } else {
            T.showShort(context, R.string.current_task_is_not_exist);
        }
    }

    private void openExportDataActivity(Context context) {
        if (mTaskManager.getDataSource() != null) {
            final ExportController controller = new ExportController(context);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.app_tip);
            builder.setIcon(R.mipmap.ic_tip);
            builder.setMessage(R.string.export_tip);
            builder.setNegativeButton(R.string.app_no, null);
            builder.setInverseBackgroundForced(true);
            builder.setPositiveButton(R.string.app_yes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        controller.execute();
                    }
                }
            }).show();
        } else {
            T.showShort(context, R.string.current_task_is_not_exist);
        }
    }

    public boolean openOrCreateDefaultTask() {
        if (mTaskManager.openOrCreateDefaultTask()) {
            initCollectTypeList();
            return true;
        } else {
            return false;
        }
    }

    public boolean isDataSourceNull() {
        return mTaskManager.getDataSource() == null ? true : false;
    }

    private void openSystemSettingActivity(Context context) {
        Intent intent = new Intent(context, SystemSettingActivity_.class);
        context.startActivity(intent);
    }

    public Bitmap getItemBitmapByIndex(int index) {
        return mCollectTypeList.get(index).mBitmap;
    }

    public String getItemNameByIndex(int index) {
        return mCollectTypeList.get(index).mItemAlias;
    }

    public class CollectTypeItem {
        public Bitmap mBitmap;
        public String mItemAlias;
        public String mItemName;
        public boolean mIsSystemItem;
        public boolean mIsGoToDeatail;
    }
}
