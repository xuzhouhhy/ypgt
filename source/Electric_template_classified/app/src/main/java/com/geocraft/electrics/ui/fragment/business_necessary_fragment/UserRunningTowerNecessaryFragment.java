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

import common.geocraft.untiltools.ConvertHelper;
import common.geocraft.untiltools.T;

/**用户架空杆塔运行杆
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_yhjkgtyxg_bt)
public class UserRunningTowerNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessEditText etMC;
	@ViewById
	BusinessEditText etSSKX;
	@ViewById
	BusinessEditText etSSXL;
	@ViewById
	BusinessSpinner spCNW;
	@ViewById
	BusinessEditText etSJDW;
	@ViewById
	BusinessManager viewYXDW;
	@ViewById
	BusinessEditText etSSWLGTY;

	@ViewById
	BusinessEditText etGTSXH;


	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;

		DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
		String str = dataSet.GetFieldValueByName(Enum.USERRUNNINGTOWER_FIELD_HLXX);
		str = numberToName(str);
		dataSet.SetFiledValueByName(Enum.USERRUNNINGTOWER_FIELD_HLXX,str);

		super.init();

		if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
			int primartyKey = ((RecordActivity) getActivity()).getController().getDataSetParentKey();
			if (primartyKey > 0) {
				DataSet queyDataSet = taskManager.getDataSource().getDataSetByName(dataSet.GroupName, Enum.DATA_SET_NAME_YHJKGT_WLG);
				queyDataSet.PrimaryKey = primartyKey;
				queyDataSet = dbManager.queryByPrimaryKey(queyDataSet, true);
				if (queyDataSet != null) {
					//运行单位
					viewYXDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERPHYSICSTOWER_FIELD_YXDW));
					//上级单位
					etSJDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERPHYSICSTOWER_FIELD_SJDW));
					//所属馈线
					etSSKX.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERPHYSICSTOWER_FIELD_SSKX));
					//所属线路
					etSSXL.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERPHYSICSTOWER_FIELD_SSXL));
					//城农网
					spCNW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERPHYSICSTOWER_FIELD_CNW));
					//物理杆名称
					etSSWLGTY.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERPHYSICSTOWER_FIELD_GTMC));
					//运行杆名称
					etMC.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERPHYSICSTOWER_FIELD_GTMC));
					//查询运行杆数量
					List<DataSet> listRunning = dbManager.queryByCondition(queyDataSet,Enum.USERRUNNINGTOWER_FIELD_SSXL,queyDataSet.GetFieldValueByName(Enum.USERPHYSICSTOWER_FIELD_SSXL),false);
					etGTSXH.setControlValue(ConvertHelper.Int2String(listRunning.size()));
				}
			}
		}
	}

	private String nameToNumber(String str)
	{
		if(str.equals("中回"))
		{
			str = "0";
		}
		else if(str.equals("左一"))
		{
			str="1";
		}
		else if(str.equals("左二"))
		{
			str="3";
		}
		else if(str.equals("左三"))
		{
			str="5";
		}
		else if(str.equals("左四"))
		{
			str="7";
		}
		else if(str.equals("左五"))
		{
			str="9";
		}
		else if(str.equals("右一"))
		{
			str="2";
		}
		else if(str.equals("右二"))
		{
			str="4";
		}
		else if(str.equals("右三"))
		{
			str="6";
		}
		else if(str.equals("右四"))
		{
			str="8";
		}
		else if(str.equals("右五"))
		{
			str="10";
		}
		else
		{
			str = "0";
		}

		return str;
	}

	private String numberToName(String str)
	{
		if(str.equals("0"))
		{
			str = "中回";
		}
		else if(str.equals("1"))
		{
			str = "左一";
		}
		else if(str.equals("3"))
		{
			str = "左二";
		}
		else if(str.equals("5"))
		{
			str = "左三";
		}
		else if(str.equals("7"))
		{
			str = "左四";
		}
		else if(str.equals("9"))
		{
			str = "左五";
		}
		else if(str.equals("2"))
		{
			str = "右一";
		}
		else if(str.equals("4"))
		{
			str = "右二";
		}
		else if(str.equals("6"))
		{
			str = "右三";
		}
		else if(str.equals("8"))
		{
			str = "右四";
		}
		else if(str.equals("10"))
		{
			str = "右五";
		}
		else
		{
			str = "中回";
		}

		return str;
	}

	@Override
	public boolean logicCheck() {
		//判断该运行杆已存在存在
		if(((RecordActivity)getActivity()).getController().isCreateRecord())
		{
			DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
			List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.USERRUNNINGTOWER_FIELD_MC, etMC.getControlValue(), false);
			if (dataSetList.size() > 0) {
				T.show(getContext(), getString(R.string.yxg_exsit), Toast.LENGTH_SHORT);
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

		String str = dataSet.GetFieldValueByName(Enum.USERRUNNINGTOWER_FIELD_HLXX);

		str = nameToNumber(str);

		dataSet.SetFiledValueByName(Enum.USERRUNNINGTOWER_FIELD_HLXX,str);
	}
}
