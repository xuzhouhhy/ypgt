package com.geocraft.electrics.sr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.Nullable;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PhotoRules;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.ui.controller.PhotoManagerController;
import com.geocraft.electrics.ui.view.DataValidityInfoView;
import com.geocraft.electrics.ui.view.DataValidityInfoView_;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.FileUtils;

/**
 * 杆桩/井界面contrlloer
 *
 * @author kingdon
 */
@EBean
public class WellController extends BaseController {

    @Bean
    TaskManager mTaskManager;

    @Bean
    DbManager mDbManager;

    @Bean
    BasicFragmentFactory mBasicFragmentFactory;
    @Bean
    WellDatasets mWellDatasets;

    private WellType mWellType = WellType.JK;
    private String mFirstType;
    //是否创建标识
    private boolean mIsCreateRecord = true;
    private List<DataSet> mDataSets = new ArrayList<DataSet>();
    private List<BasicFragmentFactory.FragmentDatasetOption> mFragmentDatasetOptions =
            new ArrayList<BasicFragmentFactory.FragmentDatasetOption>();
    private DataSet mCurrentDataSet;
    private int mLineId;
    private int mWellId;
    private int mFramgmentIndex = -1;//第一个为mainfragment,不在可选项fragment栈列表中维护

    //获取Intent传递参数
    public void initIntentData(Context context) {
        initLineIdFromIntent(context);
        initWellIdFromIntent(context);
        initWellTyepFromIntent(context);
        if (isPassedFirstType(context)) {
            getFirstTypeFromIntent(context);
        }
    }

    public void initDatas() throws CloneNotSupportedException {
        initDatasets();
        refreshCurDatasetAndFragments();
    }

    private void refreshCurDatasetAndFragments() {
        initCurrentDataSet();
        mBasicFragmentFactory.initFragments(mWellType, mCurrentDataSet);
        getCurFragmentDatasetOptions();
    }

    private void initDatasets() throws CloneNotSupportedException {
        List<DatasetOption> datasetOptions = mWellDatasets.getWellDataSets();
        for (int i = 0; i < datasetOptions.size(); i++) {
            DatasetOption datasetOption = datasetOptions.get(i);
            DataSet dataset = mTaskManager.getDataSource().
                    getDataSetByName(mFirstType, datasetOption.getDatasetName());
            if (!mIsCreateRecord && datasetOption.getWellType() == mWellType) {
                dataset.PrimaryKey = mWellId;
                DataSet temp = mDbManager.queryByPrimaryKey(dataset, true);
                if (temp != null) {
                    dataset = temp;
                }
            }
            mDataSets.add(dataset);
        }
    }

    //获取当前数据集
    public DataSet getCurrentDataSet(String datasetName) {
        for (DataSet dataSet : mDataSets) {
            if (datasetName.equals(dataSet.Name)) {
                return dataSet;
            }
        }
        return null;
    }

    public DataSet getCurrentDataSet() {
        return mCurrentDataSet;
    }

    public void setCurrentDataSet(String datasetName) {
        mCurrentDataSet = getCurrentDataSet(datasetName);
    }

    private void initCurrentDataSet() {
        if (mWellType == WellType.JK) {
            mCurrentDataSet = getCurrentDataSet(Enum.GY_JKXLTZXX);
        } else if (mWellType == WellType.DL) {
            mCurrentDataSet = getCurrentDataSet(Enum.GY_DLXLTZXX);
        }
    }

    /**
     * 获取当前类型可选采集项
     */
    public List<BasicFragmentFactory.FragmentDatasetOption> getCurFragmentDatasetOptions() {
        if (mWellType == WellType.JK) {
            mFragmentDatasetOptions = mBasicFragmentFactory.getJKFramentItems();
        } else if (mWellType == WellType.DL) {
            mFragmentDatasetOptions = mBasicFragmentFactory.getDLFramentItems();
        }
        return mFragmentDatasetOptions;
    }

    //是否新建
    public boolean isCreateRecord() {
        return mIsCreateRecord;
    }

    public BasicFragmentFactory.FragmentDatasetOption getDataFragment(int index) {
        if (index < 0 || index > mFragmentDatasetOptions.size() - 1) {
            return null;
        }
        return mFragmentDatasetOptions.get(index);
    }

    /**
     * 获取选中的fragment项大小
     */
    public int getCheckedFragmentSize() {
        return getCheckedFragments().size();
    }

    public List<BasicFragmentFactory.FragmentDatasetOption> getCheckedFragments() {
        List<BasicFragmentFactory.FragmentDatasetOption> fragmentDatasetOptions =
                new ArrayList<BasicFragmentFactory.FragmentDatasetOption>();
        for (int i = 0; i < mFragmentDatasetOptions.size(); i++) {
            BasicFragmentFactory.FragmentDatasetOption fragmentDatasetOption
                    = mFragmentDatasetOptions.get(i);
            if (fragmentDatasetOption.isChecked()) {
                fragmentDatasetOptions.add(fragmentDatasetOption);
            }
        }
        return fragmentDatasetOptions;
    }

