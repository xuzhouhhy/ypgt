package com.geocraft.electrics.ui.fragment.business_necessary_fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.event.SearchControlEvent;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity_;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSpinner;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import common.geocraft.untiltools.T;

/***配电杆塔物理杆
 * 作者  zhouqin
 * 时间 2016/6/11.
 */
@EFragment(R.layout.fragment_pdgtwlg_bt)
public class ElectricPhysicsTowerNecessaryFragment extends BusinessFragment {
	@ViewById
	LinearLayout linearLayoutRoot;


	@Override
	protected void init() {
		mLinearLayout = linearLayoutRoot;
		super.init();


	}






}
