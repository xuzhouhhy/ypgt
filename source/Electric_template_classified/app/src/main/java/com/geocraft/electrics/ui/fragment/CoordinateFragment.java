package com.geocraft.electrics.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.event.SearchControlEvent;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.activity.SearchActivity_;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;
import com.geocraft.electrics.utils.GpsHelper;
import com.geocraft.electrics.utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.Iterator;

import common.geocraft.untiltools.ConvertHelper;
import common.geocraft.untiltools.T;

/**
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EFragment(R.layout.fragment_coord)
public class CoordinateFragment extends BusinessFragment implements GpsStatus.Listener {

    @ViewById
    LinearLayout linearLayoutRoot;

    @ViewById
    BusinessSearch   viewJRDBS;
    @ViewById
    TextView         tvJRDBS;
    @ViewById
    Button          btnPreferenceJRD;
    @ViewById
    TextView         tvTag;
    @ViewById
    Button           btnCoordUpdate;
    @ViewById
    Button          btnAltitudeUpdate;
    @ViewById
    BusinessEditText etLongtitude;
    @ViewById
    BusinessEditText etLatitude;
    @ViewById
    BusinessEditText etAltitude;


    @Override
    protected void init() {
        mLinearLayout = linearLayoutRoot;
        super.init();
    }

    @AfterViews
    void initViews()
    {
        String dataSetName = ((RecordActivity)getActivity()).getController().getCurrentDataSet().Name;
        if(dataSetName.equals(Enum.DATA_SET_NAME_JLX_J) || dataSetName.equals(Enum.DATA_SET_NAME_JLX_J_Z))
        {
            tvJRDBS.setVisibility(View.VISIBLE);
            viewJRDBS.setVisibility(View.VISIBLE);
            btnPreferenceJRD.setVisibility(View.VISIBLE);
        }

        GpsHelper.getInstance().addGpsStatusListener(this);

        if(!ElectricApplication.BUS.isRegistered(this))
        {
            ElectricApplication.BUS.register(this);
        }
    }

    @Override
    public void onGpsStatusChanged(int event) {
        GpsStatus gpsStatus= GpsHelper.getInstance().lm.getGpsStatus(null);
        //Utils.DisplayToastShort(GPSService.this, "GPS status listener  ");
        switch(event)
        {
            case GpsStatus.GPS_EVENT_FIRST_FIX:{
                //第一次定位时间UTC gps可用
                //Log.v(TAG,"GPS is usable");
                break;
            }

            case GpsStatus.GPS_EVENT_SATELLITE_STATUS:{//周期的报告卫星状态
                //得到所有收到的卫星的信息，包括 卫星的高度角、方位角、信噪比、和伪随机号（及卫星编号）
                Iterable<GpsSatellite> allSatellites;
                allSatellites = gpsStatus.getSatellites();
                int numTotal = 0;
                int numLock = 0;

                for(Iterator<GpsSatellite> iterator = allSatellites.iterator();iterator.hasNext();)
                {
                    if(((GpsSatellite)iterator.next()).usedInFix())
                    {
                        numLock++;
                    }

                    numTotal++;
                }

                tvTag.setText("卫星数: " + numLock + "|" + numTotal);
                if( numLock < 4)
                {
                    btnCoordUpdate.setEnabled(false);
                    btnAltitudeUpdate.setEnabled(false);
                }
                else
                {
                    btnCoordUpdate.setEnabled(true);
                    btnAltitudeUpdate.setEnabled(true);
                }
                break;
            }

            case GpsStatus.GPS_EVENT_STARTED:{
                //Utils.DisplayToastShort(GPSService.this, "GPS start Event");
                break;
            }

            case GpsStatus.GPS_EVENT_STOPPED:{
                //Utils.DisplayToastShort(GPSService.this, "GPS **stop*** Event");
                break;
            }

            default :
                break;
        }
    }

    //接受从接入点查询的值
    private void accessValues(SearchControlEvent event)
    {
        //赋值
        String accessPointName = event.dataSet.GetFieldValueByName(Enum.ACCESSPOINT_FIELD_GJSBMC);  //挂接点名称
        String accessPointType = event.dataSet.GetFieldValueByName(Enum.ACCESSPOINT_FIELD_GJSBLX); //挂接点类型
        String accessPointID = event.dataSet.GetFieldValueByName(Enum.ACCESSPOINT_FIELD_YWXTID);   //挂接点ID

        //没有控件对应的字段赋值
        ((RecordActivity)getActivity()).getController().getCurrentDataSet().SetFiledValueByName(Enum.ACCESSPOINT_FIELD_GJSBMC,accessPointName);
        ((RecordActivity)getActivity()).getController().getCurrentDataSet().SetFiledValueByName(Enum.ACCESSPOINT_FIELD_GJSBLX,accessPointType);

        //有控件对应的字段赋值
        viewJRDBS.setControlValue(accessPointID);
    }

    //拒绝从接入点查询的值
    private void refuseValues()
    {
        viewJRDBS.setControlValue("");
    }

    @Subscribe
    public void onEventMainThread(final SearchControlEvent event) {

        DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
        if(dataSet.Name.equals(event.Name) && event.dataSet.Name.equals(Enum.DATA_SET_NAME_JRDBS)) {
            //获取坐标值

            //获取当前坐标
            Location location = GpsHelper.getInstance().getLastLocation();
            if (location == null) {
                T.show(getContext(), getString(R.string.app_getpositionfaild), Toast.LENGTH_SHORT);
                viewJRDBS.setControlValue("");
                return;
            }

            accessValues(event);
        }
    }

    @Click
    void btnPreferenceJRD(){
        DataSet dataSet = ((RecordActivity)getActivity()).getController().getCurrentDataSet();
        PropertyDictionay.SearchEntiry searchEntiry = new PropertyDictionay.SearchEntiry();
        searchEntiry.tableName = Enum.GROUP_NAME_QT+"_"+Enum.DATA_SET_NAME_JRDBS;
        searchEntiry.fieldName = Enum.ACCESSPOINT_FIELD_GJSBMC;
        searchEntiry.uniqueFlag = dataSet.Name;
        Location location = GpsHelper.getInstance().getLastLocation();
        if(location != null)
        {
            Intent intent = new Intent(getContext(), SearchActivity_.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.INTENT_DATA_PREFERENCE_JRDBS,true);
            bundle.putSerializable(Constants.INTENT_DATA_SEARCHENTITRY,searchEntiry);
            bundle.putDouble(Constants.INTENT_DATA_JRDBS_LONGITUDE, location.getLongitude());
            bundle.putDouble(Constants.INTENT_DATA_JRDBS_LATITUDE,location.getLatitude());
            intent.putExtras(bundle);
            (getContext()).startActivity(intent);
        }
        else {
            T.show(getContext(),getString(R.string.app_getpositionfaild),Toast.LENGTH_SHORT);
        }

    }
    @Click
    void btnCoordUpdate()
    {
        Location location = GpsHelper.getInstance().getLastLocation();
        if(location != null)
        {
            etLongtitude.setControlValue(ConvertHelper.Format(location.getLongitude(),8));
            etLatitude.setControlValue(ConvertHelper.Format(location.getLatitude(),8));
            etAltitude.setControlValue(ConvertHelper.Format(location.getAltitude(),3));
        }
        else
        {
            T.show(getContext(),getString(R.string.app_getpositionfaild),Toast.LENGTH_SHORT);
        }
    }

    @Click
    void btnAltitudeUpdate()
    {
        Location location = GpsHelper.getInstance().getLastLocation();
        if(location != null)
        {
            etAltitude.setControlValue(ConvertHelper.Format(location.getAltitude(),3));
        }
        else
        {
            T.show(getContext(),getString(R.string.app_getpositionfaild),Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        GpsHelper.getInstance().removeGpsStatusListener(this);
        ElectricApplication.BUS.unregister(this);
    }
}
