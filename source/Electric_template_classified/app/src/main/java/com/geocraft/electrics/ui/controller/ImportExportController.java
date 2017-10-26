package com.geocraft.electrics.ui.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.ui.fragment.ImportExportResultDialogFragment;
import com.geocraft.electrics.ui.fragment.ImportExportResultDialogFragment_;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by hanpengfei on 2016/6/6.
 */
public class ImportExportController extends AsyncTask<Integer,Integer,String>
{
    protected ArrayList<String> importFiles = null;
    protected ProgressDialog progressDialog;
    protected String progressMsg = "";
    private  Context context;
    protected  DbManager dbManager = DbManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());
    protected  TaskManager taskManager = TaskManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());

    public ImportExportController(Context context,ArrayList<String> filePaths)
    {
        importFiles = filePaths;
        progressDialog = new ProgressDialog(context);
        this.context = context;
    }

    public ImportExportController(Context context)
    {
        progressDialog = new ProgressDialog(context);
        this.context = context;
    }

    @Override
    protected String doInBackground(Integer... params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);// 设置是否可以通过点击Back键取消
        progressDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        progressDialog.setMax(0);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressDialog.dismiss();

        ImportExportResultDialogFragment fragment = new ImportExportResultDialogFragment_();
        fragment.message = progressMsg;
        fragment.show(((Activity)context).getFragmentManager(),"");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.incrementProgressBy(values[0]);
        progressDialog.setMessage(progressMsg);
    }
}
