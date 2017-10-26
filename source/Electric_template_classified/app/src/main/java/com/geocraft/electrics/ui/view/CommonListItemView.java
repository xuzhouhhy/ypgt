package com.geocraft.electrics.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.huace.log.logger.L;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@EViewGroup(R.layout.itemview_commonlist)
public class CommonListItemView extends LinearLayout {

    @ViewById
    TextView itemTextCommonList;
    @ViewById
    ImageView itemImageCommonList;
    public CommonListItemView(Context context) {
        super(context);
    }
    public void bind(Bitmap bitmap, String Name) {
        try {
            itemImageCommonList.setImageBitmap(bitmap);
            itemTextCommonList.setText(Name);
        } catch (Exception e) {
            L.printException(e);
        }
    }


}
