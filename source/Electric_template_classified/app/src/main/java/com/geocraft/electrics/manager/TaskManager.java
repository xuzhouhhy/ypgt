package com.geocraft.electrics.manager;

import android.util.Xml;

import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSource;
import com.geocraft.electrics.entity.TaskInfo;
import com.geocraft.electrics.utils.ElectricFileUtils;
import com.geocraft.electrics.utils.ElectricXmlUtils;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import common.geocraft.untiltools.FileUtils;
import common.geocraft.untiltools.Tools;

/**
 * Created by Administrator on 2016/6/5.
 */
@EBean(scope = EBean.Scope.Singleton)
public class TaskManager {

	private DataSource mDataSource;
	private TaskInfo mTaskInfo;

	@Bean
	DbManager mDbManager;

	public TaskInfo getTaskInfo() {
		return mTaskInfo;
	}

	public DataSource getDataSource() {
		return mDataSource;
	}

	public boolean checkTaskExist(TaskInfo taskInfo) {
		if (taskInfo == null) {
			return false;
		}
		if (FileUtils.existFile(ConstPath.getTaskRootFolder() + taskInfo.getTaskName())) {
			return true;
		}
		return false;
	}

	public boolean openOrCreateDefaultTask() {
		TaskInfo defaultTaskInfo = getDefaultTask();
		if (checkTaskExist(defaultTaskInfo)) {
			try {
				return openTask(defaultTaskInfo.getTaskName());
			} catch (TaskExistException e) {
				L.printException(e);
				return false;
			}
		} else {
			try {
				return createTask(defaultTaskInfo);
			} catch (TaskExistException e) {
				L.printException(e);
				return false;
			}
		}
	}

	public boolean createTask(TaskInfo taskInfo) throws TaskExistException {
		if (!createTaskFolder(taskInfo)) {
			throw new TaskExistException();
		}
		if (!createTemplateFolder(taskInfo)) {
			deleteFolderTask(taskInfo);
			return false;
		}
		if (!createDataBase(taskInfo)) {
			deleteFolderTask(taskInfo);
			return false;
		}
		createPhotoFolder(taskInfo);
		createResultFolder(taskInfo);
		if (!createTaskFile(taskInfo)) {
			deleteFolderTask(taskInfo);
			mDataSource = null;
			return false;
		}
		mTaskInfo = taskInfo;
		return true;
	}

	public boolean openTask(String taskName) throws TaskExistException {
		String taskPath = ConstPath.getTaskRootFolder() + taskName;
		if (!FileUtils.existFile(taskPath)) {
			throw new TaskExistException();
		}
		if (!initTaskInfo(taskPath, taskName)) {
			return false;
		}
		if (!initDataSource(taskPath, taskName)) {
			return false;
		}
		String dbPath = taskPath + File.separator + taskName + Constants.DB_SUFFIX;
		if (!mDbManager.openOrCreateDatabases(dbPath)) {
			return false;
		}
		return true;
	}

	public void closeCurrentTask() {
		mDbManager.close();
		mTaskInfo = null;
		mDataSource = null;
	}

	private TaskInfo getDefaultTask() {
		TaskInfo defaultTaskInfo = new TaskInfo();
		defaultTaskInfo.setTaskName(Constants.DEFAULT_TASK_NAME);
		defaultTaskInfo.setCollector(Constants.DEFAULT_TASK_COLLECTOR);
		defaultTaskInfo.setCollectTime(Tools.GetCurrentFormatTime1());
		defaultTaskInfo.setTemplateName(Constants.DEFAULT_TASK_TEMPLATE_NAME);
		defaultTaskInfo.setDescription(Constants.DEFAULT_TASK_DESCRIPTION);
		return defaultTaskInfo;
	}

	private boolean createTaskFolder(TaskInfo taskInfo) {
		File fileDirFile = new File(ConstPath.getTaskRootFolder() + taskInfo.getTaskName());
		if (fileDirFile.exists()) {
			return false;
		}
		return fileDirFile.mkdirs();
	}

	private boolean createTemplateFolder(TaskInfo taskInfo) {
		String fileName = ConstPath.getTaskRootFolder() + taskInfo.getTaskName() + File.separator
				+ Constants.TASK_TEMPLATE_FOLDER;
		File fileDirFile = new File(fileName);
		if (!fileDirFile.exists()) {
			fileDirFile.mkdirs();
		}
		String templateSourceFolderPath = ConstPath.getTemplateRootFolder() + taskInfo.getTemplateName();
		try {
			FileUtils.copyFolder(templateSourceFolderPath, fileName);
		} catch (IOException e) {
			L.printException(e);
			return false;
		}
		return true;
	}

