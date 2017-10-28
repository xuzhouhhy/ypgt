package com.geocraft.electrics.sr;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.geocraft.electrics.R;


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
                    FragmentItemView.ViewHodler viewHodler =
                            (FragmentItemView.ViewHodler) buttonView.getTag();
                    mController.updateFragment(isChecked,
                            (BasicFragmentFactory.FragmentDatasetOption)
                                    getItem(viewHodler.getPosition()));
                }
            };

    public FragmentAdapter(Context context, WellController controller) {
        mContext = context;
        mController = controller;
    }

    @Override
    public int getCount() {
        return mController.getFragmentDatasetOptions().size();
    }

    @Override
    public Object getItem(int position) {
        return mController.getFragmentDatasetOptions().get(position);
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
        BasicFragmentFactory.FragmentDatasetOption fragmentDatasetOption = (BasicFragmentFactory.FragmentDatasetOption)
                getItem(position);
        fragmentItemView.bind(fragmentDatasetOption.getFramentName());
        fragmentItemView.setOnCheckedChangeListener(mOnCheckedChangeListener, position);
        fragmentItemView.setSelected(true);
        return fragmentItemView;
    }

}