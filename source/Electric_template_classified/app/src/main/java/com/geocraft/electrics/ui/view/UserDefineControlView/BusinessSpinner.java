package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.geocraft.electrics.R;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/8.
 */
public class BusinessSpinner extends Spinner implements DataInterActionInterface{

	ArrayList<String> dataList = new ArrayList<>();
	ArrayAdapter<String> dataAdapter;

	String mValue;


	public BusinessSpinner(Context context) {
		super(context, Spinner.MODE_DIALOG);
	}

	public BusinessSpinner(Context context, int mode) {
		super(context, Spinner.MODE_DIALOG);
	}

	public BusinessSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public int geteControlType() {
		return PropertyDictionay.OperateCode.Type_MenuList;
	}

	@Override
	public void setControlValue(String text) {
		if (dataList.contains(text)) {
			this.setSelection(dataList.indexOf(text));
			mValue = text;
		} else {
			this.setSelection(dataList.indexOf(""));
		}
	}

	@Override
	public String getControlValue() {
		return this.getSelectedItem() != null ? this.getSelectedItem().toString() : "";
	}

	@Override
	public void setControlValue(FieldInfo fieldInfo, String text) {

		dataList = fieldInfo.Dictionay.menuList;
		dataList.add("");
		dataAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.simple_spinner_item, dataList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				if (position == dataList.size() - 1) {
					view.setVisibility(INVISIBLE);
				}
				return view;
			}

			@Override
			public int getCount() {
				return super.getCount() - 1;
			}
		};
		dataAdapter.setDropDownViewResource(R.layout.item_single_choice);
		this.setAdapter(dataAdapter);

		setControlValue(text);
	}

}
