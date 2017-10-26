package com.geocraft.electrics.event;

/**
 * 作者  zhouqin
 * 时间 2016/6/10.
 */
public class ThreeLevelMenuPostEvent {
    private String mFirstValue;
    private String mSecondValue;
    private String mThirdValue;

    public ThreeLevelMenuPostEvent(String firstValue, String secondValue, String thirdValue) {
        mFirstValue = firstValue;
        mSecondValue = secondValue;
        mThirdValue = thirdValue;
    }

    public String getFirstValue() {
        return mFirstValue;
    }

    public String getSecondValue() {
        return mSecondValue;
    }

    public String getThirdValue() {
        return mThirdValue;
    }
}
