package com.geocraft.electrics.sr;

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
        ViewHodler viewHodler = new ViewHodler();
        viewHodler.setPosition(position);
        ck.setTag(viewHodler);
        ck.setOnCheckedChangeListener(onCheckedChangeListener);
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
