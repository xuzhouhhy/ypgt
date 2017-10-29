package com.geocraft.electrics.sr;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;
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
@EViewGroup(R.layout.sr_itemview_photo_manager)
public class SrPhotoManagerItemView extends LinearLayout {

    @ViewById
    TextView tvPhotoType;
    @ViewById
    ImageView imgVPhoto;
    @ViewById
    Button btnTakePhoto;
    @ViewById
    Button btnImportPhoto;
    @ViewById
    Button btnDeletePhoto;
    private Context mContext;

    public SrPhotoManagerItemView(Context context) {
        super(context);
        this.mContext = context;
    }

    @AfterViews
    void init() {
        int interval = (int) mContext.getResources().getDimension(R.dimen
                .grid_view_horizontal_interval);
        imgVPhoto.setPadding(interval, 0, interval, 0);
        int width = (int) (ScreenUtils.getScreenWidth(mContext) / 3.0);
        int height = (int) (width * 4 / 3.0);
        imgVPhoto.setLayoutParams(new LayoutParams(width, height));
    }

    public void setPhotoType(String photoType) {
        this.tvPhotoType.setText(photoType);
    }

    public void setImgVPhoto(Drawable drawable) {
        if (drawable != null) {
            this.imgVPhoto.setImageDrawable(drawable);
        }
    }

    public void setButtonOnClickListener(OnClickListener listener, int position) {
        SrPhotoManagerItemView.ViewHolder viewHolder = new SrPhotoManagerItemView.ViewHolder();
        viewHolder.setPosition(position);
        btnTakePhoto.setTag(viewHolder);
        btnTakePhoto.setOnClickListener(listener);
        btnImportPhoto.setTag(viewHolder);
        btnImportPhoto.setOnClickListener(listener);
        btnDeletePhoto.setTag(viewHolder);
        btnDeletePhoto.setOnClickListener(listener);
        imgVPhoto.setTag(viewHolder);
        imgVPhoto.setOnClickListener(listener);
    }

    public static class ViewHolder {

        private int mPosition;

        public int getPosition() {
            return mPosition;
        }

        public void setPosition(int mPosition) {
            this.mPosition = mPosition;
        }
    }
}
