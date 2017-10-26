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
 * 分布式电源
 * 作者  zhouqin
 * 时间 2016/6/13.
 */
@EFragment(R.layout.fragment_fbsdy_bt)
public class DistributedGenerationNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessEditText etSJDW;

	@ViewById
	BusinessManager viewGLDW;

	@ViewById
	BusinessEditText etYHBH;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();

		if(!ElectricApplication.BUS.isRegistered(this))
		{
			ElectricApplication.BUS.register(this);
		}
	}

	@Override
	public boolean logicCheck() {
		//判断分布式电源是否已存在
		if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
			DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
			List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.DISTRIBUTEGENERATION_FIELD_YHBH, etYHBH.getControlValue(), false);
			if (dataSetList.size() > 0) {
				T.show(getContext(), getString(R.string.fbsdy_exsit), Toast.LENGTH_SHORT);
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

		etSJDW.setControlValue(viewGLDW.getControlValue());

		super.getValue(dataSet);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ElectricApplication.BUS.unregister(this);
	}
}
