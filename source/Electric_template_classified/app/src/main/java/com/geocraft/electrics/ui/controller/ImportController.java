package com.geocraft.electrics.ui.controller;

import android.content.Context;

import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.entity.GroupAndDataSetName;
import com.geocraft.electrics.utils.ElectricFileUtils;
import com.geocraft.electrics.utils.PinYinUtil;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import java.util.ArrayList;

/**
 * Created by hanpengfei on 2016/6/6.
 */
public class ImportController extends ImportExportController
{
    public ImportController(Context context,ArrayList<String> filePaths)
    {
        super(context,filePaths);
    }
    public void cleanTable(DataSet dataSet){
        dbManager.clearTableByName(dataSet.GroupName+"_"+dataSet.Name);

        for (int i=0;i< dataSet.DataSets.size();i++){
            cleanTable(dataSet.DataSets.get(i));
        }
    }
    @Override
    protected String doInBackground(Integer... params) {

        try
        {
            //导入之前，先清空表
            for(DataSetGroup group : taskManager.getDataSource().DataSourceGroups)
            {
                for(DataSet dataSet : group.DataSets)
                {
                    cleanTable(dataSet);
                }
            }

            //通过Excel文件构建数据集
            String fileName = "";
            String temp = "";
            String success = "      导入成功!\n";
            String failed = "      导入失败!\n";
            String tableName = "";
            String groudName = "";
            String dataSetName = "";
            for(int i = 0; i < importFiles.size(); i++)
            {
                fileName = ElectricFileUtils.getFileName(importFiles.get(i));

                //通过文件名获取数据表的名称，先判断这个表是否存在
                tableName = PinYinUtil.getFirstSpell(fileName);
                if(!dbManager.tableIsExist(tableName))
                {
                    temp = failed;
                }
                else
                {
                    //获取当前任务的数据源对象,获取该导入文件对应的数据集，从而获取对应的表结构
                    GroupAndDataSetName tmpObj = Utils.getGourpAndDataSetName(fileName);
                    if(tmpObj.groupName.isEmpty() || tmpObj.dataSetName.isEmpty())
                    {
                        temp = failed;
                    }
                    else
                    {
                        DataSet templateDataSet = taskManager.getDataSource().getDataSetByName(PinYinUtil.getFirstSpell(tmpObj.groupName),PinYinUtil.getFirstSpell(tmpObj.dataSetName));
                        if(templateDataSet == null)
                        {
                            temp = failed;
                        }
                        else
                        {
                            ArrayList<DataSet> dstaSets = Utils.XLSToDataSets(templateDataSet,importFiles.get(i));

                            if(dstaSets.size() > 0)
                            {
                                //导入数据库
                                boolean bFlag = true; //如果有一条记录没有导入成，则导入失败

                                progressDialog.setMax(dstaSets.size());

                                for(DataSet dataSet : dstaSets)
                                {
                                    int count = dbManager.insert(dataSet);
                                    if(count <= 0)
                                    {
                                        temp = failed;
                                        bFlag = false;
                                        break;
                                    }

                                    //进度条更新
                                    publishProgress(1);
                                }

                                if(bFlag)
                                {
                                    temp = success;
                                }
                            }
                            else
                            {
                                temp = failed;
                            }
                        }
                    }
                }

                progressMsg += fileName + temp;
            }
        }
        catch(Exception e)
        {
            L.printException(e);
            progressMsg += "导入失败!";
            progressDialog.dismiss();
        }

        return "";
    }
}
