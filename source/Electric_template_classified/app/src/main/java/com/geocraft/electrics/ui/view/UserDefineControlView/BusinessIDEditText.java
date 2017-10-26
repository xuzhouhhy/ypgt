package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.content.Context;
import android.util.AttributeSet;

import com.andreabaccega.widget.FormEditText;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;

import common.geocraft.untiltools.Tools;

/**
 * Created by Administrator on 2016/6/8.
 */
public class BusinessIDEditText extends FormEditText implements DataInterActionInterface {

	public BusinessIDEditText(Context context) {
		super(context);
	}

	public BusinessIDEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BusinessIDEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setControlValue(String text) {
		if(text.isEmpty())
		{
			setText(Tools.GetCurrentFormatTime());
		}
		else
		{
			setText(text);
		}
	}

	@Override
	public String getControlValue() {
		return this.getText().toString();
	}

	@Override
	public void setControlValue(FieldInfo fieldInfo, String text) {
		setControlValue(text);
	}
}
