package com.geocraft.electrics.entity;

import com.huace.log.logger.L;

import java.util.ArrayList;

/**
 * Created by hanpengfei on 2016/6/4.
 */
public class DataSource {
    public String Name;
    public String Alias;
    public int Type;
    public String Description;
    public String AssignPeople;
    public String AcceptPeople;
    public String CreateTime;
    public String CompleteTime;
    public String State;
    public ArrayList<DataSetGroup> DataSourceGroups = new ArrayList<DataSetGroup>();

    public DataSource() {
        Name = "";
        Alias = "";
        Type = 0;
        Description = "";
        AssignPeople = "";
        AcceptPeople = "";
        CreateTime = "";
        CompleteTime = "";
        State = "";
    }

    public DataSetGroup getGroupByName(String groupName) {
        for (int i = 0; i < DataSourceGroups.size(); i++) {
            if (DataSourceGroups.get(i).Name.equalsIgnoreCase(groupName)) {
                return DataSourceGroups.get(i);
            }
        }
        return null;
    }

    private DataSet getDataSetByName(DataSet dataSet, String name) {
        DataSet dataSetTmp = null;
        try {
            if (dataSet.Name.equals(name)) {
                dataSetTmp = (DataSet) dataSet.clone();
            } else {
                for (DataSet tmp : dataSet.DataSets) {
                    DataSet dataSet1 = getDataSetByName(tmp, name);
                    if (dataSet1 != null) {
                        dataSetTmp = dataSet1;
                        break;
                    }
                }
            }
        } catch (CloneNotSupportedException e) {
            L.printException(e);
        }


        return dataSetTmp;
    }

    public DataSet getDataSetByName(String groupName, String name) {
        DataSet dataSet = null;
        for (DataSetGroup group : DataSourceGroups) {
            if (group.Name.equals(groupName)) {
                for (DataSet item : group.DataSets) {
                    dataSet = getDataSetByName(item, name);
                    if (dataSet != null) {
                        return dataSet;
                    }
                }
                break;
            }
        }
        return dataSet;
    }
}
