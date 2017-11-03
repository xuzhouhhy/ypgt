package com.geocraft.electrics.sr.controller;

import android.app.Activity;
import android.content.Context;

import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.PhotoRules;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.sr.BasicFragmentProxy;
import com.geocraft.electrics.sr.DatasetOption;
import com.geocraft.electrics.sr.FragmentOption;
import com.geocraft.electrics.sr.PreFragmentFactory;
import com.geocraft.electrics.sr.WellDatasets;
import com.geocraft.electrics.sr.WellType;
import com.geocraft.electrics.sr.event.UpdateWellNameArgs;
import com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment;
import com.geocraft.electrics.sr.spacer.GY_HWG_spacerFragment;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    BasicFragmentProxy mBasicFragmentProxy;
    @Bean
    WellDatasets mWellDatasets;
    @Bean
    PreFragmentFactory mPreFragmentFactory;

    private WellType mWellType = WellType.JK;
    private String mFirstType;
    //是否创建标识
    private boolean mIsCreateRecord = true;
    private List<DataSet> mDataSets = new ArrayList<DataSet>();
    private List<FragmentOption> mFragmentOptions =
            new ArrayList<FragmentOption>();
    private DataSet mCurrentDataSet;
    private int mLineId = -1;
    private int mWellId = -1;
    private int mFramgmentIndex = -1;

    private Map<String, SrPhotoManagerFragment> mPhotoFragments =
            new HashMap<String, SrPhotoManagerFragment>();
    private GY_HWG_spacerFragment mSpacerFragment;
    /**
     * 编辑间隔的内存管理
     */
    private List<DataSet> mSpacerDs = new ArrayList<>();

    //获取Intent传递参数
    public void initIntentData(Context context) {
        initLineIdFromIntent(context);
        initWellIdFromIntent(context);
        initWellTypeFromIntent(context);
        isFirstTypeFromIntent(context);
    }

    public void initDatas() throws CloneNotSupportedException {
        initDatasets();
        mPreFragmentFactory.initPreFramentItems();
        initCurDatasetAndFragments();
    }

    private void initCurDatasetAndFragments() {
        initCurrentDataSet();
        mFragmentOptions = mBasicFragmentProxy.initFragmentOptions(mWellType,
                mCurrentDataSet);
        clearSpecialData();
    }

    private void clearSpecialData() {
        mPhotoFragments.clear();
        mSpacerFragment = null;
        mSpacerDs.clear();
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

    private void initCurrentDataSet() {
        if (mWellType == WellType.JK) {
            mCurrentDataSet = getCurrentDataSet(Enum.GY_JKXLTZXX);
        } else if (mWellType == WellType.DL) {
            mCurrentDataSet = getCurrentDataSet(Enum.GY_DLXLTZXX);
        } else if (mWellType == WellType.KBS) {
            mCurrentDataSet = getCurrentDataSet(Enum.GY_KBSTZXX);
        }
    }

    public FragmentOption getNextFragment() {
        FragmentOption fragmentOption = mPreFragmentFactory.getNextCheckedFragment();
        if (fragmentOption != null) {
            return fragmentOption;
        }
        if (mFramgmentIndex < 0) {
            fragmentOption = getFirstDataOption();
        } else {
            fragmentOption = getNextCheckedDataFragment();
        }
        if (fragmentOption != null) {
            mPreFragmentFactory.setNextFramgmentIndexOfNext();
        }
        return fragmentOption;
    }

    public FragmentOption getPreFragment() {
        FragmentOption fragmentOption = getPreCheckedFragment();
        if (fragmentOption != null) {
            return fragmentOption;
        }
        if (null == fragmentOption) {
            fragmentOption = mPreFragmentFactory.getPreCheckedFragment();
        }
        if (fragmentOption != null) {
            mFramgmentIndex = -1;
        }
        return fragmentOption;
    }

    /**
     * 获取当前类型可选采集项
     */
    public List<FragmentOption> getCurVisibleFragmentOptions() {
        List<FragmentOption> fragmentOptions = new ArrayList<FragmentOption>();
        for (int i = 0; i < mFragmentOptions.size(); i++) {
            FragmentOption option = mFragmentOptions.get(i);
            String parentNameKey = option.getParentNameKey();
            if (null == parentNameKey || parentNameKey.isEmpty()) {
                fragmentOptions.add(option);
            }
        }
        return fragmentOptions;
    }

    //是否新建
    public boolean isCreateRecord() {
        return mIsCreateRecord;
    }

    public List<FragmentOption> getCheckedFragments() {
        List<FragmentOption> fragmentOptions = new ArrayList<FragmentOption>();
        for (int i = 0; i < mFragmentOptions.size(); i++) {
            FragmentOption fragmentOption = mFragmentOptions.get(i);
            if (fragmentOption.isChecked()) {
                fragmentOptions.add(fragmentOption);
            }
        }
        return fragmentOptions;
    }

    private FragmentOption getFirstDataOption() {
        int index = 0;
        if (mFragmentOptions.size() == 0) {
            return null;
        }
        FragmentOption fragmentOption = mFragmentOptions.get(index);
        if (fragmentOption.isChecked()) {
            mFramgmentIndex++;
            return fragmentOption;
        } else {
            fragmentOption = getFragmentDatasetOption(index);
        }
        if (mFramgmentIndex >= 0) {
            return fragmentOption;
        }
        return null;
    }

    private FragmentOption getNextCheckedDataFragment() {
        if (mFramgmentIndex < 0 || mFramgmentIndex >= mFragmentOptions.size() - 1) {
            return null;
        }
        FragmentOption fragmentOption = getFragmentDatasetOption(mFramgmentIndex);
        if (fragmentOption != null) {
            return fragmentOption;
        }
        return null;
    }

    private FragmentOption getPreCheckedFragment() {
        if (mFramgmentIndex <= 0 || mFramgmentIndex > mFragmentOptions.size() - 1) {
            return null;
        }
        for (int i = mFramgmentIndex; i >= 0; i--) {
            FragmentOption fragmentOption = mFragmentOptions.get(i);
            if (i < mFramgmentIndex) {
                if (fragmentOption.isChecked()) {
                    mFramgmentIndex = i;
                    return fragmentOption;
                }
            }
        }
        return null;
    }

    /**
     * 更新制定fragment状态
     *
     * @param framgmentNameKey fragment队列中fragmentName
     * @param isChecked        选中状态
     */
    public void updateFragmentStatus(String framgmentNameKey, boolean isChecked) {
        for (FragmentOption option : mFragmentOptions) {
            if (framgmentNameKey.equals(option.getNameKey())) {
                option.setChecked(isChecked);
            }
            String parentNameKey = option.getParentNameKey();
            if (null == parentNameKey || parentNameKey.isEmpty()) {
                continue;
            }
            if (parentNameKey.equals(framgmentNameKey)) {
                option.setChecked(isChecked);
            }
        }
    }

    public boolean isHasNextFragment() {
        return mPreFragmentFactory.isHasNextFragmentOption() || isHasNextFragmentOption();
    }

    public boolean isHasPreFragment() {
        return mPreFragmentFactory.getFramgmentIndex() > 0;
    }

    private boolean isHasNextFragmentOption() {
        int index = 0;
        if (mFragmentOptions.size() == 0) {
            return false;
        }
        FragmentOption fragmentOption;
        if (mFramgmentIndex < index) {
            fragmentOption = mFragmentOptions.get(index);
            if (fragmentOption.isChecked()) {
                return true;
            }
        }
        for (int i = index; i < mFragmentOptions.size(); i++) {
            fragmentOption = mFragmentOptions.get(i);
            if (i > mFramgmentIndex) {
                if (fragmentOption.isChecked()) {
                    return true;
                }
            }
        }
        return false;
    }


    private FragmentOption getFragmentDatasetOption(int framgmentIndex) {
        for (int i = framgmentIndex; i < mFragmentOptions.size(); i++) {
            FragmentOption fragmentOption = mFragmentOptions.get(i);
            if (i > framgmentIndex) {
                if (fragmentOption.isChecked()) {
                    mFramgmentIndex = i;
                    return fragmentOption;
                }
            }
        }
        return null;
    }

    private void initLineIdFromIntent(Context context) {
        if (((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_LINE_ID)) {
            String lineId = ((Activity) context).getIntent()
                    .getStringExtra(Constants.INTENT_DATA_LINE_ID);
            mLineId = Integer.valueOf(lineId);
        }
    }

    public int getLineId() {
        return mLineId;
    }

    private void initWellIdFromIntent(Context context) {
        if (((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_WELL_ID)) {
            String wellId = ((Activity) context).getIntent()
                    .getStringExtra(Constants.INTENT_DATA_WELL_ID);
            mWellId = Integer.valueOf(wellId);
            if (mWellId > 0) {
                mIsCreateRecord = false;
            }
        }
    }

    private void initWellTypeFromIntent(Context context) {
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
        } else if (value.equals(String.valueOf(WellType.DL.ordinal()))) {
            mWellType = WellType.DL;
        } else if (value.equals(String.valueOf(WellType.KBS.ordinal()))) {
            mWellType = WellType.KBS;
        }
    }

    private void isFirstTypeFromIntent(Context context) {
        if (((Activity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_GROUP_NAME)) {
            mFirstType = ((Activity) context).getIntent()
                    .getStringExtra(Constants.INTENT_DATA_SET_GROUP_NAME);
        }
    }

    public boolean saveRecord(List<SrPhotoManagerController.PhotoItemInfo> taskPhotoList,
                              List<DataSet> dataSets) {
        if (mCurrentDataSet == null) {
            return false;
        }
        if (mIsCreateRecord) {
            if (null != dataSets) {
                //保存间隔点，获取间隔点id组成的F_SpacerIds字段值
                String spacerIds = saveSpacer(dataSets);
                //F_SpacerIds字段值写给当前基桩dataset
                mCurrentDataSet.SetFiledValueByName(Enum.DLJ_JGD, spacerIds);
            }
            int key = mDbManager.insert(mCurrentDataSet);
            if (key >= 0) {
                mCurrentDataSet.PrimaryKey = key;
                renamePhotoAndMove(taskPhotoList);
                return true;
            } else {
                return false;
            }
        } else {
            if (null != dataSets) {
                //保存和更新间隔
                String spacerIds = saveAndupdateSpacer(dataSets);
                //F_SpacerIds字段值写给当前基桩dataset
                mCurrentDataSet.SetFiledValueByName(Enum.DLJ_JGD, spacerIds);
            }
            if (mDbManager.update(mCurrentDataSet)) {
                renamePhotoAndMove(taskPhotoList);
                return true;
            } else {
                return false;
            }
        }
    }

    private String saveSpacer(List<DataSet> dataSets) {
        StringBuilder builder = new StringBuilder();
        for (DataSet ds : dataSets) {
            int key = mDbManager.insert(ds);
            if (key >= 0) {
                builder.append(key);
                builder.append("&");
            }
        }
        return builder.toString();
    }

    private String saveAndupdateSpacer(List<DataSet> dataSets) {
        StringBuilder builder = new StringBuilder();
        for (DataSet ds : dataSets) {
            int key = ds.PrimaryKey;
            if (key < 0) {
                key = mDbManager.insert(ds);
                if (key >= 0) {
                    builder.append(key);
                    builder.append("&");
                }
            } else {
                if (mDbManager.update(ds)) {
                    builder.append(key);
                    builder.append("&");
                }
            }
        }
        return builder.toString();
    }

    private void renamePhotoAndMove(List<SrPhotoManagerController.PhotoItemInfo> taskPhotoList) {
        String lineName = queryLineName();
        if (null == lineName || lineName.isEmpty()) {
            return;
        }

        for (int i = 0; i < taskPhotoList.size(); i++) {
            SrPhotoManagerController.PhotoItemInfo photoItemInfo = taskPhotoList.get(i);
            if (photoItemInfo == null) {
                continue;
            }
            if (photoItemInfo.photoPath.contains(Constants.TASK_CACHE_PATH)) {
                File oldFile = new File(photoItemInfo.photoPath);
                File newFile = new File(getNewPhotoPath(photoItemInfo, lineName));
                File cacheFile = new File(getTaskPath() + File.separator +
                        Constants.TASK_PHOTO_FOLDER + File.separator +
                        Constants.TASK_CACHE_PATH + File.separator + newFile.getName());
                try {
                    FileUtils.copyFile(oldFile, newFile);
                    FileUtils.copyFile(oldFile, cacheFile);
                } catch (IOException e) {
                    L.printException(e);
                }
            } else if (photoItemInfo.photoPath.contains(Constants.PHOTO_SUFFIX)) {
                File oldFile = new File(photoItemInfo.photoPath);
                File newFile = new File(getNewPhotoPath(photoItemInfo, lineName));
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

    private String queryLineName() {
        if (mLineId > -1) {
            DataSet dataSet = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
                    Enum.DATA_SET_NAME_LINE);
            dataSet.PrimaryKey = mLineId;
            DataSet ds = mDbManager.queryByPrimaryKey(dataSet, true);
            return ds.GetFieldValueByName(Enum.LINE_FIELD_NAME);
        }
        return null;
    }

    private String getTaskPath() {
        return ConstPath.getTaskRootFolder() + mTaskManager.getTaskInfo().getTaskName();
    }

    private String getNewPhotoPath(SrPhotoManagerController.PhotoItemInfo photoItemInfo,
                                   String lineName) {
        PhotoRules photoRules = getPhotoRules(photoItemInfo.mPhotoType);
        if (photoRules == null) {
            return "";
        }
        String[] photoRuleArray = photoRules.Rules.split(",");
        String photoPrefix = lineName + "_";
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
        String photoPath = taskPath + File.separator + Constants.TASK_PHOTO_FOLDER +
                lineName + File.separator;
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

    public List<SrPhotoManagerController.PhotoItemInfo> getPhotoInfoList() {
        List<SrPhotoManagerController.PhotoItemInfo> photoItemInfoList = new ArrayList<>();
        for (Object object : mPhotoFragments.entrySet()) {
            Map.Entry entry = (Map.Entry) object;
            SrPhotoManagerFragment photoFragment = (SrPhotoManagerFragment) entry.getValue();
            if (photoFragment != null) {
                photoItemInfoList.addAll(photoFragment.getTaskPhotoList());
            }
        }
        return photoItemInfoList;
    }

    /**
     * @return 当前编辑好的spacer数据集
     */
    public List<DataSet> getSpacerDatasets() {
        if (null != mSpacerFragment) {
            return mSpacerFragment.getSpacerDatasetList();
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
//    public String getTitle() {
//        return mCurrentDataSet.Alias;
//    }

    public void switchWellType(WellType wellType) {
        mWellType = wellType;
        initCurDatasetAndFragments();
        ElectricApplication.BUS.post(new UpdateWellNameArgs());
    }

    public WellType getWellType() {
        return mWellType;
    }

    public PreFragmentFactory getPreFragmentFactory() {
        return mPreFragmentFactory;
    }

    public int getWellId() {
        return mWellId;
    }

    public Map<String, SrPhotoManagerFragment> getPhotoFragments() {
        return mPhotoFragments;
    }

    public void setSpacerFragment(GY_HWG_spacerFragment spacerFragment) {
        mSpacerFragment = spacerFragment;
        if (null != mSpacerFragment) {
            mSpacerDs = mSpacerFragment.getSpacerDatasetList();
        }
    }

    public List<DataSet> getSpacerDs() {
        return mSpacerDs;
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
