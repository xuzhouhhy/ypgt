package com.geocraft.electrics.ui.activity.andvance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

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
import com.geocraft.electrics.ui.fragment.business_basic_fragment.advance.TowerMainFragment_;
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
 */
@EBean
public class TowerController extends BaseController {

    @Bean
    TaskManager mTaskManager;

    @Bean
    DbManager mDbManager;

    @Bean
    BasicFragmentFactory mBasicFragmentFactory;
    @Bean
    WellDatasets mWellDatasets;

    WellType mWellType = WellType.JK;
    private String mFirstType;
    private String mSecondType;
    private int mDataSetKey;
    private int mDataSetParentKey = -1;
    private String mDataSetSearchValue = "";
    private boolean mIsCreateRecord;
    private DataSet mCurrentDataSet;
    private boolean mIsEditParent;
    private DataSet mTowerDataSet;
    private DataSet mWGFDataSet;
    private List<DataSet> mDataSets;
    private List<BasicFragmentFactory.DataFragment> mDataFragments =
            new ArrayList<BasicFragmentFactory.DataFragment>();
    private List<BasicFragmentFactory.DataFragment> mChekedFragments =
            new ArrayList<BasicFragmentFactory.DataFragment>();
    private FragmentOption mFragmentOption = new FragmentOption();

    //是否创建标识
    //编辑需要传染id key

    //获取Intent传递参数
    public void initIntentData(Context context) {
        if (isPassedFirstType(context)) {
            getFirstTypeFromIntent(context);
        }
        if (isPassedSecondType(context)) {
            getSecondTypeFromIntent(context);
        }
        if (isPassedCreateTag(context)) {
            getCreatedRecordFromIntent(context);
        }
        if (isPasseDataSetKey(context)) {
            getDataSetKey(context);
        }
        if (isPassedParentDataSetKey(context)) {
            getParentDataSetKey(context);
        }
        if (isPassedSearchFieldValue(context)) {
            getSearchFieldValue(context);
        }
        if (isPassedIsEditParent(context)) {
            getIsEditParent(context);
        }
    }

    //是否新建
    public boolean isCreateRecord() {
        return mIsCreateRecord;
    }

    //获取当前数据集
    public DataSet getCurrentDataSet(String datasetName) {
        mCurrentDataSet = null;
        for (DataSet dataSet : mDataSets) {
            if (datasetName.equals(dataSet.Name)) {
                mCurrentDataSet = dataSet;
                return dataSet;
            }
        }
        return null;
    }

    public DataSet getCurrentDataSet() {
        return mCurrentDataSet;
    }

    public void upFragment(boolean isCheked, BasicFragmentFactory.DataFragment dataFragment) {
        try {
            if (isCheked) {
                mChekedFragments.add(dataFragment);
            } else {
                mChekedFragments.remove(dataFragment);
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    public void setsCurrentDataSet(String datasetName) {
        mCurrentDataSet = null;
        for (DataSet dataSet : mDataSets) {
            if (datasetName.equals(dataSet.Name)) {
                mCurrentDataSet = dataSet;
                break;
            }
        }
    }

    public void initDataSet() throws CloneNotSupportedException {
        List<String> datasetNames = mWellDatasets.getDatasetNames();
        for (int i = 0; i < datasetNames.size(); i++) {
            initDataSetByDasetName(datasetNames.get(i));
        }
        if (mDataSets.size() == 0) {
            mCurrentDataSet = getCurrentDataSet(Enum.GY_JKXLTZXX);
        }
    }

    public void initDataSetByDasetName(String datasetName) throws CloneNotSupportedException {
        DataSet dataset = mTaskManager.getDataSource().getDataSetByName(mFirstType, datasetName);
        if (!mIsCreateRecord && mDataSetKey > 0) {
            dataset.PrimaryKey = mDataSetKey;
            DataSet temp = mDbManager.queryByPrimaryKey(dataset, true);
            if (temp != null) {
                dataset = temp;
            }
        }
        mDataSets.add(dataset);
    }

    public List<BasicFragmentFactory.DataFragment> getDataFragments() {
        mDataFragments.clear();
        if (mWellType == WellType.JK) {
            mDataFragments = mBasicFragmentFactory.getJKFramentItems();
        } else if (mWellType == WellType.DL) {
            mDataFragments = mBasicFragmentFactory.getDLFramentItems();
        } else {
            mDataFragments = mBasicFragmentFactory.getDYFramentItems();
        }
        return mDataFragments;
    }

    public BasicFragmentFactory.DataFragment getDataFragment(int index) {
        if (index < 0 || index > mChekedFragments.size() - 1) {
            return null;
        }
        return mChekedFragments.get(index);
    }

    public boolean isEditParent() {
        return mIsEditParent;
    }

    private void getIsEditParent(Context context) {
        mIsEditParent = ((Activity) context).getIntent().getBooleanExtra(
                Constants.INTENT_DATA_IS_FROM_EDIT_PARENT, false);
    }

    private boolean isPassedIsEditParent(Context context) {
        return ((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_IS_FROM_EDIT_PARENT);
    }

    private void getSearchFieldValue(Context context) {
        mDataSetSearchValue = ((Activity) context).getIntent()
                .getStringExtra(Constants.INTENT_DATA_SEARCH_FIELD_VALUE);
    }

    private boolean isPassedSearchFieldValue(Context context) {
        return ((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_SEARCH_FIELD_VALUE);
    }

    private void getParentDataSetKey(Context context) {
        mDataSetParentKey = ((Activity) context).getIntent()
                .getIntExtra(Constants.INTENT_DATA_SET_PARENT_KEY, -1);
    }

    private boolean isPassedParentDataSetKey(Context context) {
        return ((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_PARENT_KEY);
    }

    private boolean isPassedFirstType(Context context) {
        return ((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_GROUP_NAME);
    }

    private boolean isPassedSecondType(Context context) {
        return ((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_NAME);
    }

    private boolean isPassedCreateTag(Context context) {
        return ((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_IS_CREATE_RECORD);
    }

    private boolean isPasseDataSetKey(Context context) {
        return ((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_KEY);
    }

    private void getFirstTypeFromIntent(Context context) {
        mFirstType = ((Activity) context).getIntent()
                .getStringExtra(Constants.INTENT_DATA_SET_GROUP_NAME);
    }

    private void getSecondTypeFromIntent(Context context) {
        mSecondType = ((Activity) context).getIntent()
                .getStringExtra(Constants.INTENT_DATA_SET_NAME);
    }

    private void getCreatedRecordFromIntent(Context context) {
        mIsCreateRecord = ((Activity) context).getIntent()
                .getBooleanExtra(Constants.INTENT_DATA_IS_CREATE_RECORD, true);
    }

    private void getDataSetKey(Context context) {
        mDataSetKey = ((Activity) context).getIntent()
                .getIntExtra(Constants.INTENT_DATA_SET_KEY, -1);
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
        for (int i = 0; i < photoRuleArray.length; i++) {
            photoPrefix += mCurrentDataSet.GetFieldValueByName(photoRuleArray[i]) + "_";
        }
        String photoName = "";
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

    public int getDataSetParentKey() {
        return mDataSetParentKey;
    }

    public void updateWellType(WellType wellType) {
        mWellType = wellType;
    }


    class ExtensionFilter implements FilenameFilter {
        String ext;

        public ExtensionFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return name.contains(ext);
        }
    }


}
