package com.geocraft.electrics.sr;

/**
 * 井号类型
 */

public enum WellType {
    JK,//架空杠
    DL,//电缆井
    KBS;//开闭所

    public String getDatasetName() {
        String datasetName = "";
        switch (this) {
            case JK:
                datasetName = "GY_JKXLTZXX";
                break;
            case DL:
                datasetName = "GY_DLXLTZXX";
                break;
            case KBS:
                datasetName = "GY_KBSTZXX";
                break;
            default:
                break;
        }
        return datasetName;
    }
}
