package com.geocraft.electrics.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.controller.RecordController;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/10.
 */
public class InitRecordInfoAsyncTask extends AsyncTask<RecordController, Integer, Boolean> {

	Context mContext;
	RecordController mController;
	ProgressDialog mProgressDialog;

	public InitRecordInfoAsyncTask(Context context, RecordController controller) {
		super();
		this.mContext = context;
		this.mController = controller;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		String message;
		if (mController.isCreateRecord()) {
			message = mContext.getString(R.string.init_record_info_loading);
		} else {
			message = mContext.getString(R.string.load_record_info_loading);
		}
		mProgressDialog = ProgressDialog.show(mContext,
				mContext.getString(R.string.dlg_tip),
				message,
				false);
	}

	@Override
	protected Boolean doInBackground(RecordController... params) {
		try {
			mController.initDataSet();
			((RecordActivity)mContext).initFragment();
			return true;
		} catch (CloneNotSupportedException e) {
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
			if (!aBoolean) {
				String message;
				if (mController.isCreateRecord()) {
					message = mContext.getString(R.string.init_record_info_failed);
				} else {
					message = mContext.getString(R.string.load_record_info_failed);
				}
				mProgressDialog.dismiss();
				T.showShort(mContext, message);
			}else{
				((RecordActivity)mContext).refreshViewPagerRecord();
				((RecordActivity)mContext).initView();
				mProgressDialog.dismiss();
			}
		} catch (Exception e) {
			mProgressDialog.dismiss();
			L.printException(e);
		}
	}

}
