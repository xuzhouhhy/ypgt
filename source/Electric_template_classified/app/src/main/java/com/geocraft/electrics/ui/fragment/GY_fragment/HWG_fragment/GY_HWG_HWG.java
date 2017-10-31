package com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.fragment.WellBaseFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_gy__hwg__hwg)
public class GY_HWG_HWG extends WellBaseFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @ViewById
    Button btnGyHwgInterval;

    private Dialog mDialog;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnGyHwgInterval:
                    onGyHwgIntervalDetail();
                    break;
                case R.id.btnYes:
                    onSaveInterval();
                    break;
                case R.id.btnNo:
                    dialogDismiss();
                    break;
                case R.id.btnNext:
                    onNextInterval();
                    break;
                default:
                    break;
            }
        }
    };

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
        button = (Button) dialogView.findViewById(R.id.btnNext);
        button.setOnClickListener(mOnClickListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView).setCancelable(true);
        mDialog = builder.create();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }


//    @ViewById
//    BusinessEditText etMC;
//    @ViewById
//    BusinessEditText etSJDW;
//    @ViewById
//    BusinessManager viewGLDW;

    @Override
    public boolean logicCheck() {
//        //判断该充电桩已存在
//        if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
//            DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
//            List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.DATA_SET_TOWER_ID, etMC.getControlValue(), false);
//            if (dataSetList.size() > 0) {
//                T.show(getContext(), getString(R.string.exist_tower_id), Toast.LENGTH_SHORT);
//                return false;
//            }
//        }

        return true;
    }


    private void dialogDismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    private void onSaveInterval() {

    }

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        btnGyHwgInterval.setOnClickListener(mOnClickListener);
        super.init();
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
