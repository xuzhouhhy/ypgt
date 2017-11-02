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
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.activity.WellActivity;
import com.geocraft.electrics.sr.controller.WellController;
import com.geocraft.electrics.sr.fragment.WellBaseFragment;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenu;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuCreator;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuItem;
import com.geocraft.electrics.ui.view.swipemenulist.SwipeMenuListView;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import common.geocraft.untiltools.DensityUtils;
import common.geocraft.untiltools.T;

@EFragment(R.layout.fragment_gy__hwg__spacer)
public class GY_HWG_spacerFragment extends WellBaseFragment implements
        AdapterView.OnItemClickListener {

    @ViewById
    protected SwipeMenuListView listViewCommon;
    protected SpacerAdapter mAdapter;
    @ViewById
    LinearLayout linearLayoutRoot;
    @Bean
    SpacerController mController;
    private WellActivity mActivity;
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
                                GY_HWG_spacerFragment.this.getActivity(), position);
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
        }
    };


    @Override
    public void init() {
        mLinearLayout = linearLayoutRoot;
        mActivity = ((WellActivity) this.getActivity());
        mIsNew = mActivity.getController().isCreateRecord();
        mDataSet = mActivity.getController().getCurrentDataSet();
        WellController controller = mActivity.getController();

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


    // TODO: 2017/11/1 新增对话框

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO: 2017/11/1 弹出对话框
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
    private void onGyHwgIntervalDetail() {
        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.view_gy__hwg__jg_extend, null);
        Button button = (Button) dialogView.findViewById(R.id.btnYes);
        button.setOnClickListener(mOnClickListener);
        button = (Button) dialogView.findViewById(R.id.btnNo);
        button.setOnClickListener(mOnClickListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView).setCancelable(true);
        mDialog = builder.create();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }


    private void dialogDismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    private void onSaveInterval() {

    }


    @Override
    public void getValue(DataSet dataSet) {

//        if(!viewGLDW.strSecondManager.isEmpty()) {
//            etSJDW.setControlValue(viewGLDW.strSecondManager);
//        }
        super.getValue(dataSet);
    }

    private void onNextInterval() {

    }
}
