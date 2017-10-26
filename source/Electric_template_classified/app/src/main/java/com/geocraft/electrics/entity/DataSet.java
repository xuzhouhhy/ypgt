package com.geocraft.electrics.entity;

import com.huace.log.logger.L;

import java.util.ArrayList;

/**
 * Created by hanpengfei on 2016/6/4.
 */
public class DataSet implements Cloneable {
    public String Name;
    public String Alias;
    public String EntityType;
    public String DeviceType;
    public String Mark;
    public String Symbol;
    public boolean IsVisible;
    public int PrimaryKey;
    public String State;
    public String First;
    public String Second;
    public String Third;
    public String GroupName;
    public boolean IsExport;
    public boolean IsShowPhoto;
    public boolean IsShowCoordinate;
    public boolean IsShowBase;
    public boolean IsShowNecessary;
    public String SearchField;
    public String ValueField;
    public ArrayList<FieldInfo> FieldInfos = new ArrayList<FieldInfo>();
    public ArrayList<PhotoRules> PhotoRules = new ArrayList<PhotoRules>();
    public ArrayList<DataSet> DataSets = new ArrayList<DataSet>();
    private boolean mIsShowInDeviceList;//跳转到第三级

    public DataSet() {
        Name = "";
        Alias = "";
        EntityType = "";
        DeviceType = "";
        Mark = "";
        Symbol = "";
        IsVisible = false;
        PrimaryKey = -1;
        State = "";
        First = "";
        Second = "";
        Third = "";
        GroupName = "";
        IsExport = true;
        IsShowPhoto = false;
        IsShowCoordinate = false;
        IsShowBase = true;
        IsShowNecessary = true;
        SearchField = "";
        ValueField = "";
        mIsShowInDeviceList = false;
    }

    public String GetFieldNameByName(String name) {
        for (int i = 0; i < FieldInfos.size(); i++) {
            if (name.equals(FieldInfos.get(i).Name)) {
                return FieldInfos.get(i).Alias;
            }
        }
        return null;
    }

    public String GetFieldValueByAlias(String name) {
        String str = "";

        for (int i = 0; i < FieldInfos.size(); i++) {
            if (name.equals(FieldInfos.get(i).Alias)) {
                str = FieldInfos.get(i).Value;
                break;
            }
        }
        return str;
    }

    public String GetFieldValueByName(String name) {
        String str = "";
        for (int i = 0; i < FieldInfos.size(); i++) {
            if (name.equals(FieldInfos.get(i).Name)) {
                str = FieldInfos.get(i).Value;
                break;
            }
        }
        return str;
    }

    public void SetFiledValueByAlias(String alias, String value) {
        for (int i = 0; i < FieldInfos.size(); i++) {
            if (alias.equals(FieldInfos.get(i).Alias)) {
                FieldInfos.get(i).Value = value;
                break;
            }
        }
    }

    public void SetFiledValueByName(String name, String value) {
        for (int i = 0; i < FieldInfos.size(); i++) {
            if (name.equals(FieldInfos.get(i).Name)) {
                FieldInfos.get(i).Value = value;
                break;
            }
        }
    }

    public ViewEntiry getViewEntiry() {
        ViewEntiry entiry = new ViewEntiry();

        entiry.First = First;
        entiry.Second = Second;
        entiry.Third = Third;

        return entiry;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        DataSet dataSet = null;
        try {
            dataSet = (DataSet) super.clone();

            //字段列表
            ArrayList<FieldInfo> fieldInfosTmp = new ArrayList<>();
            for (FieldInfo field : FieldInfos) {
                fieldInfosTmp.add((FieldInfo) field.clone());
            }
            dataSet.FieldInfos = fieldInfosTmp;

            //照片列表
            ArrayList<PhotoRules> photoRulesTmp = new ArrayList<>();
            for (com.geocraft.electrics.entity.PhotoRules photoRules : PhotoRules) {
                photoRulesTmp.add((PhotoRules) photoRules.clone());
            }
            dataSet.PhotoRules = photoRulesTmp;

            //数据集列表
            ArrayList<DataSet> dataSetsTmp = new ArrayList<>();
            for (DataSet dataSetTmp : DataSets) {
                dataSetsTmp.add((DataSet) dataSetTmp.clone());
            }
            dataSet.DataSets = dataSetsTmp;
        } catch (CloneNotSupportedException e) {
            L.printException(e);
        }
        return dataSet;
    }

    public class ViewEntiry {
        public String First;
        public String Second;
        public String Third;

        public ViewEntiry() {
            First = null;
            Second = null;
            Third = null;
        }
    }

    public boolean isShowInDeviceList() {
        return mIsShowInDeviceList;
    }

    public void setmIsShowInDeviceList(boolean mIsShowInDeviceList) {
        this.mIsShowInDeviceList = mIsShowInDeviceList;
    }
}
