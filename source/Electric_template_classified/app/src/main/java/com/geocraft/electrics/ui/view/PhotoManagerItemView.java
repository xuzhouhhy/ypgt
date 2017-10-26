package com.geocraft.electrics.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import common.geocraft.untiltools.ScreenUtils;

/**
 * Created by Administrator on 2016/6/7.
 */
@EViewGroup(R.layout.itemview_photo_manager)
public class PhotoManagerItemView extends LinearLayout {

	private Context mContext;

	@ViewById
	TextView tvPhotoType;
	@ViewById
	ImageView imgVPhoto;

	public PhotoManagerItemView(Context context) {
		super(context);
		this.mContext = context;
	}

	@AfterViews
	void init() {
		int interval = (int)mContext.getResources().getDimension(R.dimen
				.grid_view_horizontal_interval);
		imgVPhoto.setPadding(interval, 0, interval, 0);
		int width = (int)(ScreenUtils.getScreenWidth(mContext) / 3.0);
		int height = (int) (width * 4 / 3.0);
		imgVPhoto.setLayoutParams(new LinearLayout.LayoutParams(width, height));
	}

	public void setPhotoType(String photoType) {
		this.tvPhotoType.setText(photoType);
	}

	public void setImgVPhoto(Drawable drawable) {
		if (drawable != null) {
			this.imgVPhoto.setImageDrawable(drawable);
		}
	}

}
