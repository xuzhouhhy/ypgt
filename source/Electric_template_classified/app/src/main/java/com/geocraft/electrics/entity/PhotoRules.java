package com.geocraft.electrics.entity;

import com.huace.log.logger.L;

import java.util.ArrayList;

/**
 * Created by hanpengfei on 2016/6/4.
 */
public class PhotoRules implements Cloneable
{
    public String Type;
    public int    Number;
    public String Rules;

    public PhotoRules()
    {
        Type = "";
        Number = 0;
        Rules = "";
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        PhotoRules rules = null;
        try
        {
           rules = (PhotoRules) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            L.printException(e);
        }
        return rules;
    }
}
