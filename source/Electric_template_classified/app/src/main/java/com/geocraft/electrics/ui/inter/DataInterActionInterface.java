package com.geocraft.electrics.ui.inter;

import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;

/**
 * Created by Administrator on 2016/6/8.
 */
public interface DataInterActionInterface {

	public void setControlValue(String text);

	public void setControlValue(FieldInfo fieldInfo,String text);

	public String getControlValue();

}
