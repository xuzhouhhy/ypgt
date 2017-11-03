package com.geocraft.electrics.sr.spacer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.sr.activity.WellActivity;
import com.geocraft.electrics.sr.controller.WellController;
import com.geocraft.electrics.sr.fragment.WellBaseFragment;
import com.geocraft.electrics.ui.view.DataValidityInfoView;
import com.geocraft.electrics.ui.view.DataValidityInfoView_;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessConcatSpinner;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenu;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuCreator;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuItem;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuListView;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.DensityUtils;
import common.geocraft.untiltools.T;

@EFragment(R.layout.fragment_gy__hwg__spacer)
public class GY_HWG_spacerFragment extends WellBaseFragment implements
        AdapterView.OnItemClickListener {

    @ViewById
    LinearLayout linearLayoutRoot;
    @ViewById
    protected SwipeMenuListView listViewCommon;
    protected SpacerAdapter mAdapter;
    @ViewById
    Button btnAddSpacer;
    @Bean
    SpacerController mController;
    private WellActivity mActivity;
    private DataSet mCurrentDataSet;
    /**
     * dialog显示的间隔是新建还是编辑
     */
    private boolean mIsNewSpacer;
    /**
     * 当前编辑间隔的位置
     */
    private int mSpacerPosition;
    private SwipeMenuCreator menuCreator = new SwipeMenuCreator() {
        @Override
        public void create(SwipeMenu menu) {
            try {
                SwipeMenuItem item = new SwipeMenuItem(mActivity);
                item.setBackground(R.color.red);
                item.setWidth(DensityUtils.dp2px(mActivity,
                        getResources().getDimension(R.dimen.list_view_menu_width)));
                item.setIcon(R.mipmap.btn_project_delete_normal);
                menu.addMenuItem(item);
            } catch (Exception e) {
                L.printException(e);
            }
        }
    };
    private Dialog mDialog;
    private SwipeMenuListView.OnMenuItemClickListener mOnMenuItemClickListener =
            new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    try {
                        mController.showRemoveTargetRecordDialog(
                                GY_HWG_spacerFragment.this, position);
                    } catch (Exception e) {
                        L.printException(e);
                        T.showShort(GY_HWG_spacerFragment.this.getActivity(),
                                R.string.toast_file_not_found + "");
                    }
                    listViewCommon.setAdapter(mAdapter);
                    return false;
                }
            };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnYes:
                    onSaveAndUpdateSpacer();
                    break;
                case R.id.btnNo:
                    dialogDismiss();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void init() {

        ElectricApplication.BUS.register(this);
        mActivity = ((WellActivity) this.getActivity());
        mIsNew = mActivity.getController().isCreateRecord();
        mDataSet = mActivity.getController().getCurrentDataSet();
        WellController controller = mActivity.getController();
        mCurrentDataSet = mController.getSpacerDataset();

        mController.setLineId(controller.getLineId());
        mController.setWellId(controller.getWellId());
        mController.setWellType(controller.getWellType());

        listViewCommon.setOnItemClickListener(this);
        listViewCommon.setMenuCreator(menuCreator);
        listViewCommon.setOnMenuItemClickListener(mOnMenuItemClickListener);
        mAdapter = new SpacerAdapter(mActivity, mController);
        listViewCommon.setAdapter(mAdapter);
        SpacerAsyncTask task = new SpacerAsyncTask(mActivity,
                mController);
        task.execute(mController);
    }

    public SpacerAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DataSet dataSet = mController.getDataSets().get(position);
        onGyHwgIntervalDetail(dataSet);
        mSpacerPosition = position;
    }

    public void refreshListView(int position) {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
            int listCount = mController.getDataSets().size();
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

    /**
     * 点击环网柜间隔详情按钮
     */
    private void onGyHwgIntervalDetail(DataSet dataSet) {
        mIsNewSpacer = (dataSet == null);
        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.view_gy__hwg__jg_extend, null);
        Button button = (Button) dialogView.findViewById(R.id.btnYes);
        button.setOnClickListener(mOnClickListener);
        button = (Button) dialogView.findViewById(R.id.btnNo);
        button.setOnClickListener(mOnClickListener);
        BusinessConcatSpinner spinner = (BusinessConcatSpinner) dialogView.findViewById(R.id.F_JGDYLX);
        initSpinnerView(spinner);
        spinner = (BusinessConcatSpinner) dialogView.findViewById(R.id.F_JGSBZT);
        initSpinnerView(spinner);
        mLinearLayout = (LinearLayout) dialogView.findViewById(R.id.linearLayoutRoot);
        if (null != dataSet) {
            super.initData(false, dataSet);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView).setCancelable(true);
        mDialog = builder.create();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }


    @Click
    void btnAddSpacer() {
        onGyHwgIntervalDetail(null);
    }

    private void dialogDismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    /**
     * 给spinner初始化数据
     *
     * @param spinner spinner控件
     */
    public void initSpinnerView(BusinessConcatSpinner spinner) {
        List<FieldInfo> fieldInfoList = mCurrentDataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            String tag = spinner.getTag().toString();
            if (null == tag || tag.isEmpty()) {
                return;
            }
            if (tag.equalsIgnoreCase(fieldInfo.Alias)) {
                spinner.setControlValue(fieldInfo, fieldInfo.Default);
                spinner.setData(mCurrentDataSet, fieldInfo);
            }
        }
    }

    private void onSaveAndUpdateSpacer() {
        DataSet dataSet;
        if (mIsNewSpacer) {
            dataSet = mController.getSpacerDataset();
        } else {
            dataSet = mController.getDataSets().get(mSpacerPosition);
        }
        getValue(dataSet);
        boolean isContinueCheck = checkDataValidity(getContext(), dataSet);
        if (!isContinueCheck) {
            return;
        }
        if (mIsNewSpacer) {
            mController.getDataSets().add(dataSet);
        }
        refreshListView(0);
        dialogDismiss();
    }

    public boolean checkDataValidity(Context context, DataSet dataSet) {
        List<String> illegalFieldList = new ArrayList<>();
        List<String> illegalPhotoList = new ArrayList<>();
        illegalFieldList.clear();
        illegalPhotoList.clear();
        List<FieldInfo> fieldInfoList = dataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfoTemp = fieldInfoList.get(i);
            if (fieldInfoTemp == null) {
                continue;
            }
            if (fieldInfoTemp.IsRequired && fieldInfoTemp.Value.length() <= 0) {
                illegalFieldList.add(fieldInfoTemp.Alias);
            }
        }
        if (illegalFieldList.size() > 0 || illegalPhotoList.size() > 0) {
            DataValidityInfoView validityInfoView = DataValidityInfoView_.build(context);
            validityInfoView.setFieldErrorInfo(illegalFieldList);
            validityInfoView.setPhotoErrorInfo(illegalPhotoList);
            new AlertDialog.Builder(context)
                    .setIcon(R.mipmap.ic_warning)
                    .setTitle(R.string.dlg_warning)
                    .setView(validityInfoView)
                    .show();
            return false;
        }
        return true;
    }

    @Override
    public void getValue(DataSet dataSet) {
        super.getValue(dataSet);
    }

    /**
     * 获取当前内存中的间隔dataset
     *
     * @return dataset
     */
    public List<DataSet> getSpacerDatasetList() {
        return mController.getDataSets();
    }

    @Override
    public void onDestroyView() {
        ElectricApplication.BUS.unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onDataSynEvent(SpacerRefreshEvent event) {
        //参数-1是为了没有给选中项添加阴影
        refreshListView(0);
    }

}
