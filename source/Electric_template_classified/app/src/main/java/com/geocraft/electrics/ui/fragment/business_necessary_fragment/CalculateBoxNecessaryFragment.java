package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.event.SearchControlEvent;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity_;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.MeterBasicFragment_;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessManager;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessScanEdit;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;
import com.geocraft.electrics.utils.SPUtils;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import common.geocraft.untiltools.ConvertHelper;
import common.geocraft.untiltools.T;
import common.geocraft.untiltools.Tools;

/**计量箱
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_jlx_bt)
public class CalculateBoxNecessaryFragment extends BusinessFragment {

    @ViewById
    BusinessSearch viewJLXZ;
    @ViewById
    BusinessManager viewGLDW;
    @ViewById
    BusinessEditText etSJDW;

    @ViewById
    BusinessEditText etZCDW;
    @ViewById
    BusinessSearch viewZCDW;
    @ViewById
    BusinessSearch searchXH;

    @ViewById
    LinearLayout linearLayoutRoot;
    @ViewById
    BusinessScanEdit viewJLXTMBH;

    @ViewById
    BusinessSearch viewMC;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();

        //设置默认焦点
        viewJLXTMBH.setFocus();

        if (!ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.register(this);
        }

        //控制界面显示
        DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();

        viewJLXZ.searchEntiry.tableName = dataSet.GroupName + "_" + Enum.DATA_SET_NAME_JLX_J_Z;
        viewJLXZ.searchEntiry.fieldName = Enum.CALCULATEBOXGROUP_FIELD_ZM;

        searchXH.searchEntiry.tableName = Enum.GROUP_NAME_QT +"_"+Enum.DATA_SET_NAME_JLXXH;
        searchXH.searchEntiry.fieldName = Enum.FIELD_NAME_JLXXH_MC;

        if(dataSet.GroupName.equals(Enum.GROUP_NAME_DYYH))  //低压用户
        {
            viewZCDW.searchEntiry.tableName = Enum.GROUP_NAME_DYYH + "_" + Enum.DATA_SET_NAME_DYKHDA;
            viewZCDW.searchEntiry.fieldName = Enum.LOWVOLTAGEUSER_FIELD_KHMC;
        }
        else if(dataSet.GroupName.equals(Enum.GROUP_NAME_ZXYH)) //专线用户
        {
            viewZCDW.searchEntiry.tableName = Enum.GROUP_NAME_ZXYH + "_" + Enum.DATA_SET_NAME_GYYHD;
            viewZCDW.searchEntiry.fieldName = Enum.HIGHVOLTAGEUSER_FIELD_MC;
        }
        else if(dataSet.GroupName.equals(Enum.GROUP_NAME_ZBYH)) //专变用户
        {
            viewZCDW.searchEntiry.tableName = Enum.GROUP_NAME_ZBYH + "_" + Enum.DATA_SET_NAME_GYYHD;
            viewZCDW.searchEntiry.fieldName = Enum.HIGHVOLTAGEUSER_FIELD_MC;
        }

        //获取资产单位值
        String strZCDW = dataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_ZCDW);

        //如果全是数据，则认为是用户编号
        if(Utils.isNumeric(strZCDW))
        {
            viewZCDW.setControlValue(strZCDW);
        }

        //如果是新建，将名称带过来
        if(((RecordActivity)getActivity()).getController().isCreateRecord())
        {
            List<DataSet> list = dbManager.queryAll(dataSet,true);
            if(list.size() > 0)
            {
                viewMC.setControlValue(list.get(list.size() - 1).GetFieldValueByName(Enum.CALCULATEBOX_FIELD_MC));
            }
        }
	}

    @Subscribe
    public void onEventMainThread(SearchControlEvent event) {
        DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
        if((!event.Name.isEmpty()) && (!dataSet.Name.equals(event.Name)))
        {
            return;
        }
        if (event.dataSet.Name.equals(Enum.DATA_SET_NAME_JLX_J_Z)) {

            dataSet = null;
            try {
                 dataSet = (DataSet)event.dataSet.clone();
            }
            catch(CloneNotSupportedException e)
            {
                L.printException(e);
            }

            //将该DataSet伪装成一个计量箱
            dataSet.Name = Enum.DATA_SET_NAME_JLX_J;

            //设置业务系统ID
            dataSet.SetFiledValueByName(Enum.CALCULATEBOXGROUP_FIELD_YWXTID,Tools.GetCurrentFormatTime());
            ((RecordActivity)getActivity()).initData(dataSet);

            //设置挂接设备类型和挂接设备名称
            ((RecordActivity)getActivity()).getController().getCurrentDataSet().SetFiledValueByName(Enum.CALCULATEBOX_FIELD_GJSBMC,dataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_GJSBMC));
            ((RecordActivity)getActivity()).getController().getCurrentDataSet().SetFiledValueByName(Enum.CALCULATEBOX_FIELD_GJSBLX,dataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_GJSBLX));

            //计量箱组
            viewJLXZ.setControlValue(dataSet.GetFieldValueByName(Enum.CALCULATEBOXGROUP_FIELD_ZM));
        }
        else if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_DYKHDA))
        {
            viewZCDW.setControlValue(event.dataSet.GetFieldValueByName(Enum.LOWVOLTAGEUSER_FIELD_KHBH));
        }
        else if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_GYYHD))
        {
            viewZCDW.setControlValue(event.dataSet.GetFieldValueByName(Enum.HIGHVOLTAGEUSER_FIELD_YWXYID));
        }else if (event.dataSet.Name.equals(Enum.DATA_SET_NAME_JLXXH)){
            searchXH.setControlValue(event.dataSet.GetFieldValueByName(Enum.FIELD_NAME_JLXXH_MC));
        }else if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_TQXX)){
            viewMC.setControlValue(event.dataSet.GetFieldValueByName(Enum.ZONEAREA_FIELD_MC));
        }
    }

    @Override
    public void getValue(DataSet dataSet) {

        //给资产单位赋值，如果用户编号为空，则使用管理单位
        if(viewZCDW.getControlValue().isEmpty())
        {
            etZCDW.setControlValue(viewGLDW.getControlValue());
            if(!viewGLDW.strSecondManager.isEmpty()) {
                etSJDW.setControlValue(viewGLDW.strSecondManager);
            }
        }
        else
        {
            etZCDW.setControlValue(viewZCDW.getControlValue());
            etSJDW.setControlValue(viewZCDW.getControlValue());
        }

        super.getValue(dataSet);
    }

    //业务逻辑判断
    @Override
    public boolean logicCheck() {

        DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();

        //判断给计量箱是否存在
        if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
            if (((RecordActivity) getActivity()).getController().isCreateRecord()) {
                List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.CALCULATEBOX_FIELD_JLXTMBH, viewJLXTMBH.getControlValue(), false);
                if (dataSetList.size() > 0) {
                    T.show(getContext(), getString(R.string.jlx_exsit), Toast.LENGTH_SHORT);
                    return false;
                }
            }
        }

        String type = dataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_BXLX);
        String row = dataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_H);
        String colum = dataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_L);

        //单体表箱，只能是一行一类
        if(type.equals(Enum.CALCULATEBOX_DTBX))
        {
            if(!(row.equals("1") && colum.equals("1")))
            {
                T.show(getContext(),getString(R.string.jlx_logiccheck_1),Toast.LENGTH_SHORT);
                return false;
            }
        }
        else if(type.equals(Enum.CALCULATEBOX_HTBX)) //合体表箱不能是一行一列
        {
            if((row.equals("1") && colum.equals("1")))
            {
                T.show(getContext(),getString(R.string.jlx_logiccheck_2),Toast.LENGTH_SHORT);
                return false;
            }
        }


        //如果是新建，则提示是否新建电能表
        if(((RecordActivity)getActivity()).getController().isCreateRecord())
        {
            //将用到的值，存起来
            SPUtils.put(getActivity(),Constants.INTENT_DATA_SET_GROUP_NAME,dataSet.GroupName);
            SPUtils.put(getActivity(),Constants.INTENT_CALCULATEBOX_CODE,viewJLXTMBH.getControlValue());

            List list = ElectricApplication_.getInstance().getList();
            final Activity activity = (Activity) list.get(list.size() - 2);
            //获取倒数第二个Activity
            new AlertDialog.Builder(activity)
                    .setTitle(R.string.app_tip)
                    .setMessage(R.string.is_open_dnb_dialog_tip)
                    .setNegativeButton(R.string.app_no, null)
                    .setPositiveButton(R.string.app_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showMeter(activity);
                        }
                    }).show();
        }

        return true;
    }

    public void showMeter(Activity activity)
    {
        String groupName = (String)SPUtils.get(activity,Constants.INTENT_DATA_SET_GROUP_NAME,"");
        String code = (String)SPUtils.get(activity,Constants.INTENT_CALCULATEBOX_CODE,"");
        DataSet dataSet = taskManager.getDataSource().getDataSetByName(groupName,Enum.DATA_SET_NAME_JLX_J);
        DataSet dataSetMeter = taskManager.getDataSource().getDataSetByName(groupName,Enum.DATA_SET_NAME_JLXYDNBDGX);
        List<DataSet> dataSetQuery  = dbManager.queryByCondition(dataSet,Enum.CALCULATEBOX_FIELD_JLXTMBH,code,true);
        if(dataSetQuery.size() > 0) {
            DataSet dataSetTmp = dataSetQuery.get(dataSetQuery.size() - 1);
            Intent intent = new Intent(activity, DeviceShowListActivity_.class);
            intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, dataSet.GroupName);
            intent.putExtra(Constants.INTENT_DATA_SET_NAME, Enum.DATA_SET_NAME_JLXYDNBDGX);
            intent.putExtra(Constants.INTENT_DATA_SET_PARENT_KEY, dataSetTmp.PrimaryKey);
            intent.putExtra(Constants.INTENT_DATA_SET_QUERY_VALUE, dataSetTmp.GetFieldValueByName(dataSetMeter.ValueField));
            activity.startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ElectricApplication.BUS.unregister(this);
    }
}
