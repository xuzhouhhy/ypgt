package com.geocraft.electrics.event;

/**
 * Created by hanpengfei on 2016/6/14.
 */
public class ThreeLevelMenuEvent extends  BaseEvent
{
    public String firstValue;
    public String secondValue;
    public String thirdValue;

    public ThreeLevelMenuEvent(String name, String msg,String firstValue,String secondValue,String thirdValue)
    {
        super(name,msg);

        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }
}
