package com.geocraft.electrics.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity;
import com.geocraft.electrics.ui.controller.DeviceShowListController;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/10.
 */
public class InitDeviceListAsyncTask extends AsyncTask<DeviceShowListController, Integer, Boolean> {

	Context mContext;
	DeviceShowListController mController;
	ProgressDialog mProgressDialog;

	public InitDeviceListAsyncTask(Context context, DeviceShowListController controller) {
		super();
		this.mContext = context;
		this.mController = controller;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog = ProgressDialog.show(mContext,
				mContext.getString(R.string.dlg_tip),
				mContext.getString(R.string.init_device_show_list_loading),
				false);
	}

	@Override
	protected Boolean doInBackground(DeviceShowListController... params) {
		try {
			mController.initDataFromDB();
			return true;
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}


	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Boolean aBoolean) {
		try {
			mProgressDialog.dismiss();
			if (!aBoolean) {
				T.showShort(mContext, R.string.get_data_null);
			} else {
				((DeviceShowListActivity) mContext).refreshListView(0);
			}
		} catch (Exception e) {
			L.printException(e);
		}
	}

}
