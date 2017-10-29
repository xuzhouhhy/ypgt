package com.geocraft.electrics.sr.fragment;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.GridView;
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
import com.geocraft.electrics.sr.BasicFragmentFactory;
import com.geocraft.electrics.sr.FragmentAdapter;
import com.geocraft.electrics.sr.WellActivity;
import com.geocraft.electrics.sr.WellController;
import com.geocraft.electrics.sr.WellType;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 井号配置设置主界面
 *
 * @author kingdon
 */
@EFragment(R.layout.fragment_well_main)
public class WellMainFragment extends Fragment {
    private final static String NAME_KEY_MARK = "&";
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
    GridView grd_ck_fragment;
    private WellActivity mContext;
    private FragmentAdapter mFragmentAdapter;
    private WellController mWellController;


    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    WellType wellType = getWellType(checkedId);
                    mWellController.updateWellType(wellType);
                    mFragmentAdapter.notifyDataSetChanged();
                }
            };

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

    @AfterViews
    protected void init() {
        mContext = ((WellActivity) this.getActivity());
        mWellController = mContext.getController();
        mIsNew = mWellController.isCreateRecord();
        mDataSet = mWellController.getCurrentDataSet();
        initViewData();
    }

    private void updateViewClickable(View view, boolean isEnable) {
        view.setEnabled(isEnable);
        view.setClickable(isEnable);
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
                //初始化类型
                if (rg_tower_type.getTag() != null && rg_tower_type.getTag().toString()
                        .equalsIgnoreCase(fieldInfo.Alias)) {
                    if (mIsNew) {
                        setWellType(fieldInfo.Default);
                    } else {
                        setWellType(fieldInfo.Value);
                    }
                }
            }
            updateViewClickable(rg_tower_type, mIsNew);
        } catch (Exception e) {
            L.printException(e);
        }
    }

    public void getValue() {
        List<FieldInfo> fieldInfoList = mDataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            if (grd_ck_fragment.getTag().toString().equalsIgnoreCase(fieldInfo.Alias)) {
                fieldInfo.Value = getCheckedFragmentkeyValue();
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
    }

    private String getCheckedFragmentkeyValue() {
        StringBuilder sb = new StringBuilder();
        List<BasicFragmentFactory.FragmentDatasetOption> fragmentDatasetOptions =
                mWellController.getCheckedFragments();
        for (int i = 0; i < fragmentDatasetOptions.size(); i++) {
            sb.append(NAME_KEY_MARK + fragmentDatasetOptions.get(i).getFramentNameKey());
        }
        if (sb.toString().trim().length() > 0) {
            sb.replace(0, 1, "");
        }
        return sb.toString();
    }

}
