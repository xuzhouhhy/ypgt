package com.geocraft.electrics.ui.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.ui.activity.SystemSettingActivity;
import com.geocraft.electrics.utils.SPUtils;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

/**
 * Created by Administrator on 2016/6/6.
 */
@EBean
public class SystemSettingController extends BaseController {

    @AfterInject
    void init() {

    }

    public boolean getSPDefaultTaskStatus(Context context) {
        return (Boolean) SPUtils.get(context, Constants.SP_KEY_IS_DEFAULT_TASK_MODE, true);
    }

    public void commit(Context context) {
        SPUtils.put(context, Constants.SP_KEY_IS_DEFAULT_TASK_MODE, ((SystemSettingActivity) context).getUIDefaultStatus());

    }

    public void showDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_tip);
        builder.setIcon(R.mipmap.ic_tip);
        builder.setMessage(R.string.restart_to_apply);
        builder.setCancelable(false);
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton(R.string.app_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((SystemSettingActivity) context).finish();
            }
        }).show();
    }

    /**
     * BusinessConcatSpinner的添加按钮是否显示
     *
     * @param checked 是否显示
     * @param context context
     */
    public void setAddSpinnerSwitch(boolean checked, Context context) {
        SPUtils.put(context, Constants.SPKEY_SPINNER_SWITCH, Boolean.valueOf(checked));
    }

    /**
     * 获取BusinessConcatSpinner的添加按钮是否显示
     *
     * @param context context
     * @return 开关
     */
    public boolean getSpSpinnerAdd(Context context) {
        return (Boolean) SPUtils.get(context, Constants.SPKEY_SPINNER_SWITCH, true);
    }
}