    public BasicFragmentFactory.FragmentDatasetOption getFirstDataFragment() {
        int index = 0;
        if (mFragmentDatasetOptions.size() == 0) {
            return null;
        }
        BasicFragmentFactory.FragmentDatasetOption fragmentDatasetOption
                = mFragmentDatasetOptions.get(index);
        if (fragmentDatasetOption.isChecked()) {
            mFramgmentIndex++;
            return fragmentDatasetOption;
        } else {
            fragmentDatasetOption = getFragmentDatasetOption(index);
        }
        if (mFramgmentIndex >= 0) {
            return fragmentDatasetOption;
        }
        return null;
    }

    public BasicFragmentFactory.FragmentDatasetOption getNextCheckedDataFragment() {
        if (mFramgmentIndex < 0 || mFramgmentIndex >= mFragmentDatasetOptions.size() - 1) {
            return null;
        }
        BasicFragmentFactory.FragmentDatasetOption fragmentDatasetOption =
                getFragmentDatasetOption(mFramgmentIndex);
        if (fragmentDatasetOption != null) {
            return fragmentDatasetOption;
        }
        return null;
    }

    @Nullable
    private BasicFragmentFactory.FragmentDatasetOption getFragmentDatasetOption(
            int framgmentIndex) {
        for (int i = framgmentIndex; i < mFragmentDatasetOptions.size(); i++) {
            BasicFragmentFactory.FragmentDatasetOption fragmentDatasetOption
                    = mFragmentDatasetOptions.get(i);
            if (i > framgmentIndex) {
                if (fragmentDatasetOption.isChecked()) {
                    mFramgmentIndex = i;
                    return fragmentDatasetOption;
                }
            }
        }
        return null;
    }

    public BasicFragmentFactory.FragmentDatasetOption getPreCheckedDataFragment() {
        if (mFramgmentIndex <= 0 || mFramgmentIndex > mFragmentDatasetOptions.size() - 1) {
            return null;
        }
        for (int i = mFramgmentIndex; i >= 0; i--) {
            BasicFragmentFactory.FragmentDatasetOption fragmentDatasetOption
                    = mFragmentDatasetOptions.get(i);
            if (i < mFramgmentIndex) {
                if (fragmentDatasetOption.isChecked()) {
                    mFramgmentIndex = i;
                    return fragmentDatasetOption;
                }
            }
        }
        return null;
    }

