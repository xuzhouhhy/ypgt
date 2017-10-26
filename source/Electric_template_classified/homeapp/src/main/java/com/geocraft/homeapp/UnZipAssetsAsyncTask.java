package com.geocraft.homeapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.huace.log.logger.L;

import java.io.IOException;

import common.geocraft.untiltools.T;

/**
 * 作者  zhouqin
 * 时间 2016/6/13.
 */
public class UnZipAssetsAsyncTask extends AsyncTask<MainController,Integer,Boolean> {

	Context mContext;
	MainController mController;
	ProgressDialog mProgressDialog;
	@Override
	protected Boolean doInBackground(MainController... params) {
		try {
			int versionCode = mController.getVersionCode(mContext);
			AssetsManager.initConfigRes(mContext, versionCode);
			return true;
		} catch (IOException e) {
			L.e(e.getMessage());
			return false;
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog = ProgressDialog.show(mContext,
				mContext.getString(R.string.dlg_tip),
				mContext.getString(R.string.unzipping),
				false);
	}

	public UnZipAssetsAsyncTask(Context context,MainController mainController) {
		mContext = context;
		mController  = mainController;
	}

	@Override
	protected void onPostExecute(Boolean aBoolean) {
		super.onPostExecute(aBoolean);
		try{
			mProgressDialog.dismiss();
			if(!aBoolean){
				T.showShort(mContext, R.string.unzip_failure);
			}else{
				T.showShort(mContext, R.string.unzip_success);
			}
		}catch (Exception e){
			L.printException(e);
		}
	}


}
