package com.geocraft.electrics.ui.controller;

import android.content.Context;
import android.content.Intent;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.TaskInfo;
import com.geocraft.electrics.manager.TaskExistException;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.ui.activity.NewTaskActivity;
import com.geocraft.electrics.ui.activity.TaskManageActivity_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import common.geocraft.untiltools.FileUtils;
import common.geocraft.untiltools.T;
import common.geocraft.untiltools.Tools;

/**
 * Created by Administrator on 2016/6/4.
 */
@EBean
public class NewTaskController extends BaseController {

	private TaskInfo mCurrentTaskInfo;
	private ArrayList<String> mFileList;

	@Bean
	TaskManager mTaskManager;

	public void initTemplateFolderList() throws IOException {
		ArrayList<File> fileList = FileUtils.listFiles(ConstPath.getTemplateRootFolder());
		mFileList = new ArrayList<>();
		for (int i = 0; i < fileList.size(); i++) {
			mFileList.add(fileList.get(i).getName());
		}
	}

	public ArrayList<String> getFileList(Context context) {
		if (mFileList == null) {
			mFileList = new ArrayList<>();
			mFileList.add(context.getString(R.string.not_found));
		}
		return mFileList;
	}

	public boolean createTask(Context context) throws TaskExistException {
		mCurrentTaskInfo = new TaskInfo();
		mCurrentTaskInfo.setTaskName(((NewTaskActivity) context).getTaskName());
		mCurrentTaskInfo.setCollector(((NewTaskActivity) context).getTaskCollector());
		mCurrentTaskInfo.setCollectTime(Tools.GetCurrentFormatTime1());
		mCurrentTaskInfo.setTemplateName(((NewTaskActivity) context).getTaskTemplate());
		mCurrentTaskInfo.setDescription(((NewTaskActivity) context).getTaskDescription());
		return mTaskManager.createTask(mCurrentTaskInfo);
	}

	public void operateWhenFinish(Context context, boolean isSucceed) {
		Intent intent = new Intent(context, TaskManageActivity_.class);
		intent.putExtra(Constants.INTENT_DATA_NEW_TASK_RESULT, isSucceed);
		((NewTaskActivity) context).setResult(ConstRequestCode.REQUEST_CODE_NEW_TASK, intent);
		((NewTaskActivity) context).finish();
	}
}
