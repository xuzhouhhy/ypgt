package com.geocraft.electrics.event;

/**
 * 线路菜单
 *
 * @author kingdon
 */

public class LineMenuEventArgs {
    private String mLineName;

    public LineMenuEventArgs(String lineName) {
        mLineName = lineName;
    }

    public String getLineName() {
        return mLineName;
    }
}
