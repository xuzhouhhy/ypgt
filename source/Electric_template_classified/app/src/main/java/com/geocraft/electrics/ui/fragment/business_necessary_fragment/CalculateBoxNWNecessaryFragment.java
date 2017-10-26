package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity_;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessScanEdit;
import com.geocraft.electrics.utils.SPUtils;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 变电站信息
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_jlxnw_bt)
public class CalculateBoxNWNecessaryFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @ViewById
    BusinessEditText etMC;
    @ViewById
    BusinessEditText etSSBYQ;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();

        //控制界面显示
        DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();

        //如果是新建，将名称带过来
        if(((RecordActivity)getActivity()).getController().isCreateRecord())
        {
            List<DataSet> list = dbManager.queryAll(dataSet,true);
            if(list.size() > 0)
            {
                etMC.setControlValue(list.get(list.size() - 1).GetFieldValueByName(Enum.CALCULATEBOX_FIELD_MC));
            }
        }

        //初始化所属变压器
        int primaryKey = ((RecordActivity) getActivity()).getController().getDataSetParentKey();
        DataSet queyDataSet = taskManager.getDataSource().getDataSetByName(dataSet.GroupName, Enum.DATA_SET_NAME_BYQ);
        queyDataSet.PrimaryKey = primaryKey;
        queyDataSet = dbManager.queryByPrimaryKey(queyDataSet, true);
        if (queyDataSet != null) {
            etSSBYQ.setControlValue(queyDataSet.GetFieldValueByName(Enum.TRANSFORMER_FIELD_MC));
        }
    }

    @Override
    public boolean logicCheck() {
        //判断变压器是否存在
        if(((RecordActivity)getActivity()).getController().isCreateRecord())
        {
            DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
            List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.CALCULATEBOX_FIELD_MC, etMC.getControlValue(), false);
            if (dataSetList.size() > 0) {
                T.show(getContext(), getString(R.string.jlx_exsit), Toast.LENGTH_SHORT);
                return false;
            }

            //新建，提示新建电能表
            SPUtils.put(getActivity(), Constants.INTENT_DATA_SET_GROUP_NAME,dataSet.GroupName);
            SPUtils.put(getActivity(),Constants.INTENT_CALCULATEBOX_NAME,etMC.getControlValue());

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
        String name = (String)SPUtils.get(activity,Constants.INTENT_CALCULATEBOX_NAME,"");
        DataSet dataSet = taskManager.getDataSource().getDataSetByName(groupName,Enum.DATA_SET_NAME_JLX);
        DataSet dataSetMeter = taskManager.getDataSource().getDataSetByName(groupName,Enum.DATA_SET_NAME_DNB);
        List<DataSet> dataSetQuery  = dbManager.queryByCondition(dataSet,Enum.CALCULATEBOX_FIELD_MC,name,true);
        if(dataSetQuery.size() > 0) {
            DataSet dataSetTmp = dataSetQuery.get(dataSetQuery.size() - 1);
            Intent intent = new Intent(activity, DeviceShowListActivity_.class);
            intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, dataSet.GroupName);
            intent.putExtra(Constants.INTENT_DATA_SET_NAME, Enum.DATA_SET_NAME_DNB);
            intent.putExtra(Constants.INTENT_DATA_SET_PARENT_KEY, dataSetTmp.PrimaryKey);
            intent.putExtra(Constants.INTENT_DATA_SET_QUERY_VALUE, dataSetTmp.GetFieldValueByName(dataSetMeter.ValueField));
            activity.startActivity(intent);
        }
    }
}
