package com.geocraft.electrics.event;

import com.geocraft.electrics.entity.DataSet;

/**
 * Created by hanpengfei on 2016/6/9.
 */
public class SearchControlEvent extends BaseEvent
{
    public DataSet dataSet = null;
    public SearchControlEvent(String name, String msg,DataSet dataSet)
    {
        super(name,msg);

        this.dataSet = dataSet;
    }
}
