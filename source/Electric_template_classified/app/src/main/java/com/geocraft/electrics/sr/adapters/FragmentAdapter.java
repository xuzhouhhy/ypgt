package com.geocraft.electrics.sr.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.event.CheckFragmentEvent;
import com.geocraft.electrics.sr.FragmentOption;
import com.geocraft.electrics.sr.controller.WellController;
import com.geocraft.electrics.sr.view.FragmentItemView;


/**
 * 采集可选项
 */
public class FragmentAdapter extends BaseAdapter {
    private WellController mController;
    private Context mContext;

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (null == buttonView.getTag()) {
                        return;
                    }
                    updateFragmentStatus(buttonView, isChecked);
                }
            };

    public FragmentAdapter(Context context, WellController controller) {
        mContext = context;
        mController = controller;
    }

    private void updateFragmentStatus(CompoundButton buttonView, boolean isChecked) {
        FragmentItemView.ViewHodler viewHodler =
                (FragmentItemView.ViewHodler) buttonView.getTag();
        ElectricApplication.BUS.post(new CheckFragmentEvent(viewHodler.getPosition(), isChecked));
    }

    @Override
    public int getCount() {
        return mController.getCurFragmentDatasetOptions().size();
    }

    @Override
    public Object getItem(int position) {
        return mController.getCurFragmentDatasetOptions().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FragmentItemView fragmentItemView =
                com.geocraft.electrics.sr.view.FragmentItemView_.build(parent.getContext());
        fragmentItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
        FragmentOption datasetOption = (FragmentOption) getItem(position);
        boolean isChecked = datasetOption.isChecked();
        boolean isVisible = true;
        String parentNamekey = datasetOption.getParentNameKey();
        if (null == parentNamekey || parentNamekey.isEmpty()) {
            isVisible = false;
        }
        if (null == datasetOption.getParentNameKey()) {
            fragmentItemView.bind(position, isVisible, datasetOption.getFramentNameKey(),
                    datasetOption.getFramentName(), isChecked, mOnCheckedChangeListener);
        }
        return fragmentItemView;
    }

}
