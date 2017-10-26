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

/**用户箱式变
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_yhxsb_bt)
public class UserBoxTypeTransFormerNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessEditText etMC;
	@ViewById
	BusinessEditText etSSKX;
	@ViewById
	BusinessSpinner spDYDJ;
	@ViewById
	BusinessEditText etZCDW;
	@ViewById
	BusinessSpinner spZCXZ;
	@ViewById
	BusinessEditText etSJDW;
	@ViewById
	BusinessManager viewYXDW;


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
					//所属馈线
					etSSKX.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_SSKX));
					//电压等级
					spDYDJ.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_DYDJ));
					//资产性质
					spZCXZ.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_ZCXZ));
					//资产单位
					etZCDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_ZCDW));
				}
			}
		}

		if(!ElectricApplication.BUS.isRegistered(this))
		{
			ElectricApplication.BUS.register(this);
		}
	}

	@Override
	public boolean logicCheck() {
		//判断该箱式变已存在
		if(((RecordActivity)getActivity()).getController().isCreateRecord())
		{
			DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
			List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.USERBOXTYPETRANSFORMER_FIELD_MC, etMC.getControlValue(), false);
			if (dataSetList.size() > 0) {
				T.show(getContext(), getString(R.string.yhxsb_exsit), Toast.LENGTH_SHORT);
				return false;
			}
		}

		return true;
	}

	@Subscribe
	public void onEventMainThread(SearchControlEvent event)
	{
		DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
		if((!event.Name.isEmpty()) && (!dataSet.Name.equals(event.Name)))
		{
			return;
		}

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