	private boolean createDataBase(TaskInfo taskInfo) {
		String taskDbName = taskInfo.getTaskName() + Constants.DB_SUFFIX;
		String taskDbParentPath = ConstPath.getTaskRootFolder() + taskInfo.getTaskName();
		String taskDbPath = taskDbParentPath + File.separator + taskDbName;
		if (!mDbManager.openOrCreateDatabases(taskDbPath)) {
			return false;
		}
		initDataSource(taskDbParentPath, taskInfo.getTaskName());
		if (!mDbManager.crateTableByDataSource(mDataSource)) {
			mDbManager.close();
			return false;
		}
		return true;
	}

	private boolean createTaskFile(TaskInfo taskInfo) {
		String taskFileName = taskInfo.getTaskName() + Constants.TASK_SUFFIX;
		String fileName = ConstPath.getTaskRootFolder() + taskInfo.getTaskName() + File.separator
				+ taskFileName;
		File fileDirFile = new File(fileName);
		if (!fileDirFile.exists()) {
			try {
				fileDirFile.createNewFile();
			} catch (IOException e) {
				L.printException(e);
				return false;
			}
			try {
				OutputStream task = new FileOutputStream(fileDirFile);
				writeTaskFile(task, taskInfo);
			} catch (Exception e) {
				L.printException(e);
				return false;
			}


		}
		return true;
	}

	private void writeTaskFile(OutputStream out, TaskInfo taskInfo) throws Exception {
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(out, "UTF-8");
		serializer.startDocument("UTF-8", true);


		serializer.startTag(null, Constants.XML_TAG_TASK);

		serializer.startTag(null, Constants.XML_TAG_NAME);
		serializer.text(taskInfo.getTaskName());
		serializer.endTag(null, Constants.XML_TAG_NAME);

		serializer.startTag(null, Constants.XML_TAG_COLLECTOR);
		serializer.text(taskInfo.getCollector());
		serializer.endTag(null, Constants.XML_TAG_COLLECTOR);

		serializer.startTag(null, Constants.XML_TAG_TIME);
		serializer.text(taskInfo.getCollectTime());
		serializer.endTag(null, Constants.XML_TAG_TIME);

		serializer.startTag(null, Constants.XML_TAG_TEMPLATE);
		serializer.text(taskInfo.getTemplateName());
		serializer.endTag(null, Constants.XML_TAG_TEMPLATE);

		serializer.startTag(null, Constants.XML_TAG_DESCRIPTION);
		serializer.text(taskInfo.getDescription());
		serializer.endTag(null, Constants.XML_TAG_DESCRIPTION);

		serializer.endTag(null, Constants.XML_TAG_TASK);

		serializer.endDocument();
		out.flush();
		out.close();
	}

	private void createPhotoFolder(TaskInfo taskInfo) {
		String fileName = ConstPath.getTaskRootFolder() + taskInfo.getTaskName() + File.separator
				+ Constants.TASK_PHOTO_FOLDER;
		File fileDirFile = new File(fileName);
		if (!fileDirFile.exists()) {
			fileDirFile.mkdirs();
		}
		String cachePath = fileName + Constants.TASK_CACHE_PATH;
		File cacheFile = new File(cachePath);
		if (!cacheFile.exists()) {
			cacheFile.mkdirs();
		}
	}

	//TODO:扩充
	private void createResultFolder(TaskInfo taskInfo) {
		String fileName = ConstPath.getTaskRootFolder() + taskInfo.getTaskName() + File.separator
				+ Constants.TASK_RESULT_FOLDER;
		File fileDirFile = new File(fileName);
		if (!fileDirFile.exists()) {
			fileDirFile.mkdirs();
		}
	}

	private void deleteFolderTask(TaskInfo taskInfo) {
		FileUtils.delFolder(ConstPath.getTaskRootFolder() + taskInfo.getTaskName());
	}

	private boolean initTaskInfo(String taskPath, String taskName) {
		String taskFileName = taskName + Constants.TASK_SUFFIX;
		String taskFilePath = taskPath + File.separator + taskFileName;
		mTaskInfo = null;
		mTaskInfo = ElectricXmlUtils.getTaskInfoByTsk(taskFilePath);
		if (mTaskInfo != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean initDataSource(String taskPath, String taskName) {
		String taskDbName = taskName + Constants.DB_SUFFIX;
		String taskDbPath = taskPath + File.separator + taskDbName;
		if (!FileUtils.existFile(taskDbPath)) {
			return false;
		}
		initDataSourceByTemplate(taskPath);
		return true;
	}

	private boolean initDataSourceByTemplate(String taskPath) {
		String templateParentPath = taskPath + File.separator + Constants.TASK_TEMPLATE_FOLDER;
		if (!FileUtils.existFile(templateParentPath)) {
			return false;
		}
		File templateParentFile = new File(templateParentPath);
		File[] files = templateParentFile.listFiles();
		for (File file : files) {
			String templateSuffix = ElectricFileUtils.getFileExt(file);
			if (templateSuffix.equalsIgnoreCase("xml") && file.getName().contains("模板")) {
				mDataSource = null;
				mDataSource = Utils.XmlDataSource(file.getPath());
				return true;
			}
		}
		return false;
	}

}
