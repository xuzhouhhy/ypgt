package com.geocraft.electrics.sr.spacer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.factory.DeleteDataSetFactory;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.sr.WellType;
import com.geocraft.electrics.sr.activity.TowerShowListActivity;
import com.huace.log.logger.L;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.T;

import static com.geocraft.electrics.sr.WellType.DL;

/**
 * 间隔适配器
 */
@EBean
public class SpacerController extends BaseController {
    @Bean
    TaskManager mTaskManager;
    @Bean
    DbManager mDbManager;

    private int mLineId;

    private int mWellId;

    private WellType mWellType = DL;

    private List<DataSet> mDataSets = new ArrayList<>();
    private List<Integer> mIds = new ArrayList<Integer>();

    public List<DataSet> getDataSets() {
        return mDataSets;
    }

    //获取到所有
    public void showRemoveTargetRecordDialog(final Fragment fragment, final int position) {
        final Context context = fragment.getActivity();
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
                                ((GY_HWG_spacerFragment_) fragment).refreshListView(0);
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

    /**
     * 从数据库查询间隔dataset
     */
    public void initDataFromDB() {
        if (mLineId < 0 || mWellId < 0) {
            return;
        }
        mDataSets.clear();
        switch (mWellType) {
            case JK:
                queryJkWells();
                break;
            case DL:
                queryDlWells();
                break;
            case KBS:
            default:
                break;
        }
    }

    private void queryDlWells() {
        //jk dataset
        DataSet dlDs = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
                Enum.GY_DLXLTZXX);
        dlDs.PrimaryKey = 1;
        //当前查看和编辑的基桩
        DataSet dataSet = mDbManager.queryByPrimaryKey(dlDs, true);
        //获取查询到基桩的间隔点
        String intervalId = dataSet.GetFieldValueByName(Enum.DLJ_JGD);
        querySpacerDatasets(intervalId);
    }

    private void queryJkWells() {
        //jk dataset
        DataSet jkDs = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
                Enum.GY_JKXLTZXX);
        jkDs.PrimaryKey = mWellId;
        //当前查看和编辑的基桩
        DataSet dataSet = mDbManager.queryByPrimaryKey(jkDs, true);
        //获取查询到基桩的间隔点
        String intervalId = dataSet.GetFieldValueByName(Enum.DLJ_JGD);
        querySpacerDatasets(intervalId);
    }

    private void querySpacerDatasets(String intervalId) {
        String[] ids = intervalId.split("&");
        if (0 == ids.length) {
            return;
        }
        //间隔表
        DataSet jgDs = mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ,
                Enum.spacer);
        for (String id : ids) {
            try {
                jgDs.PrimaryKey = Integer.valueOf(id);
            } catch (NumberFormatException e) {
                L.e(e, "F_SpacerIds field of GY_DLXLTZXX table has wrong format");
                continue;
            }
            DataSet intervalDs = mDbManager.queryByPrimaryKey(jgDs, true);
            mDataSets.add(intervalDs);
        }
    }

    public int getmLineId() {
        return mLineId;
    }

    public void setLineId(int mLineId) {
        this.mLineId = mLineId;
    }

    public int getmWellId() {
        return mWellId;
    }

    public void setWellId(int mWellId) {
        this.mWellId = mWellId;
    }

    public WellType getmWellType() {
        return mWellType;
    }

    public void setWellType(WellType mWellType) {
        this.mWellType = mWellType;
    }

    /**
     * 生成间隔dataset
     *
     * @return dataset
     */
    public DataSet getSpacerDataset() {
        return mTaskManager.getDataSource().getDataSetByName(Enum.GYCJ, Enum.spacer);
    }
}
