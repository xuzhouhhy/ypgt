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
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 充换电站
 * 作者  zhouqin
 * 时间 2016/6/13.
 */
@EFragment(R.layout.fragment_chdz_bt)
public class ChargingStationNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessEditText etMC;
	@ViewById
	BusinessEditText etSJDW;
	@ViewById
	BusinessManager viewGLDW;


	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();
	}

	@Override
	public boolean logicCheck() {
		//判断该充换电站是否已存在
		if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
			DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
			List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.CHARGINGSTATION_FIELD_MC, etMC.getControlValue(), false);
			if (dataSetList.size() > 0) {
				T.show(getContext(), getString(R.string.chdz_exsit), Toast.LENGTH_SHORT);
				return false;
			}
		}

		return true;
	}



	@Override
	public void getValue(DataSet dataSet) {

		etSJDW.setControlValue(viewGLDW.getControlValue());

		super.getValue(dataSet);
	}
}
