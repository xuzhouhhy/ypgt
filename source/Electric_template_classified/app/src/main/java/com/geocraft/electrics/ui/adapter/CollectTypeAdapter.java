package com.geocraft.electrics.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.geocraft.electrics.R;
import com.geocraft.electrics.ui.controller.CollectTypeController;
import com.geocraft.electrics.ui.view.CollectTypeItemView;
import com.geocraft.electrics.ui.view.CollectTypeItemView_;


/**
 * Created by Administrator on 2016/6/6.
 */
public class CollectTypeAdapter extends BaseAdapter {

	private Context mContext;
	private CollectTypeController mController;

	public CollectTypeAdapter(Context context, CollectTypeController controller) {
		this.mContext = context;
		this.mController = controller;
	}

	@Override
	public int getCount() {
		return mController.getCollectTypeList().size();
	}

	@Override
	public Object getItem(int position) {
		return mController.getCollectTypeList().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		CollectTypeItemView collectTypeItemView;
		if (convertView == null) {
			collectTypeItemView = CollectTypeItemView_.build(mContext);
		} else {
			collectTypeItemView = (CollectTypeItemView) convertView;
		}
		collectTypeItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
		collectTypeItemView.setImage(mController.getItemBitmapByIndex(position));
		collectTypeItemView.setItemTextViewName(mController.getItemNameByIndex(position));
		return collectTypeItemView;
	}
}
