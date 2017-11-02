package com.geocraft.electrics.sr.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zhongshibu02 on 2017/11/2.
 */
@EViewGroup(R.layout.itemview_interval)
public class IntervalViewItem extends LinearLayout{

    @ViewById
    TextView tvIntervalName;

    public IntervalViewItem(Context context) {
        super(context);
    }

    public void bind(String intervalName) {
        tvIntervalName.setText(intervalName);
    }
}
