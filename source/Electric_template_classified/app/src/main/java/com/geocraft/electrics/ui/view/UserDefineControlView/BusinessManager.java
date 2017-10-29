package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
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
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.ui.fragment.control_fragment.AdministrationDialogFragment;
import com.geocraft.electrics.ui.fragment.control_fragment.AdministrationDialogFragment_;
import com.geocraft.electrics.ui.fragment.control_fragment.ManagerDialogFragment;
import com.geocraft.electrics.ui.fragment.control_fragment.ManagerDialogFragment_;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.DefaultLong;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanpengfei on 2016/6/26.
 */
@EViewGroup(R.layout.view_manager)
public class BusinessManager extends LinearLayout implements DataInterActionInterface,ManagerDialogFragment.OnSelectedListener
{
    @ViewById
    EditText etText;
    @ViewById
    Button  btnMore;

    public String strFirstManager = "";
    public String strSecondManager = "";
    public String strThirdManager = "";


    public BusinessManager(Context context)
    {
        super(context);
    }

    public BusinessManager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public BusinessManager(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public int getControlerType() {
        return PropertyDictionay.OperateCode.Type_Manager;
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
        ManagerDialogFragment_ dialog = new ManagerDialogFragment_();
        dialog.onSelectedListener = this;
        dialog.show(((Activity)getContext()).getFragmentManager(),"");
    }

    @Override
    public void OnSelected(String first, String second, String third) {
        strFirstManager = first;
        strSecondManager = second;
        strThirdManager = third;

        etText.setText(strThirdManager);
    }

}
