package com.geocraft.electrics.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.manager.TaskExistException;
import com.geocraft.electrics.ui.controller.TaskManageController;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/10.
 */
public class OpenTaskAsyncTask extends AsyncTask<TaskManageController, Integer, Boolean> {

	Context mContext;
	TaskManageController mController;
	ProgressDialog mProgressDialog;
	String mExceptionMsg = "";
	String mTaskName = "";

	public OpenTaskAsyncTask(Context context, TaskManageController controller, String taskName) {
		super();
		this.mContext = context;
		this.mController = controller;
		mExceptionMsg = "";
		mTaskName = taskName;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog = ProgressDialog.show(mContext,
				mContext.getString(R.string.dlg_tip),
				mContext.getString(R.string.open_new_task_loading),
				false);
	}

	@Override
	protected Boolean doInBackground(TaskManageController... params) {
		try {
			return mController.openTargetTask(mTaskName);
		} catch (TaskExistException e) {
			L.printException(e);
			mExceptionMsg = mContext.getString(R.string.target_task_file_not_found);
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
			if (aBoolean) {
				T.showShort(mContext, R.string.open_task_succeed);
				mController.operateWhenFinish(mContext, true);
			} else {
				if(mExceptionMsg.length() > 0){
					T.showShort(mContext, mExceptionMsg);
				}else{
					T.showShort(mContext, R.string.open_target_task_failed);
				}
			}
		} catch (Exception e) {
			mProgressDialog.dismiss();
			L.printException(e);
		}
	}
}
