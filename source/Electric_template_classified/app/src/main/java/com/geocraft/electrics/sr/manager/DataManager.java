package com.geocraft.electrics.sr.manager;

import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.DatasetOption;
import com.geocraft.electrics.sr.WellType;

import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * Created by SHC on 2017/11/3.
 */
@EBean(scope = EBean.Scope.Singleton)
public class DataManager {
    public final static String SPACER_ID_MARKER = "&";
//
//    private void querySpacerDatasets(String intervalId) {
//        String[] ids = intervalId.split("&");
//        if (0 == ids.length) {
//            return;
//        }
//        //间隔表
//        DataSet jgDs = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
//                Enum.spacer);
//        for (String id : ids) {
//            try {
//                jgDs.PrimaryKey = Integer.valueOf(id);
//            } catch (NumberFormatException e) {
//                L.e(e, "F_SpacerIds field of GY_DLXLTZXX table has wrong format");
//                continue;
//            }
//            DataSet intervalDs = mDbManager.queryByPrimaryKey(jgDs, true);
//            mDataSets.add(intervalDs);
//        }
//    }

//    private void initDatasets(int lineId, WellType wellType) {
//        List<DatasetOption> datasetOptions = mWellDatasets.getWellDataSets();
//        for (int i = 0; i < datasetOptions.size(); i++) {
//            DatasetOption datasetOption = datasetOptions.get(i);
//            DataSet dataset = mTaskManager.getDataSource().
//                    getDataSetByName(mFirstType, datasetOption.getDatasetName());
//            if (!mIsCreateRecord && datasetOption.getWellType() == mWellType) {
//                dataset.PrimaryKey = mWellId;
//                DataSet temp = mDbManager.queryByPrimaryKey(dataset, true);
//                if (temp != null) {
//                    dataset = temp;
//                }
//            }
//            mDataSets.add(dataset);
//        }
//    }
}
