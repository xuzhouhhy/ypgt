package com.geocraft.electrics.sr.spacer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.DataSet;
import com.huace.log.logger.L;


/**
 * 间隔适配器
 */
public class SpacerAdapter extends BaseAdapter {
    private SpacerController mController;
    private Context mContext;

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (null == buttonView.getTag()) {
                        return;
                    }
                    updateFragmentStatus(buttonView, isChecked);
                }
            };

    public SpacerAdapter(Context context, SpacerController controller) {
        mContext = context;
        mController = controller;
    }

    private void updateFragmentStatus(CompoundButton buttonView, boolean isChecked) {
        // TODO: 2017/11/1
    }

    @Override
    public int getCount() {
        return mController.getDataSets().size();
    }

    @Override
    public Object getItem(int position) {
        return mController.getDataSets().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpacerItemView itemView = SpacerItemView_.build(parent.getContext());
        if (position % 2 != 0) {
            itemView.setBackgroundResource(R.drawable.selector_iv_bg_odd);
        } else {
            itemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
        }
        DataSet temp = mController.getDataSets().get(position);
        String status = temp.GetFieldValueByAlias(Constants.DATA_SET_STATE_ALIAS);

        String firstField = temp.GetFieldNameByName(temp.First) + ":";
        String first = temp.GetFieldValueByName(temp.First);
        String secondField = temp.GetFieldNameByName(temp.Second) + ":";
        String second = temp.GetFieldValueByName(temp.Second);
        String thirdField = temp.GetFieldNameByName(temp.Third) + ":";
        String third = temp.GetFieldValueByName(temp.Third);

        itemView.bind((position + 1) + "", firstField, first, secondField, second,
                thirdField, third, temp.isShowInDeviceList());
        itemView.setNumberColor(getStatus(status));
        itemView.setSelected(true);
        return itemView;
    }

    public int getStatus(String status) {
        if (status.isEmpty()) {
            L.w("null string got");
        }
        if (Constants.DATA_SET_STATE_IMPORT.equals(status)) {
            return Constants.STATUS_IMPORT;
        } else if (Constants.DATA_SET_STATE_EDIT.equals(status)) {
            return Constants.STATUS_EDIT;
        } else if (Constants.DATA_SET_STATE_CREATE.equals(status)) {
            return Constants.STATUS_CREATE;
        } else {
            return Constants.STATUS_CREATE;
        }
    }

}
