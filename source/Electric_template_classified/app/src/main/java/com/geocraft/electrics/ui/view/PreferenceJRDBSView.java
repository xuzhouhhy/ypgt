package com.geocraft.electrics.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import common.geocraft.untiltools.ConvertHelper;

/**
 * 作者  zhouqin
 * 时间 2016/7/4.
 */
@EViewGroup(R.layout.itemview_search_jdrbs_prefer)
public class PreferenceJRDBSView extends LinearLayout {

	@ViewById
	TextView tvName;
	@ViewById
	TextView tvDistance;

	public PreferenceJRDBSView(Context context) {
		super(context);
	}

	public PreferenceJRDBSView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public PreferenceJRDBSView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public void bind(String name,String distance){
		tvName.setText(name);
		tvDistance.setText(distance);
	}
	public String  getName(){
		return tvName.getText().toString();
	}

	public boolean isDistanceValid(){
		String distance  = tvDistance.getText().toString();
		double dis = ConvertHelper.ConverDouble(distance);
		if (dis>2){
			return false;
		}
		else {
			return true;
		}
	}
}
