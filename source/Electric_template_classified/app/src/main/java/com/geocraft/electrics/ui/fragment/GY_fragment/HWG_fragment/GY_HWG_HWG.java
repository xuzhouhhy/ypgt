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

    private Dialog mDialog;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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
    protected void init() {
        mLinearLayout = linearLayoutRoot;
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
