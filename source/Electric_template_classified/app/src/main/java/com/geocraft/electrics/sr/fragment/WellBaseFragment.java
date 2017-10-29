package com.geocraft.electrics.sr.fragment;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.sr.WellActivity;

import org.androidannotations.annotations.EFragment;

/**
 * 井号采集fragment基类
 */
@EFragment(R.layout.fragment_business)
public class WellBaseFragment extends BusinessFragment {

    @Override
    protected void init() {
        mIsNew = ((WellActivity) this.getActivity()).getController().isCreateRecord();
        mDataSet = ((WellActivity) this.getActivity()).getController().getCurrentDataSet();
        super.initData(mIsNew, mDataSet);
    }
}

