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
@EFragment(R.layout.dialogfragment_manager)
public class ManagerDialogFragment extends BaseDialogFragment
{
    @ViewById
    Spinner spFirst;
    @ViewById
    Spinner spSecond;
    @ViewById
    Spinner spThird;
    @ViewById
    Button btnCancel;
    @ViewById
    Button btnOk;


    protected DbManager dbManager = DbManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());
    protected TaskManager taskManager = TaskManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());

    public List<DataSet> list = new ArrayList<>();

    ArrayList<String> listFirst = new ArrayList<>();
    ArrayList<String> listSecond = new ArrayList<>();
    ArrayList<String> listThird = new ArrayList<>();

    ArrayAdapter<String> adapterFirst = null;
    ArrayAdapter<String> adapterSecond = null;
    ArrayAdapter<String> adapterThird = null;

    public OnSelectedListener onSelectedListener = null;


    public interface  OnSelectedListener
    {
        void OnSelected(String first, String second, String third);
    }


    @AfterViews
    void init()
    {
        //从数据库里加载行政区信息
        DataSet dataSetTmp = taskManager.getDataSource().getDataSetByName(Enum.GROUP_NAME_QT,Enum.DATA_SET_NAME_GLDW);
        list = dbManager.queryAll(dataSetTmp,false);

        for(DataSet dataSet : list)
        {
            String str = dataSet.GetFieldValueByName(Enum.MANAGER_FIELD_YJGDGS);
            if(!listFirst.contains(str))
            {
                listFirst.add(str);
            }
        }

        adapterFirst = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listFirst);
        adapterFirst.setDropDownViewResource(R.layout.item_single_choice);
        spFirst.setAdapter(adapterFirst);
        spFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listSecond.clear();
                listThird.clear();

                Utils.setSingleSelected(spFirst);

                //获取当前选择的字符串
                String str = listFirst.get(position);
                //初始化City
                for(DataSet dataSet : list)
                {
                    if(dataSet.GetFieldValueByName(Enum.MANAGER_FIELD_YJGDGS).equals(str))
                    {
                        String strTmp = dataSet.GetFieldValueByName(Enum.MANAGER_FIELD_EJGDGS);
                        if(!listSecond.contains(strTmp))
                        {
                            listSecond.add(strTmp);
                        }
                    }
                }

                adapterSecond.notifyDataSetChanged();
                adapterThird.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterSecond = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listSecond);
        adapterSecond.setDropDownViewResource(R.layout.item_single_choice);
        spSecond.setAdapter(adapterSecond);
        spSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listThird.clear();

                Utils.setSingleSelected(spSecond);

                //获取当前选择的字符串
                String str = listSecond.get(position);
                //初始化City
                for(DataSet dataSet : list)
                {
                    if(dataSet.GetFieldValueByName(Enum.MANAGER_FIELD_EJGDGS).equals(str))
                    {
                        String strTmp = dataSet.GetFieldValueByName(Enum.MANAGER_FIELD_GDS);
                        if(!listThird.contains(strTmp))
                        {
                            listThird.add(strTmp);
                        }
                    }
                }

                adapterThird.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapterThird = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item, listThird);
        adapterThird.setDropDownViewResource(R.layout.item_single_choice);
        spThird.setAdapter(adapterThird);
        Utils.setSingleSelected(spThird);
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
            onSelectedListener.OnSelected(Utils.getString(spFirst),Utils.getString(spSecond),Utils.getString(spThird));
        }
        dismiss();
    }

}
