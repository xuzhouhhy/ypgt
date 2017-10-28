package com.geocraft.electrics.sr.fragment;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.sr.FragmentAdapter;
import com.geocraft.electrics.sr.WellActivity;
import com.geocraft.electrics.sr.WellController;
import com.geocraft.electrics.sr.WellType;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 环网柜
 */
@EFragment(R.layout.acitivity_tower_main)
public class WellMainFragment extends Fragment {
    protected DataSet mDataSet;
    protected Boolean mIsNew;
    protected TaskManager taskManager = TaskManager_.getInstance_(
            ElectricApplication_.getInstance().getApplicationContext());
    protected DbManager dbManager = DbManager_.getInstance_(
            ElectricApplication_.getInstance().getApplicationContext());
    @ViewById
    LinearLayout linearLayoutRoot;
    @ViewById
    RadioGroup rg_tower_type;
    @ViewById
    EditText edt_F_GH;
    @ViewById
    GridView grd_ck_fragment;
    private FragmentAdapter mFragmentAdapter;
    private WellController mWellController;
    RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    WellType wellType = getWellType(checkedId);
                    mWellController.updateWellType(wellType);
                    mFragmentAdapter.notifyDataSetChanged();
                }
            };
    private WellActivity mContext;

    @NonNull
    private WellType getWellType(int checkedId) {
        WellType wellType = WellType.JK;
        if (checkedId == R.id.rb_jk) {
            wellType = WellType.JK;
        } else if (checkedId == R.id.rb_dl) {
            wellType = WellType.DL;
        } else if (checkedId == R.id.rb_dy) {
            wellType = WellType.DY;
        }
        return wellType;
    }

    @AfterViews
    protected void init() {
        mContext = ((WellActivity) this.getActivity());
        mWellController = mContext.getController();
        mIsNew = mWellController.isCreateRecord();
        mDataSet = mWellController.getCurrentDataSet();
        initViewData();
    }

    private void initViewData() {
        try {
            rg_tower_type.setOnCheckedChangeListener(mOnCheckedChangeListener);
            mFragmentAdapter = new FragmentAdapter(this.getActivity(), mWellController);
            grd_ck_fragment.setAdapter(mFragmentAdapter);
            List<FieldInfo> fieldInfoList = mDataSet.FieldInfos;
            for (int i = 0; i < fieldInfoList.size(); i++) {
                FieldInfo fieldInfo = fieldInfoList.get(i);
                if (fieldInfo == null) {
                    continue;
                }
                if (edt_F_GH.getTag() != null && edt_F_GH.getTag().toString()
                        .equalsIgnoreCase(fieldInfo.Alias)) {
                    if (mIsNew) {
                        edt_F_GH.setText(fieldInfo.Default);
                    } else {
                        edt_F_GH.setText(fieldInfo.Value);
                    }
                }
                if (rg_tower_type.getTag() != null && rg_tower_type.getTag().toString()
                        .equalsIgnoreCase(fieldInfo.Alias)) {
                    if (mIsNew) {
                        setWellType(fieldInfo.Default);
                    } else {
                        setWellType(fieldInfo.Value);
                    }
                }
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    public void getValue() {
        String wellName = edt_F_GH.getText().toString();
        if (wellName.equals("")) {
            T.showShort(mContext, mContext.getString(R.string.well_name_empty));
            return;
        }
        List<FieldInfo> fieldInfoList = mDataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            if (edt_F_GH.getTag() != null && edt_F_GH.getTag().toString()
                    .equalsIgnoreCase(fieldInfo.Alias)) {
                fieldInfo.Value = wellName;
            }
            if (rg_tower_type.getTag() != null && rg_tower_type.getTag().toString()
                    .equalsIgnoreCase(fieldInfo.Alias)) {
                fieldInfo.Value = String.valueOf(getWellType());
            }
        }
    }

    private WellType getWellType() {
        return getWellType(rg_tower_type.getCheckedRadioButtonId());
    }

    private void setWellType(String value) {
        if (null == value || value.isEmpty()) {
            rg_tower_type.check(R.id.rb_jk);
        }
        if (value.equals(String.valueOf(WellType.JK.ordinal()))) {
            rg_tower_type.check(R.id.rb_jk);
        }
        if (value.equals(String.valueOf(WellType.DL.ordinal()))) {
            rg_tower_type.check(R.id.rb_dl);
        }
        if (value.equals(String.valueOf(WellType.DY.ordinal()))) {
            rg_tower_type.check(R.id.rb_dy);
        }
    }

}