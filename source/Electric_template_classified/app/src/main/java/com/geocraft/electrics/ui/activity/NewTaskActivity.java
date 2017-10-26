package com.geocraft.electrics.ui.activity;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.task.NewTaskAsyncTask;
import com.geocraft.electrics.ui.controller.NewTaskController;
import com.geocraft.electrics.ui.view.ElectricEditText;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

import common.geocraft.untiltools.T;

/**
 * Created by Administrator on 2016/6/4.
 */
@EActivity(R.layout.activity_new_task)
@OptionsMenu(R.menu.menu_new_task)
public class NewTaskActivity extends BaseActivity {

	ArrayAdapter<String> templateAdapter = null;

	@ViewById
	ElectricEditText edtTxtCollector;
	@ViewById
	ElectricEditText edtTxtTaskName;
	@ViewById
	Spinner spinnerTaskTemplate;
	@ViewById
	EditText edtTxtTaskDescription;

	@Bean
	NewTaskController mController;

	@OptionsItem(android.R.id.home)
	void home() {
		mController.operateWhenFinish(this, false);
	}

	@OptionsItem
	void actionTaskCommit() {
		if (edtTxtTaskName.getText().length() > 0) {
			NewTaskAsyncTask newTaskAsyncTask = new NewTaskAsyncTask(this, mController);
			newTaskAsyncTask.execute(mController);
		} else {
			T.showShort(this, R.string.task_name_is_null);
		}
	}

	@AfterViews
	void init() {
		try {
			mController.initTemplateFolderList();
		} catch (IOException e) {
			L.printException(e);
			T.showShort(this, R.string.init_template_spinner_failure);
		}
		templateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,
				mController.getFileList(this));
		spinnerTaskTemplate.setAdapter(templateAdapter);
		spinnerTaskTemplate.setPrompt(getString(R.string.template_without_maohao));
		spinnerTaskTemplate.setSelection(0);//默认第一项
	}

	public String getTaskName() {
		return edtTxtTaskName.getText().toString();
	}

	public String getTaskCollector() {
		return edtTxtCollector.getText().toString();
	}

	public String getTaskTemplate() {
		return spinnerTaskTemplate.getSelectedItem().toString();
	}

	public String getTaskDescription() {
		return edtTxtTaskDescription.getText().toString();
	}

}
