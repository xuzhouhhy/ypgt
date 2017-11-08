package com.geocraft.electrics.sr.fragment.jz;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.sr.fragment.WellBaseInfoFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_gy_jz_dx)
public class JZ_BLX extends WellBaseInfoFragment {

    @ViewById
    LinearLayout linearLayoutRoot;


    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }

}
