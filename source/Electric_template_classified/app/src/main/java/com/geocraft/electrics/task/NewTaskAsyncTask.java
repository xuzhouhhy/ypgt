package com.geocraft.electrics.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.ui.controller.NewTaskController;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/10.
 */
public class NewTaskAsyncTask extends AsyncTask<NewTaskController, Integer, Boolean> {

	ProgressDialog mProgressDialog;
	NewTaskController mController;
	Context mContext;
	String message = "";

	public NewTaskAsyncTask(Context context, NewTaskController controller) {
		super();
		this.mContext = context;
		this.mController = controller;
		message = "";
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog = ProgressDialog.show(mContext,
				mContext.getString(R.string.dlg_tip),
				mContext.getString(R.string.create_new_task_loading),
				false);
	}

	@Override
	protected Boolean doInBackground(NewTaskController... params) {
		try {
			return mController.createTask(mContext);
		} catch (Exception e) {
			L.printException(e);
			message = mContext.getString(R.string.msg_task_name_already_exists);
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
			if (aBoolean) {
				mProgressDialog.dismiss();
				T.showShort(mContext, R.string.create_task_succeed);
				mController.operateWhenFinish(mContext, true);
			} else {
				mProgressDialog.dismiss();
				if (message.length() > 0) {
					T.showShort(mContext, message);
				} else {
					T.showShort(mContext, R.string.create_task_failed_tip);
				}
			}
		} catch (Exception e) {
			mProgressDialog.dismiss();
			L.printException(e);
		}
	}

}
