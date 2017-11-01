package com.geocraft.electrics.sr.spacer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.factory.DeleteDataSetFactory;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.sr.LineFactory;
import com.geocraft.electrics.sr.activity.TowerShowListActivity;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 间隔适配器
 */
@EBean
public class SpacerController extends BaseController {
    @Bean
    TaskManager mTaskManager;
    @Bean
    DbManager mDbManager;
    private List<DataSet> mDataSets = new ArrayList<>();
    private List<Integer> mIds = new ArrayList<Integer>();

    public List<DataSet> getDataSets() {
        return mDataSets;
    }

    //获取到所有
    public void showRemoveTargetRecordDialog(final Context context, final int position) {
        new AlertDialog.Builder(context)
                .setIcon(R.mipmap.ic_tip)
                .setTitle(context.getString(R.string.remove_record))
                .setMessage(context.getString(R.string.confirm_delete_record))
                .setNegativeButton(R.string.dlg_no, null)
                .setPositiveButton(R.string.dlg_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            DataSet dataSet = mDataSets.get(position);
                            DeleteDataSetFactory deleteDataSetFactory = new DeleteDataSetFactory();
                            if (deleteDataSetFactory.deleteAllDataSet(dataSet)) {
                                mDataSets.remove(position);
                                ((TowerShowListActivity) context).refreshListView(position);
                                T.showShort(context, R.string.remove_record_succeed);
                            } else {
                                T.showShort(context, R.string.remove_record_failure);
                            }
                        } catch (Exception e) {
                            L.printException(e);
                            T.showLong(context, R.string.remove_failed);
                        }
                    }
                }).show();
    }


    public void initDataFromDB() {
//        if (null == dataSetGroup) {
//            return;
//        }
//        mDataSets.clear();
//        for (DataSet dataset : dataSetGroup.DataSets) {
//            if (!LineFactory.oneOfLineDataset(dataset)) {
//                continue;
//            }
//            List<DataSet> dataSets = mDbManager.queryByCondition(dataset,
//                    Enum.GY_JKXLTZXX_FIELD_LINEID, mLineId, true);
//            mDataSets.addAll(dataSets);
//        }
    }
}
