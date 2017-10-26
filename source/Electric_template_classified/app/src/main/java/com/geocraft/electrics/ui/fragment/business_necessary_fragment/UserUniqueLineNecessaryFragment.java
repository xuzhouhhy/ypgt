package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.event.SearchControlEvent;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessManager;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import common.geocraft.untiltools.T;

/**用户专线
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_yhzx_bt)
public class UserUniqueLineNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessEditText etSJDW;

	@ViewById
	BusinessSearch searchSKX;
	@ViewById
	TextView tvSSKX;

	@ViewById
	BusinessSearch searchZCDW;

	@ViewById
	BusinessManager viewYXDW;

	@ViewById
	BusinessEditText etXLMC;

	@ViewById
	BusinessSearch searchBDZ;

	@ViewById
	BusinessSearch searchQDSB;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();

		if(!ElectricApplication.BUS.isRegistered(this))
		{
			ElectricApplication.BUS.register(this);
		}

		DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
		if(dataSet.GroupName.equals(Enum.GROUP_NAME_ZXYH))
		{
			searchSKX.setVisibility(View.GONE);
			tvSSKX.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean logicCheck() {
		//判断该新浪是否存在
		if(((RecordActivity)getActivity()).getController().isCreateRecord())
		{
			DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
			List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.USERUNIQUELINE_FILED_XLMC, etXLMC.getControlValue(), false);
			if (dataSetList.size() > 0) {
				T.show(getContext(), getString(R.string.xl_exsit), Toast.LENGTH_SHORT);
				return false;
			}
		}

		return true;
	}

	@Override
	public void getValue(DataSet dataSet) {

		//如果是专线用户，线路名称和馈线名称一致
		if(dataSet.GroupName.equals(Enum.GROUP_NAME_ZXYH))
		{
			searchSKX.setControlValue(etXLMC.getControlValue());
		}

		if(!viewYXDW.strSecondManager.isEmpty()) {
			etSJDW.setControlValue(viewYXDW.strSecondManager);
		}

		super.getValue(dataSet);
	}

	@Subscribe
	public void onEventMainThread(SearchControlEvent event)
	{
		DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
		if((!event.Name.isEmpty()) && (!dataSet.Name.equals(event.Name)))
		{
			return;
		}

		if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_KXXX))
		{
			searchSKX.setControlValue(event.dataSet.GetFieldValueByName(Enum.FEEDERLINE_FIELD_MC));
		}
		else if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_GYYHD))
		{
			searchZCDW.setControlValue(event.dataSet.GetFieldValueByName(Enum.HIGHVOLTAGEUSER_FIELD_YWXYID));
		}
		else if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_BDZXX))
		{
			searchBDZ.setControlValue(event.dataSet.GetFieldValueByName(Enum.TRANSFORMERSTATION_FIELD_MC));
		}
		else if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_XLQDSBXX))
		{
			searchQDSB.setControlValue(event.dataSet.GetFieldValueByName(Enum.STARTDEVICE_FIELD_MC));
		}
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ElectricApplication.BUS.unregister(this);
	}
}
