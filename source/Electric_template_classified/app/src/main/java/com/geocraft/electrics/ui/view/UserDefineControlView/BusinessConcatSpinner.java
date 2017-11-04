package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.sr.task.UpdateTemplateAsyncTask;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;
import com.geocraft.electrics.utils.SPUtils;

import java.util.ArrayList;

/**
 * Created by zhongshibu02 on 2017/10/29.
 */
public class BusinessConcatSpinner extends LinearLayout implements DataInterActionInterface {

    ArrayAdapter<String> dataAdapter;
    Spinner spinner;
    Button button;
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
        LayoutInflater mInflater = LayoutInflater.from(context);
        mRootView = mInflater.inflate(R.layout.view_business_concat_pinner, this, false);
        addView(mRootView);
        spinner = (Spinner) mRootView.findViewById(R.id.spinner);
        button = (Button) mRootView.findViewById(R.id.button);
        button.setOnClickListener(mListener);
        if (!((Boolean) SPUtils.get(context, Constants.SPKEY_SPINNER_SWITCH, true))) {
            button.setVisibility(GONE);
        }

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
        if (null == mEditText || mEditText.getText().toString().isEmpty()) {
            return;
        }
        String text = mEditText.getText().toString();
        String select = "";
        if (spinner.getSelectedItem() != null) {
            select = spinner.getSelectedItem().toString();
        }
        mDatalist.remove("");
        mFieldInfo.Dictionay.menuList.add(text);
        mDatalist.add("");
        dataAdapter.notifyDataSetChanged();
        UpdateTemplateAsyncTask task = new UpdateTemplateAsyncTask(getContext(),
                mDataSet, mFieldInfo);
        task.execute(text);
        setControlValue(select);
        dialogDismiss();
    }

    @Override
    public String getControlValue() {
        return spinner.getSelectedItem() != null ? spinner.getSelectedItem().toString() : "";
    }

    @Override
    public void setControlValue(final String text) {
        spinner.post(new Runnable() {
            @Override
            public void run() {
                if (mDatalist.contains(text)) {
                    spinner.setSelection(mDatalist.indexOf(text), true);
                    mValue = text;
                } else {
                    spinner.setSelection(mDatalist.indexOf(""), true);
                }
            }
        });
    }

    @Override
    public void setControlValue(FieldInfo fieldInfo, String text) {
        mDatalist = fieldInfo.Dictionay.menuList;
        mDatalist.remove("");
        mDatalist.add("");
        dataAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.simple_spinner_item, mDatalist) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position == mDatalist.size() - 1) {
                    view.setVisibility(GONE);
                }
                return view;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };
        dataAdapter.setDropDownViewResource(R.layout.item_single_choice);
        spinner.setAdapter(dataAdapter);
        setControlValue(text);
    }

    public void setData(DataSet dataSet, FieldInfo fieldInfo) {
        mDataSet = dataSet;
        mFieldInfo = fieldInfo;
    }

    @Override
    public int geteControlType() {
        return PropertyDictionay.OperateCode.type_concat;
    }
}
