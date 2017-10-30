package com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.fragment.WellBaseFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_gy__jtxl__base)
public class GY_JTXL_Base extends WellBaseFragment {

    @ViewById
    LinearLayout linearLayoutRoot;


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
