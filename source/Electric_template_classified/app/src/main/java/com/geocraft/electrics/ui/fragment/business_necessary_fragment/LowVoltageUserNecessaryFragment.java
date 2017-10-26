package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.widget.LinearLayout;
import android.widget.Toast;

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
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessManager;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessScanEdit;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import common.geocraft.untiltools.T;
import common.geocraft.untiltools.Tools;

/**低压用户档案
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_dykhda_bt)
public class LowVoltageUserNecessaryFragment extends BusinessFragment {

    @ViewById
    LinearLayout linearLayoutRoot;

    @ViewById
    BusinessEditText etTQHB;

    @ViewById
    BusinessSearch viewTQMC;

    @ViewById
    BusinessEditText etKHBH;

    @ViewById
    BusinessScanEdit viewJLXTXM;

    @ViewById
    BusinessSearch viewBYQMC;

    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();

        //设置默认焦点
        viewJLXTXM.setFocus();

        if(!ElectricApplication.BUS.isRegistered(this))
        {
            ElectricApplication.BUS.register(this);
        }
    }

    @Subscribe
    public void onEventMainThread(SearchControlEvent event)
    {
        DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
        if((!event.Name.isEmpty()) && (!dataSet.Name.equals(event.Name)))
        {
            return;
        }

        if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_TQXX))
        {
            viewTQMC.setControlValue(event.dataSet.GetFieldValueByName(Enum.ZONEAREA_FIELD_MC));
            etTQHB.setControlValue(event.dataSet.GetFieldValueByName(Enum.ZONEAREA_FIELD_YWXYID));
        }
        else if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_BYQXX))
        {
            viewBYQMC.setControlValue(event.dataSet.GetFieldValueByName(Enum.TRANSFORMER_FIELD_MC));
        }
    }

    @Override
    public boolean logicCheck() {
        //判断低压客户是否已存在
        if(((RecordActivity)getActivity()).getController().isCreateRecord())
        {
            DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
            List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.LOWVOLTAGEUSER_FIELD_KHBH, etKHBH.getControlValue(), false);
            if (dataSetList.size() > 0) {
                T.show(getContext(), getString(R.string.dykhda_exsit), Toast.LENGTH_SHORT);
                return false;
            }
        }

        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ElectricApplication.BUS.unregister(this);
    }
}
