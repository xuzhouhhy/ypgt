package com.geocraft.electrics.sr.fragment;


import android.widget.LinearLayout;

import com.geocraft.electrics.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * 环网柜
 */
@EFragment(R.layout.fragment_jrd_jb)
public class HWGBasicFragment extends WellBaseFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }
}
