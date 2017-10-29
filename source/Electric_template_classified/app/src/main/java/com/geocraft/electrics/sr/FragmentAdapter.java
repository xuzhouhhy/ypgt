package com.geocraft.electrics.sr;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.event.CheckFragmentEvent;


/**
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
        BasicFragmentFactory.FragmentDatasetOption fragmentDatasetOption =
                (BasicFragmentFactory.FragmentDatasetOption)
                        getItem(viewHodler.getPosition());
        fragmentDatasetOption.setChecked(isChecked);
        ElectricApplication.BUS.post(new CheckFragmentEvent());
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
        FragmentItemView fragmentItemView = null;
        if (convertView == null) {
            fragmentItemView = com.geocraft.electrics.sr.FragmentItemView_.build(parent.getContext());
        } else {
            fragmentItemView = (FragmentItemView) convertView;
        }
        fragmentItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
        BasicFragmentFactory.FragmentDatasetOption datasetOption =
                (BasicFragmentFactory.FragmentDatasetOption) getItem(position);
        boolean isChecked = datasetOption.isChecked();
        fragmentItemView.bind(position, datasetOption.getFramentName(), isChecked);
        fragmentItemView.setOnCheckedChangeListener(mOnCheckedChangeListener);
        fragmentItemView.setSelected(true);
        return fragmentItemView;
    }

}
