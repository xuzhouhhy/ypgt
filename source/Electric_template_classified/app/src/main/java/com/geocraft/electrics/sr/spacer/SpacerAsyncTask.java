package com.geocraft.electrics.sr.spacer;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.sr.activity.TowerShowListActivity;
import com.geocraft.electrics.sr.controller.TowerShowListController;
import com.geocraft.electrics.sr.spacer.SpacerController;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 * 间隔
 */
public class SpacerAsyncTask extends AsyncTask<SpacerController, Integer, Boolean> {

    private Context mContext;
    SpacerController mController;
    ProgressDialog mProgressDialog;

    public SpacerAsyncTask(Context context, SpacerController controller) {
        super();
        this.mContext = context;
        this.mController = controller;
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
    protected Boolean doInBackground(SpacerController... params) {
        try {
            mController.initDataFromDB();
            return true;
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
            if (!aBoolean) {
                T.showShort(mContext, R.string.get_data_null);
            } else {
                ElectricApplication.BUS.post(new SpacerRefreshEvent());
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

}
