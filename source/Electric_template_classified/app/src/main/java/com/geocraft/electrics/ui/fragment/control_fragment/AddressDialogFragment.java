package com.geocraft.electrics.ui.fragment.control_fragment;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanpengfei on 2016/6/26.
 */
@EFragment(R.layout.dialogfragment_address)
public class AddressDialogFragment extends BaseDialogFragment
{
    @ViewById
    Spinner spProvince;
    @ViewById
    Spinner spCity;
    @ViewById
    Spinner spCountry;
    @ViewById
    Spinner spTown;
    @ViewById
    Spinner spVillage;
    @ViewById
    Spinner spHamlet;
    @ViewById
    Button btnCancel;
    @ViewById
    Button btnOk;

    protected DbManager dbManager = DbManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());
    protected TaskManager taskManager = TaskManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());

    public List<DataSet> list = new ArrayList<>();

    ArrayList<String> listProvince = new ArrayList<>();
    ArrayList<String> listCity = new ArrayList<>();
    ArrayList<String> listCountry = new ArrayList<>();
    ArrayList<String> listTown = new ArrayList<>();
    ArrayList<String> listVillage = new ArrayList<>();
    ArrayList<String> listHamlet = new ArrayList<>();

    ArrayAdapter<String> adapterProvince = null;
    ArrayAdapter<String> adapterCity = null;
    ArrayAdapter<String> adapterCountry = null;
    ArrayAdapter<String> adapterTown = null;
    ArrayAdapter<String> adapterVillage = null;
    ArrayAdapter<String> adapterHamlet = null;

    public OnSelectedListener onSelectedListener = null;


    public interface  OnSelectedListener
    {
        void OnSelected(String province, String city, String country,String town,String village,String hamlet);
    }


    public void initData(){
        //从数据库里加载行政区信息
        DataSet dataSetTmp = taskManager.getDataSource().getDataSetByName(Enum.GROUP_NAME_QT,Enum.DATA_SET_NAME_DZXX);
        list = dbManager.queryAll(dataSetTmp,false);

        for(DataSet dataSet : list)
        {
            String str = dataSet.GetFieldValueByName(Enum.ADDRESS_FIELD_SHENG);
            if(!listProvince.contains(str))
            {
                listProvince.add(str);
            }
        }
    }

    @AfterViews
    void init()
    {
        adapterProvince = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listProvince);
        adapterProvince.setDropDownViewResource(R.layout.item_single_choice);
        spProvince.setAdapter(adapterProvince);
        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               listCity.clear();
                listCountry.clear();
                listTown.clear();
                listVillage.clear();
                listHamlet.clear();

                Utils.setSingleSelected(spProvince);

                //获取当前选择的字符串
                String str = listProvince.get(position);
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
                adapterTown.notifyDataSetChanged();
                adapterVillage.notifyDataSetChanged();
                adapterHamlet.notifyDataSetChanged();

                int i = 0;
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
                listCountry.clear();
                listTown.clear();
                listVillage.clear();
                listHamlet.clear();

                Utils.setSingleSelected(spCity);

                //获取当前选择的字符串
                String str = listCity.get(position);
                //初始化City
                for(DataSet dataSet : list)
                {
                    if(dataSet.GetFieldValueByName(Enum.ADMINISTRATION_FIELD_SHI).equals(str))
                    {
                        String strTmp = dataSet.GetFieldValueByName(Enum.ADMINISTRATION_FIELD_XIAN);
                        if(!listCountry.contains(strTmp))
                        {
                            listCountry.add(strTmp);
                        }
                    }
                }

                adapterCountry.notifyDataSetChanged();
                adapterTown.notifyDataSetChanged();
                adapterVillage.notifyDataSetChanged();
                adapterHamlet.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterCountry = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listCountry);
        adapterCountry.setDropDownViewResource(R.layout.item_single_choice);
        spCountry.setAdapter(adapterCountry);
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listTown.clear();
                listVillage.clear();
                listHamlet.clear();

                Utils.setSingleSelected(spCountry);

                //获取当前选择的字符串
                String str = listCountry.get(position);
                //初始化City
                for(DataSet dataSet : list)
                {
                    if(dataSet.GetFieldValueByName(Enum.ADMINISTRATION_FIELD_XIAN).equals(str))
                    {
                        String strTmp = dataSet.GetFieldValueByName(Enum.ADDRESS_FIELD_ZHEN);
                        if(!listTown.contains(strTmp))
                        {
                            listTown.add(strTmp);
                        }
                    }
                }

                adapterTown.notifyDataSetChanged();
                adapterVillage.notifyDataSetChanged();
                adapterHamlet.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterTown = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listTown);
        adapterTown.setDropDownViewResource(R.layout.item_single_choice);
        spTown.setAdapter(adapterTown);
        spTown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listVillage.clear();
                listHamlet.clear();

                Utils.setSingleSelected(spTown);

                //获取当前选择的字符串
                String str = listTown.get(position);
                //初始化City
                for(DataSet dataSet : list)
                {
                    if(dataSet.GetFieldValueByName(Enum.ADDRESS_FIELD_ZHEN).equals(str))
                    {
                        String strTmp = dataSet.GetFieldValueByName(Enum.ADDRESS_FIELD_XIANG);
                        if(!listVillage.contains(strTmp))
                        {
                            listVillage.add(strTmp);
                        }
                    }
                }

                adapterVillage.notifyDataSetChanged();
                adapterHamlet.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterVillage = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listVillage);
        adapterVillage.setDropDownViewResource(R.layout.item_single_choice);
        spVillage.setAdapter(adapterVillage);
        spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listHamlet.clear();

                Utils.setSingleSelected(spVillage);

                //获取当前选择的字符串
                String str = listVillage.get(position);
                //初始化City
                for(DataSet dataSet : list)
                {
                    if(dataSet.GetFieldValueByName(Enum.ADDRESS_FIELD_XIANG).equals(str))
                    {
                        String strTmp = dataSet.GetFieldValueByName(Enum.ADDRESS_FIELD_CUN);
                        if(!listHamlet.contains(strTmp))
                        {
                            listHamlet.add(strTmp);
                        }
                    }
                }

                adapterHamlet.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterHamlet = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listHamlet);
        adapterHamlet.setDropDownViewResource(R.layout.item_single_choice);
        spHamlet.setAdapter(adapterHamlet);
        Utils.setSingleSelected(spHamlet);
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
            onSelectedListener.OnSelected(Utils.getString(spProvince),Utils.getString(spCity),Utils.getString(spCountry),Utils.getString(spTown),Utils.getString(spVillage),Utils.getString(spHamlet));
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
