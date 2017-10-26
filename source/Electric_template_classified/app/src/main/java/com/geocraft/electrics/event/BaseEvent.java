package com.geocraft.electrics.event;

/**
 * Created by hanpengfei on 2016/6/5.
 */
public class BaseEvent
{
    public String Name;
    public String Msg;

    public BaseEvent(String name, String msg)
    {
        Name = name;
        Msg = msg;
    }
}
