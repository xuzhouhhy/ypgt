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
    private List<String> mPropterDatasets = new ArrayList<String>();

    public static String getMainDatasetName(WellType wellType) {
        if (wellType == WellType.JK) {
            return Enum.GY_JKXLTZXX;
        } else {
            return Enum.GY_DLXLTZXX;
        }
    }

    /**
     * 获取井号表
     *
     * @return
     */
    public List<String> getWellDatasetNames() {
        List<String> datsetNames = new ArrayList<String>();
        datsetNames.add(Enum.GY_JKXLTZXX);
        datsetNames.add(Enum.GY_DLXLTZXX);
        return datsetNames;
    }

    /**
     * 获取井号表属性
     *
     * @return
     */
    public List<String> getGetPropterDatasets() {
        if (mPropterDatasets.size() > 0) {
            return mPropterDatasets;
        }
        return mPropterDatasets;
    }

    /**
     * 当前表是否为井号属性表
     */
    public boolean isPropterDataset(String datasetName) {
        mPropterDatasets = getGetPropterDatasets();
        return mPropterDatasets.contains(datasetName);
    }
}
