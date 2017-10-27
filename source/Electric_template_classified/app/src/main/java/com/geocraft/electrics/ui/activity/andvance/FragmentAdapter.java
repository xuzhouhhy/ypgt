package com.geocraft.electrics.ui.activity.andvance;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.geocraft.electrics.R;


/**
 */
public class FragmentAdapter extends BaseAdapter {
    private TowerController mController;
    private Context mContext;

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mController.setFragment(isChecked, (BasicFragmentFactory.DataFragment)
                            getItem(FragmentItemView.sPosition));
                }
            };

    public FragmentAdapter(Context context, TowerController controller) {
        mContext = context;
        mController = controller;
    }

    @Override
    public int getCount() {
        return mController.getDataFragments().size();
    }

    @Override
    public Object getItem(int position) {
        return mController.getDataFragments().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FragmentItemView fragmentItemView = null;
        if (convertView == null) {
            fragmentItemView = FragmentItemView_.build(parent.getContext());
        } else {
            fragmentItemView = (FragmentItemView) convertView;
        }
        if (position % 2 != 0) {
            fragmentItemView.setBackgroundResource(R.drawable.selector_iv_bg_odd);
        } else {
            fragmentItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
        }
        BasicFragmentFactory.DataFragment dataFragment = (BasicFragmentFactory.DataFragment)
                getItem(position);
        fragmentItemView.bind(dataFragment.mFramentName);
        fragmentItemView.setOnCheckedChangeListener(mOnCheckedChangeListener, position);
        fragmentItemView.setSelected(true);
        return fragmentItemView;
    }

}
