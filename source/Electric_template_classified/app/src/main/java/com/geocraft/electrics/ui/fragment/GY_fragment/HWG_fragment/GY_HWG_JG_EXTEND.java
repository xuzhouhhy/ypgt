package com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_gy__hwg__jg_extend)
public class GY_HWG_JG_EXTEND extends LinearLayout {

    @ViewById
    LinearLayout linearLayoutRoot;

    public GY_HWG_JG_EXTEND(Context context) {
        super(context);
        init();
    }

    public GY_HWG_JG_EXTEND(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GY_HWG_JG_EXTEND(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

}
