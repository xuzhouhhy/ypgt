package com.geocraft.electrics.sr.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.event.OpenSystemTakePhotoEventArgs;
import com.geocraft.electrics.event.RefreshPhotoAdapterEventArgs;
import com.geocraft.electrics.sr.UtilFile;
import com.geocraft.electrics.sr.activity.WellActivity;
import com.geocraft.electrics.sr.controller.SrPhotoManagerController;
import com.geocraft.electrics.sr.event.CopyImportFileFinishedEvent;
import com.geocraft.electrics.sr.event.OpenSystemImportePhotoEventArgs;
import com.geocraft.electrics.sr.view.SrPhotoManagerItemView;
import com.geocraft.electrics.sr.view.SrPhotoManagerItemView_;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.List;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/7.
 */
@EFragment(R.layout.fragment_photo_manager)
public class SrPhotoManagerFragment extends WellBaseFragment {

    PhotoManagerAdapter mAdapter;
    @Bean
    SrPhotoManagerController mController;
    @ViewById
    GridView gridViewPhotoList;
    PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener =
            new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int position = item.getIntent().getIntExtra(
                            Constants.INTENT_DATA_PHOTO_ITEM_POSITION, -1);
                    switch (item.getItemId()) {
                        case R.id.menu_photo_retake: {
                            mController.reTakePhoto(position);
                            break;
                        }
                        case R.id.menu_photo_delete: {
                            mController.showPhotoItemDeleteTipDialog(position);
                            break;
                        }
                    }
                    return false;
                }
            };
    AdapterView.OnItemLongClickListener mOnItemLongClickListener =
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    SrPhotoManagerController.PhotoItemInfo photoItemInfoTemp =
                            mController.getTaskPhotoList().get(position);
                    if (photoItemInfoTemp != null && !photoItemInfoTemp.noPhoto) {
                        showPhotoPopupMenu(SrPhotoManagerFragment.this.getActivity(), view, position);
                    }
                    return true;
                }
            };
    private String photoPath;

    /**
     * 拍照点击事件
     */
    private View.OnClickListener mOnClickEffectiveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SrPhotoManagerItemView.ViewHolder viewHolder = (SrPhotoManagerItemView.ViewHolder) view.getTag();
            int position = viewHolder.getPosition();
            switch (view.getId()) {
                case R.id.btnTakePhoto:
                    mController.doTakePhotoOperate(position);
                    break;
                case R.id.btnImportPhoto:
                    mController.doImportPhotoOperate(position);
                    break;
                case R.id.btnDeletePhoto:
                    mController.showPhotoItemDeleteTipDialog(position);
                    break;
                case R.id.imgVPhoto:
                    mController.doPreviewPhotoOperate(position);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void init() {
        ElectricApplication.BUS.register(this);
        mActivity = ((WellActivity) this.getActivity());
        mDataSet = mActivity.getController().getCurrentDataSet();
        mIsNew = mActivity.getController().isCreateRecord();
        mController.initParams(this.getContext(), mIsNew, mDataSet);
        mController.initTaskPhotoList();
        mAdapter = new PhotoManagerAdapter();
        gridViewPhotoList.setAdapter(mAdapter);
        gridViewPhotoList.setOnItemLongClickListener(mOnItemLongClickListener);
    }


    @Subscribe
    public void onEventMainThread(RefreshPhotoAdapterEventArgs args) {
        try {
            if (args.getCurrentDataSetName().equals(mController.getCurrentDataSet().Name)) {
                photoPath = "";
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    @Subscribe
    public void onEventMainThread(OpenSystemTakePhotoEventArgs args) {
        try {
            if (args.getCurrentDataSetName().equals(mController.getCurrentDataSet().Name)) {
                openSystemTakePhotoActivity(args.getFile());
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onImportPhoto(OpenSystemImportePhotoEventArgs args) {
        try {
            if (args.getCurrentDataSetName().equals(mController.getCurrentDataSet().Name)) {
                openSystemImportPhotoActivity(args.getFile());
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCopyImportPhotoFinish(CopyImportFileFinishedEvent args) {
        try {
            mController.reSetPhotoPath(photoPath);
        } catch (Exception e) {
            L.printException(e);
        }
    }

    private void openSystemImportPhotoActivity(File file) {
        photoPath = file.getPath();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, ConstRequestCode.REQUEST_CODE_IMPORT_PHOTO);
    }

    private void showPhotoPopupMenu(Context context, View view, int position) {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_photo_item_expand, popup.getMenu());
        popup.setOnMenuItemClickListener(mOnMenuItemClickListener);

        Intent intent = new Intent();
        intent.putExtra(Constants.INTENT_DATA_PHOTO_ITEM_POSITION, position);
        popup.getMenu().getItem(0).setIntent(intent);
        popup.getMenu().getItem(1).setIntent(intent);
        popup.show();
    }

    private void openSystemTakePhotoActivity(File file) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (this.getContext().getPackageManager().resolveActivity(intent, 0) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            photoPath = file.getPath();
            startActivityForResult(intent, ConstRequestCode.REQUEST_CODE_TAKE_PHOTO);
        } else {
            T.showShort(this.getContext(), R.string.open_system_take_photo_exception);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ConstRequestCode.REQUEST_CODE_TAKE_PHOTO: {
                if (resultCode == Activity.RESULT_OK) {
                    mController.reSetPhotoPath(photoPath);
                }
            }
            break;
            case ConstRequestCode.REQUEST_CODE_IMPORT_PHOTO: {
                if (resultCode == Activity.RESULT_OK) {
                    String srcPhotoPath = getSelectedMediaFilePath(data);
                    startCopyFileThread(srcPhotoPath);
                }
            }
            break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startCopyFileThread(final String srcPhotoPath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtilFile.copyFile(srcPhotoPath, photoPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ElectricApplication.BUS.post(new CopyImportFileFinishedEvent());
            }
        }).run();

    }

    private String getSelectedMediaFilePath(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        String picturePath = null;
        try {
            cursor = getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);

            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return picturePath;
    }

    public List<SrPhotoManagerController.PhotoItemInfo> getTaskPhotoList() {
        return mController.getTaskPhotoList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ElectricApplication.BUS.unregister(this);
    }

    @Override
    public void getValue(DataSet dataSet) {
        mController.saveValue();
    }

    class PhotoManagerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mController.getTaskPhotoList().size();
        }

        @Override
        public Object getItem(int position) {
            return mController.getTaskPhotoList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SrPhotoManagerItemView photoManagerItemView;
            if (convertView == null) {
                photoManagerItemView = SrPhotoManagerItemView_.build(parent.getContext());
            } else {
                photoManagerItemView = (SrPhotoManagerItemView) convertView;
            }

            photoManagerItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
            SrPhotoManagerController.PhotoItemInfo photoItemInfoTemp =
                    mController.getTaskPhotoList().get(position);
            if (photoItemInfoTemp != null) {
                photoManagerItemView.setPhotoType(photoItemInfoTemp.mPhotoType);
                photoManagerItemView.setImgVPhoto(photoItemInfoTemp.mPhotoImage);
                photoManagerItemView.setButtonOnClickListener(mOnClickEffectiveListener, position);
            }
            return photoManagerItemView;
        }
    }
}
