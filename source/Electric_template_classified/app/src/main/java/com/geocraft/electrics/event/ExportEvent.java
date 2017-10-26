package com.geocraft.electrics.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by hanpengfei on 2016/6/7.
 */
public class ExportEvent extends BaseEvent
{
    public ExportEvent(String name, String msg)
    {
        super(name,msg);
    }
}
