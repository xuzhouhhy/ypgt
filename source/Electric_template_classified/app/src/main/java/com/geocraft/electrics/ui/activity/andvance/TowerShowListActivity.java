package com.geocraft.electrics.ui.activity.andvance;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenu;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuCreator;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuItem;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuListView;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.DensityUtils;
import common.geocraft.untiltools.T;

/**
 */
@OptionsMenu(R.menu.menu_task_manager)
@EActivity(R.layout.activity_device_show_list)
public class TowerShowListActivity extends BaseActivity implements
        AdapterView.OnItemClickListener {

    @ViewById
    protected SwipeMenuListView listViewCommon;
    protected TowerShowListAdapter mAdapter;
    @Bean
    TowerShowListController mController;
    @ViewById
    EditText etSearch;
    @ViewById
    ImageView ivDeleteText;
    public TextWatcher SearchWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                ivDeleteText.setVisibility(View.GONE);
            } else {
                ivDeleteText.setVisibility(View.VISIBLE);
            }
            mController.doSomethingInThread(etSearch.getText().toString());
        }
    };
    SwipeMenuCreator menuCreator = new SwipeMenuCreator() {
        @Override
        public void create(SwipeMenu menu) {
            try {
                SwipeMenuItem item = new SwipeMenuItem(TowerShowListActivity.this);
                item.setBackground(R.color.red);
                item.setWidth(DensityUtils.dp2px(TowerShowListActivity.this,
                        getResources().getDimension(R.dimen.list_view_menu_width)));
                item.setIcon(R.mipmap.btn_project_delete_normal);
                menu.addMenuItem(item);
            } catch (Exception e) {
                L.printException(e);
            }
        }
    };

    private SwipeMenuListView.OnMenuItemClickListener mOnMenuItemClickListener =
            new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    try {
                        mController.showRemoveTargetRecordDialog(TowerShowListActivity.this, position);
                    } catch (Exception e) {
                        L.printException(e);
                        T.showShort(TowerShowListActivity.this, R.string.toast_file_not_found + "");
                    }
                    listViewCommon.setAdapter(mAdapter);
                    return false;
                }
            };

    public TowerShowListAdapter getAdapter() {
        return mAdapter;
    }

    @AfterViews
    void init() {
        mController.initCollectTypeList();
        listViewCommon.setOnItemClickListener(this);
        listViewCommon.setMenuCreator(menuCreator);
        listViewCommon.setOnMenuItemClickListener(mOnMenuItemClickListener);
        mAdapter = new TowerShowListAdapter(this, mController);
        listViewCommon.setAdapter(mAdapter);
        InitTowerListAsyncTask initDeviceListAsyncTask = new InitTowerListAsyncTask(this,
                mController);
        initDeviceListAsyncTask.execute(mController);
        etSearch.addTextChangedListener(SearchWatcher);
        //模拟电杆等数据
        List<DataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataSet dataSet = new DataSet();
            dataSet.First = "first" + i;
            dataSet.Second = "second" + i;
            dataSet.Third = "third" + i;
            dataSet.setmIsShowInDeviceList(true);
            dataSets.add(dataSet);
        }
        mController.refreshList(dataSets);
    }

    @OptionsItem
    void actionNewProject() {
        mController.openRecordActivityToAdd(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mController.hasChildDataSets()) {
            mController.openChildDataSetActivity(TowerShowListActivity.this, position);
        } else {
            mController.openRecordActivityToChange(TowerShowListActivity.this, position);
        }
    }

    @Override
    protected void onDestroy() {
        mController.clearDataSetList();
        super.onDestroy();
    }

    public void refreshListView(int position) {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
            int listCount = mController.getItems().size();
            if (position <= 0) {
                return;
            }
            if (position < listCount) {
                listViewCommon.setSelection(position);
            } else {
                listViewCommon.setSelection(listCount - 1);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstRequestCode.REQUEST_CODE_EDIT_PARENT_DATA_SET: {
                L.i("onActivityResult:DeviceList");
                InitTowerListAsyncTask initDeviceListAsyncTask = new InitTowerListAsyncTask(this,
                        mController);
                initDeviceListAsyncTask.execute(mController);
            }
            break;
            case ConstRequestCode.REQUEST_CODE_OPEN_RECORDACTIVITY: {
                if (data == null) {
                    return;
                }
                boolean isRefresh = data.getBooleanExtra(Constants
                        .INTENT_DATA_IS_REFRESH_DATA_SET_LIST, false);
                if (isRefresh) {
                    try {
                        InitTowerListAsyncTask initDeviceListAsyncTask = new InitTowerListAsyncTask(this,
                                mController);
                        initDeviceListAsyncTask.execute(mController);
                    } catch (Exception e) {
                        L.printException(e);
                    }
                }
                break;
            }
            default:
                break;
        }
    }

    @Click
    void ivDeleteText() {
        etSearch.setText("");
    }

}
