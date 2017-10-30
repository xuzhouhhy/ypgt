package com.geocraft.electrics.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.ui.absinterface.OnClickEffectiveListener;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity;
import com.geocraft.electrics.ui.controller.DeviceShowListController;
import com.geocraft.electrics.ui.view.DeviceShowItemView;
import com.geocraft.electrics.ui.view.DeviceShowItemView_;
import com.huace.log.logger.L;


/**
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
public class DeviceShowListAdapter extends BaseAdapter {
    private DeviceShowListController mController;
    /**
     * 线路名称
     */
    private Context mContext;

    private OnClickEffectiveListener mOnClickEffectiveListener = new OnClickEffectiveListener() {
        @Override
        public void onEffectiveClick(View v) {
            DeviceShowItemView.ViewHodler viewHodler =
                    (DeviceShowItemView.ViewHodler) v.getTag();
            ((DeviceShowListActivity) mContext).openActivity(viewHodler.getPrimaryKey());
        }
    };

    public DeviceShowListAdapter(Context context, DeviceShowListController controller) {
        mContext = context;
        mController = controller;
    }

    @Override
    public int getCount() {
        return mController.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return mController.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DeviceShowItemView deviceShowItemView = DeviceShowItemView_.build(parent.getContext());
        if (position % 2 != 0) {
            deviceShowItemView.setBackgroundResource(R.drawable.selector_iv_bg_odd);
        } else {
            deviceShowItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
        }
        DataSet temp = mController.getItems().get(position);
        String status = temp.GetFieldValueByAlias(Constants.DATA_SET_STATE_ALIAS);

        String firstField = temp.GetFieldNameByName(temp.First) + ":";
        String first = temp.GetFieldValueByName(temp.First);
        String secondField = temp.GetFieldNameByName(temp.Second) + ":";
        String second = temp.GetFieldValueByName(temp.Second);
        String thirdField = temp.GetFieldNameByName(temp.Third) + ":";
        String third = temp.GetFieldValueByName(temp.Third);

        deviceShowItemView.bind((position + 1) + "", firstField, first, secondField, second,
                thirdField, third, temp.isShowInDeviceList());
        deviceShowItemView.setNumberColor(getStatus(status));
        deviceShowItemView.setSelected(true);
        deviceShowItemView.setOnClickListener(mOnClickEffectiveListener, temp.PrimaryKey);
        return deviceShowItemView;
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
