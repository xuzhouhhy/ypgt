package com.geocraft.electrics.sr.view;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.sr.BasicFragmentFactory;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * 采集项处理
 */
@EViewGroup(R.layout.itemview_fragment_checklist)
public class FragmentItemView extends LinearLayout {
    @ViewById
    CheckBox ck;


    public FragmentItemView(Context context) {
        super(context);
    }

    public void bind(int position, String fragmentKey, String fragmentName, boolean isChecked,
                     CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        ck.setOnCheckedChangeListener(onCheckedChangeListener);
        ck.setText(fragmentName);
        ck.setChecked(isChecked);
        ViewHodler viewHodler = new ViewHodler();
        viewHodler.setPosition(position);
        ck.setTag(viewHodler);
        if (BasicFragmentFactory.isBaseFragment(fragmentKey)) {
            ck.setClickable(false);
        } else {
            ck.setClickable(true);
        }
    }

    public static class ViewHodler {
        private int mPosition;

        public int getPosition() {
            return mPosition;
        }

        public void setPosition(int position) {
            mPosition = position;
        }
    }

}
