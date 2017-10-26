package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.view.LayoutInflater;
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
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;
import com.huace.log.logger.L;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import common.geocraft.untiltools.T;

/**计量箱组
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_jlxz_bt)
public class CalculateBoxNecessaryGroupFragment extends BusinessFragment {

	@ViewById
	LinearLayout linearLayoutRoot;

	@ViewById
	BusinessEditText etZM;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
	super.init();
	}


	@Override
	public boolean logicCheck() {

		DataSet dataSet = ((RecordActivity) getActivity()).getController().getCurrentDataSet();

		//判断给计量箱是否存在
		if(((RecordActivity)getActivity()).getController().isCreateRecord()) {
			if (((RecordActivity) getActivity()).getController().isCreateRecord()) {
				List<DataSet> dataSetList = dbManager.queryByCondition(dataSet, Enum.CALCULATEBOXGROUP_FIELD_ZM, etZM.getControlValue(), false);
				if (dataSetList.size() > 0) {
					T.show(getContext(), getString(R.string.jlxz_exsit), Toast.LENGTH_SHORT);
					return false;
				}
			}
		}

		String type = dataSet.GetFieldValueByName(Enum.CALCULATEBOXGROUP_FIELD_BXLX);
		String row = dataSet.GetFieldValueByName(Enum.CALCULATEBOXGROUP_FIELD_H);
		String colum = dataSet.GetFieldValueByName(Enum.CALCULATEBOXGROUP_FIELD_L);

		//单体表箱，只能是一行一类
		if(type.equals(Enum.CALCULATEBOX_DTBX))
		{
			if(!(row.equals("1") && colum.equals("1")))
			{
				T.show(getContext(),getString(R.string.jlx_logiccheck_1),Toast.LENGTH_SHORT);
				return false;
			}
		}
		else if(type.equals(Enum.CALCULATEBOX_HTBX)) //合体表箱不能是一行一列
		{
			if((row.equals("1") && colum.equals("1")))
			{
				T.show(getContext(),getString(R.string.jlx_logiccheck_2),Toast.LENGTH_SHORT);
				return false;
			}
		}

		return true;
	}
}
