package com.geocraft.electrics.ui.fragment;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.sr.fragment.WellBaseFragment;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.utils.GpsHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Iterator;
import java.util.List;

import common.geocraft.untiltools.ConvertHelper;
import common.geocraft.untiltools.T;

@EFragment(R.layout.fragment_coordfragment)
public class Coordfragment extends WellBaseFragment implements GpsStatus.Listener {

    @ViewById
    LinearLayout linearLayoutRoot;

    @ViewById
    TextView tvTag;
    @ViewById
    Button btnCoordUpdate;
    @ViewById
    Button btnAltitudeUpdate;
    @ViewById
    BusinessEditText etLongtitude;
    @ViewById
    BusinessEditText etLatitude;
    @ViewById
    BusinessEditText etAltitude;

    private String nstrLatTag;
    private String nstrLonTag;
    private String nstrAtitleTag;


    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }

    public void setTags(String strlattag, String strlontag, String strhigh) {
        nstrLatTag = strlattag;
        nstrLonTag = strlontag;
        nstrAtitleTag = strhigh;
    }

    @AfterViews
    void initViews() {
        GpsHelper.getInstance().addGpsStatusListener(this);
        initFieldValue(mDataSet);
        setButtonEnable(0);
    }

    private void initFieldValue(DataSet dataSet) {
        if (dataSet == null) {
            return;
        }
        List<FieldInfo> fieldInfoList = dataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }

            if (nstrLatTag != null && nstrLatTag.equalsIgnoreCase(fieldInfo.Alias)) {
                setlatvalue(fieldInfo.Value);
            } else if (nstrLonTag != null && nstrLonTag.equalsIgnoreCase(fieldInfo.Alias)) {
                setlonvalue(fieldInfo.Value);
            } else if (nstrAtitleTag != null && nstrAtitleTag.equalsIgnoreCase(fieldInfo.Alias)) {
                setAltitudevalue(fieldInfo.Value);
            }
        }
    }

    @Override
    public void getValue(DataSet dataSet) {
        List<FieldInfo> fieldInfoList = dataSet.FieldInfos;
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            if (fieldInfo == null) {
                continue;
            }
            if (nstrLatTag != null && nstrLatTag.equalsIgnoreCase(fieldInfo.Alias)) {
                fieldInfo.Value = etLatitude.getControlValue();
            } else if (nstrLonTag != null && nstrLonTag.equalsIgnoreCase(fieldInfo.Alias)) {
                fieldInfo.Value = etLongtitude.getControlValue();
            } else if (nstrAtitleTag != null && nstrAtitleTag.equalsIgnoreCase(fieldInfo.Alias)) {
                fieldInfo.Value = etAltitude.getControlValue();
            }
        }
    }

    @Override
    public void onGpsStatusChanged(int event) {
        GpsStatus gpsStatus = GpsHelper.getInstance().lm.getGpsStatus(null);
        //Utils.DisplayToastShort(GPSService.this, "GPS status listener  ");
        switch (event) {
            case GpsStatus.GPS_EVENT_FIRST_FIX: {
                //第一次定位时间UTC gps可用
                //Log.v(TAG,"GPS is usable");
                break;
            }

            case GpsStatus.GPS_EVENT_SATELLITE_STATUS: {//周期的报告卫星状态
                //得到所有收到的卫星的信息，包括 卫星的高度角、方位角、信噪比、和伪随机号（及卫星编号）
                Iterable<GpsSatellite> allSatellites;
                allSatellites = gpsStatus.getSatellites();
                int numTotal = 0;
                int numLock = 0;

                for (Iterator<GpsSatellite> iterator = allSatellites.iterator(); iterator.hasNext(); ) {
                    if (((GpsSatellite) iterator.next()).usedInFix()) {
                        numLock++;
                    }

                    numTotal++;
                }
                String mvalue = "卫星数: " + numLock + "|" + numTotal;
                updateLatinfo(mvalue);
                // tvTag.setText("卫星数: " + numLock + "|" + numTotal);
//                if( numLock < 4)
//                {
//                    btnCoordUpdate.setEnabled(false);
//                    btnAltitudeUpdate.setEnabled(false);
//                }
//                else
//                {
//                    btnCoordUpdate.setEnabled(true);
//                    btnAltitudeUpdate.setEnabled(true);
//                }
                setButtonEnable(numLock);
                break;
            }

            case GpsStatus.GPS_EVENT_STARTED: {
                //Utils.DisplayToastShort(GPSService.this, "GPS start Event");
                break;
            }

            case GpsStatus.GPS_EVENT_STOPPED: {
                //Utils.DisplayToastShort(GPSService.this, "GPS **stop*** Event");
                break;
            }

            default:
                break;
        }
    }

    @Click
    void btnCoordUpdate() {
        Location location = GpsHelper.getInstance().getLastLocation();
        if (location != null) {
            etLongtitude.setControlValue(ConvertHelper.Format(location.getLongitude(), 8));
            etLatitude.setControlValue(ConvertHelper.Format(location.getLatitude(), 8));
            etAltitude.setControlValue(ConvertHelper.Format(location.getAltitude(), 3));
        } else {
            T.show(getContext(), getString(R.string.app_getpositionfaild), Toast.LENGTH_SHORT);
        }
    }

    @Click
    void btnAltitudeUpdate() {
        Location location = GpsHelper.getInstance().getLastLocation();
        if (location != null) {
            etAltitude.setControlValue(ConvertHelper.Format(location.getAltitude(), 3));
        } else {
            T.show(getContext(), getString(R.string.app_getpositionfaild), Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        GpsHelper.getInstance().removeGpsStatusListener(this);
        ElectricApplication.BUS.unregister(this);
    }

    @UiThread
    void setlonvalue(String mvalue) {
        try {
            if (etLongtitude != null) {
                etLongtitude.setControlValue(mvalue);
            }
        } catch (Exception ex) {
        }
    }

    @UiThread
    void setlatvalue(String mvalue) {
        try {
            if (etLatitude != null) {
                etLatitude.setControlValue(mvalue);
            }
        } catch (Exception ex) {
        }
    }

    @UiThread
    void setAltitudevalue(String mvalue) {
        try {
            if (etAltitude != null) {
                etAltitude.setControlValue(mvalue);
            }
        } catch (Exception ex) {
        }
    }

    @UiThread
    void setButtonEnable(int num) {
        try {
            if (num < 4) {
                btnCoordUpdate.setEnabled(false);
                btnAltitudeUpdate.setEnabled(false);
            } else {
                btnCoordUpdate.setEnabled(true);
                btnAltitudeUpdate.setEnabled(true);
            }
        } catch (Exception ex) {
        }
    }

    @UiThread
    void updateLatinfo(String strvalue) {
        try {
            tvTag.setText(strvalue);
        } catch (Exception ex) {
        }
    }
}
