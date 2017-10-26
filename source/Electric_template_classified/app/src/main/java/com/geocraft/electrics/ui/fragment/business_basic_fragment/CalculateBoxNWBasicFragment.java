package com.geocraft.electrics.ui.fragment.business_basic_fragment;


import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessScanEdit;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**终端设备
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_jlxnw_jb)
public class CalculateBoxNWBasicFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @ViewById
    BusinessScanEdit viewTMBH;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();

        //设置默认焦点
        viewTMBH.setFocus();
    }
}
