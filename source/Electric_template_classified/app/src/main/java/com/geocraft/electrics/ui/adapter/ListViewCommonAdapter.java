package com.geocraft.electrics.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.geocraft.electrics.R;
import com.geocraft.electrics.ui.controller.CommonListController;
import com.geocraft.electrics.ui.view.CommonListItemView;
import com.geocraft.electrics.ui.view.CommonListItemView_;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
public class ListViewCommonAdapter extends BaseAdapter {
    CommonListController mController;

    public ListViewCommonAdapter(CommonListController controller) {
        mController = controller;
    }

    @Override
    public int getCount() {
        return mController.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return mController.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonListItemView commonListItemView;
        if (convertView == null) {
            commonListItemView = CommonListItemView_.build(parent.getContext());
        } else {
            commonListItemView = (CommonListItemView) convertView;
        }

        if(position % 2 != 0){
            commonListItemView.setBackgroundResource(R.drawable.selector_iv_bg_odd);
        }else{
            commonListItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
        }

        commonListItemView.bind(mController.getItems().get(position).mBitmap,
                mController.getItems().get(position).mAlias);
        return commonListItemView;
    }
}