    private void initLineIdFromIntent(Context context) {
        if (((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_LINE_ID)) {
            mLineId = ((Activity) context).getIntent()
                    .getIntExtra(Constants.INTENT_DATA_LINE_ID, -1);
        }
    }

    public int getLineId() {
        return mLineId;
    }

    private void initWellIdFromIntent(Context context) {
        if (((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_WELL_ID)) {
            mWellId = ((Activity) context).getIntent()
                    .getIntExtra(Constants.INTENT_DATA_WELL_ID, -1);
            if (mWellId > 0) {
                mIsCreateRecord = false;
            }
        }
    }

    private void initWellTyepFromIntent(Context context) {
        if (((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_WELL_TYPE)) {
            String wellTypeKey = ((Activity) context).getIntent()
                    .getStringExtra(Constants.INTENT_DATA_WELL_TYPE);
            try {
                initWellType(wellTypeKey);
            } catch (CloneNotSupportedException e) {
                L.printException(e);
            }
        }
    }

    private void initWellType(String value) throws CloneNotSupportedException {
        if (null == value || value.isEmpty()) {
            return;
        }
        if (value.equals(String.valueOf(WellType.JK.ordinal()))) {
            mWellType = WellType.JK;
        }
        if (value.equals(String.valueOf(WellType.DL.ordinal()))) {
            mWellType = WellType.DL;
        }
    }

    private boolean isPassedFirstType(Context context) {
        return ((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_GROUP_NAME);
    }

    private void getFirstTypeFromIntent(Context context) {
        mFirstType = ((Activity) context).getIntent()
                .getStringExtra(Constants.INTENT_DATA_SET_GROUP_NAME);
    }

    public int getFramgmentIndex() {
        return mFramgmentIndex;
    }

    public void setFramgmentIndex(int framgmentIndex) {
        mFramgmentIndex = framgmentIndex;
    }


    public boolean checkDataValidity(Context context,
                                     List<PhotoManagerController.PhotoItemInfo> taskPhotoList) {
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
        for (int j = 0; j < taskPhotoList.size(); j++) {
            if (!FileUtils.existFile(taskPhotoList.get(j).photoPath)) {
                illegalPhotoList.add(taskPhotoList.get(j).mPhotoType);
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

    @Deprecated
    public boolean saveRecord_(List<PhotoManagerController.PhotoItemInfo> taskPhotoList) {
        if (mCurrentDataSet == null) {
            return false;
        }
        if (mIsCreateRecord) {
            int key = mDbManager.insert(mCurrentDataSet);
            if (key >= 0) {
                mCurrentDataSet.PrimaryKey = key;
                renamePhotoAndMove(taskPhotoList);
                return true;
            } else {
                return false;
            }
        } else {
            if (mDbManager.update(mCurrentDataSet)) {
                renamePhotoAndMove(taskPhotoList);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean saveRecord(List<PhotoManagerController.PhotoItemInfo> taskPhotoList) {
        if (mCurrentDataSet == null) {
            return false;
        }
        if (mIsCreateRecord) {
            int key = mDbManager.insert(mCurrentDataSet);
            if (key >= 0) {
                mCurrentDataSet.PrimaryKey = key;
                renamePhotoAndMove(taskPhotoList);
                return true;
            } else {
                return false;
            }
        } else {
            if (mDbManager.update(mCurrentDataSet)) {
                renamePhotoAndMove(taskPhotoList);
                return true;
            } else {
                return false;
            }
        }
    }

    private void renamePhotoAndMove(List<PhotoManagerController.PhotoItemInfo> taskPhotoList) {
        for (int i = 0; i < taskPhotoList.size(); i++) {
            PhotoManagerController.PhotoItemInfo photoItemInfo = taskPhotoList.get(i);
            if (photoItemInfo == null) {
                continue;
            }
            if (photoItemInfo.photoPath.contains(Constants.TASK_CACHE_PATH)) {
                File oldFile = new File(photoItemInfo.photoPath);
                File newFile = new File(getNewPhotoPath(photoItemInfo));
                File cacheFile = new File(getTaskPath() + File.separator +
                        Constants.TASK_PHOTO_FOLDER + File.separator +
                        Constants.TASK_CACHE_PATH + File.separator + newFile.getName());
                try {
                    FileUtils.copyFile(oldFile, newFile);
                    FileUtils.copyFile(oldFile, cacheFile);
                } catch (IOException e) {
                    L.printException(e);
                }
            } else {
                File oldFile = new File(photoItemInfo.photoPath);
                File newFile = new File(getNewPhotoPath(photoItemInfo));
                if (!oldFile.getPath().equals(newFile.getPath())) {
                    try {
                        FileUtils.copyFile(oldFile, newFile);
                    } catch (IOException e) {
                        L.printException(e);
                    }
                }
            }
        }
    }

    private String getTaskPath() {
        return ConstPath.getTaskRootFolder() + mTaskManager.getTaskInfo().getTaskName();
    }

    private String getNewPhotoPath(PhotoManagerController.PhotoItemInfo photoItemInfo) {
        PhotoRules photoRules = getPhotoRules(photoItemInfo.mPhotoType);
        if (photoRules == null) {
            return "";
        }
        String[] photoRuleArray = photoRules.Rules.split(",");
        String photoPrefix = "";
        for (String aPhotoRuleArray : photoRuleArray) {
            photoPrefix += mCurrentDataSet.GetFieldValueByName(aPhotoRuleArray) + "_";
        }
        String photoName;
        if (photoItemInfo.mPhotoType.equals(Constants.TEXT_PHOTO_SUFFIX)) {
            if (photoPrefix.contains("_")) {
                photoPrefix = photoPrefix.substring(0, photoPrefix.lastIndexOf('_'));
            }
            photoName = photoPrefix + Constants.PHOTO_SUFFIX;
        } else {
            photoName = photoPrefix + photoRules.Type + Constants.PHOTO_SUFFIX;
        }
        String taskPath = getTaskPath();
        String photoPath = taskPath + File.separator + Constants.TASK_PHOTO_FOLDER;
        photoPath = Utils.getPhotoDir(photoPath, photoRules, mCurrentDataSet);
        return photoPath + photoName;
    }

    private PhotoRules getPhotoRules(String type) {
        List<PhotoRules> photoRulesList = mCurrentDataSet.PhotoRules;
        for (int i = 0; i < photoRulesList.size(); i++) {
            PhotoRules photoRulesTemp = photoRulesList.get(i);
            if (photoRulesTemp.Type.equals(type)) {
                return photoRulesTemp;
            }
        }
        return null;
    }

    //清空当前数据集的相关照片
    public void clearPhotoCache() {
        try {
            String taskPath = ConstPath.getTaskRootFolder() + mTaskManager.getTaskInfo().getTaskName();
            String photoCachePath = taskPath + File.separator + Constants.TASK_PHOTO_FOLDER +
                    Constants.TASK_CACHE_PATH;
            File photoCacheFolder = new File(photoCachePath);
            ExtensionFilter myFilter = new ExtensionFilter(mCurrentDataSet.Alias);
            for (File file : photoCacheFolder.listFiles(myFilter)) {
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    //获取Activity标题
    public String getTitle() {
        return mCurrentDataSet.Alias;
    }

    public void updateWellType(WellType wellType) {
        mWellType = wellType;
        refreshCurDatasetAndFragments();
    }

    public WellType getWellType() {
        return mWellType;
    }

    private class ExtensionFilter implements FilenameFilter {
        String ext;

        public ExtensionFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return name.contains(ext);
        }
    }


}
