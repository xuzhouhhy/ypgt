package com.geocraft.electrics.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.ui.activity.CommonListActivity_;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity_;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.controller.RecordController;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 */
public class RecordCommitAsyncTask extends AsyncTask<RecordController, Integer, Boolean> {


    Context mContext;
    RecordController mController;
    ProgressDialog mProgressDialog;

    public RecordCommitAsyncTask(Context context, RecordController controller) {
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
    protected Boolean doInBackground(RecordController... params) {
        try {
            return mController.saveRecord();
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
        if (mController.isEditParent()) {
            Intent intent = new Intent(mContext, CommonListActivity_.class);
            ((RecordActivity) mContext).setResult(
                    ConstRequestCode.REQUEST_CODE_EDIT_PARENT_DATA_SET, intent);
            ((RecordActivity) mContext).finish();
        } else {
            Intent intent = new Intent(mContext, DeviceShowListActivity_.class);
            intent.putExtra(Constants.INTENT_DATA_IS_REFRESH_DATA_SET_LIST, true);
            ((RecordActivity) mContext).setResult(
                    ConstRequestCode.REQUEST_CODE_OPEN_RECORDACTIVITY, intent);
        /*	if(!mController.isShowContinueDialog(mContext)) {
            }*/
            ((RecordActivity) mContext).finish();
        }
    }

}
