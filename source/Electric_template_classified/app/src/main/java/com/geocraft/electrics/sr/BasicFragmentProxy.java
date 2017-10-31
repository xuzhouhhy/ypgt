package com.geocraft.electrics.sr;

import com.geocraft.electrics.entity.DataSet;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * 可选项frament管理
 */
@EBean
public class BasicFragmentProxy {
    public final static String KEY_GY_DLXL_Base = "GY_DLXL_Base";
    @Bean
    JKFragments mJKFragments;
    @Bean
    DLFragments mDLFragments;


    public void initFragments(WellType wellType, DataSet dataset) {
        if (wellType == WellType.JK) {
            mJKFragments.initJKFramentDtatas(dataset);
        } else if (wellType == WellType.DL) {
            mDLFragments.initDLFramentDtatas(dataset);
        }
    }

    /**
     * 获取当前类型可选采集项
     */
    public List<FragmentOption> getFragmentDatasetOptions(WellType wellType) {
        if (wellType == WellType.JK) {
            return mJKFragments.getJKFramentItems();
        } else if (wellType == WellType.DL) {
            return mDLFragments.getDLFramentItems();
        }
        return null;
    }

    public List<FragmentOption> refreshDatasetOptions(WellType wellType, DataSet dataset) {
        initFragments(wellType, dataset);
        return getFragmentDatasetOptions(wellType);
    }

}
