package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity_;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessManager;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSpinner;
import com.geocraft.electrics.utils.SPUtils;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 用户架空杆塔物理杆
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_yhjkgtwlg_bt)
public class UserPhysicsTowerNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessEditText etGTMC;
	@ViewById
	BusinessEditText etZCDW;
	@ViewById
	BusinessSpinner spZCXZ;
	@ViewById
	BusinessEditText etSJDW;
	@ViewById
	BusinessManager viewYXDW;
	@ViewById
	BusinessEditText etSSKX;
	@ViewById
	BusinessEditText etSSXL;
	@ViewById
	BusinessSpinner spCNW;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();

		if (((RecordActivity) getActivity()).getController().isCreateRecord()) {

			DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
			int primartyKey = ((RecordActivity) getActivity()).getController().getDataSetParentKey();
			if (primartyKey > 0) {
				DataSet queyDataSet = taskManager.getDataSource().getDataSetByName(dataSet.GroupName, Enum.DATA_SET_NAME_YHZX);
				queyDataSet.PrimaryKey = primartyKey;
				queyDataSet = dbManager.queryByPrimaryKey(queyDataSet, true);
				if (queyDataSet != null) {
					String lineName = queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_XLMC);
					//如果是第一根杆，从专线中把数据带过，如果不是，则赋值上根杆的数据
					List<DataSet> towerList = dbManager.queryByCondition(dataSet, Enum.USERPHYSICSTOWER_FIELD_SSXL, lineName, false);
					if (towerList.size() > 0) {
						((RecordActivity) getActivity()).initData(towerList.get(towerList.size() - 1));
					} else {
						//运行单位
						viewYXDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_YXDW));
						//上级单位
						etSJDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_SJDW));
						//所属馈线
						etSSKX.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_SSKX));
						//所属线路
						etSSXL.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_XLMC));
						//资产性质
						spZCXZ.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_ZCXZ));
						//资产单位
						etZCDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_ZCDW));
						//城农网
						spCNW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_CNW));
					}
				}
			}
		}


	}

	@Override
	public boolean logicCheck() {
		//判断该箱式变已存在
		if (((RecordActivity) getActivity()).getController().isCreateRecord()) {
			DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
			List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.USERPHYSICSTOWER_FIELD_GTMC, etGTMC.getControlValue(), false);
			if (dataSetList.size() > 0) {
				T.show(getContext(), getString(R.string.wlg_exsit), Toast.LENGTH_SHORT);
				return false;
			}

			//将用到的值，存起来
			SPUtils.put(getActivity(),Constants.INTENT_DATA_SET_GROUP_NAME,dataSet.GroupName);
			SPUtils.put(getActivity(),Constants.INTENT_PHYSICSTOWER_TOEWENAME,etGTMC.getControlValue());

			List list = ElectricApplication_.getInstance().getList();
			final Activity activity = (Activity) list.get(list.size() - 2);
			//获取倒数第二个Activity
			new AlertDialog.Builder(activity)
					.setTitle(R.string.app_tip)
					.setMessage(R.string.is_open_yxg_dialog_tip)
					.setNegativeButton(R.string.app_no, null)
					.setPositiveButton(R.string.app_yes, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							showRunningTower(activity);
						}
					}).show();
		}

		return true;
	}

	public void showRunningTower(Activity activity)
	{
		String groupName = (String) SPUtils.get(activity, Constants.INTENT_DATA_SET_GROUP_NAME,"");
		String lineName = (String)SPUtils.get(activity,Constants.INTENT_PHYSICSTOWER_TOEWENAME,"");
		DataSet dataSet = taskManager.getDataSource().getDataSetByName(groupName,Enum.DATA_SET_NAME_YHJKGT_WLG);
		DataSet dataSetRunning = taskManager.getDataSource().getDataSetByName(groupName,Enum.DATA_SET_NAME_YHJKGT_YXG);
		List<DataSet> dataSetQuery  = dbManager.queryByCondition(dataSet,Enum.USERPHYSICSTOWER_FIELD_SSXL,lineName,true);
		if(dataSetQuery.size() > 0) {
			DataSet dataSetTmp = dataSetQuery.get(dataSetQuery.size() - 1);
			Intent intent = new Intent(activity, DeviceShowListActivity_.class);
			intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, dataSet.GroupName);
			intent.putExtra(Constants.INTENT_DATA_SET_NAME, Enum.DATA_SET_NAME_YHJKGT_YXG);
			intent.putExtra(Constants.INTENT_DATA_SET_PARENT_KEY, dataSetTmp.PrimaryKey);
			intent.putExtra(Constants.INTENT_DATA_SET_QUERY_VALUE, dataSetTmp.GetFieldValueByName(dataSetRunning.ValueField));
			activity.startActivity(intent);
		}
	}

	@Override
	public void getValue(DataSet dataSet) {

		if(!viewYXDW.strSecondManager.isEmpty()) {
			etSJDW.setControlValue(viewYXDW.strSecondManager);
		}

		super.getValue(dataSet);
	}

}
