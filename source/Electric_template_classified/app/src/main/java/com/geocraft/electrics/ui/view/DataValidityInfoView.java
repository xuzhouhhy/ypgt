package com.geocraft.electrics.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.TextView;

import com.geocraft.electrics.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
@EViewGroup(R.layout.fragment_data_validity)
public class DataValidityInfoView extends ScrollView {

	@ViewById
	TextView tvFieldTitle;
	@ViewById
	TextView tvFieldErrorInfo;
	@ViewById
	TextView tvPhotoTitle;
	@ViewById
	TextView tvPhotoErrorInfo;

	public DataValidityInfoView(Context context) {
		super(context);
	}

	public DataValidityInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setFieldErrorInfo(List<String> errorInfo) {
		if (errorInfo.size() > 0) {
			tvFieldTitle.setVisibility(VISIBLE);
			String info = "";
			for (int i = 0; i < errorInfo.size(); i++) {
				info += errorInfo.get(i) + "   " +
						getContext().getString(R.string.field_not_allow_null) + "\n";
			}
			tvFieldErrorInfo.setText(info);
		} else {
			tvFieldTitle.setVisibility(GONE);
		}
	}

	public void setPhotoErrorInfo(List<String> errorInfo) {
		if (errorInfo.size() > 0) {
			tvPhotoTitle.setVisibility(VISIBLE);
			String info = "";
			for (int i = 0; i < errorInfo.size(); i++) {
				info += errorInfo.get(i) +
						getContext().getString(R.string.photo_not_allow_null) + "\n";
			}
			tvPhotoErrorInfo.setText(info);
		} else {
			tvPhotoTitle.setVisibility(GONE);
		}
	}

}

