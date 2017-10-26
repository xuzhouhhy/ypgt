package com.geocraft.electrics.ui.fragment.business_basic_fragment;


import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * 低压电缆分支箱
 *
 * @author kingdon
 */
@EFragment(R.layout.fragment_branch_box)
public class LowVoltageBranchBoxBasicFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }
}
