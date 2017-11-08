package com.geocraft.electrics.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.ui.adapter.CollectTypeAdapter;
import com.geocraft.electrics.ui.controller.CollectTypeController;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Administrator on 2016/6/4.
 */
@EActivity(R.layout.activity_collect_type)
public class CollectTypeActivity extends BaseActivity {

    CollectTypeAdapter mAdapter;

    @ViewById
    GridView gridViewCollectType;

    @ViewById
    TextView tvRegister;

    @Bean
    CollectTypeController mController;
    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mController.openTargetActivity(CollectTypeActivity.this, position);
        }
    };
    private boolean mIsInit;

    @AfterViews
    void init() {
        mIsInit = true;
        hideHomeButton();
        gridViewCollectType.setOnItemClickListener(mOnItemClickListener);
        mAdapter = new CollectTypeAdapter(CollectTypeActivity.this, mController);
        gridViewCollectType.setAdapter(mAdapter);
        mController.showRegisterHint(this);
        tvRegister.setText(ElectricApplication.getVersionName(this));
    }

    public void refreshCollectTypeListView() {
        try {
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mController.setActionBatTitle(this);
        if (mIsInit) {
            mIsInit = false;
        }else {
            if (mController.isDataSourceNull()) {
                mController.initCollectTypeList();
                refreshCollectTypeListView();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstRequestCode.REQUEST_CODE_OPEN_TASK_MANAGER: {
                if (data == null) {
                    return;
                }
                boolean isNew =
                        data.getBooleanExtra(Constants.INTENT_DATA_TASK_MANAGER_RESULT, false);
                if (isNew) {
                    mController.initCollectTypeList();
                    mAdapter.notifyDataSetChanged();
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_tip);
        builder.setIcon(R.mipmap.ic_tip);
        builder.setMessage(R.string.app_alert_exit_application);
        builder.setNegativeButton(R.string.app_no, null);
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton(R.string.app_yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON1) {
                    // 退出应用程序
                    android.os.Process.killProcess(android.os.Process.myPid());
                } else {
                    return;
                }
            }
        }).show();
    }

    public CollectTypeAdapter getAdapter() {
        return mAdapter;
    }
}
