package com.geocraft.electrics.sr.manager;

import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.sr.WellDatasets;
import com.geocraft.electrics.sr.WellType;
import com.huace.log.logger.L;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据管理单例类
 */
@EBean(scope = EBean.Scope.Singleton)
public class DataManager {
    public final static String SPACER_ID_MARKER = "&";
    private TaskManager mTaskManager = TaskManager_.getInstance_(
            ElectricApplication_.getInstance().getApplicationContext());
    private DbManager mDbManager = DbManager_.getInstance_(
            ElectricApplication_.getInstance().getApplicationContext());

    /**
     * 获取当前线路，指定类型的基桩号
     *
     * @param lineId   线路名称，
     * @param wellType 基桩类型
     */
    private List<DataSet> getWells(int lineId, WellType wellType) {
        String datasetName = WellDatasets.getMainDatasetName(wellType);
        DataSet dataset = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
                datasetName);
        return mDbManager.queryByCondition(dataset, Enum.GY_JKXLTZXX_FIELD_LINEID,
                String.valueOf(lineId), true);
    }

    /**
     * 获取当前线路，指定类型的基桩号名称
     *
     * @param lineId   线路名称，
     * @param wellType 基桩类型
     */
    public List<String> getWellNames(int lineId, WellType wellType) {
        List<DataSet> dataSets = getWells(lineId, wellType);
        List<String> wellNames = new ArrayList<>();
        for (DataSet dataSet : dataSets) {
            String name = dataSet.GetFieldValueByName(Enum.GYCJ_LINE_F_GH);
            if (null == name || name.isEmpty()) {
                continue;
            }
            wellNames.add(name);
        }
        return wellNames;
    }

    /**
     * 获取当前线路，指定类型的基桩号
     *
     * @param lineId 线路名称，
     */
    public List<DataSet> getWellsOfLine(int lineId) {
        List<DataSet> dataSets = new ArrayList<>();
        for (WellType wellType : WellType.values()) {
            dataSets.addAll(getWells(lineId, wellType));
        }
        return dataSets;
    }

    /**
     * 获取当前线路，非开闭所基桩名称
     *
     * @param lineId 线路名称
     */
    public List<String> getWellNames_JK_DL(int lineId) {
        List<String> wellNames = new ArrayList<>();
        List<DataSet> dataSets = new ArrayList<DataSet>();
        dataSets.addAll(getWells(lineId, WellType.JK));
        dataSets.addAll(getWells(lineId, WellType.DL));
        for (DataSet dataSet : dataSets) {
            String name = dataSet.GetFieldValueByName(Enum.GYCJ_LINE_F_GH);
            if (null == name || name.isEmpty()) {
                continue;
            }
            wellNames.add(name);
        }
        return wellNames;
    }


}
