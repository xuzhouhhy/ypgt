package com.geocraft.electrics.ui.activity;

import android.content.Intent;
import android.widget.ExpandableListView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.ui.adapter.TaskManageAdapter;
import com.geocraft.electrics.ui.controller.TaskManageController;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;


/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@OptionsMenu(R.menu.menu_task_manager)
@EActivity(R.layout.activity_task_manage)
public class TaskManageActivity extends BaseActivity
		implements ExpandableListView.OnGroupExpandListener {

	@ViewById
	ExpandableListView expandableListViewTask;

	@Bean
	TaskManageController mController;

	TaskManageAdapter mTaskManageAdapter;

	@AfterViews
	void init() {

		mTaskManageAdapter = new TaskManageAdapter(mController, this);
		expandableListViewTask.setAdapter(mTaskManageAdapter);
		expandableListViewTask.setGroupIndicator(null);
		expandableListViewTask.setOnGroupExpandListener(this);
	}

	public void refreshExpandableListView(int position) {
		try {
			if (mTaskManageAdapter != null) {
				mTaskManageAdapter.notifyDataSetChanged();
				expandableListViewTask.collapseGroup(position);
			}
		} catch (Exception e) {
			L.printException(e);
		}
	}

	@OptionsItem(android.R.id.home)
	void home() {
		mController.operateWhenFinish(this, false);
	}

	@OptionsItem
	void actionNewProject() {
		mController.openNewTaskActivity(this);
	}

	@Override
	public void onGroupExpand(int groupPosition) {
		//控制最多一个ParentItem弹出
		for (int i = 0; i < mController.getParentItems().size(); i++) {
			if (groupPosition != i) {
				expandableListViewTask.collapseGroup(i);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case ConstRequestCode.REQUEST_CODE_NEW_TASK: {
				if (data == null) {
					return;
				}
				boolean isSucceed =
						data.getBooleanExtra(Constants.INTENT_DATA_NEW_TASK_RESULT, false);
				if (isSucceed) {
					mController.operateWhenFinish(this, true);
				}
				break;
			}
			default:
				break;
		}

	}
}
