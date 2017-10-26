package com.geocraft.electrics.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.geocraft.electrics.base.BaseFragment;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.event.OpenSystemTakePhotoEventArgs;
import com.geocraft.electrics.event.RefreshPhotoAdapterEventArgs;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.controller.PhotoManagerController;
import com.geocraft.electrics.ui.view.PhotoManagerItemView;
import com.geocraft.electrics.ui.view.PhotoManagerItemView_;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/7.
 */
@EFragment(R.layout.fragment_photo_manager)
public class PhotoManagerFragment extends BaseFragment {

	PhotoManagerAdapter mAdapter;
	private String photoPath;

	@Bean
	PhotoManagerController mController;

	@ViewById
	GridView gridViewPhotoList;

	AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			mController.doPhotoOperate(position);
		}
	};

	AdapterView.OnItemLongClickListener mOnItemLongClickListener =
			new AdapterView.OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
					PhotoManagerController.PhotoItemInfo photoItemInfoTemp =
							mController.getTaskPhotoList().get(position);
					if (photoItemInfoTemp != null && !photoItemInfoTemp.noPhoto) {
						showPhotoPopupMenu(PhotoManagerFragment.this.getActivity(), view, position);
					}
					return true;
				}
			};

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

	@AfterViews
	void init() {
		ElectricApplication.BUS.register(this);
		mController.initParams(
				this.getContext(),
				((RecordActivity) this.getContext()).getController().isCreateRecord(),
				((RecordActivity) this.getContext()).getController().getCurrentDataSet());
		mController.initTaskPhotoList();
		mAdapter = new PhotoManagerAdapter();
		gridViewPhotoList.setAdapter(mAdapter);
		gridViewPhotoList.setOnItemClickListener(mOnItemClickListener);
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
			default:
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public List<PhotoManagerController.PhotoItemInfo> getTaskPhotoList() {
		return mController.getTaskPhotoList();
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
			PhotoManagerItemView photoManagerItemView;
			if (convertView == null) {
				photoManagerItemView = PhotoManagerItemView_.build(parent.getContext());
			} else {
				photoManagerItemView = (PhotoManagerItemView) convertView;
			}

			photoManagerItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
			PhotoManagerController.PhotoItemInfo photoItemInfoTemp =
					mController.getTaskPhotoList().get(position);
			if (photoItemInfoTemp != null) {
				photoManagerItemView.setPhotoType(photoItemInfoTemp.mPhotoType);
				photoManagerItemView.setImgVPhoto(photoItemInfoTemp.mPhotoImage);
			}
			return photoManagerItemView;
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ElectricApplication.BUS.unregister(this);
	}
}
