package com.geocraft.electrics.ui.controller;

import android.content.Context;
import android.media.MediaMetadata;

import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.entity.TaskInfo;
import com.geocraft.electrics.event.ExportEvent;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import common.geocraft.untiltools.SDCardManager;

/**
 * Created by hanpengfei on 2016/6/6.
 */
public class ExportController extends ImportExportController {
	private boolean bFlag;
	public ExportController(Context context) {
		super(context);

		//注册
		if (!ElectricApplication.BUS.isRegistered(this)) {
			ElectricApplication.BUS.register(this);
		}
	}

	@Override
	protected String doInBackground(Integer... params) {

		try {
			TaskInfo taskInfo = taskManager.getTaskInfo();

			//通过Excel文件构建数据集
			String exportPath = "";
			String templatePath = "";
			String taskPath = "";
			String resultPath = "";
			String failed = "      导出失败!\n";
			String success = "      导出成功!\n";

			String message = "";

			taskPath = ConstPath.getTaskRootFolder() + taskInfo.getTaskName();
			if (!SDCardManager.IsExisted(taskPath)) {
				SDCardManager.CreateFolder(taskPath);
			}

			resultPath = taskPath + "/成果/";
			if (!SDCardManager.IsExisted(resultPath)) {
				SDCardManager.CreateFolder(resultPath);
			}

			templatePath = taskPath + "/模板/";

			 bFlag = false;
			for (DataSetGroup group : taskManager.getDataSource().DataSourceGroups) {
				exportPath = resultPath;
				exportPath += group.Alias + "/";
				if (!SDCardManager.IsExisted(exportPath)) {
					SDCardManager.CreateFolder(exportPath);
				}
				exportData(group.DataSets,templatePath,exportPath,message,success,failed);
			}
			if(!bFlag)
			{
				progressMsg += "数据库中无数据!";
			}

		} catch (Exception e) {
			progressMsg += "导出失败!";
			progressDialog.dismiss();
			L.printException(e);
		}

		return "";
	}

	private void exportData(List<DataSet> dataSets, String templatePath,String exportPath,
	                        String message,String success,String failed){
		for (DataSet dataSet : dataSets)
		{
			//只有需要导出的才导出
			if(dataSet.IsExport) {
				//从数据库里获取数据
				List<DataSet> tmpDataSets = dbManager.queryAll(dataSet,false);
				if(tmpDataSets.size() > 0) {
					//导出数据
					if (Utils.DataSetsToXLS(progressDialog, tmpDataSets, dataSet.Name, dataSet.Alias, templatePath, exportPath)) {
						message = dataSet.Alias + success;
					} else {
						message = dataSet.Alias + failed;
					}
					progressMsg += message;
					bFlag = true;
				}
			}
			if (dataSet.DataSets.size()>0){
				exportData(dataSet.DataSets,templatePath,exportPath,message,success,failed);
			}
		}
	}

	@Subscribe
	public void onEventMainThread(ExportEvent event) {
		publishProgress(1);
	}
}
