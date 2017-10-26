package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.task.LoadAddressListAsyncTask;
import com.geocraft.electrics.ui.fragment.control_fragment.AddressDialogFragment;
import com.geocraft.electrics.ui.fragment.control_fragment.AddressDialogFragment_;
import com.geocraft.electrics.ui.fragment.control_fragment.AdministrationDialogFragment;
import com.geocraft.electrics.ui.fragment.control_fragment.ManagerDialogFragment;
import com.geocraft.electrics.ui.fragment.control_fragment.ManagerDialogFragment_;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanpengfei on 2016/6/26.
 */
@EViewGroup(R.layout.view_address)
public class BusinessAddress extends LinearLayout implements DataInterActionInterface,AddressDialogFragment.OnSelectedListener
{
    @ViewById
    EditText etText;
    @ViewById
    Button  btnMore;

    public String strProvince = "";
    public String strCity = "";
    public String strCountry = "";
    public String strTown = "";
    public String strVillage = "";
    public String strHamlet = "";
    public Context mContext;

    public BusinessAddress(Context context)
    {
        super(context);
        mContext =context;
    }

    public BusinessAddress(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext =context;
    }

    public BusinessAddress(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext =context;
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
        AddressDialogFragment dialog = new AddressDialogFragment_();
        dialog.onSelectedListener = this;
        LoadAddressListAsyncTask loadAddressListAsyncTask = new LoadAddressListAsyncTask(mContext,dialog);
        loadAddressListAsyncTask.execute(dialog);

    }

    @Override
    public void OnSelected(String province, String city, String country, String town, String village, String hamlet) {
        strProvince = province;
        strCity = city;
        strCountry = country;
        strTown = town;
        strVillage = village;
        strHamlet = hamlet;

        etText.setText(strProvince+strCity+strCountry+strTown+strVillage+strHamlet);
    }

}
