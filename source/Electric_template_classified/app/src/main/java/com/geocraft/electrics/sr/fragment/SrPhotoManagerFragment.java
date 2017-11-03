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
import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 照片管理功能
 */
@EFragment(R.layout.fragment_photo_manager)
public class SrPhotoManagerFragment extends WellBaseFragment {

    PhotoManagerAdapter mAdapter;
    @Bean
    SrPhotoManagerController mController;
    @ViewById
    GridView gridViewPhotoList;
    private List<String> mTagList = new ArrayList<String>();
    private String mPhotoPath;

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
    public void getValue(DataSet dataSet) {
        mController.saveData();
    }

    @Override
    protected void init() {
        if (!ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.register(this);
        }
        mController.initParams(this.getContext(),
                ((WellActivity) this.getContext()).getController().getCurrentDataSet(), mTagList);
        mController.initTaskPhotoList();
        mAdapter = new PhotoManagerAdapter();
        gridViewPhotoList.setAdapter(mAdapter);
        gridViewPhotoList.setOnItemLongClickListener(mOnItemLongClickListener);
    }

    /**
     * 设置需要显示的字段对象标签
     *
     * @param tagList 模板配置需要显示photo type属性
     */
    public void setTags(List<String> tagList) {
        mTagList = tagList;
    }


    @Subscribe
    public void onEventMainThread(RefreshPhotoAdapterEventArgs args) {
        try {
            if (args.getCurrentDataSetName().equals(mController.getCurrentDataSet().Name)) {
                mPhotoPath = "";
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
            mController.reSetPhotoPath(mPhotoPath);
        } catch (Exception e) {
            L.printException(e);
        }
    }

    private void openSystemImportPhotoActivity(File file) {
        mPhotoPath = file.getPath();
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
            mPhotoPath = file.getPath();
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
                    mController.reSetPhotoPath(mPhotoPath);
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
                    UtilFile.copyFile(srcPhotoPath, mPhotoPath);
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
        if (ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.unregister(this);
        }
        super.onDestroyView();
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
