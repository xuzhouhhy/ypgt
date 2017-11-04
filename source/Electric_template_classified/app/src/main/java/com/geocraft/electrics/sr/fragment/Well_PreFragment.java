package com.geocraft.electrics.sr.fragment;

import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
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
import com.geocraft.electrics.sr.event.UpdateWellNameArgs;
import com.geocraft.electrics.sr.manager.DataManager;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.utils.NameFormatter;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;

import common.geocraft.untiltools.T;

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
    @ViewById
    BusinessEditText F_JZID;
    @Bean
    DataManager mDataManager;
    private String WELL_NAME_PRIX = "#";
    private String WELL_FISTR_NAME = "#001";
    private String WELL_KBS_NAME = "#000";
    private String WELL_NAME_FORMAT = "000";
    private int WELL_NAME_LENGTH_LIMIT = 4;
    private WellController mWellController;
    private boolean mIsCreateForDefine;

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    WellType wellType = getWellType(checkedId);
                    mWellController.switchWellType(wellType);
                }
            };

    @Override
    protected void init() {
        if (!ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.register(this);
        }
        mLinearLayout = linearLayoutRoot;
        mActivity = ((WellActivity) this.getActivity());
        mDataSet = mActivity.getController().getCurrentDataSet();
        mIsCreateForDefine = mActivity.getController().isCreateRecord();
        mIsNew = mIsCreateForDefine && ((WellActivity) this.getActivity()).isGoNext();
        mWellController = ((WellActivity) this.getActivity()).getController();
        super.initData(mIsNew, mDataSet);
        initDefineViewData();
    }

    @Override
    public void getValue(DataSet dataSet) {
        super.getValue(dataSet);
        getUserDefineData(dataSet);
    }

    @Override
    public boolean logicCheck() {
        return isDataValid();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onDataSynEvent(UpdateWellNameArgs event) {
        updateWellNameEditable(F_JZID, mIsCreateForDefine);
        if (mIsCreateForDefine) {
            F_JZID.setText(initWellName(""));
        }
    }

    @Override
    public void onDestroyView() {
        if (ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.unregister(this);
        }
        super.onDestroyView();
    }

    private boolean isDataValid() {
        String curWellName = F_JZID.getText().toString();
        int value = 0;
        if (mIsCreateForDefine) {
            curWellName = formatInput(curWellName);
            String nextName = getNextWellName(mWellController.getLineId(),
                    mWellController.getWellType());
            nextName = nextName.substring(WELL_NAME_PRIX.length());
            value = curWellName.compareTo(nextName);
        }
        if (value < 0) {
            T.showShort(mActivity, R.string.well_name_is_too_samll);
            return false;
        }
        return true;
    }

    private String formatInput(String name) {
        if (null == name || name.isEmpty()) {
            name = getDefaultWellName(mWellController.getWellType());
        }
        int cnt = Integer.valueOf(name);
        String formatValue = new DecimalFormat(WELL_NAME_FORMAT).format(cnt);
        return formatValue;
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
                //基桩号处理
                if (Enum.GYCJ_LINE_F_GH.equals(fieldInfo.Name)) {
                    F_JZID.setText(initWellName(fieldInfo.Value));
                }
            }
            updateRadioClickable(rg_tower_type, mIsCreateForDefine);
            initWellNameControl();
        } catch (Exception e) {
            L.printException(e);
        }
    }

    private void initWellNameControl() {
        F_JZID.setFilters(new InputFilter[]{new InputFilter.LengthFilter(
                WELL_NAME_LENGTH_LIMIT)});
        F_JZID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String value = F_JZID.getText().toString();
                    value = formatInput(value);
                    F_JZID.setText(value);
                    F_JZID.setSelection(value.length());
                }
            }
        });
        updateWellNameEditable(F_JZID, mIsCreateForDefine);
    }

    private String getDefaultWellName(WellType wellType) {
        switch (wellType) {
            case JK:
                // the same with next DL
            case DL:
                return WELL_FISTR_NAME;
            case KBS:
                return WELL_KBS_NAME;
            default:
                return WELL_FISTR_NAME;
        }
    }

    private String getNextWellName(int lineId, WellType wellType) {
        List<String> wellNames = mDataManager.getWellNames_JK_DL(lineId);
        if (wellNames.size() == 0) {
            return getDefaultWellName(wellType);
        }
        return NameFormatter.getNextNameWithDigitSuffixFormat(wellNames,
                WELL_NAME_PRIX, 1, WELL_NAME_FORMAT);
    }

    private String initWellName(String wellName) {
        String name = wellName;
        if (mWellController.getWellType() == WellType.KBS) {
            name = getDefaultWellName(mWellController.getWellType());
        }
        if (null == name || name.isEmpty()) {
            name = getNextWellName(mWellController.getLineId(), mWellController.getWellType());
        }
        return name.substring(WELL_NAME_PRIX.length());
    }

    private String getWellName() {
        String value = F_JZID.getText().toString();
        if (null == value || value.isEmpty()) {
            return "";
        }
        value = formatInput(value);
        return WELL_NAME_PRIX + value;
    }

    private void getUserDefineData(DataSet dataSet) {
        List<FieldInfo> fieldInfoList = dataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            if (Enum.GY_JKXLTZXX_FIELD_GZlX.equalsIgnoreCase(fieldInfo.Name)) {
                fieldInfo.Value = String.valueOf(getWellType(rg_tower_type
                        .getCheckedRadioButtonId()).ordinal());
            }
            if (Enum.GY_JKXLTZXX_FIELD_LINEID.equalsIgnoreCase(fieldInfo.Name)) {
                fieldInfo.Value = String.valueOf(mWellController.getLineId());
            }
            if (Enum.GYCJ_LINE_F_GH.equalsIgnoreCase(fieldInfo.Name)) {
                fieldInfo.Value = String.valueOf(getWellName());
            }
        }
    }

    private void updateRadioClickable(RadioGroup radioGroup, boolean isEnable) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(isEnable);
            radioGroup.getChildAt(i).setClickable(isEnable);
        }
    }

    private void updateWellNameEditable(View view, boolean isEnable) {
        if (mWellController.getWellType() == WellType.KBS) {
            isEnable = false;
        }
        view.setEnabled(isEnable);
        view.setClickable(isEnable);
    }

    @NonNull
    private WellType getWellType(int checkedId) {
        WellType wellType = WellType.JK;
        if (checkedId == R.id.rb_jk) {
            wellType = WellType.JK;
        } else if (checkedId == R.id.rb_dl) {
            wellType = WellType.DL;
        } else if (checkedId == R.id.rb_kbs) {
            wellType = WellType.KBS;
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
        } else if (value.equals(String.valueOf(WellType.DL.ordinal()))) {
            rg_tower_type.check(R.id.rb_dl);
        } else if (value.equals(String.valueOf(WellType.KBS.ordinal()))) {
            rg_tower_type.check(R.id.rb_kbs);
        }
    }


}
