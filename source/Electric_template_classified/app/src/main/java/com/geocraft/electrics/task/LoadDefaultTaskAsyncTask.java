package com.geocraft.electrics.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.ui.activity.CollectTypeActivity;
import com.geocraft.electrics.ui.controller.CollectTypeController;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/15.
 */
public class LoadDefaultTaskAsyncTask extends AsyncTask<CollectTypeController, Integer, Boolean> {

	Context mContext;
	CollectTypeController mController;
	ProgressDialog mProgressDialog;

	public LoadDefaultTaskAsyncTask(Context context, CollectTypeController controller) {
		super();
		this.mContext = context;
		this.mController = controller;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog = ProgressDialog.show(
				mContext,
				mContext.getString(R.string.dlg_tip),
				mContext.getString(R.string.load_default_task_loading),
				false
		);
	}

	@Override
	protected Boolean doInBackground(CollectTypeController... params) {
		try{
			return mController.openOrCreateDefaultTask();
		}catch (Exception e) {
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
		try{
			mProgressDialog.dismiss();
			if(aBoolean) {
				((CollectTypeActivity) mContext).refreshCollectTypeListView();
				mController.setActionBatTitle(mContext);
				T.showShort(mContext, R.string.load_default_task_succeed);
			}else{
				T.showShort(mContext, R.string.load_default_task_failed);
			}
		}catch (Exception e){
			mProgressDialog.dismiss();
			L.printException(e);
		}
	}
}
