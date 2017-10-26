package com.geocraft.electrics.entity;

import com.huace.log.logger.L;

import java.util.ArrayList;

/**
 * Created by hanpengfei on 2016/6/4.
 */
public class FieldInfo implements  Cloneable
{
    public String    Name;
    public String    Alias;
    public String    Type;
    public int       Length;
    public String    Default;
    public String    Value;
    public boolean   IsSystemField;
    public boolean   IsRequired;
    public boolean   IsUnique;
    public boolean   IsVisible;
    public boolean   IsDisable;
    public boolean   IsCopy;
    public boolean   IsNum;
    public int       Sort;
    public int       OperateCode;
    public PropertyDictionay Dictionay = new PropertyDictionay();

    public FieldInfo()
    {
        Name = "";
        Alias = "";
        Type = "";
        Length = 0;
        Default = "";
        Value = "";
        IsSystemField = false;
        IsRequired = false;
        IsUnique = false;
        IsVisible = false;
        IsDisable = false;
        IsCopy = false;
        IsNum = false;
        Sort = 0;
        OperateCode = 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        FieldInfo fieldInfo = null;
        try {
            fieldInfo = (FieldInfo) super.clone();
            fieldInfo.Dictionay = (PropertyDictionay) Dictionay.clone();
        }
        catch (CloneNotSupportedException e)
        {
            L.printException(e);
        }
        return fieldInfo;
    }
}
