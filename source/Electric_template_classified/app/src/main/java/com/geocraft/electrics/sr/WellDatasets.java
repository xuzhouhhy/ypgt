package com.geocraft.electrics.sr;

import com.geocraft.electrics.constants.Enum;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 井号相关表
 */
@EBean
public class WellDatasets {
    private List<DatasetOption> mDatasetOptions = new ArrayList<DatasetOption>();

    public static String getMainDatasetName(WellType wellType) {
        if (wellType == WellType.JK) {
            return Enum.GY_JKXLTZXX;
        } else if (wellType == WellType.DL) {
            return Enum.GY_DLXLTZXX;
        } else {
            return Enum.GY_KBSTZXX;
        }
    }

    /**
     * 获取井号表(前期业务考虑)
     */
    public List<DatasetOption> getWellDataSets() { // TODO: 2017/10/29 待优化
        if (mDatasetOptions != null && mDatasetOptions.size() > 0) {
            return mDatasetOptions;
        }
        List<DatasetOption> datasetOptions = new ArrayList<DatasetOption>();
        DatasetOption jk = new DatasetOption(Enum.GY_JKXLTZXX, WellType.JK);
        DatasetOption dl = new DatasetOption(Enum.GY_DLXLTZXX, WellType.DL);
        DatasetOption kbs = new DatasetOption(Enum.GY_KBSTZXX, WellType.KBS);

        datasetOptions.add(jk);
        datasetOptions.add(dl);
        datasetOptions.add(kbs);
        return datasetOptions;
    }

}
