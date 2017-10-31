package com.geocraft.electrics.sr.fragment;


import android.support.v4.app.Fragment;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.sr.FragmentOption;
import com.geocraft.electrics.sr.activity.WellActivity;
import com.geocraft.electrics.sr.adapters.FragmentAdapter;
import com.geocraft.electrics.sr.controller.WellController;
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
    GridView grd_ck_fragment;
    private WellController mWellController;

    @AfterViews
    protected void init() {
        mWellController = ((WellActivity) this.getActivity()).getController();
        mIsNew = mWellController.isCreateRecord();
        mDataSet = mWellController.getCurrentDataSet();
        initViewData();
    }

    private void initViewData() {
        try {
            FragmentAdapter fragmentAdapter = new FragmentAdapter(this.getActivity(), mWellController);
            grd_ck_fragment.setAdapter(fragmentAdapter);
        } catch (Exception e) {
            L.printException(e);
        }
    }

    /**
     * 获取界面直
     */
    public void getValue(DataSet dataSet) {
        List<FieldInfo> fieldInfoList = dataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            if (grd_ck_fragment.getTag().toString().equalsIgnoreCase(fieldInfo.Alias)) {
                fieldInfo.Value = getCheckedFragmentkeyValue();
            }
            if (mIsNew) {
                //线路id 编辑状态不需要改变
                if (Enum.GY_JKXLTZXX_FIELD_LINEID.equalsIgnoreCase(fieldInfo.Name)) {
                    fieldInfo.Value = String.valueOf(mWellController.getLineId());
                }
            }
        }
    }

    private String getCheckedFragmentkeyValue() {
        StringBuilder sb = new StringBuilder();
        List<FragmentOption> fragmentOptions = mWellController.getCheckedFragments();
        for (int i = 0; i < fragmentOptions.size(); i++) {
            sb.append(NAME_KEY_MARK).append(fragmentOptions.get(i).getNameKey());
        }
        if (sb.toString().trim().length() > 0) {
            sb.replace(0, 1, "");
        }
        return sb.toString();
    }

}
