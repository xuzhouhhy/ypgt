package com.geocraft.electrics.ui.activity.andvance;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 */
@EViewGroup(R.layout.itemview_fragment_checklist)
public class FragmentItemView extends LinearLayout {
    public static int sPosition;
    @ViewById
    CheckBox ck;

    public FragmentItemView(Context context) {
        super(context);
    }

    public void bind(String fragmentName) {
        ck.setText(fragmentName);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener
                                                   onCheckedChangeListener, int position) {
        sPosition = position;
        ck.setOnCheckedChangeListener(onCheckedChangeListener);
    }

}
