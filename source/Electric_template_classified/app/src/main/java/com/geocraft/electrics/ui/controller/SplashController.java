package com.geocraft.electrics.ui.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.ui.activity.CollectTypeActivity_;
import com.geocraft.electrics.ui.activity.SplashActivity;
import com.geocraft.electrics.ui.activity.TaskManageActivity_;

import org.androidannotations.annotations.EBean;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@EBean
public class SplashController extends BaseController {
    public void splash(Context context){
        Intent intent = new Intent(context,
                CollectTypeActivity_.class);
        context.startActivity(intent);
        ((Activity)context).finish();
    }

}
