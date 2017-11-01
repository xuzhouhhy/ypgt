package com.geocraft.electrics.sr.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.PhotoRules;
import com.geocraft.electrics.entity.TaskInfo;
import com.geocraft.electrics.event.OpenSystemTakePhotoEventArgs;
import com.geocraft.electrics.event.RefreshPhotoAdapterEventArgs;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.sr.event.OpenSystemImportePhotoEventArgs;
import com.geocraft.electrics.utils.ElectricBitmapUtils;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.FileUtils;
import common.geocraft.untiltools.T;
import common.geocraft.untiltools.Tools;

/**
 * Created by Administrator on 2016/6/7.
 */
@EBean
public class SrPhotoManagerController extends BaseController {

    DataSet mDataSet;
    boolean mIsNew;
    Context mContext;

    @Bean
    TaskManager mTaskManager;

    List<PhotoItemInfo> mTaskPhotoList = new ArrayList<>();

    /**
     * 拍照操作
     *
     * @param position gridview位置
     */
    public void doTakePhotoOperate(int position) {
        PhotoItemInfo photoItemInfoTemp = mTaskPhotoList.get(position);
        if (photoItemInfoTemp == null) {
            return;
        }
        if (photoItemInfoTemp.noPhoto) {
            openTakePhotoActivity(position);
        }
    }

    /**
     * 放大预览照片
     *
     * @param position gridview位置
     */
    public void doPreviewPhotoOperate(int position) {
        PhotoItemInfo photoItemInfoTemp = mTaskPhotoList.get(position);
        if (photoItemInfoTemp == null) {
            return;
        }
        if (!photoItemInfoTemp.noPhoto) {
            openPreviewPhotoActivity(position);
        }
    }

    @AfterInject
    void init() {
    }

    public void initParams(Context context, boolean isNew, DataSet dataSet) {
        this.mContext = context;
        this.mIsNew = isNew;
        this.mDataSet = dataSet;
    }

    public DataSet getCurrentDataSet() {
        return this.mDataSet;
    }

    public List<PhotoItemInfo> getTaskPhotoList() {
        return mTaskPhotoList;
    }

