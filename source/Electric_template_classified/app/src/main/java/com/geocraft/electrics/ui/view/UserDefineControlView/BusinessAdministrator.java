package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.ui.fragment.control_fragment.AdministrationDialogFragment;
import com.geocraft.electrics.ui.fragment.control_fragment.AdministrationDialogFragment_;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanpengfei on 2016/6/26.
 */
@EViewGroup(R.layout.view_administrator)
public class BusinessAdministrator extends LinearLayout implements DataInterActionInterface,AdministrationDialogFragment.OnSelectedListener
{
    @ViewById
    EditText etText;
    @ViewById
    Button  btnMore;

    private String strProvice = "";
    private String strCiry = "";
    private String strCountry = "";


    public BusinessAdministrator(Context context)
    {
        super(context);
    }

    public BusinessAdministrator(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public BusinessAdministrator(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public void setControlValue(String text) {
        etText.setText(text);
    }

    @Override
    public void setControlValue(FieldInfo fieldInfo, String text) {
        setControlValue(text);
    }

    @Override
    public String getControlValue() {
        return etText.getText().toString();
    }

    @Click
    void btnMore()
    {
        AdministrationDialogFragment_ dialog = new AdministrationDialogFragment_();
        dialog.onSelectedListener = this;
        dialog.show(((Activity)getContext()).getFragmentManager(),"");
    }

    @Override
    public void OnSelected(String provice, String ciry, String country) {
        strProvice = provice;
        strCiry = ciry;
        strCountry = country;

        etText.setText(strCountry);
    }

}
