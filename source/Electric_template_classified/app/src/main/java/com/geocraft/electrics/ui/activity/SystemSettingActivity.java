package com.geocraft.electrics.ui.activity;

import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.ui.controller.SystemSettingController;
import com.geocraft.electrics.ui.fragment.RegisterFragment;
import com.geocraft.electrics.ui.fragment.RegisterFragment_;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Administrator on 2016/6/6.
 */
@EActivity(R.layout.activity_system_setting)
@OptionsMenu(R.menu.menu_new_task)
public class SystemSettingActivity extends BaseActivity {

	@Bean
	SystemSettingController mController;

	@ViewById
	SwitchCompat switchCompatTaskDefault;

	@ViewById
	LinearLayout linearLayoutRegister;


	@AfterViews
	void init() {
		switchCompatTaskDefault.setChecked(mController.getSPDefaultTaskStatus(this));
		if (ElectricApplication_.getInstance().mIsOpenRegister) {
			linearLayoutRegister.setVisibility(View.VISIBLE);
		} else {
			linearLayoutRegister.setVisibility(View.GONE);
		}
	}

	@OptionsItem
	void actionTaskCommit() {
		try {
			mController.commit(this);
			mController.showDialog(this);
		} catch (Exception e) {
			L.printException(e);
		}
	}

	public boolean getUIDefaultStatus() {
		return switchCompatTaskDefault.isChecked();
	}

	@Click
	void linearLayoutRegister() {
		RegisterFragment registerFragment = new RegisterFragment_();
		registerFragment.show(getFragmentManager(), "reg");
	}

}
