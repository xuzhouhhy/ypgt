package com.geocraft.electrics.ui.fragment.business_basic_fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * 柱上断路器
 * 作者  zhouqin
 * 时间 2016/6/29.
 */
@EFragment(R.layout.fragment_pd_zsdlq_jb)
public class PoleMountedcircuitbreakerBasicFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();

	}
}
