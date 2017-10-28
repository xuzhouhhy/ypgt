package com.geocraft.electrics.sr;

import com.geocraft.electrics.entity.DataSet;

/**
 * Created by zhongshibu02 on 2017/10/27.
 */

public class LineFactory {


    /**
     * dataset是不是"架空线路台帐信息"、"电缆线路台帐信息"、"环网柜台帐信息"信息之一
     *
     * @param dataset 数据集
     * @return true 是；false 不是
     */
    public static boolean oneOfLineDataset(DataSet dataset) {
        String[] names = new String[]{"GY_JKXLTZXX", "GY_DLXLTZXX"};
        for (String name : names) {
            if (dataset.Name.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
