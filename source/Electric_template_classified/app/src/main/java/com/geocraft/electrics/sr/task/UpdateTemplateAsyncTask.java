package com.geocraft.electrics.sr.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.geocraft.electrics.R;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.huace.log.logger.L;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/10.
 */
public class UpdateTemplateAsyncTask extends AsyncTask<String, Integer, Boolean> {

    private Context mContext;
    private ProgressDialog mProgressDialog;

    private DataSet mDataSet;
    private FieldInfo mFieldInfo;

    public UpdateTemplateAsyncTask(Context context, DataSet dataSet, FieldInfo fieldInfo) {
        super();
        this.mContext = context;
        mDataSet = dataSet;
        mFieldInfo = fieldInfo;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = ProgressDialog.show(mContext,
                mContext.getString(R.string.dlg_tip),
                mContext.getString(R.string.update_template_file),
                false);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            TaskManager taskManager = TaskManager_.getInstance_(mContext);
            taskManager.writeMenuList(mDataSet, mFieldInfo, params[0]);
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
                T.showShort(mContext, R.string.update_template_file_fail);
            } else {
                T.showShort(mContext, R.string.update_template_file_success);
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

}
