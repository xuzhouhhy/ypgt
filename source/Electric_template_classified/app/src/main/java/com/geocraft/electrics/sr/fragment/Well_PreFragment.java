package com.geocraft.electrics.sr.fragment;

import android.support.annotation.NonNull;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.sr.WellType;
import com.geocraft.electrics.sr.activity.WellActivity;
import com.geocraft.electrics.sr.controller.WellController;
import com.huace.log.logger.L;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_well_pre_base)
public class Well_PreFragment extends WellBaseInfoFragment {
    protected TaskManager taskManager = TaskManager_.getInstance_(
            ElectricApplication_.getInstance().getApplicationContext());
    protected DbManager dbManager = DbManager_.getInstance_(
            ElectricApplication_.getInstance().getApplicationContext());
    @ViewById
    LinearLayout linearLayoutRoot;
    @ViewById
    RadioGroup rg_tower_type;
    private WellController mWellController;
    private boolean mIsCreateForDefine;

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    WellType wellType = getWellType(checkedId);
                    mWellController.updateWellType(wellType);
                }
            };

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        mActivity = ((WellActivity) this.getActivity());
        mDataSet = mActivity.getController().getCurrentDataSet();
        mIsCreateForDefine = mActivity.getController().isCreateRecord();
        mIsNew = mIsCreateForDefine ?
                ((WellActivity) this.getActivity()).isGoNext() : false;
        mWellController = ((WellActivity) this.getActivity()).getController();
        super.initData(mIsNew, mDataSet);
        initDefineViewData();
    }

    private void initDefineViewData() {
        try {
            rg_tower_type.setOnCheckedChangeListener(mOnCheckedChangeListener);
            List<FieldInfo> fieldInfoList = mDataSet.FieldInfos;
            for (int i = 0; i < fieldInfoList.size(); i++) {
                FieldInfo fieldInfo = fieldInfoList.get(i);
                if (fieldInfo == null) {
                    continue;
                }
                //初始化类型
                if (rg_tower_type.getTag() != null && rg_tower_type.getTag().toString()
                        .equalsIgnoreCase(fieldInfo.Alias)) {
                    setWellType(fieldInfo.Value);
                }
            }
            updateRadioClickable(rg_tower_type, mIsCreateForDefine);
        } catch (Exception e) {
            L.printException(e);
        }
    }

    @Override
    public void getValue(DataSet dataSet) {
        getUserDefineData(dataSet);
        super.getValue(dataSet);
    }

    private void getUserDefineData(DataSet dataSet) {
        List<FieldInfo> fieldInfoList = dataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            if (mIsNew) {
                //类型 编辑状态不需要改变
                if (Enum.GY_JKXLTZXX_FIELD_GZlX.equalsIgnoreCase(fieldInfo.Name)) {
                    fieldInfo.Value = String.valueOf(getWellType(rg_tower_type
                            .getCheckedRadioButtonId()).ordinal());
                }
            }
            if (mIsNew) {
                //线路id 编辑状态不需要改变
                if (Enum.GY_JKXLTZXX_FIELD_LINEID.equalsIgnoreCase(fieldInfo.Name)) {
                    fieldInfo.Value = String.valueOf(mWellController.getLineId());
                }
            }
        }
    }

    private void updateRadioClickable(RadioGroup radioGroup, boolean isEnable) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(isEnable);
            radioGroup.getChildAt(i).setClickable(isEnable);
        }
    }

    @NonNull
    private WellType getWellType(int checkedId) {
        WellType wellType = WellType.JK;
        if (checkedId == R.id.rb_jk) {
            wellType = WellType.JK;
        } else if (checkedId == R.id.rb_dl) {
            wellType = WellType.DL;
        }
        return wellType;
    }

    private void setWellType(String value) {
        if (null == value || value.isEmpty()) {
            rg_tower_type.check(R.id.rb_jk);
            return;
        }
        if (value.equals(String.valueOf(WellType.JK.ordinal()))) {
            rg_tower_type.check(R.id.rb_jk);
        }
        if (value.equals(String.valueOf(WellType.DL.ordinal()))) {
            rg_tower_type.check(R.id.rb_dl);
        }
    }


}
