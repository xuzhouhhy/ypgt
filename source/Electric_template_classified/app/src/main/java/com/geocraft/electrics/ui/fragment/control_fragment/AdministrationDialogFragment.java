package com.geocraft.electrics.ui.fragment.control_fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BaseDialogFragment;
import com.geocraft.electrics.constants.Enum;

import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanpengfei on 2016/6/26.
 */
@EFragment(R.layout.dialogfragment_administration)
public class AdministrationDialogFragment extends BaseDialogFragment
{
    @ViewById
    Spinner spProvince;
    @ViewById
    Spinner spCity;
    @ViewById
    Spinner spCounty;
    @ViewById
    Button btnCancel;
    @ViewById
    Button btnOk;

    protected DbManager dbManager = DbManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());
    protected TaskManager taskManager = TaskManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());

    public List<DataSet> list = new ArrayList<>();

    ArrayList<String> listProvice = new ArrayList<>();
    ArrayList<String> listCity = new ArrayList<>();
    ArrayList<String> listCounty = new ArrayList<>();

    ArrayAdapter<String> adapterProvice = null;
    ArrayAdapter<String> adapterCity = null;
    ArrayAdapter<String> adapterCountry = null;

    public OnSelectedListener onSelectedListener = null;


    public interface  OnSelectedListener
    {
        void OnSelected(String provice,String ciry,String country);
    }


    @AfterViews
    void init()
    {

        //从数据库里加载行政区信息
        DataSet dataSetTmp = taskManager.getDataSource().getDataSetByName(Enum.GROUP_NAME_QT,Enum.DATA_SET_NAME_XZQ);
        list = dbManager.queryAll(dataSetTmp,false);

        for(DataSet dataSet : list)
        {
            String str = dataSet.GetFieldValueByName(Enum.ADMINISTRATION_FIELD_SHENG);
            if(!listProvice.contains(str))
            {
                listProvice.add(str);
            }
        }

        adapterProvice = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listProvice);
        adapterProvice.setDropDownViewResource(R.layout.item_single_choice);
        spProvince.setAdapter(adapterProvice);
        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listCounty.clear();
                listCity.clear();

                Utils.setSingleSelected(spProvince);

                //获取当前选择的字符串
                String str = listProvice.get(position);
                //初始化City
                for(DataSet dataSet : list)
                {
                    if(dataSet.GetFieldValueByName(Enum.ADMINISTRATION_FIELD_SHENG).equals(str))
                    {
                        String strTmp = dataSet.GetFieldValueByName(Enum.ADMINISTRATION_FIELD_SHI);
                        if(!listCity.contains(strTmp))
                        {
                            listCity.add(strTmp);
                        }
                    }
                }

                adapterCity.notifyDataSetChanged();
                adapterCountry.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterCity = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listCity);
        adapterCity.setDropDownViewResource(R.layout.item_single_choice);
        spCity.setAdapter(adapterCity);
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listCounty.clear();

                Utils.setSingleSelected(spCity);

                //获取当前选择的字符串
                String str = listCity.get(position);
                //初始化City
                for(DataSet dataSet : list)
                {
                    if(dataSet.GetFieldValueByName(Enum.ADMINISTRATION_FIELD_SHI).equals(str))
                    {
                        String strTmp = dataSet.GetFieldValueByName(Enum.ADMINISTRATION_FIELD_XIAN);
                        if(!listCounty.contains(strTmp))
                        {
                            listCounty.add(strTmp);
                        }
                    }
                }

                adapterCountry.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterCountry = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listCounty);
        adapterCountry.setDropDownViewResource(R.layout.item_single_choice);
        spCounty.setAdapter(adapterCountry);
        Utils.setSingleSelected(spCounty);
    }

    @Click
    void btnCancel()
    {
        dismiss();
    }

    @Click
    void btnOk()
    {
        if(onSelectedListener != null)
        {
            onSelectedListener.OnSelected(Utils.getString(spProvince),Utils.getString(spCity),Utils.getString(spCounty));
        }
        dismiss();
    }

    @Override
    public void onDestroyView() {
        list.clear();
        list = null;
        super.onDestroyView();
    }
}
