package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by liu on 2016/5/16.
 */
public abstract class BaseControl extends LinearLayout {

    public BaseControl(Context context) {
        super(context);
    }

    public BaseControl(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public BaseControl(Context context, AttributeSet attrs, int defStle) //android 3.0 and above
    {
        super(context, attrs, defStle);
    }

    /**
     * 名称设置/获取
     *
     * @param strname
     */
    public abstract void SetName(String strname);

    public abstract String GetName();

    /**
     * 代码设置/获取
     *
     * @param strcode
     */
    public abstract void SetCode(String strcode);

    public abstract String GetCode();

    /**
     * 设置/获取控件值
     *
     * @param strvalue
     */
    public abstract void SetValue(String strvalue);

    public abstract String GetValue();

    /**
     * 设置/获取EDIT控件是否可用
     *
     * @param benable
     */
    public abstract void SetEditEnable(boolean benable);

    public abstract boolean GetEditEnable();

    /**
     * 设置/获取控件是否可用
     *
     * @param benable
     */
    public abstract void SetEnable(boolean benable);

    public abstract boolean GetEnable();

    /**
     * 获取控件ID值
     *
     * @return
     */
    public abstract int GetControlID();
}
