package com.geocraft.electrics.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.geocraft.electrics.R;
import com.geocraft.electrics.entity.TaskInfo;
import com.geocraft.electrics.ui.controller.TaskManageController;
import com.geocraft.electrics.ui.view.TaskChildItemView;
import com.geocraft.electrics.ui.view.TaskChildItemView_;
import com.geocraft.electrics.ui.view.TaskParentItemView;
import com.geocraft.electrics.ui.view.TaskParentItemView_;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
public class TaskManageAdapter extends BaseExpandableListAdapter {


	private final TaskManageController mController;
	private final Context mContext;

	public TaskManageAdapter(TaskManageController taskManageController, Context context) {
		mController = taskManageController;
		mContext = context;

	}

	@Override
	public int getGroupCount() {
		return mController.getParentItems().size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public String getGroup(int groupPosition) {
		return mController.getParentItems().get(groupPosition).getTaskName();
	}

	@Override
	public TaskInfo getChild(int groupPosition, int childPosition) {
		return mController.getParentItems().get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		TaskParentItemView taskParentItemView;
		if (convertView == null) {
			taskParentItemView = TaskParentItemView_.build(parent.getContext());
		} else {
			taskParentItemView = (TaskParentItemView) convertView;
		}

		if(groupPosition % 2 != 0){
			taskParentItemView.setBackgroundResource(R.drawable.selector_iv_bg_odd);
		}else{
			taskParentItemView.setBackgroundResource(R.drawable.selector_iv_bg_even);
		}

		taskParentItemView.bind(mController.getParentItems().get(groupPosition).getTaskName());
		return taskParentItemView;
	}

	@Override
	public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		TaskChildItemView taskChildItemView;
		if (convertView == null) {
			taskChildItemView = TaskChildItemView_.build(parent.getContext());
		} else {
			taskChildItemView = (TaskChildItemView) convertView;
		}

		View.OnClickListener deleteListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mController.openDeleteTaskTipDialog(mContext, groupPosition);

			}
		};
		View.OnClickListener openListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mController.openTask(mContext, groupPosition);
			}
		};

		taskChildItemView.setBtnOpenOnClickListener(openListener);
		taskChildItemView.setBtnDeleteOnClickListener(deleteListener);
		taskChildItemView.bind(mController.getParentItems().get(groupPosition));
		return taskChildItemView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}
