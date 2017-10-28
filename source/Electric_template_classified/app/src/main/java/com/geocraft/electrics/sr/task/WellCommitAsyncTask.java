package com.geocraft.electrics.sr.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.sr.WellActivity;
import com.geocraft.electrics.sr.WellController;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 */
public class WellCommitAsyncTask extends AsyncTask<WellController, Integer, Boolean> {


	Context mContext;
	WellController mController;
	ProgressDialog mProgressDialog;

	public WellCommitAsyncTask(Context context, WellController controller) {
		super();
		this.mContext = context;
		this.mController = controller;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mProgressDialog = ProgressDialog.show(mContext,
				mContext.getString(R.string.dlg_tip),
				mContext.getString(R.string.save_record_info_loading),
				false);
	}

	@Override
	protected Boolean doInBackground(WellController... params) {
		try {
			return mController.saveRecord(((WellActivity) mContext).getPhotoInfoList());
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
			if (mController.isCreateRecord()) {
				if (aBoolean) {
					finishAndBackResult();
					T.showShort(mContext, R.string.save_data_set_succeed);
				} else {
					T.showShort(mContext, R.string.save_data_set_failed);
				}
			} else {
				if (aBoolean) {
					finishAndBackResult();
					T.showShort(mContext, R.string.change_data_set_succeed);
				} else {
					T.showShort(mContext, R.string.change_data_set_failed);
				}
			}
		} catch (Exception e) {
			L.printException(e);
		}
	}

	public void finishAndBackResult() {
//		if (mController.isEditParent()) {
//			Intent intent = new Intent(mContext, CommonListActivity_.class);
//			((RecordActivity) mContext).setResult(
//					ConstRequestCode.REQUEST_CODE_EDIT_PARENT_DATA_SET, intent);
//			((RecordActivity) mContext).finish();
//		} else {
//			Intent intent = new Intent(mContext, DeviceShowListActivity_.class);
//			intent.putExtra(Constants.INTENT_DATA_IS_REFRESH_DATA_SET_LIST, true);
//			((RecordActivity) mContext).setResult(
//					ConstRequestCode.REQUEST_CODE_OPEN_RECORDACTIVITY, intent);
//		/*	if(!mController.isShowContinueDialog(mContext)) {
//			}*/
//			((RecordActivity) mContext).finish();
//		}
	}

}
