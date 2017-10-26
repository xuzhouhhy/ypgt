package com.geocraft.electrics.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * 作者  zhouqin
 * 时间 2016/6/5.
 */
@EViewGroup(R.layout.itemview_collect_type_gridview)
public class CollectTypeItemView extends RelativeLayout {

	@ViewById
	ImageView itemImageViewContent;
	@ViewById
	TextView itemTextViewName;

	public CollectTypeItemView(Context context) {
		super(context);
	}

	public void setImage(Bitmap bitmap) {
		if (bitmap != null) {
			this.itemImageViewContent.setImageBitmap(bitmap);
		}
	}

	public void setItemTextViewName(String name) {
		if (name != null) {
			this.itemTextViewName.setText(name);
		}
	}

}
