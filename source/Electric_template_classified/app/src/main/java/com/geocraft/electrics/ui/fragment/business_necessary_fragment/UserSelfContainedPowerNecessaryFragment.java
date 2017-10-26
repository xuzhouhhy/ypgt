package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

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
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**用户自备电源
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_yhzbdy_bt)
public class UserSelfContainedPowerNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessEditText etSJDW;

	@ViewById
	BusinessManager viewGLDW;

	@ViewById
	BusinessEditText etYHBH;

	@ViewById
	BusinessEditText etYHMC;

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
					//管理单位
					viewGLDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_YXDW));
					//上级单位
					etSJDW.setControlValue(queyDataSet.GetFieldValueByName(Enum.USERUNIQUELINE_FILED_SJDW));


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

	}

	@Override
	public boolean logicCheck() {
		return super.logicCheck();
	}



	@Override
	public void getValue(DataSet dataSet) {

		if(!viewGLDW.strSecondManager.isEmpty()) {
			etSJDW.setControlValue(viewGLDW.strSecondManager);
		}

		super.getValue(dataSet);
	}
}
