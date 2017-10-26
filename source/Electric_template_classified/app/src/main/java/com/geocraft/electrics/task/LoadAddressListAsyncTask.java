package com.geocraft.electrics.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.ui.fragment.control_fragment.AddressDialogFragment;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/10.
 */
public class LoadAddressListAsyncTask extends AsyncTask<AddressDialogFragment, Integer, Boolean> {

	Context mContext;
	AddressDialogFragment mFragment;
	ProgressDialog mProgressDialog;

	public LoadAddressListAsyncTask(Context context, AddressDialogFragment fragment) {
		super();
		this.mContext = context;
		mFragment = fragment;

	}

	@Override
	protected Boolean doInBackground(AddressDialogFragment... params) {
		try {
			mFragment.initData();
			return true;
		} catch (Exception e) {
			L.printException(e);
			mProgressDialog.dismiss();
			T.showShort(mContext, mContext.getString(R.string.init_address_failure));
			return false;
		}
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
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Boolean aBoolean) {
		try {
			mProgressDialog.dismiss();
			mFragment.show(((Activity) mContext).getFragmentManager(), "");
			if (!aBoolean) {
				T.showShort(mContext, R.string.get_data_null);
			} else {

			}
		} catch (Exception e) {
			L.printException(e);
		}
	}

}
