package com.geocraft.electrics.ui.fragment.business_basic_fragment;


import android.view.View;
import android.widget.LinearLayout;

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
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch_;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;
import com.geocraft.electrics.utils.Utils;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

/**计量箱组
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_jlxz_jb)
public class CalculateBoxGroupBasicFragment extends BusinessFragment {

	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessManager viewGLDW;
	@ViewById
	BusinessEditText etSJDW;

	@ViewById
	BusinessEditText etZCDW;
	@ViewById
	BusinessSearch viewZCDW;
	@ViewById
	BusinessSearch searchXH;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();

		if (!ElectricApplication.BUS.isRegistered(this)) {
			ElectricApplication.BUS.register(this);
		}

		searchXH.searchEntiry.tableName = Enum.GROUP_NAME_QT + "_" + Enum.DATA_SET_NAME_JLXXH;
		searchXH.searchEntiry.fieldName = Enum.FIELD_NAME_JLXXH_MC;
		//控制界面显示
		DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
		if(dataSet.GroupName.equals(Enum.GROUP_NAME_DYYH))  //低压用户
		{
			viewZCDW.searchEntiry.tableName = Enum.GROUP_NAME_DYYH + "_" + Enum.DATA_SET_NAME_DYKHDA;
			viewZCDW.searchEntiry.fieldName = Enum.LOWVOLTAGEUSER_FIELD_KHMC;
		}
		else if(dataSet.GroupName.equals(Enum.GROUP_NAME_ZXYH)) //专线用户
		{
			viewZCDW.searchEntiry.tableName = Enum.GROUP_NAME_ZXYH + "_" + Enum.DATA_SET_NAME_GYYHD;
			viewZCDW.searchEntiry.fieldName = Enum.HIGHVOLTAGEUSER_FIELD_MC;
		}
		else if(dataSet.GroupName.equals(Enum.GROUP_NAME_ZBYH)) //专变用户
		{
			viewZCDW.searchEntiry.tableName = Enum.GROUP_NAME_ZBYH + "_" + Enum.DATA_SET_NAME_GYYHD;
			viewZCDW.searchEntiry.fieldName = Enum.HIGHVOLTAGEUSER_FIELD_MC;
		}

		//获取资产单位值
		String strZCDW = dataSet.GetFieldValueByName(Enum.CALCULATEBOXGROUP_FIELD_ZCDW);

		//如果全是数据，则认为是用户编号
		if(Utils.isNumeric(strZCDW))
		{
			viewZCDW.setControlValue(strZCDW);
		}
	}

	@Override
	public void getValue(DataSet dataSet) {

		//给资产单位赋值，如果用户编号为空，则使用管理单位
		if(viewZCDW.getControlValue().isEmpty())
		{
			etZCDW.setControlValue(viewGLDW.getControlValue());
			if(!viewGLDW.strSecondManager.isEmpty()) {
				etSJDW.setControlValue(viewGLDW.strSecondManager);
			}
		}
		else
		{
			etZCDW.setControlValue(viewZCDW.getControlValue());
			etSJDW.setControlValue(viewZCDW.getControlValue());
		}

		super.getValue(dataSet);
	}

	@Subscribe
	public void onEventMainThread(SearchControlEvent event) {
		DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
		if((!event.Name.isEmpty()) && (!dataSet.Name.equals(event.Name)))
		{
			return;
		}

		if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_DYKHDA))
		{
			viewZCDW.setControlValue(event.dataSet.GetFieldValueByName(Enum.LOWVOLTAGEUSER_FIELD_KHBH));
			etZCDW.setControlValue(viewZCDW.getControlValue());
		}
		else if(event.dataSet.Name.equals(Enum.DATA_SET_NAME_GYYHD))
		{
			viewZCDW.setControlValue(event.dataSet.GetFieldValueByName(Enum.HIGHVOLTAGEUSER_FIELD_YWXYID));
			etZCDW.setControlValue(viewZCDW.getControlValue());
		}else if (event.dataSet.Name.equals(Enum.DATA_SET_NAME_JLXXH)){
			searchXH.setControlValue(event.dataSet.GetFieldValueByName(Enum.FIELD_NAME_JLXXH_MC));
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ElectricApplication.BUS.unregister(this);
	}
}
