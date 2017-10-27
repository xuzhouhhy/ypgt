package com.geocraft.electrics.ui.fragment.business_basic_fragment.advance;


import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * 环网柜
 */
@EFragment(R.layout.fragment_jrd_jb)
public class TowerMainFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }
}
