package com.geocraft.electrics.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.entity.DataSource;
import com.geocraft.electrics.entity.FieldInfo;
import com.huace.log.logger.L;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/4.
 */
@EBean(scope = EBean.Scope.Singleton)
public class DbManager {

    private SQLiteDatabase mSQLiteDatabase;

    /**
     * get current db
     *
     * @return db
     */
    public SQLiteDatabase getSQLiteDatabase() {
        return mSQLiteDatabase;
    }

    /**
     * open or create db
     *
     * @param dbPath db's path
     * @return boolean
     */
    public synchronized boolean openOrCreateDatabases(String dbPath) {
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
        if (mSQLiteDatabase == null) {
            return false;
        }
        return true;
    }

    /**
     * close db
     */
    public synchronized void close() {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.close();
        }
    }

    /**
     * create table by datasource
     *
     * @param dataSource
     * @return boolean
     */
    public synchronized boolean crateTableByDataSource(DataSource dataSource) {
        if (dataSource == null) {
            return false;
        }
        List<DataSetGroup> dataSetGroupList = dataSource.DataSourceGroups;
        for (int i = 0; i < dataSetGroupList.size(); i++) {
            List<DataSet> dataSetList = dataSetGroupList.get(i).DataSets;
            createTableByDataSet(dataSetList);
        }
        return true;
    }

    private boolean createTableByDataSet(List<DataSet> dataSets) {

        for (int i = 0; i < dataSets.size(); i++) {
            if (!createTableByDataSet(dataSets.get(i))) {
                return false;
            }
            createTableByDataSet(dataSets.get(i).DataSets);
        }
        return true;
    }

    /**
     * create table by dataset
     *
     * @param dataSet
     * @return boolean
     */
    public synchronized boolean createTableByDataSet(DataSet dataSet) {
        try {
            if (dataSet == null) {
                return false;
            }
            String createSql = getCreateTableString(dataSet);
            mSQLiteDatabase.execSQL(createSql);
            return true;
        } catch (Exception e) {
            L.printException(e);
            return false;
        }
    }

    /**
     * drop table by table's name
     *
     * @param tableName
     * @return boolean
     */
    public synchronized boolean deleteTableByName(String tableName) {
        try {
            if (tableName.length() <= 0 || mSQLiteDatabase == null) {
                return false;
            }
            mSQLiteDatabase.execSQL("DROP TABLE [" + tableName + "]");
            return true;
        } catch (Exception e) {
            L.printException(e);
            return false;
        }
    }

    /**
     * delete all data of table
     *
     * @param tableName
     * @return boolean
     */
    public synchronized boolean clearTableByName(String tableName) {
        try {
            if (tableName.length() < 0 || mSQLiteDatabase == null) {
                return false;
            }
            mSQLiteDatabase.execSQL("DELETE FROM [" + tableName + "]");
            return true;
        } catch (Exception e) {
            L.printException(e);
            return false;
        }
    }

    /**
     * insert new data into table
     *
     * @param dataSet
     * @return the primary key of record
     */
    public synchronized int insert(DataSet dataSet) {
        if (mSQLiteDatabase == null || dataSet == null) {
            return -1;
        }

        //将状态值设置为 新建
        if (!dataSet.GetFieldValueByAlias("状态").equals("导入")) {
            dataSet.SetFiledValueByAlias("状态", "新建");
        }

        ContentValues contentValues = getContentValues(dataSet);
        if (contentValues == null) {
            return -1;
        }

        return (int) mSQLiteDatabase.insert("[" + dataSet.GroupName + "_" + dataSet.Name + "]", null, contentValues);
    }

    /**
     * update data
     *
     * @param dataSet
     * @return boolean
     */
    public synchronized boolean update(DataSet dataSet) {
        if (mSQLiteDatabase == null || dataSet == null
                || dataSet.Name.length() <= 0 || dataSet.PrimaryKey < 0) {
            return false;
        }

        String status = dataSet.GetFieldValueByAlias("状态");
        if (status.equals("导入")) {
            dataSet.SetFiledValueByAlias("状态", "修改");
        }

        ContentValues contentValues = getContentValues(dataSet);
        if (contentValues == null) {
            return false;
        }
        String selection = "_id=?";
        String[] args = {String.valueOf(dataSet.PrimaryKey)};

        if (mSQLiteDatabase.update("[" + dataSet.GroupName + "_" + dataSet.Name + "]", contentValues, selection, args) > 0) {
            return true;
        }
        return false;
    }

    /**
     * delete data
     *
     * @param dataSet
     * @return boolean
     */
    public synchronized boolean delete(DataSet dataSet) {
        if (mSQLiteDatabase == null || dataSet == null
                || dataSet.Name.length() <= 0 || dataSet.PrimaryKey < 0) {
            return false;
        }
        String status = dataSet.GetFieldValueByAlias("状态");
        if (status.equals("导入") || status.equals("修改")) {
            dataSet.SetFiledValueByAlias("状态", "删除");
            ContentValues contentValues = getContentValues(dataSet);
            if (contentValues == null) {
                return false;
            }
            String selection = "_id=?";
            String[] args = {String.valueOf(dataSet.PrimaryKey)};

            if (mSQLiteDatabase.update("[" + dataSet.GroupName + "_" + dataSet.Name + "]", contentValues, selection, args) > 0) {
                return true;
            } else {
                dataSet.State = status;
                return false;
            }
        } else {
            String selection = "_id=?";
            String[] args = {String.valueOf(dataSet.PrimaryKey)};
            if (mSQLiteDatabase.delete("[" + dataSet.GroupName + "_" + dataSet.Name + "]", selection, args) > 0) {
                return true;
            }
            return false;
        }
    }

    /**
     * get whole data of table
     *
     * @param dataSet
     * @return dataset's list
     */
    public synchronized List<DataSet> queryAll(DataSet dataSet, boolean isFilter) {
        List<DataSet> dataSetList = new ArrayList<>();
        if (mSQLiteDatabase == null || dataSet == null || dataSet.Name.length() <= 0) {
            return dataSetList;
        }
        Cursor cursor = mSQLiteDatabase.query("[" + dataSet.GroupName + "_" + dataSet.Name + "]", null, null, null, null, null, null);
        if (cursor == null) {
            return dataSetList;
        }
        if (cursor.moveToFirst()) {
            do {
                DataSet dataSetTemp = null;
                dataSetTemp = getDataSetByCursor(dataSet, cursor, isFilter);
                if (dataSetTemp != null) {
                    dataSetList.add(dataSetTemp);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dataSetList;
    }

    /**
     * get data by keyword
     *
     * @param dataSet
     * @param fieldName
     * @param keyWord
     * @return
     */
    public synchronized List<DataSet> queryByKeyword(DataSet dataSet, String fieldName, String
            keyWord, boolean isFilter) {
        List<DataSet> dataSetList = new ArrayList<>();
        if (mSQLiteDatabase == null || dataSet == null || dataSet.Name.length() <= 0
                || fieldName.length() <= 0) {
            return dataSetList;
        }
        String selection = fieldName + " like '%" + keyWord + "%'";
        String[] args = {keyWord};
        Cursor cursor = null;
        if (keyWord.isEmpty()) {
            cursor = mSQLiteDatabase.query("[" + dataSet.GroupName + "_" + dataSet.Name + "]", null, null, null, null, null, null);
        } else {
            cursor = mSQLiteDatabase.query("[" + dataSet.GroupName + "_" + dataSet.Name + "]", null, selection, null, null, null, null);
        }
        if (cursor == null) {
            return dataSetList;
        }
        if (cursor.moveToFirst()) {
            do {
                DataSet dataSetTemp = getDataSetByCursor(dataSet, cursor, isFilter);
                if (dataSetTemp != null) {
                    dataSetList.add(dataSetTemp);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dataSetList;
    }

    /**
     * get data by primary key
     *
     * @param dataSet
     * @return
     */
    public synchronized DataSet queryByPrimaryKey(DataSet dataSet, boolean isFilter) {
        if (mSQLiteDatabase == null || dataSet == null || dataSet.Name.length() <= 0
                || dataSet.PrimaryKey < 0) {
            return null;
        }
        String selection = "_id=?";
        String[] args = {String.valueOf(dataSet.PrimaryKey)};
        Cursor cursor = mSQLiteDatabase.query("[" + dataSet.GroupName + "_" + dataSet.Name + "]",
                null, selection, args, null, null, null);
        if (cursor == null) {
            return null;
        }
        if (cursor.moveToFirst()) {
            dataSet = getDataSetByCursor(dataSet, cursor, isFilter);
            cursor.close();
        } else {
            return null;
        }
        return dataSet;
    }

    /**
     * get data by condition
     *
     * @param dataSet
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public synchronized List<DataSet> queryByCondition(DataSet dataSet, String fieldName, String
            fieldValue, boolean isFilter) {
        List<DataSet> dataSetList = new ArrayList<>();
        if (mSQLiteDatabase == null || dataSet == null || dataSet.Name.length() <= 0
                || fieldName.length() <= 0) {
            return dataSetList;
        }

        String selection = "";

        if (fieldValue.isEmpty()) {
            selection = fieldName;
        } else {
            selection = fieldName + "= '" + fieldValue + "'";
        }

        Cursor cursor = mSQLiteDatabase.query("[" + dataSet.GroupName + "_" + dataSet.Name + "]", null, selection, null, null, null, null);
        if (cursor == null) {
            return dataSetList;
        }
        if (cursor.moveToFirst()) {
            do {
                DataSet dataSetTemp = getDataSetByCursor(dataSet, cursor, isFilter);
                if (dataSetTemp != null) {
                    dataSetList.add(dataSetTemp);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dataSetList;
    }

    private synchronized DataSet getDataSetByCursor(DataSet dataSet, Cursor cursor, boolean isFilter) {
        DataSet dataSetClone = new DataSet();
        try {
            dataSetClone = (DataSet) dataSet.clone();
        } catch (CloneNotSupportedException e) {
            L.printException(e);
        }
        List<FieldInfo> fieldInfoList = dataSetClone.FieldInfos;
        dataSetClone.PrimaryKey = cursor.getInt(0);
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfoTemp = fieldInfoList.get(i);
            int index = cursor.getColumnIndex(fieldInfoTemp.Name);
            fieldInfoTemp.Value = cursor.getString(index);
        }
        if (isFilter && dataSetClone.GetFieldValueByAlias("状态").equals("删除")) {
            return null;
        }
        return dataSetClone;
    }

    private ContentValues getContentValues(DataSet dataSet) {
        ContentValues values = new ContentValues();
        try {
            for (int i = 0; i < dataSet.FieldInfos.size(); i++) {
                FieldInfo fieldInfoTemp = dataSet.FieldInfos.get(i);
                values.put("[" + fieldInfoTemp.Name + "]", fieldInfoTemp.Value);
            }
            return values;
        } catch (Exception e) {
            L.printException(e);
            return values;
        }
    }

    private String getCreateTableString(DataSet dataSet) {
        StringBuilder buffer = new StringBuilder("CREATE TABLE ");
        buffer.append("[" + dataSet.GroupName + "_" + dataSet.Name + "]");
        buffer.append("(");
        buffer.append("_id integer PRIMARY KEY AUTOINCREMENT, ");

        for (int i = 0; i < dataSet.FieldInfos.size(); i++) {
            FieldInfo fieldInfoTemp = dataSet.FieldInfos.get(i);
            buffer.append("[" + fieldInfoTemp.Name + "]");

            if (i == dataSet.FieldInfos.size() - 1) {
                buffer.append(" text )");
            } else {
                buffer.append(" text, ");
            }
        }
        return buffer.toString();
    }

    public synchronized boolean tableIsExist(String tableName) {
        if (mSQLiteDatabase == null) {
            return false;
        }

        String selection = "name = '[" + tableName + "]' and type = 'table' ";
        String[] args = {"name"};
        Cursor cursor = mSQLiteDatabase.query("sqlite_master", args, selection, null, null, null, null);
        if (cursor == null) {
            return false;
        }
        if (cursor.moveToFirst()) {
            return true;
        }

        return true;
    }
}
