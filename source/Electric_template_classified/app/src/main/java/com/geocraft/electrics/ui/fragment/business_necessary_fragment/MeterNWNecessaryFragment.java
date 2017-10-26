package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessScanEdit;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 变电站信息
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_dnbnw_bt)
public class MeterNWNecessaryFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @ViewById
    BusinessScanEdit viewTMBH;

    @ViewById
    BusinessEditText etSSBYQ;
    @ViewById
    BusinessEditText etSSJLX;
    @ViewById
    BusinessEditText etJLXBH;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();

        //控制界面显示
        DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();

        //如果是新建，将名称带过来
        if(((RecordActivity)getActivity()).getController().isCreateRecord())
        {
            //初始化所属变压器
            int primaryKey = ((RecordActivity) getActivity()).getController().getDataSetParentKey();
            DataSet queyDataSet = taskManager.getDataSource().getDataSetByName(dataSet.GroupName, Enum.DATA_SET_NAME_JLX);
            queyDataSet.PrimaryKey = primaryKey;
            queyDataSet = dbManager.queryByPrimaryKey(queyDataSet, true);
            if (queyDataSet != null) {
                etSSBYQ.setControlValue(queyDataSet.GetFieldValueByName(Enum.CALCULATEBOX_FILED_SSBYQ));
                etSSJLX.setControlValue(queyDataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_MC));
                etJLXBH.setControlValue(queyDataSet.GetFieldValueByName(Enum.CALCULATEBOX_FILED__TMBH));
            }
        }
    }

    @Override
    public boolean logicCheck() {
        //判断杆塔是否存在
        if(((RecordActivity)getActivity()).getController().isCreateRecord())
        {
            DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
            List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.METER_FILED__TMBH, viewTMBH.getControlValue(), false);
            if (dataSetList.size() > 0) {
                T.show(getContext(), getString(R.string.dnb_exsit), Toast.LENGTH_SHORT);
                return false;
            }
        }

        return true;
    }
}
