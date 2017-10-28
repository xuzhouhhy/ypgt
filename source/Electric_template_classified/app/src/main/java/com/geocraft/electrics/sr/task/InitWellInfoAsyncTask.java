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
public class InitWellInfoAsyncTask extends AsyncTask<WellController, Integer, Boolean> {

    Context mContext;
    WellController mController;
    ProgressDialog mProgressDialog;

    public InitWellInfoAsyncTask(Context context, WellController controller) {
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
                mContext.getString(R.string.dlg_tip), message, false);
    }

    @Override
    protected Boolean doInBackground(WellController... params) {
        try {
            mController.initDataSet();
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
            } else {
                ((WellActivity) mContext).initView();
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {
            mProgressDialog.dismiss();
            L.printException(e);
        }
    }

}
