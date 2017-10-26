package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity_;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessScanEdit;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSpinner;


import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import common.geocraft.untiltools.ConvertHelper;
import common.geocraft.untiltools.T;
import common.geocraft.untiltools.Tools;

/**
 * 接入点标识
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_dnb_bt)
public class MeterNecessaryFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @ViewById
    BusinessEditText etAZDZ;

    @ViewById
    BusinessEditText etJLXTMBB;

    @ViewById
    BusinessSpinner spDBBWH;
    @ViewById
    BusinessSpinner spDBBWL;

    @ViewById
    BusinessScanEdit viewDBTMBH;

    private int rowNumber = 0;
    private int columNumber = 0;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();

        //设置默认焦点
        viewDBTMBH.setFocus();

        if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
            int primaryKey = ((RecordActivity) getActivity()).getController().getDataSetParentKey();
            DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();

            DataSet queyDataSet = taskManager.getDataSource().getDataSetByName(dataSet.GroupName, Enum.DATA_SET_NAME_JLX_J);
            queyDataSet.PrimaryKey = primaryKey;

            queyDataSet = dbManager.queryByPrimaryKey(queyDataSet, true);
            if (queyDataSet != null) {
                etAZDZ.setControlValue(queyDataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_AZDZ));
                etJLXTMBB.setControlValue(queyDataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_JLXTMBH));
                rowNumber = ConvertHelper.ConverInt(queyDataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_H));
                columNumber = ConvertHelper.ConverInt(queyDataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_L));
            }

            //自动生成电能表序号
           List<DataSet> meterList =  dbManager.queryByCondition(dataSet,Enum.METER_FIELD_JLXTMBH,queyDataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_JLXTMBH),true);
            if(meterList.size() == 0)
            {
                spDBBWH.setControlValue("1");
                spDBBWL.setControlValue("1");
            }
            else
            {
                //获取最后一块电能表的行列号
                int h = ConvertHelper.ConverInt(meterList.get(meterList.size()-1).GetFieldValueByName(Enum.METER_FIELD_DBBW_X));
                int l = ConvertHelper.ConverInt(meterList.get(meterList.size()-1).GetFieldValueByName(Enum.METER_FIELD_DBBW_L));

                h = (l + 1) > columNumber ? h + 1 : h;
                l = (l + 1) <= columNumber ? l + 1 : 1;


                spDBBWH.setControlValue(h + "");
                spDBBWL.setControlValue(l + "");
            }
        }
    }

    @Override
    public boolean logicCheck() {
        DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();

        //判断该电脑表是否存在
        if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
            List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.METER_FIELD__DBTMBH, viewDBTMBH.getControlValue(), false);
            if (dataSetList.size() > 0) {
                T.show(getContext(), getString(R.string.dnb_exsit), Toast.LENGTH_SHORT);
                return false;
            }

            //判断该表位是否存在
            String sql = Enum.METER_FIELD_JLXTMBH + " = '" + etJLXTMBB.getControlValue() + "'" + "and " + Enum.METER_FIELD_DBBW_X + " ='" + spDBBWH.getControlValue() + "' and " + Enum.METER_FIELD_DBBW_L + " ='" + spDBBWL.getControlValue() + "'";
            dataSetList = dbManager.queryByCondition(dataSet, sql, "", false);
            if (dataSetList.size() > 0) {
                T.show(getContext(), getString(R.string.dnb_hl_exsit), Toast.LENGTH_SHORT);
                return false;
            }

            //判读计量箱数据
            dataSetList = dbManager.queryByCondition(dataSet, Enum.METER_FIELD_JLXTMBH, etJLXTMBB.getControlValue(), false);
            if (dataSetList.size() == (rowNumber * columNumber)) {
                T.show(getContext(), getString(R.string.dnb_logiccheck_1), Toast.LENGTH_SHORT);
                return false;
            }
        }

            if(dataSet.GroupName.equals(Enum.GROUP_NAME_DYYH)) {
                //判断该电能表是否在低压用户表中
                DataSet dataSetUser = taskManager.getDataSource().getDataSetByName(Enum.GROUP_NAME_DYYH, Enum.DATA_SET_NAME_DYKHDA);
                List<DataSet> userList = dbManager.queryByCondition(dataSetUser, Enum.LOWVOLTAGEUSER_FIELD_DNBTXM, viewDBTMBH.getControlValue(), false);
                if (userList.size() > 0) {
                    //如果低压用户中计量箱条形码与采集的条形码不一样，直接更新
                    String code = dataSetUser.GetFieldValueByName(Enum.LOWVOLTAGEUSER_FIELD_JLXTXM);
                    if (!code.equals(etJLXTMBB.getControlValue())) {
                        dataSetUser.SetFiledValueByName(Enum.LOWVOLTAGEUSER_FIELD_JLXTXM, etJLXTMBB.getControlValue());
                        dbManager.update(dataSetUser);
                    }
                } else {
                    //添加一个低压用户
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.app_tip);
                    builder.setIcon(R.mipmap.ic_tip);
                    builder.setMessage(R.string.dyyh_logiccheck_1);
                    builder.setInverseBackgroundForced(true);
                    builder.setPositiveButton(R.string.app_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == DialogInterface.BUTTON_POSITIVE) {

                                Intent intent = new Intent(getActivity(), DeviceShowListActivity_.class);
                                intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, Enum.GROUP_NAME_DYYH);
                                intent.putExtra(Constants.INTENT_DATA_SET_NAME, Enum.DATA_SET_NAME_DYKHDA);
                                intent.putExtra(Constants.INTENT_DATA_SET_PARENT_KEY, "-1");
                                intent.putExtra(Constants.INTENT_DATA_SET_QUERY_VALUE, "");
                                getActivity().startActivity(intent);

                            } else {
                                return;
                            }
                        }
                    }).show();

                    return false;
                }
            }

        return true;
    }
}
