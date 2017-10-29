package com.geocraft.electrics.ui.fragment.GY_fragment.GY_DLFJX;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.fragment.WellBaseFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_gy__dlfjx__gzzsq)
public class GY_DLFJX_GZZSQ extends WellBaseFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

//    @ViewById
//    BusinessEditText etMC;
//    @ViewById
//    BusinessEditText etSJDW;
//    @ViewById
//    BusinessManager viewGLDW;

    @Override
    public boolean logicCheck() {
//        //判断该充电桩已存在
//        if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
//            DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
//            List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.DATA_SET_TOWER_ID, etMC.getControlValue(), false);
//            if (dataSetList.size() > 0) {
//                T.show(getContext(), getString(R.string.exist_tower_id), Toast.LENGTH_SHORT);
//                return false;
//            }
//        }

        return true;
    }

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }


    @Override
    public void getValue(DataSet dataSet) {

//        if(!viewGLDW.strSecondManager.isEmpty()) {
//            etSJDW.setControlValue(viewGLDW.strSecondManager);
//        }
        super.getValue(dataSet);
    }
}
