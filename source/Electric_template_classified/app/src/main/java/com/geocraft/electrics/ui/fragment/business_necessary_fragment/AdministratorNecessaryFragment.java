package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * 管理单位
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_xzq_bt)
public class AdministratorNecessaryFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }
}
