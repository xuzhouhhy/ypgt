package com.geocraft.electrics.sr.spacer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.ui.absinterface.OnClickEffectiveListener;
import com.geocraft.electrics.ui.view.DeviceShowItemView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * 间隔列表视图
 */
@EViewGroup(R.layout.itemview_spacer_list)
public class SpacerItemView extends LinearLayout {
    @ViewById
    TextView txtNumber;
    @ViewById
    TextView txtName;
    @ViewById
    TextView txtKind;
    @ViewById
    TextView txtType;
    @ViewById
    Button btnMenu;
    @ViewById
    LinearLayout linearLayContent;

    public SpacerItemView(Context context) {
        super(context);
    }

    public void bind(String number, String firstField, String firstValue, String secondField,
                     String secondValue, String thirdField, String thirdValue,
                     boolean isShowMenu) {
        txtNumber.setText(number);
        txtName.setText(firstField + firstValue);
        txtKind.setText(secondField + secondValue);
        txtType.setText(thirdField + thirdValue);
        if (isShowMenu) {
            btnMenu.setVisibility(VISIBLE);
        } else {
            btnMenu.setVisibility(GONE);
        }
        if (secondValue.isEmpty() && thirdValue.isEmpty()) {
            linearLayContent.setVisibility(View.GONE);
        } else {
            linearLayContent.setVisibility(View.VISIBLE);
            if (thirdValue.isEmpty()) {
                txtType.setText(null);
                txtType.setVisibility(GONE);
            }
            if (secondValue.isEmpty()) {
                txtKind.setText(null);
                txtKind.setVisibility(GONE);
            }
        }
    }

    public void setNumberColor(int status) {
        switch (status) {
            default:
            case Constants.STATUS_CREATE: {
                txtNumber.setTextColor(Color.GREEN);
            }
            break;
            case Constants.STATUS_EDIT: {
                txtNumber.setTextColor(Color.RED);
            }
            break;
            case Constants.STATUS_IMPORT: {
                txtNumber.setTextColor(Color.BLACK);
            }
            break;
        }
    }

    public void setOnClickListener(OnClickEffectiveListener mOnClickEffectiveListener,
                                   int primaryKey) {
        DeviceShowItemView.ViewHodler viewHodler = new DeviceShowItemView.ViewHodler();
        viewHodler.setPrimaryKey(primaryKey);
        btnMenu.setTag(viewHodler);
        btnMenu.setOnClickListener(mOnClickEffectiveListener);
    }

    public static class ViewHodler {
        private int mPrimaryKey;

        public int getPrimaryKey() {
            return mPrimaryKey;
        }

        public void setPrimaryKey(int primaryKey) {
            this.mPrimaryKey = primaryKey;
        }
    }

}
