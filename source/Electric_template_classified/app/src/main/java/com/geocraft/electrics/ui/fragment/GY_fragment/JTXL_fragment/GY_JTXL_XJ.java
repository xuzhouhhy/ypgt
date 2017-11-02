package com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.sr.fragment.WellBaseFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_gy__jtxl__xj)
public class GY_JTXL_XJ extends WellBaseFragment {
    @ViewById
    LinearLayout linearLayoutRoot;


    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }
}
