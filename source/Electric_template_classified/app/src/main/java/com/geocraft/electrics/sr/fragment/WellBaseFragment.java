package com.geocraft.electrics.sr.fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.WellActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 */
@EFragment(R.layout.fragment_business)
public class WellBaseFragment extends BusinessFragment {

    protected DataSet mDataSet;
    protected Boolean mIsNew;
    protected LinearLayout mLinearLayout;

    @AfterViews
    protected void init() {
        mIsNew = ((WellActivity) this.getActivity()).getController().isCreateRecord();
        mDataSet = ((WellActivity) this.getActivity()).getController().getCurrentDataSet();
        super.initData(mIsNew, mDataSet);

    }
}

