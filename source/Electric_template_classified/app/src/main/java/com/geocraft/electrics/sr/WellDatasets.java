package com.geocraft.electrics.sr;

import com.geocraft.electrics.constants.Enum;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 井号相关表
 */
@EBean
public class WellDatasets {

    public List<String> getDatasetNames() {
        List<String> datsetNames = new ArrayList<String>();
        datsetNames.add(Enum.GY_JKXLTZXX);
        datsetNames.add(Enum.GY_DLXLTZXX);
        datsetNames.add(Enum.GY_HYGTZXX);
        // TODO: 2017/10/27 待完善
        return datsetNames;
    }
}
