package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.geocraft.electrics.R;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;

import java.util.ArrayList;

/**
 * Created by zhongshibu02 on 2017/10/29.
 */
public class BusinessConcatSpinner extends LinearLayout implements DataInterActionInterface {

    ArrayAdapter<String> dataAdapter;
    Spinner spinner;
    Button button;
    private TaskManager mTaskManager;
    private View mRootView;
    private String mValue;
    private ArrayList<String> mDatalist = new ArrayList<>();
    private DataSet mDataSet;
    private FieldInfo mFieldInfo;
    private Dialog mDialog;
    private EditText mEditText;
    private OnClickListener mListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    onShowDialog();
                    break;
                case R.id.btnYes:
                    onAddSpinnerItem();
                    break;
                case R.id.btnNo:
                    dialogDismiss();
                    break;
                default:
                    break;
            }

        }
    };

    public BusinessConcatSpinner(Context context) {
        super(context);
        init();
    }

    public BusinessConcatSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BusinessConcatSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        Context context = getContext();
        mTaskManager = TaskManager_.getInstance_(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        mRootView = mInflater.inflate(R.layout.view_business_concat_pinner, null);
        addView(mRootView);
        spinner = (Spinner) mRootView.findViewById(R.id.spinner);
        button = (Button) mRootView.findViewById(R.id.button);
        button.setOnClickListener(mListener);
    }

    private void dialogDismiss() {
        if (null != mDialog) {
            mDialog.dismiss();
        }
    }

    private void onShowDialog() {
        Context context = getContext();
        View dialogView = BusinessConcatSpinner.inflate(context, R.layout.view_new_spinner_item, null);
        mEditText = (EditText) dialogView.findViewById(R.id.edtSpinnerItem);
        Button button = (Button) dialogView.findViewById(R.id.btnYes);
        button.setOnClickListener(mListener);
        button = (Button) dialogView.findViewById(R.id.btnNo);
        button.setOnClickListener(mListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView).setCancelable(true);
        mDialog = builder.create();
        mDialog.show();
    }

    private void onAddSpinnerItem() {
        if (null == mEditText || mEditText.toString().isEmpty()) {
            return;
        }
        String text = mEditText.getText().toString();
        mDatalist.add(text);
        dataAdapter.notifyDataSetChanged();
        onAddFieldMenulistToTemplate(text);
        dialogDismiss();
    }

    private void onAddFieldMenulistToTemplate(String text) {
        mTaskManager.writeMenuList(mDataSet,mFieldInfo,text);
    }

    @Override
    public String getControlValue() {
        return spinner.getSelectedItem() != null ? spinner.getSelectedItem().toString() : "";
    }

    @Override
    public void setControlValue(String text) {
        if (mDatalist.contains(text)) {
            spinner.setSelection(mDatalist.indexOf(text));
            mValue = text;
        } else {
            spinner.setSelection(mDatalist.indexOf(""));
        }
    }

    @Override
    public void setControlValue(FieldInfo fieldInfo, String text) {
        mDatalist = fieldInfo.Dictionay.menuList;
        dataAdapter = new
                ArrayAdapter<String>(this.getContext(), R.layout.simple_spinner_item, mDatalist);
        dataAdapter.setDropDownViewResource(R.layout.item_single_choice);
        spinner.setAdapter(dataAdapter);
        setControlValue(text);
    }

    public void setData(DataSet dataSet, FieldInfo fieldInfo) {
        mDataSet = dataSet;
        mFieldInfo = fieldInfo;
    }

    @Override
    public int getControlerType() {
        return 0;
    }
}
