package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/***
 * 配电杆塔运行杆
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_pdgtyxg_bt)
public class ElectricRunningTowerNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;
	@ViewById
	BusinessEditText etSSWLGTY;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();

		if (((RecordActivity) getActivity()).getController().isCreateRecord()) {

			DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();
			int primartyKey = ((RecordActivity) getActivity()).getController().getDataSetParentKey();
			if (primartyKey > 0) {
				DataSet queyDataSet = taskManager.getDataSource().getDataSetByName(dataSet.GroupName, Enum.DATA_SET_NAME_PDGT_WLG);
				queyDataSet.PrimaryKey = primartyKey;
				queyDataSet = dbManager.queryByPrimaryKey(queyDataSet, true);
				if (queyDataSet != null) {
					String towerName = queyDataSet.GetFieldValueByName(Enum.PDGT_WLG_FILED_GTMC);

						etSSWLGTY.setControlValue(towerName);

				}
			}
		}

	}


}
