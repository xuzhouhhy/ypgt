package com.geocraft.electrics.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.CountDownTimer;

import com.geocraft.electrics.BuildConfig;
import com.geocraft.electrics.R;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.event.NotRegisteredTempUseEvent;
import com.geocraft.electrics.event.SCanBarEvent;
import com.geocraft.electrics.utils.AssetsManager;
import com.geocraft.electrics.utils.BASE64;
import com.geocraft.electrics.utils.DeviceIDManager;
import com.geocraft.electrics.utils.GpsHelper;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;
import com.huace.log.logger.LogLevel;
import com.huace.log.logger.Settings;
import com.scaninput.ScanInputService;

import org.androidannotations.annotations.EApplication;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import common.geocraft.untiltools.FileUtils;
import mexxen.mx5010.barcode.BarcodeConfig;
import mexxen.mx5010.barcode.BarcodeEvent;
import mexxen.mx5010.barcode.BarcodeListener;
import mexxen.mx5010.barcode.BarcodeManager;

/**
 * 作者  zhouqin
 * 时间 2016/6/3.
 */
@EApplication
public class ElectricApplication extends Application {

    public final static EventBus BUS = new EventBus();
    public static BarcodeManager barcodeManager = null;
    public boolean mIsRegistered;

    public boolean mUsePermitted;
    //是否开启注册功能
    public boolean mIsOpenRegister = true;
    private List mList = new LinkedList();
    //条码扫描
    private BarcodeConfig barcodeConfig = new BarcodeConfig(this);
    private ScanInputService mScanInputService = new ScanInputService();
    private MyBarcodeListener barcodeListener = new MyBarcodeListener();

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            L.printException(e);
        }
        return null;
    }

    /**
     * get version code of app
     */
    public static int getVersionCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            L.printException(e);
        }
        return verCode;
    }

    public void scanCode(Context context) {
        L.i("scanCode");
        mScanInputService.getInstance().Scan(context, ScanInputService.BROADCAST_GET_TEXT, Constants.SCAN_ACTION, Constants.SCAN_DATA);
    }

    public void autoScan(Context context) {
        mScanInputService.getInstance().Scan(context, ScanInputService.AUTO_GET_TEXT);
    }

    @Override
    public void onCreate() {

        super.onCreate();
        BUS.register(this);
        //0.解压缩Assets
        try {
            int versionCode = getVersionCode(getApplicationContext());
            AssetsManager.initConfigRes(getApplicationContext(), versionCode);
        } catch (IOException e) {
            L.printException(e);
        } catch (Exception e) {
            L.printException(e);
        }
        //1。注册日志管理器
        //CrashManager.getManager(getApplicationContext()).registerCrashHandler(ConstPath.getLogPath());
        Settings settings = L.init(getString(R.string.app_name)).logFile(ConstPath.getLogFilePathName());
        if (BuildConfig.DEBUG) {
            //For debug, showDefaultValue all.
            settings.logLevel(LogLevel.FULL);
            L.wtf("DEBUG");
        } else {
            //For release, only showDefaultValue error and assert.
            settings.logLevel(LogLevel.ERROR);
            L.wtf("RELEASE");
        }

        //条码扫描初始化
        //设置条码扫描
        barcodeManager = new BarcodeManager(this);
        barcodeManager.addListener(barcodeListener);

        //设置条码状态
        barcodeConfig.setDecodeingIlluminiation(false);  //补光打开
        barcodeConfig.setDecodeAimIlluminiation(true);  //十字
        barcodeConfig.setOutputMode(2);

        //初始化GPS
        GpsHelper.getInstance().init();
        mIsRegistered = checkRegisterStatus();
        String textVerify;
        if (barcodeConfig.getHardwareTpye() == null) {
            textVerify = Build.MODEL;
            L.i("getHardwareType:" + textVerify);
            if (Constants.PERMITION_DEVICE_TYPE2.equals(textVerify)) {
                mUsePermitted = true;
            } else {
                mUsePermitted = true;
            }
        } else {
            textVerify = barcodeConfig.getHardwareTpye();
            L.i("getHardwareType:" + textVerify);
            if (textVerify.equals(Constants.PERMITION_DEVICE_TYPE)) {
                mUsePermitted = true;
            } else {
                mUsePermitted = true;
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        //销毁条码扫描对象
        barcodeManager.removeListener(barcodeListener);
        barcodeManager.dismiss();
    }

    /**
     * 初始化时判断注册状态
     *
     * @return
     */
    public boolean checkRegisterStatus() {

        boolean bReg = false;
        String strPassword = "";
        try {
            strPassword = FileUtils.readFile(ConstPath.getRegisterPath());
            if (!strPassword.isEmpty() && strPassword.length() > 5) {
                String strDeviceID = DeviceIDManager.GetDeviceID();

                String strBase64 = BASE64.Encode2(strDeviceID);
                String strDeviceCode = BASE64.BaseToLongFormat(strBase64, 9);
                String strLast5 = "";
                if (strDeviceCode.length() > 5) {
                    strLast5 = strDeviceCode.substring(strDeviceCode.length() - 5, strDeviceCode.length());
                }

                long DeviceCode = Utils.ConverLong(strDeviceCode);
                long nResult = (long) (DeviceCode * 0.8 + Utils.ConverLong(strLast5) * 0.7 + 3);
                long nPassword = Utils.ConverLong(strPassword);
                if (nResult == nPassword) {
                    bReg = true;
                }
            }
        } catch (Exception ex) {
            L.printException(ex);
            bReg = false;
        }
        return bReg;
    }

    @Subscribe
    public void onEvent(NotRegisteredTempUseEvent event) {
        //1800000
        new CountDownTimer(1800000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                try {
                    if (!mIsRegistered) {
                        showEndDialog();
                    }
                } catch (Exception e) {
                    L.printException(e);
                }
            }
        }.start();
    }

    public List getList() {
        return mList;
    }

    protected void showEndDialog() {

        if (mList.size() > 0) {
            Context applicationContext = (Activity) mList.get(mList.size() - 1);
            DialogInterface.OnClickListener onPositiveListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ElectricApplication_.getInstance().exit();
                }
            };

            new AlertDialog.Builder(applicationContext)
                    .setIcon(R.mipmap.ic_warning)
                    .setTitle(R.string.dlg_warning)
                    .setCancelable(false)
                    .setMessage(applicationContext.getString(R.string.msg_end_use_please_register))
                    .setPositiveButton(R.string.dlg_yes, onPositiveListener)
                    .show();
        }
    }

    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void removeActivity() {
        if (mList.size() > 0) {
            mList.remove(mList.size() - 1);
        }
    }

    public void exit() {
        try {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i) != null) {
                    ((Activity) mList.get(i)).finish();
                }
            }
        } catch (Exception e) {
            L.printException(e);
        } finally {
            System.exit(0);
        }
    }

    private class MyBarcodeListener implements BarcodeListener {
        @Override
        public void barcodeEvent(BarcodeEvent event) {
            if (event.getOrder().equals("SCANNER_READ")) {
                String barcode = barcodeManager.getBarcode();
                BUS.post(new SCanBarEvent("ScanEvent", barcode));
            }
        }
    }
}
