package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.content.Context;
import android.util.AttributeSet;

import com.andreabaccega.widget.FormEditText;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;

/**
 * Created by Administrator on 2016/6/8.
 */
public class BusinessEditText extends FormEditText implements DataInterActionInterface {

	public BusinessEditText(Context context) {
		super(context);
	}

	public BusinessEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BusinessEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setControlValue(String text) {
		this.setText(text);
	}

	@Override
	public String getControlValue() {
		return this.getText().toString();
	}

	@Override
	public void setControlValue(FieldInfo fieldInfo, String text) {
		setText(text);
	}
}
