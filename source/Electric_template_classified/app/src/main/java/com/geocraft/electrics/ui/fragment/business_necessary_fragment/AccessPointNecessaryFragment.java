package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.event.SearchControlEvent;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.UUID;

import common.geocraft.untiltools.Tools;

/**
 * 接入点标识
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_jrdbs_bt)
public class AccessPointNecessaryFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @ViewById
    BusinessSearch viewGJSBMC;

    protected TaskManager taskManager = TaskManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();

        DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
        //如果是新建，将名称带过来
        if(((RecordActivity)getActivity()).getController().isCreateRecord())
        {
            List<DataSet> list = dbManager.queryAll(dataSet,true);
            if(list.size() > 0)
            {
                viewGJSBMC.setControlValue(list.get(list.size() - 1).GetFieldValueByName(Enum.ACCESSPOINT_FIELD_GJSBMC));
            }
        }

        if (!ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.register(this);
        }
    }

    @Subscribe
    public void onEventMainThread(SearchControlEvent event) {
        DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
        if((!event.Name.isEmpty()) && (!dataSet.Name.equals(event.Name)))
        {
            return;
        }

        if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_TQXX)){
            viewGJSBMC.setControlValue(event.dataSet.GetFieldValueByName(Enum.ZONEAREA_FIELD_MC));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ElectricApplication.BUS.unregister(this);
    }
}
