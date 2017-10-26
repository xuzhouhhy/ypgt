package com.geocraft.electrics.ui.activity.andvance;

/**
 * 工井号配置信息
 */

public class WellOption {
    private boolean mIsCheckDLXL;//电缆线路
    private boolean mIsCheckHWG;//环网柜
    private boolean mIsCheckKBS;//开闭所
    private boolean mIsCheckFJX;//分接箱
    private boolean mIsCheckDLFJX; //电缆分接箱
    private boolean mIsCheckXSBDZ;//箱式变电站

    public boolean isCheckHWG() {
        return mIsCheckHWG;
    }

    public void setCheckHWG(boolean checkHWG) {
        mIsCheckHWG = checkHWG;
    }

    public boolean isCheckDLXL() {
        return mIsCheckDLXL;
    }

    public void setCheckDLXL(boolean checkDLXL) {
        mIsCheckDLXL = checkDLXL;
    }

    public boolean isCheckKBS() {
        return mIsCheckKBS;
    }

    public void setCheckKBS(boolean checkKBS) {
        mIsCheckKBS = checkKBS;
    }

    public boolean isCheckFJX() {
        return mIsCheckFJX;
    }

    public void setCheckFJX(boolean checkFJX) {
        mIsCheckFJX = checkFJX;
    }

    public boolean isCheckDLFJX() {
        return mIsCheckDLFJX;
    }

    public void setCheckDLFJX(boolean checkDLFJX) {
        mIsCheckDLFJX = checkDLFJX;
    }

    public boolean isCheckXSBDZ() {
        return mIsCheckXSBDZ;
    }

    public void setCheckXSBDZ(boolean checkXSBDZ) {
        mIsCheckXSBDZ = checkXSBDZ;
    }
}
