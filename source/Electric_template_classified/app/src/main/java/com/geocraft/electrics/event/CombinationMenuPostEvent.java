package com.geocraft.electrics.event;

/**
 * 作者  zhouqin
 * 时间 2016/6/10.
 */
public class CombinationMenuPostEvent {
    private String mFirstValue;
    private String mSecondValue;
    public CombinationMenuPostEvent(String firstValue, String secondValue) {
        mFirstValue =firstValue;
        mSecondValue = secondValue;
    }

    public String getFirstValue() {
        return mFirstValue;
    }

    public String getSecondValue() {
        return mSecondValue;
    }
}
