package com.geocraft.electrics.sr.spacer;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.activity.WellActivity;
import com.geocraft.electrics.sr.controller.WellController;
import com.huace.log.logger.L;

import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 间隔
 */
public class SpacerAsyncTask extends AsyncTask<SpacerController, Integer, Boolean> {

    SpacerController mController;
    ProgressDialog mProgressDialog;
    private Context mContext;

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
            WellController controller = ((WellActivity) mContext).getController();
            List<DataSet> spacerDs = controller.getSpacerDs();
            if (spacerDs.size() > 0) {
                mController.setDataSets(spacerDs);
                return true;
            }
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
