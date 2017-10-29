package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.test.suitebuilder.annotation.Suppress;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.event.SCanBarEvent;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;
import com.scaninput.ScanInputService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import common.geocraft.untiltools.Tools;

/**
 * Created by hanpengfei on 2016/6/8.
 */
@EViewGroup(R.layout.view_scanedit)
public class BusinessScanEdit extends LinearLayout implements DataInterActionInterface
{
    @ViewById
    EditText etScan;
    @ViewById
    Button btnScan;

    LT40ScanReceiver mReceiver = new LT40ScanReceiver();

    public BusinessScanEdit(Context context) {
        super(context);
    }

    public BusinessScanEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @AfterViews
    void init()
    {
        IntentFilter scanIntentFilter = new IntentFilter();
        scanIntentFilter.addAction(Constants.SCAN_ACTION);
        scanIntentFilter.addAction(ScanInputService.HARDWARE_SCAN);
        this.getContext().registerReceiver(mReceiver,scanIntentFilter);
        btnScan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ElectricApplication_.getInstance().scanCode(getContext());

            }
        });
        btnScan.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    ElectricApplication.barcodeManager.startScanner();
                }

                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                  ElectricApplication.barcodeManager.stopScanner();
                }

                return false;
            }
        });

        if(!ElectricApplication.BUS.isRegistered(this))
        {
            ElectricApplication.BUS.register(this);
        }
    }

    @Override
    public int getControlerType() {
        return PropertyDictionay.OperateCode.Type_Scan;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        this.getContext().unregisterReceiver(mReceiver);
    }

    @Subscribe
    public void onEventMainThread(SCanBarEvent event)
    {
        String activityName = ((Activity)getContext()).getClass().getName();
        if(Utils.isTopActivy(activityName, getContext()))
        {
            if(etScan.isFocused()) {
            etScan.setText(Utils.replaceBlank(event.Msg).trim());
        }
        }
    }


    @Override
    public void setControlValue(String text) {
        etScan.setText(text);
    }

    @Override
    public String getControlValue() {
        return etScan.getText().toString();
    }

    @Override
    public void setControlValue(FieldInfo fieldInfo, String text) {
        etScan.setText(text);
    }

    public void setFocus()
    {
        etScan.requestFocus();
    }

    public class LT40ScanReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            L.i("onReceive");
            if (Constants.SCAN_ACTION.equals(intent.getAction())){
                String text = intent.getStringExtra(Constants.SCAN_DATA);
                String activityName = ((Activity)getContext()).getClass().getName();
                if(Utils.isTopActivy(activityName, getContext()))
                {
                    if(etScan.isFocused()) {
                        etScan.setText(text);
                    }
                }
            }else if(ScanInputService.HARDWARE_SCAN.equals(intent.getAction())){
                String text = intent.getStringExtra(ScanInputService.TEXT);
                String activityName = ((Activity)getContext()).getClass().getName();
                if(Utils.isTopActivy(activityName, getContext()))
                {
                    if(etScan.isFocused()) {
                        etScan.setText(text);
                    }
                }
            }
        }
    }
}
