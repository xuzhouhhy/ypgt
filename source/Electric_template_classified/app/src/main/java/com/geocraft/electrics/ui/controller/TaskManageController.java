package com.geocraft.electrics.ui.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.TaskInfo;
import com.geocraft.electrics.event.DeleteCurrentTaskSucceedEventArgs;
import com.geocraft.electrics.manager.TaskExistException;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.task.OpenTaskAsyncTask;
import com.geocraft.electrics.ui.activity.CollectTypeActivity_;
import com.geocraft.electrics.ui.activity.NewTaskActivity_;
import com.geocraft.electrics.ui.activity.TaskManageActivity;
import com.geocraft.electrics.utils.ElectricXmlUtils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.FileUtils;
import common.geocraft.untiltools.T;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@EBean
public class TaskManageController extends BaseController {

	@Bean
	TaskManager mTaskManager;

	private List<TaskInfo> parentItems = new ArrayList<>();

	public List<TaskInfo> getParentItems() {
		return parentItems;
	}

	public void openNewTaskActivity(final Context context) {
		if (mTaskManager.getTaskInfo() != null) {
			DialogInterface.OnClickListener onPositiveClickListener =
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							mTaskManager.closeCurrentTask();
							startNewTaskActivity(context);
							ElectricApplication.BUS.post(new DeleteCurrentTaskSucceedEventArgs());
						}
					};

			showTaskOperateTipDialog(context, context.getString(R.string.current_task_not_null_tip),
					onPositiveClickListener, null);
		} else {
			startNewTaskActivity(context);
		}
	}

	private void startNewTaskActivity(Context context) {
		Intent intent = new Intent(context, NewTaskActivity_.class);
		((TaskManageActivity) context).startActivityForResult(
				intent, ConstRequestCode.REQUEST_CODE_NEW_TASK);
	}

	public void openDeleteTaskTipDialog(final Context context, final int position) {
		final String taskName = parentItems.get(position).getTaskName();
		String message = "";
		if (mTaskManager.getTaskInfo() != null &&
				taskName.equals(mTaskManager.getTaskInfo().getTaskName())) {
			message = context.getString(R.string.delete_current_task_tip);
		} else {
			message = context.getString(R.string.delete_no_current_task_tip);
		}

		DialogInterface.OnClickListener onPositiveClickListener = new DialogInterface
				.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (deleteTask(taskName)) {
					parentItems.remove(position);
					((TaskManageActivity) context).refreshExpandableListView(position);
					T.showShort(context, R.string.delete_task_succeed);
				} else {
					T.showShort(context, R.string.delete_task_failed);
				}
			}
		};

		showTaskOperateTipDialog(context, message, onPositiveClickListener, null);
	}

	private boolean deleteTask(String taskName) {
		try {
			if (mTaskManager.getTaskInfo() != null &&
					taskName.equals(mTaskManager.getTaskInfo().getTaskName())) {
				mTaskManager.closeCurrentTask();
				FileUtils.delFolder(ConstPath.getTaskRootFolder() + taskName);
				ElectricApplication.BUS.post(new DeleteCurrentTaskSucceedEventArgs());
			} else {
				FileUtils.delFolder(ConstPath.getTaskRootFolder() + taskName);
			}
			return true;
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	public void openTask(Context context, int position) {
		String taskName = parentItems.get(position).getTaskName();
		if (mTaskManager.getTaskInfo() != null) {
			if (taskName.equals(mTaskManager.getTaskInfo().getTaskName())) {
				T.showShort(context, R.string.target_task_is_running);
			} else {
				openChangeTaskTipDialog(context, taskName);
			}
		} else {
			OpenTaskAsyncTask openTaskAsyncTask = new OpenTaskAsyncTask(context,
					TaskManageController.this, taskName);
			openTaskAsyncTask.execute(TaskManageController.this);
		}
	}

	private void openChangeTaskTipDialog(final Context context, final String taskName) {
		DialogInterface.OnClickListener onPositiveClickListener = new DialogInterface
				.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mTaskManager.closeCurrentTask();
				OpenTaskAsyncTask openTaskAsyncTask = new OpenTaskAsyncTask(context,
						TaskManageController.this, taskName);
				openTaskAsyncTask.execute(TaskManageController.this);
			}
		};

		showTaskOperateTipDialog(context, context.getString(R.string.current_task_not_null_tip),
				onPositiveClickListener, null);
	}

	public boolean openTargetTask(String taskName) throws TaskExistException {
		return mTaskManager.openTask(taskName);
	}

	private void showTaskOperateTipDialog(Context context, String message,
	                                      DialogInterface.OnClickListener onPositiveClickListener,
	                                      DialogInterface.OnClickListener onNegativeClickListener) {
		new AlertDialog.Builder(context)
				.setIcon(R.mipmap.ic_tip)
				.setTitle(R.string.dlg_tip)
				.setMessage(message)
				.setPositiveButton(R.string.dlg_yes, onPositiveClickListener)
				.setNegativeButton(R.string.dlg_no, onNegativeClickListener)
				.show();
	}

	@AfterInject
	void initController() {
		String taskParentPath = ConstPath.getTaskRootFolder();
		if (!FileUtils.existFile(taskParentPath)) {
			return;
		}
		File taskRoot = new File(taskParentPath);
		File[] taskArray = taskRoot.listFiles();
		for (File task : taskArray) {
			if (task != null) {
				String taskName = task.getName();
				String taskTskFile = task.getPath() + File.separator + taskName + Constants.TASK_SUFFIX;
				TaskInfo temp = ElectricXmlUtils.getTaskInfoByTsk(taskTskFile);
				if (temp != null) {
					parentItems.add(temp);
				}
			}
		}
	}

	public void operateWhenFinish(Context context, boolean isNew) {
		Intent intent = new Intent(context, CollectTypeActivity_.class);
		intent.putExtra(Constants.INTENT_DATA_TASK_MANAGER_RESULT, isNew);
		((TaskManageActivity) context).setResult(
				ConstRequestCode.REQUEST_CODE_OPEN_TASK_MANAGER, intent);
		((TaskManageActivity) context).finish();
	}
}