package com.geocraft.electrics.ui.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.ui.adapter.ListViewCommonAdapter;
import com.geocraft.electrics.ui.controller.CommonListController;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@EActivity(R.layout.activity_common_list)
public class CommonListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bean
    protected CommonListController mController;

    @ViewById
    protected ListView listViewCommon;

    protected ListViewCommonAdapter mAdapter;


    @AfterViews
    void init() {
        mController.obtainParamFromIntent(this);
        if (mController.ismIsFirstLevel()) {
            if (mController.IsGoToThirdLevel(this)) {
                mController.initThridLevelData(this);
            } else {
                if (!mController.ismIsSecondLevel()) {
                    mController.setFirstType(mController.getTitleFromIntent(this));
                    mController.initGroupItems();
                } else {
                    mController.setSecondType(mController.getSecondTypeFromIntent(this));
                    mController.setLineKey(mController.getLineKeyFromIntent(this));
                    mController.setFirstType(mController.getTitleFromIntent(this));
                    mController.initChildDataSetItems();
                }
            }
        }
        setTitle(mController.getTitle());
        mAdapter = new ListViewCommonAdapter(mController);
        listViewCommon.setAdapter(mAdapter);
        listViewCommon.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mController.openDeviceShowListActivity(parent, view, position);
    }

    @OptionsItem
    void actionEdit() {
        mController.openParentRecordActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_edit, menu);
        MenuItem actionSettings = menu.findItem(R.id.actionEdit);
        if (!mController.ismIsSecondLevel()) {
            actionSettings.setVisible(false);
        } else {
            actionSettings.setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConstRequestCode.REQUEST_CODE_EDIT_PARENT_DATA_SET) {
            if (resultCode == ConstRequestCode.REQUEST_CODE_EDIT_PARENT_DATA_SET) {
                if (!mController.getSecondType().isEmpty()) {
                    setResult(ConstRequestCode.REQUEST_CODE_EDIT_PARENT_DATA_SET);
                }
            }
        }
    }
}
