package com.geocraft.electrics.factory;

import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.huace.log.logger.L;

import java.util.List;

/**
 * Created by Administrator on 2016/6/15.
 */
public class DeleteDataSetFactory {

	TaskManager mTaskManager;

	DbManager mDbManager;

	public DeleteDataSetFactory() {
		mTaskManager = TaskManager_.getInstance_(ElectricApplication_.getInstance()
				.getApplicationContext());
		mDbManager = DbManager_.getInstance_(ElectricApplication_.getInstance()
				.getApplicationContext());
	}

	public boolean deleteAllDataSet(DataSet dataSet){
		if(dataSet==null){
			return true;
		}
		try {
			if (dataSet.DataSets.size()>0){
			for (int i=0;i<dataSet.DataSets.size();i++){
				DataSet dataSetTemplate = dataSet.DataSets.get(i);
				String foreignKeyValue = dataSet.GetFieldValueByName(dataSetTemplate.ValueField);
				List<DataSet> mChildDataSetsInfo = mDbManager.queryByCondition(dataSetTemplate,
						dataSetTemplate.SearchField, foreignKeyValue, true);
				for (int j=0;j<mChildDataSetsInfo.size();j++){
					if(!deleteAllDataSet(mChildDataSetsInfo.get(j))){
						return false;
					}
				}
			}
			}
			return mDbManager.delete(dataSet);
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}
	public boolean delete(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			String dataSetName = dataSet.Name;
			if (dataSetName.equals(Enum.DATA_SET_NAME_YHZX)) {
				return deleteYHZX(dataSet);
			} else if (dataSetName.equals(Enum.DATA_SET_NAME_JLX_J)) {
				return deleteJLX_J(dataSet);
			} else {
				return mDbManager.delete(dataSet);
			}
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	//删除用户专线（同时删除该线路下所有项）
	private boolean deleteYHZX(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			deleteWLGByYHZX(dataSet);       //删除物理杆
			deleteYXGByYHZX(dataSet);       //删除运行杆
			deleteYHDLDByYHZX(dataSet);     //删除用户电缆段
			deleteYHKGZByYHZX(dataSet);     //删除用户开关站
			deleteYHXSBByYHZX(dataSet);     //删除用户箱式变
			deleteYHPDSByYHZX(dataSet);     //删除用户配电室
			deleteYHBYQByYHZX(dataSet);     //删除用户变压器
			return mDbManager.delete(dataSet);
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	//通过用户专线删除物理杆
	private boolean deleteWLGByYHZX(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			String XLMC = dataSet.GetFieldValueByName(Enum.YHZX_FIELD_XLMC);
			DataSet WLGDataSetTemp = mTaskManager.getDataSource().getDataSetByName(
					dataSet.GroupName, Enum.DATA_SET_NAME_YHJKGT_WLG);
			if (WLGDataSetTemp != null && !XLMC.isEmpty()) {
				List<DataSet> WLGDataSetList = mDbManager.queryByCondition(WLGDataSetTemp, Enum
						.FIELD_SSXL, XLMC, true);
				for (int i = 0; i < WLGDataSetList.size(); i++) {
					DataSet temp = WLGDataSetList.get(i);
					if (temp == null) {
						continue;
					}
					mDbManager.delete(temp);
				}
			}
			return true;
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	//通过用户专线删除运行杆
	private boolean deleteYXGByYHZX(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			String XLMC = dataSet.GetFieldValueByName(Enum.YHZX_FIELD_XLMC);
			DataSet YXGDataSetTemp = mTaskManager.getDataSource().getDataSetByName(
					dataSet.GroupName, Enum.DATA_SET_NAME_YHJKGT_YXG);
			if (YXGDataSetTemp != null && !XLMC.isEmpty()) {
				List<DataSet> YXGDataSetList = mDbManager.queryByCondition(YXGDataSetTemp, Enum
						.FIELD_SSXL, XLMC, true);
				for (int i = 0; i < YXGDataSetList.size(); i++) {
					DataSet temp = YXGDataSetList.get(i);
					if (temp == null) {
						continue;
					}
					mDbManager.delete(temp);
				}
			}
			return true;
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	//通过用户专线删除用户电缆段
	private boolean deleteYHDLDByYHZX(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			String XLMC = dataSet.GetFieldValueByName(Enum.YHZX_FIELD_XLMC);
			DataSet YHDLDDataSetTemp = mTaskManager.getDataSource().getDataSetByName(
					dataSet.GroupName, Enum.DATA_SET_NAME_YHDLD);
			if (YHDLDDataSetTemp != null && !XLMC.isEmpty()) {
				List<DataSet> YHDLDDataSetList = mDbManager.queryByCondition(YHDLDDataSetTemp, Enum
						.FIELD_SSXL, XLMC, true);
				for (int i = 0; i < YHDLDDataSetList.size(); i++) {
					DataSet temp = YHDLDDataSetList.get(i);
					if (temp == null) {
						continue;
					}
					mDbManager.delete(temp);
				}
			}
			return true;
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	//通过用户专线删除用户开关站
	private boolean deleteYHKGZByYHZX(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			String SSKX = dataSet.GetFieldValueByName(Enum.FIELD_SSKX);
			DataSet YHKGZDataSetTemp = mTaskManager.getDataSource().getDataSetByName(
					dataSet.GroupName, Enum.DATA_SET_NAME_YHKGZ);
			if (YHKGZDataSetTemp != null && !SSKX.isEmpty()) {
				List<DataSet> YHKGZDataSetList = mDbManager.queryByCondition(YHKGZDataSetTemp, Enum
						.FIELD_SSKX, SSKX, true);
				for (int i = 0; i < YHKGZDataSetList.size(); i++) {
					DataSet temp = YHKGZDataSetList.get(i);
					if (temp == null) {
						continue;
					}
					mDbManager.delete(temp);
				}
			}
			return true;
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	//通过用户专线删除用户箱式变
	private boolean deleteYHXSBByYHZX(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			String SSKX = dataSet.GetFieldValueByName(Enum.FIELD_SSKX);
			DataSet YHXSBDataSetTemp = mTaskManager.getDataSource().getDataSetByName(
					dataSet.GroupName, Enum.DATA_SET_NAME_YHXSB);
			if (YHXSBDataSetTemp != null && !SSKX.isEmpty()) {
				List<DataSet> YHXSBDataSetList = mDbManager.queryByCondition(YHXSBDataSetTemp, Enum
						.FIELD_SSKX, SSKX, true);
				for (int i = 0; i < YHXSBDataSetList.size(); i++) {
					DataSet temp = YHXSBDataSetList.get(i);
					if (temp == null) {
						continue;
					}
					mDbManager.delete(temp);
				}
			}
			return true;
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	//通过用户专线删除用户配电室（房）
	private boolean deleteYHPDSByYHZX(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			String XLMC = dataSet.GetFieldValueByName(Enum.YHZX_FIELD_XLMC);
			DataSet YHPDSDataSetTemp = mTaskManager.getDataSource().getDataSetByName(
					dataSet.GroupName, Enum.DATA_SET_NAME_YHPDS_F);
			if (YHPDSDataSetTemp != null && !XLMC.isEmpty()) {
				List<DataSet> YHPDSDataSetList = mDbManager.queryByCondition(YHPDSDataSetTemp, Enum
						.FIELD_SSXL, XLMC, true);
				for (int i = 0; i < YHPDSDataSetList.size(); i++) {
					DataSet temp = YHPDSDataSetList.get(i);
					if (temp == null) {
						continue;
					}
					mDbManager.delete(temp);
				}
			}
			return true;
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	//通过用户专线删除用户变压器
	private boolean deleteYHBYQByYHZX(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			String XLMC = dataSet.GetFieldValueByName(Enum.YHZX_FIELD_XLMC);
			DataSet YHBYQDataSetTemp = mTaskManager.getDataSource().getDataSetByName(
					dataSet.GroupName, Enum.DATA_SET_NAME_YHBYQ);
			if (YHBYQDataSetTemp != null && !XLMC.isEmpty()) {
				List<DataSet> YHBYQDataSetList = mDbManager.queryByCondition(YHBYQDataSetTemp, Enum
						.FIELD_SSXL_ZF, XLMC, true);
				for (int i = 0; i < YHBYQDataSetList.size(); i++) {
					DataSet temp = YHBYQDataSetList.get(i);
					if (temp == null) {
						continue;
					}
					mDbManager.delete(temp);
				}
			}
			return true;
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}

	//删除计量箱（柜）
	//同时删除该计量箱下的电能表
	private boolean deleteJLX_J(DataSet dataSet) {
		try {
			if (dataSet == null) {
				return true;
			}
			String JLXTMBH = dataSet.GetFieldValueByName(Enum.CALCULATEBOX_FIELD_JLXTMBH);
			DataSet DNBDataSetTemp = mTaskManager.getDataSource().getDataSetByName(dataSet.GroupName,
					Enum.DATA_SET_NAME_JLXYDNBDGX);
			if (DNBDataSetTemp != null && !JLXTMBH.isEmpty()) {
				List<DataSet> DNBDataSetList = mDbManager.queryByCondition(DNBDataSetTemp, Enum
						.METER_FIELD_JLXTMBH, JLXTMBH, true);
				for (int i = 0; i < DNBDataSetList.size(); i++) {
					DataSet temp = DNBDataSetList.get(i);
					if (temp == null) {
						continue;
					}
					mDbManager.delete(temp);
				}
			}
			return mDbManager.delete(dataSet);
		} catch (Exception e) {
			L.printException(e);
			return false;
		}
	}
}
