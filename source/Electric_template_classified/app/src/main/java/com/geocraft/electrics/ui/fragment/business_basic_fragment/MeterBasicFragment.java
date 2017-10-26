package com.geocraft.electrics.ui.fragment.business_basic_fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.event.SearchControlEvent;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

/**高压用户点
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_dnb_jb)
public class MeterBasicFragment extends BusinessFragment {

	@ViewById
	LinearLayout linearLayoutRoot;

	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();
	}

	@Subscribe
	public void onEventMainThread(SearchControlEvent event)
	{
		DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
		if((!event.Name.isEmpty()) && (!dataSet.Name.equals(event.Name)))
		{
			return;
		}

		//查询记录箱信息
		int primaryKey = ((RecordActivity)getActivity()).getController().getDataSetParentKey();
		if(primaryKey > 0)
		{
			DataSet dataSetBox = taskManager.getDataSource().getDataSetByName(Enum.GROUP_NAME_DYYH,Enum.DATA_SET_NAME_JLX_J);
			dataSetBox.PrimaryKey = primaryKey;
			dataSetBox = dbManager.queryByPrimaryKey(dataSetBox,false);
			if(dataSetBox != null)
			{
				//获取计量箱箱条码
				String boxCode = dataSetBox.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_JLXTMBH);
				//判读是否和低压用户档案中一致
				String code = event.dataSet.GetFieldValueByName(Enum.LOWVOLTAGEUSER_FIELD_JLXTXM);

				//如果不同，则更新
				if(!boxCode.equals(code))
				{
					event.dataSet.SetFiledValueByName(Enum.LOWVOLTAGEUSER_FIELD_JLXTXM,boxCode);
					dbManager.update(event.dataSet);
				}
			}
		}
	}

}
