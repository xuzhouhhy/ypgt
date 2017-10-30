package com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.sr.fragment.WellBaseFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_gy__dlxl__hwg)
public class GY_DLXL_HWG extends WellBaseFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }

}
