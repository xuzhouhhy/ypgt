package com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.entity.DataSet;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GY_FZX_FHKG.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GY_FZX_FHKG#newInstance} factory method to
 * create an instance of this fragment.
 */
@EFragment(R.layout.fragment_gy__fzx__fhkg)
public class GY_FZX_FHKG extends BusinessFragment {

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
