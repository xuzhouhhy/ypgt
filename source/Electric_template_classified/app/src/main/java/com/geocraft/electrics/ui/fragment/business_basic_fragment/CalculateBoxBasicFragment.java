package com.geocraft.electrics.ui.fragment.business_basic_fragment;


import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import common.geocraft.untiltools.Tools;

/**计量箱
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_jlx_jb)
public class CalculateBoxBasicFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;



    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }
}
