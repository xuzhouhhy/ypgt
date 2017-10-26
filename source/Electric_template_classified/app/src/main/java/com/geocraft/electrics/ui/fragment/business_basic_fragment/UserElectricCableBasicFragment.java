package com.geocraft.electrics.ui.fragment.business_basic_fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import common.geocraft.untiltools.Tools;

/**
 * 用户电缆段
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_yhdld_jb)
public class UserElectricCableBasicFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();
	}
}
