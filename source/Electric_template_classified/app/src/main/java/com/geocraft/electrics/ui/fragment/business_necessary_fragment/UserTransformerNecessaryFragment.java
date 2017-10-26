package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.widget.LinearLayout;
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
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSpinner;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import common.geocraft.untiltools.T;
import common.geocraft.untiltools.Tools;

/**用户变压器
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_yhbyq_bt)
public class UserTransformerNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessEditText etSSXL;

	@ViewById
	BusinessEditText etSSKX;

	@ViewById
	BusinessSearch searchSSTQ;

	@ViewById
	BusinessSpinner spDYDJ;

	@ViewById
	BusinessEditText etSJDW;

	@ViewById
	BusinessManager viewYXDW;

	@ViewById
	BusinessSpinner spZCXZ;

	@ViewById
	BusinessEditText etYHBH;

	@ViewById
	BusinessEditText etYHMC;

	@ViewById
	BusinessEditText etMC;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();

		if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
			//从专线中带信息过来
			int primartyKey = ((RecordActivity) getActivity()).getController().getDataSetParentKey();
			if (primartyKey > 0) {
				DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
				DataSet queyDataSet = taskManager.getDataSource().getDataSetByName(dataSet.GroupName, Enum.DATA_SET_NAME_YHZX);
				queyDataSet.PrimaryKey = primartyKey;
				queyDataSet = dbManager.queryByPrimaryKey(queyDataSet, true);
				if (queyDataSet != null) {
					//运行单位
					viewYXDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_YXDW));
					//上级单位
					etSJDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_SJDW));

					//所属线路
					etSSXL.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_XLMC));
					//所属馈线
					etSSKX.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_SSKX));
					//电压等级
					spDYDJ.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_DYDJ));
					//资产性质
					spZCXZ.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_ZCXZ));

					//资产单位
					String strZCDW = queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_ZCDW);
					//通过线路的资产单位查询高压用户点获取用户名称
					DataSet dataSetUser = taskManager.getDataSource().getDataSetByName(dataSet.GroupName, Enum.DATA_SET_NAME_GYYHD);
					List<DataSet> list = dbManager.queryByCondition(dataSetUser, Enum.HIGHVOLTAGEUSER_FIELD_YWXYID, strZCDW, true);
					if (list.size() > 0) {
						etYHMC.setControlValue(list.get(0).GetFieldValueByName(Enum.HIGHVOLTAGEUSER_FIELD_YHMC));
						etYHBH.setControlValue(list.get(0).GetFieldValueByName(Enum.HIGHVOLTAGEUSER_FIELD_YWXYID));
					}
				}
			}
		}

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
			searchSSTQ.setControlValue(event.dataSet.GetFieldValueByName(Enum.ZONEAREA_FIELD_MC));
		}
	}

	@Override
	public boolean logicCheck() {
		//判断该用户变压器已存在
		if(((RecordActivity)getActivity()).getController().isCreateRecord())
		{
			DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
			List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.USERELECTRICCITYDISTRIBUTIONROOM_FIELD_F, etMC.getControlValue(), false);
			if (dataSetList.size() > 0) {
				T.show(getContext(), getString(R.string.yhpds_exsit), Toast.LENGTH_SHORT);
				return false;
			}
		}

		return true;
	}

	@Override
	public void getValue(DataSet dataSet) {

		if(!viewYXDW.strSecondManager.isEmpty()) {
			etSJDW.setControlValue(viewYXDW.strSecondManager);
		}

		super.getValue(dataSet);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ElectricApplication.BUS.unregister(this);
	}
}