    public void saveValue() {
        try {
            for (int i = 0; i < mDataSet.PhotoRules.size(); i++) {
                PhotoRules photoRules = mDataSet.PhotoRules.get(i);
                photoRules.setCachePath(mTaskPhotoList.get(i).photoPath);
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    public void initTaskPhotoList() {
        if (mDataSet == null) {
            return;
        }
        List<PhotoRules> photoItemList = mDataSet.PhotoRules;
        for (int i = 0; i < photoItemList.size(); i++) {
            PhotoItemInfo photoItemInfoTemp = new PhotoItemInfo();
            PhotoRules photoRulesTemp = photoItemList.get(i);
            if (photoRulesTemp == null) {
                continue;
            }
            photoItemInfoTemp.mPhotoType = photoRulesTemp.Type;
            boolean isCreate = false;
            String photoPath = photoRulesTemp.getCachePath();
            if (null == photoPath || photoPath.isEmpty()) {
                isCreate = true;
            }
            if (isCreate) {
                photoItemInfoTemp.mPhotoImage =
                        Tools.getDrawableById(mContext, R.mipmap.ic_take_photo);
                photoItemInfoTemp.photoPath = "";
                photoItemInfoTemp.noPhoto = true;
            } else {
                photoItemInfoTemp = getPhotoItemInfoBySearchFile(photoRulesTemp);
            }
            if (photoItemInfoTemp != null) {
                mTaskPhotoList.add(photoItemInfoTemp);
            }
        }
    }

    //通过搜索文件名获取照片
    private PhotoItemInfo getPhotoItemInfoBySearchFile(PhotoRules photoRulesTemp) {
        PhotoItemInfo photoItemInfoTemp = new PhotoItemInfo();
        if (mDataSet == null) {
            return null;
        }
        String photoAbsolutePath = photoRulesTemp.getCachePath();
        if (null == photoAbsolutePath || photoAbsolutePath.isEmpty()) {
            photoAbsolutePath = getPhotoPath(photoRulesTemp, photoItemInfoTemp);
        }
        if (FileUtils.existFile(photoAbsolutePath)) {
            photoItemInfoTemp.photoPath = photoAbsolutePath;
            photoItemInfoTemp.noPhoto = false;
            Bitmap bitmap = ElectricBitmapUtils.getCorrectDirectionBitmap(photoAbsolutePath);
            photoItemInfoTemp.mPhotoImage = new BitmapDrawable(bitmap);
        } else {
            photoItemInfoTemp.mPhotoImage =
                    Tools.getDrawableById(mContext, R.mipmap.ic_take_photo);
            photoItemInfoTemp.photoPath = "";
            photoItemInfoTemp.noPhoto = true;
        }
        return photoItemInfoTemp;
    }

    @NonNull
    private String getPhotoPath(PhotoRules photoRulesTemp, PhotoItemInfo photoItemInfoTemp) {
        photoItemInfoTemp.mPhotoType = photoRulesTemp.Type;
        String[] photoRuleArray = photoRulesTemp.Rules.split(",");
        String photoPrefix = "";
        for (int i = 0; i < photoRuleArray.length; i++) {
            photoPrefix += mDataSet.GetFieldValueByName(photoRuleArray[i]) + "_";
        }
        String photoName = "";
        if (photoRulesTemp.Type.equals(Constants.TEXT_PHOTO_SUFFIX)) {
            if (photoPrefix.contains("_")) {
                photoPrefix = photoPrefix.substring(0, photoPrefix.lastIndexOf('_'));
            }
            photoName = photoPrefix + Constants.PHOTO_SUFFIX;
        } else {
            photoName = photoPrefix + photoItemInfoTemp.mPhotoType + Constants.PHOTO_SUFFIX;
        }
        String taskPath = ConstPath.getTaskRootFolder() + mTaskManager.getTaskInfo().getTaskName();
        String lineName = queryLineName(Integer.valueOf(mDataSet.GetFieldValueByName("F_lineId")));
        String photoPath = taskPath + File.separator + Constants.TASK_PHOTO_FOLDER + lineName + File.separator;
        photoPath = Utils.getPhotoDir(photoPath, photoRulesTemp, mDataSet);
        return photoPath + photoName;
    }

    private String queryLineName(int lineId) {
        if (lineId > -1) {
            DataSet dataSet = mTaskManager.getDataSource().getDataSetByName("gycj", "line");
            dataSet.PrimaryKey = lineId;
            DataSet ds = DbManager_.getInstance_(mContext).queryByPrimaryKey(dataSet, true);
            return ds.GetFieldValueByName("F_lineName");
        }
        return null;
    }

    private void openTakePhotoActivity(int position) {
        if (mTaskManager.getTaskInfo() == null) {
            return;
        }
        String taskPath = ConstPath.getTaskRootFolder() + mTaskManager.getTaskInfo().getTaskName();
        String photoCachePath = taskPath + File.separator + Constants.TASK_PHOTO_FOLDER + Constants.TASK_CACHE_PATH;
        File photoCacheFile = new File(photoCachePath);
        if (!FileUtils.existFile(photoCachePath)) {
            photoCacheFile.mkdirs();
        }
        if (mTaskPhotoList.get(position) == null) {
            return;
        }
        String photoCacheName = mTaskPhotoList.get(position).mPhotoType + "_"
                + mDataSet.Alias + Constants.PHOTO_SUFFIX;
        File file = new File(photoCacheFile, photoCacheName);
        ElectricApplication.BUS.post(new OpenSystemTakePhotoEventArgs(file, mDataSet.Name));
    }

    private void openPreviewPhotoActivity(int position) {
        if (mTaskManager.getTaskInfo() == null) {
            return;
        }
        PhotoItemInfo photoItemInfoTemp = mTaskPhotoList.get(position);
        if (photoItemInfoTemp == null) {
            return;
        }
        if (!photoItemInfoTemp.noPhoto) {
            if (FileUtils.existFile(photoItemInfoTemp.photoPath)) {
                File photoFile = new File(photoItemInfoTemp.photoPath);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (mContext.getPackageManager().resolveActivity(intent, 0) != null) {
                    intent.setDataAndType(Uri.fromFile(photoFile), "image/*");
                    mContext.startActivity(intent);
                } else {
                    T.showShort(mContext, R.string.open_system_preview_photo_exception);
                }
            } else {
                T.showShort(mContext, R.string.photo_open_exception);
            }
        }
    }

    public void reTakePhoto(int position) {
        if (mTaskManager.getTaskInfo() == null) {
            return;
        }
        PhotoItemInfo photoItemInfoTemp = mTaskPhotoList.get(position);
        if (photoItemInfoTemp == null) {
            return;
        }
        if (!photoItemInfoTemp.noPhoto) {
            if (FileUtils.existFile(photoItemInfoTemp.photoPath)) {
                File file = new File(photoItemInfoTemp.photoPath);
                ElectricApplication.BUS.post(new OpenSystemTakePhotoEventArgs(file, mDataSet.Name));
            }
        }
    }

    public void showPhotoItemDeleteTipDialog(final int position) {
        new AlertDialog.Builder(mContext)
                .setIcon(R.mipmap.ic_tip)
                .setTitle(R.string.dlg_tip)
                .setMessage(R.string.photo_delete_tip)
                .setPositiveButton(R.string.dlg_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhotoItemInfo photoItemInfoTemp = mTaskPhotoList.get(position);
                        if (photoItemInfoTemp == null) {
                            return;
                        } else {
                            FileUtils.deleteFile(photoItemInfoTemp.photoPath);
                            photoItemInfoTemp.noPhoto = true;
                            photoItemInfoTemp.mPhotoImage =
                                    Tools.getDrawableById(mContext, R.mipmap.ic_take_photo);
                            photoItemInfoTemp.photoPath = "";
                            mTaskPhotoList.set(position, photoItemInfoTemp);
                            ElectricApplication.BUS.post(new RefreshPhotoAdapterEventArgs
                                    (mDataSet.Name));
                            T.showShort(mContext, R.string.photo_delete_succeed);
                        }
                    }
                })
                .setNegativeButton(R.string.dlg_no, null)
                .show();
    }

    public void reSetPhotoPath(String photoPath) {
        for (int i = 0; i < mTaskPhotoList.size(); i++) {
            PhotoItemInfo photoItemInfoTemp = mTaskPhotoList.get(i);
            if (photoItemInfoTemp == null) {
                continue;
            }
            if (photoPath.contains(photoItemInfoTemp.mPhotoType)) {
                PhotoItemInfo newPhotoItemInfo = new PhotoItemInfo();
                newPhotoItemInfo.mPhotoType = photoItemInfoTemp.mPhotoType;
                newPhotoItemInfo.photoPath = photoPath;
                newPhotoItemInfo.noPhoto = false;
                Bitmap bitmap = ElectricBitmapUtils.getCorrectDirectionBitmap(photoPath);
                newPhotoItemInfo.mPhotoImage = new BitmapDrawable(bitmap);
                mTaskPhotoList.set(i, newPhotoItemInfo);
                ElectricApplication.BUS.post(new RefreshPhotoAdapterEventArgs(mDataSet.Name));
                break;
            }
        }
    }

    /**
     * 导入图片的操作
     *
     * @param position 导入图片在gridview的位置
     */
    public void doImportPhotoOperate(int position) {
        PhotoItemInfo photoItemInfoTemp = mTaskPhotoList.get(position);
        if (photoItemInfoTemp == null) {
            return;
        }
        if (photoItemInfoTemp.noPhoto) {
            openImportPhotoActivity(position);
        }
    }

    private void openImportPhotoActivity(int position) {
        TaskInfo taskInfo = mTaskManager.getTaskInfo();
        if (taskInfo == null) {
            return;
        }
        String taskPath = ConstPath.getTaskRootFolder() + taskInfo.getTaskName();
        String photoCachePath = taskPath + File.separator + Constants.TASK_PHOTO_FOLDER + Constants.TASK_CACHE_PATH;
        File photoCacheFile = new File(photoCachePath);
        if (!FileUtils.existFile(photoCachePath)) {
            photoCacheFile.mkdirs();
        }
        if (mTaskPhotoList.get(position) == null) {
            return;
        }
        String photoCacheName = mTaskPhotoList.get(position).mPhotoType + "_"
                + mDataSet.Alias + Constants.PHOTO_SUFFIX;
        File file = new File(photoCacheFile, photoCacheName);
        ElectricApplication.BUS.post(new OpenSystemImportePhotoEventArgs(file, mDataSet.Name));
    }

    public class PhotoItemInfo {
        public String mPhotoType = "";
        public Drawable mPhotoImage = null;
        public String photoPath = "";
        public boolean noPhoto = true;
    }
}
