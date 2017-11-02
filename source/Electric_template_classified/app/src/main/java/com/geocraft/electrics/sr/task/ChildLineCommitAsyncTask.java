package com.geocraft.electrics.sr.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.sr.activity.AddChildLineActivity;
import com.geocraft.electrics.sr.controller.AddChildLineController;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 * 提交子线路异步任务
 */
public class ChildLineCommitAsyncTask extends AsyncTask<AddChildLineController, Integer, Boolean> {


    private Context mContext;
    private AddChildLineController mController;
    private ProgressDialog mProgressDialog;

    public ChildLineCommitAsyncTask(Context context, AddChildLineController controller) {
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
    protected Boolean doInBackground(AddChildLineController... params) {
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
            if (aBoolean) {
                Activity activity = ((AddChildLineActivity) mContext);
                Intent intent = new Intent();
                intent.putExtra(Constants.INTENT_DATA_IS_REFRESH_DATA_SET_LIST, true);
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();
                T.showShort(mContext, R.string.save_data_set_succeed);
            } else {
                T.showShort(mContext, R.string.save_data_set_failed);
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

}
