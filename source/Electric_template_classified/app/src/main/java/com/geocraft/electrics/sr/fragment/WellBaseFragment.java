package com.geocraft.electrics.sr.fragment;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.sr.activity.WellActivity;

import org.androidannotations.annotations.EFragment;

/**
 * 井号采集fragment基类
 */
@EFragment(R.layout.fragment_business)
public class WellBaseFragment extends BusinessFragment {
    protected WellActivity mActivity;

    @Override
    protected void init() {
        mActivity = ((WellActivity) this.getActivity());
        mIsNew = mActivity.getController().isCreateRecord();
        mDataSet = mActivity.getController().getCurrentDataSet();
        super.initData(mIsNew, mDataSet);
    }
}

