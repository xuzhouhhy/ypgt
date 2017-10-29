package com.geocraft.electrics.sr;

/**
 * 表类型映射
 */

public class DatasetOption {
    private String mDatasetName = "";
    private WellType mWellType = WellType.JK;

    public DatasetOption(String datasetName, WellType wellType) {
        mDatasetName = datasetName;
        mWellType = wellType;
    }

    public String getDatasetName() {
        return mDatasetName;
    }

    public void setDatasetName(String datasetName) {
        mDatasetName = datasetName;
    }

    public WellType getWellType() {
        return mWellType;
    }

    public void setWellType(WellType wellType) {
        mWellType = wellType;
    }
}
