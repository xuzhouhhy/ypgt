package com.geocraft.electrics.ui.fragment.business_basic_fragment.advance;


import android.app.Fragment;
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
import com.geocraft.electrics.ui.activity.andvance.FragmentAdapter;
import com.geocraft.electrics.ui.activity.andvance.TowerActivity;
import com.geocraft.electrics.ui.activity.andvance.TowerController;
import com.geocraft.electrics.ui.activity.andvance.WellType;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 环网柜
 */
@EFragment(R.layout.acitivity_tower_main)
public class TowerMainFragment extends Fragment {
    @ViewById
    LinearLayout linearLayoutRoot;
    @ViewById
    RadioGroup rg_tower_type;
    @ViewById
    EditText edt_F_GH;
    @ViewById
    GridView grd_ck_fragment;
    private FragmentAdapter mFragmentAdapter;
    private TowerController mTowerController;
    protected DataSet mDataSet;
    protected Boolean mIsNew;
    protected TaskManager taskManager = TaskManager_.getInstance_(
            ElectricApplication_.getInstance().getApplicationContext());
    protected DbManager dbManager = DbManager_.getInstance_(
            ElectricApplication_.getInstance().getApplicationContext());

    @AfterViews
    protected void init() {
        mTowerController = ((TowerActivity) this.getActivity()).getController();
        mIsNew = mTowerController.isCreateRecord();
        mDataSet = mTowerController.getCurrentDataSet();
        initViewData();

    }

    private void initViewData() {
        try {
            rg_tower_type.setOnCheckedChangeListener(mOnCheckedChangeListener);
            mFragmentAdapter = new FragmentAdapter(this.getActivity(), mTowerController);
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
                        setTowerType(fieldInfo.Default);
                    } else {
                        setTowerType(fieldInfo.Value);
                    }
                }
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    private void setTowerType(String value) {
        if (null == value || value.isEmpty()) {
            rg_tower_type.check(R.id.rb_jk);
        }
        if (value.equals("0")) {
            rg_tower_type.check(R.id.rb_jk);
        }
        if (value.equals("1")) {
            rg_tower_type.check(R.id.rb_dl);
        }
        if (value.equals("2")) {
            rg_tower_type.check(R.id.rb_dy);
        }
    }

    RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    WellType wellType = WellType.JK;
                    if (checkedId == R.id.rb_jk) {
                        wellType = WellType.JK;
                    } else if (checkedId == R.id.rb_dl) {
                        wellType = WellType.DL;
                    } else if (checkedId == R.id.rb_dy) {
                        wellType = WellType.DY;
                    }
                    mTowerController.setWellType(wellType);
                    mFragmentAdapter.notifyDataSetChanged();
                }
            };

}
