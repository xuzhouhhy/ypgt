package com.geocraft.electrics.sr.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.sr.controller.AddChildLineController;
import com.geocraft.electrics.sr.task.ChildLineCommitAsyncTask;
import com.geocraft.electrics.sr.view.IntervalViewItem;
import com.geocraft.electrics.sr.view.IntervalViewItem_;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessAddress;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessAdministrator;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessCombinationMenu;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessConcatSpinner;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessIDEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessManager;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessScanEdit;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSpinner;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;
import com.geocraft.electrics.ui.view.UserDefineControlView.EditTextDatetimeExpand;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 新建子线路
 * Created by zhongshibu02 on 2017/11/1.
 */
@EActivity(R.layout.activity_child_line)
@OptionsMenu(R.menu.menu_new_task)
public class AddChildLineActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bean
    AddChildLineController mController;
    @ViewById
    LinearLayout linearLayoutRoot;
    @ViewById
    BusinessEditText edtName;
    @ViewById
    BusinessEditText F_LINENUMBER;
    @ViewById
    BusinessEditText F_LINESTART;
    @ViewById
    BusinessEditText F_LINEEND;
    @ViewById
    BusinessConcatSpinner F_LINEZCSX;
    @ViewById
    EditTextDatetimeExpand F_LINETYRQ;
    @ViewById
    BusinessEditText edt_consturction;
    @ViewById
    BusinessEditText F_whbd;
    @ViewById
    BusinessEditText edtDeviceOwer;
    @ViewById
    LinearLayout intervalLl;
    @ViewById
    TextView tvInterval;
    @ViewById
    Button btnChooseInterval;

    private Dialog mDialog;

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnCancel:
                    dismissDialog();
                    break;
                default:
                    break;
            }
        }
    };

    @AfterViews
    void init() {
        mController.initData(getIntent());
        mController.initView(F_LINEZCSX, intervalLl);
    }

    @OptionsItem
    void actionTaskCommit() {
        getValue();
        boolean isContinueCheck = mController.checkDataValidity(this);
        if (isContinueCheck) {
            ChildLineCommitAsyncTask task = new ChildLineCommitAsyncTask(this, mController);
            task.execute(mController);
        }
    }

    @Click
    void btnChooseInterval() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.view_interval_listview, null);
        SwipeMenuListView listViewInterval = (SwipeMenuListView) dialogView.findViewById(R.id.listViewInterval);
        listViewInterval.setOnItemClickListener(this);
        IntervalAdapter mAdapter = new IntervalAdapter();
        listViewInterval.setAdapter(mAdapter);
        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(mListener);
        AlertDialog.Builder builder = new AlertDialog
                .Builder(this)
                .setView(dialogView)
                .setCancelable(false);
        mDialog = builder.create();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.show();
    }

    private void dismissDialog() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    private void getValue() {
        if (linearLayoutRoot != null) {
            List<View> childViewGroup = new ArrayList<>();
            for (int i = 0; i < linearLayoutRoot.getChildCount(); i++) {
                View viewTemp = linearLayoutRoot.getChildAt(i);
                childViewGroup.add(viewTemp);
            }
            List<FieldInfo> fieldInfoList = mController.getDataSet().FieldInfos;
            for (int i = 0; i < fieldInfoList.size(); i++) {
                FieldInfo fieldInfo = fieldInfoList.get(i);
                if (fieldInfo == null) {
                    continue;
                }
                for (View view : childViewGroup) {
                    if (view.getTag() == null) {
                        continue;
                    }
                    if (view.getTag().toString().equalsIgnoreCase(fieldInfo.Alias)) {
                        switch (((DataInterActionInterface) view).geteControlType()) {
                            case PropertyDictionay.OperateCode.Type_BaseText: {
                                fieldInfo.Value = ((BusinessEditText) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_MenuList: {
                                fieldInfo.Value = ((BusinessSpinner) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_Search: {
                                fieldInfo.Value = ((BusinessSearch) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_CombinationMenu: {
                                fieldInfo.Value = ((BusinessCombinationMenu) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_ThreeLevelMenu: {
                                fieldInfo.Value = ((BusinessTreeLevelMenu) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_Scan: {
                                fieldInfo.Value = ((BusinessScanEdit) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_Administrator: {
                                fieldInfo.Value = ((BusinessAdministrator) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_Manager: {
                                fieldInfo.Value = ((BusinessManager) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_Address: {
                                fieldInfo.Value = ((BusinessAddress) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_BusinessID: {
                                fieldInfo.Value = ((BusinessIDEditText) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_dateTime: {
                                fieldInfo.Value = ((EditTextDatetimeExpand) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.type_concat: {
                                fieldInfo.Value = ((BusinessConcatSpinner) view).getControlValue();
                                break;
                            }
                            default:
                                break;
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Pair<String, Integer> pair = mController.getIntervalName().get(position);
        tvInterval.setText(pair.first);
        mController.setIntervalId(pair.second);
        dismissDialog();
    }

    private class IntervalAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mController.getIntervalName().size();
        }

        @Override
        public Object getItem(int i) {
            return mController.getIntervalName().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            IntervalViewItem intervalShowItemView;
            try {
                if (convertView == null) {
                    intervalShowItemView = IntervalViewItem_.build(AddChildLineActivity.this);
                } else {
                    intervalShowItemView = (IntervalViewItem) convertView;
                }
            } catch (Exception ex) {
                intervalShowItemView = IntervalViewItem_.build(AddChildLineActivity.this);
            }

            if (position % 2 != 0) {
                intervalShowItemView.setBackgroundResource(R.drawable.selector_iv_bg_odd);
            } else {
                intervalShowItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
            }
            intervalShowItemView.bind(mController.getIntervalName().get(position).first);
            intervalShowItemView.setSelected(true);
            return intervalShowItemView;
        }
    }
}
