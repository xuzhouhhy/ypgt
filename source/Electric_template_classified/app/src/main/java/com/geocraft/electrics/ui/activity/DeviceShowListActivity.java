package com.geocraft.electrics.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.event.GaoyaLineRefreshEvent;
import com.geocraft.electrics.event.SCanBarEvent;
import com.geocraft.electrics.task.InitDeviceListAsyncTask;
import com.geocraft.electrics.ui.absinterface.IButtonClickCallBack;
import com.geocraft.electrics.ui.activity.andvance.TowerShowListActivity_;
import com.geocraft.electrics.ui.adapter.DeviceShowListAdapter;
import com.geocraft.electrics.ui.controller.DeviceShowListController;
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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import common.geocraft.untiltools.DensityUtils;
import common.geocraft.untiltools.T;

/**
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@OptionsMenu(R.menu.menu_task_manager)
@EActivity(R.layout.activity_device_show_list)
public class DeviceShowListActivity extends BaseActivity implements
        AdapterView.OnItemClickListener, IButtonClickCallBack {

    @ViewById
    protected SwipeMenuListView listViewCommon;
    protected DeviceShowListAdapter mAdapter;
    @Bean
    DeviceShowListController mController;
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
                SwipeMenuItem item = new SwipeMenuItem(DeviceShowListActivity.this);
                item.setBackground(R.color.red);
                item.setWidth(DensityUtils.dp2px(DeviceShowListActivity.this,
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
                        mController.showRemoveTargetRecordDialog(DeviceShowListActivity.this, position);
                    } catch (Exception e) {
                        L.printException(e);
                        T.showShort(DeviceShowListActivity.this, R.string.toast_file_not_found + "");
                    }
                    listViewCommon.setAdapter(mAdapter);
                    return false;
                }
            };

    public DeviceShowListAdapter getAdapter() {
        return mAdapter;
    }

    @AfterViews
    void init() {
        if (!ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.register(this);
        }
        mController.initScanFilter(this);
        mController.initCollectTypeList();
        listViewCommon.setOnItemClickListener(this);
        listViewCommon.setMenuCreator(menuCreator);

        listViewCommon.setOnMenuItemClickListener(mOnMenuItemClickListener);
        if (mController.isPassedFirstType(this)) {
            mController.setFirstType(mController.getFirstTypeFromIntent(this));
        }
        if (mController.isPassedSecondType(this)) {
            mController.setSecondType(mController.getTitleFromIntent(this));
            if (mController.isPassedQueryValue(this)) {
                L.i("isPassedQueryValue= true" +
                        mController.getQueryValueFromIntent(this));
                mController.setQueryValue(mController.getQueryValueFromIntent(this));
            }
            if (mController.isPassedParentKey(this)) {
                mController.setParentKey(mController.getParentKeyFromIntent(this));
            }
            setTitle(mController.getDataSetAlias());
            mAdapter = new DeviceShowListAdapter(this, mController);
            listViewCommon.setAdapter(mAdapter);
            InitDeviceListAsyncTask initDeviceListAsyncTask = new InitDeviceListAsyncTask(this,
                    mController);
            initDeviceListAsyncTask.execute(mController);
            etSearch.addTextChangedListener(SearchWatcher);
        }
    }

    @OptionsItem
    void actionNewProject() {
        mController.openRecordActivityToAdd(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mController.hasChildDataSets()) {
            mController.openChildDataSetActivity(DeviceShowListActivity.this, position);
        } else {
            mController.openRecordActivityToChange(DeviceShowListActivity.this, position);
        }
    }

    @Override
    protected void onDestroy() {
        mController.clearDataSetList();
        super.onDestroy();
        mController.unregisterScanReceiver(this);
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
                InitDeviceListAsyncTask initDeviceListAsyncTask = new InitDeviceListAsyncTask(this,
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
                        InitDeviceListAsyncTask initDeviceListAsyncTask = new InitDeviceListAsyncTask(this,
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

    @Subscribe
    public void onEventMainThread(SCanBarEvent event) {
        if (event != null) {
            String barCode = event.Msg;
            if (!barCode.isEmpty()) {
                mController.scanSelect(barCode, this);
            }
        }
    }

    @Override
    public void openActivity(String nameValue) {
        DeviceShowListController.CollectTypeItem collectTypeItem = mController.
                getCollectType();
        if (null == collectTypeItem) {
            return;
        }
        if (collectTypeItem.mItemName.equals(Enum.GYCJ)) {
            openTowerShowListActivity(nameValue);
        } else {
            openCommonListActivity(collectTypeItem, nameValue);
        }
    }

    private void openCommonListActivity(DeviceShowListController.CollectTypeItem collectTypeItem,
                                        String nameValue) {
        Intent intent = new Intent(this, CommonListActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, collectTypeItem.mItemName);
        intent.putExtra(Constants.INTENT_DATA_LINE_NAMES, nameValue);
        this.startActivity(intent);
    }

    public void openTowerShowListActivity(String nameValue) {
        Intent intent = new Intent(this, TowerShowListActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_LINE_NAMES, nameValue);
        this.startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onDataSynEvent(GaoyaLineRefreshEvent event) {
        //参数-1是为了没有给选中项添加阴影
        refreshListView(-1);
    }
}
