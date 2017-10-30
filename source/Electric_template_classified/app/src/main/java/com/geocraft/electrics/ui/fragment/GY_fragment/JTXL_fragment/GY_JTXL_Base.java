package com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.sr.fragment.WellBaseInfoFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_gy__jtxl__base)
public class GY_JTXL_Base extends WellBaseInfoFragment {

    @ViewById
    LinearLayout linearLayoutRoot;


    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }

}
