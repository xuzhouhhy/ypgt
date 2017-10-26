package com.geocraft.electrics.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import com.geocraft.electrics.app.ElectricApplication_;
import com.huace.log.logger.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanpengfei on 2016/4/22.
 */
public class GpsHelper implements LocationListener,GpsStatus.Listener
{
    private static GpsHelper instance = null;
    public LocationManager lm = null;
    private String bestProvider;
    private List<LocationListener> list = new ArrayList<LocationListener>();
    private List<GpsStatus.Listener> statusList = new ArrayList<GpsStatus.Listener>();

    private GpsHelper()
    {

    }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();

        if(lm != null)
        {
            try
            {
                lm.removeUpdates(this);
                lm.removeGpsStatusListener(this);
            }
            catch(SecurityException e)
            {
                L.printException(e);
            }
        }
    }


    public static GpsHelper getInstance()
    {
        if(instance == null)
        {
            instance = new GpsHelper();
        }

        return instance;
    }

    public void init()
    {
        lm = lm=(LocationManager) ElectricApplication_.getInstance().getSystemService(Context.LOCATION_SERVICE);

        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            //返回开启GPS导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        }

        try {
            //为获取地理位置信息时设置查询条件
            bestProvider = lm.getBestProvider(getCriteria(), true);

            //LT40默认返回网络，这里硬编码设置为GPS
            bestProvider = LocationManager.GPS_PROVIDER;

            //获取位置信息
            //如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER
            Location location = lm.getLastKnownLocation(bestProvider);

            //绑定监听，有4个参数
            //参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种
            //参数2，位置信息更新周期，单位毫秒
            //参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
            //参数4，监听
            //备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新

            // 1秒更新一次，或最小位移变化超过1米更新一次；
            //注意：此处更新准确度非常低，推荐在service里面启动一个Thread，在run中sleep(10000);然后执行handler.sendMessage(),更新位置
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
            lm.addGpsStatusListener(this);
        }
        catch(SecurityException e)
        {
            L.printException(e);
        }
    }

    private Criteria getCriteria()
    {
        Criteria criteria=new Criteria();
        //设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //设置是否要求速度
        criteria.setSpeedRequired(false);
        // 设置是否允许运营商收费
        criteria.setCostAllowed(false);
        //设置是否需要方位信息
        criteria.setBearingRequired(false);
        //设置是否需要海拔信息
        criteria.setAltitudeRequired(false);
        // 设置对电源的需求
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }

    @Override
    public void onLocationChanged(Location location)
    {
        for(int i = 0; i <  list.size();i++)
        {
            list.get(i).onLocationChanged(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int j, Bundle bundle)
    {
        for(int i = 0; i <  list.size();i++)
        {
            list.get(i).onStatusChanged(s,j,bundle);
        }
    }

    @Override
    public void onProviderEnabled(String s)
    {
        for(int i = 0; i <  list.size();i++)
        {
            list.get(i).onProviderEnabled(s);
        }
    }

    @Override
    public void onProviderDisabled(String s)
    {
        for(int i = 0; i <  list.size();i++)
        {
            list.get(i).onProviderDisabled(s);
        }
    }

    public void addLocationListener(LocationListener listener)
    {
        list.add(listener);
    }

    public void removeLocationListener(LocationListener listener)
    {
        if(listener != null)
        {
           list.remove(listener);
        }
    }

    public void addGpsStatusListener(GpsStatus.Listener listener)
    {
        statusList.add(listener);
    }

    public void removeGpsStatusListener(GpsStatus.Listener listener)
    {
        if(listener != null)
        {
            statusList.remove(listener);
        }
    }

    public Location getLastLocation()
    {
        Location location = null;
        if(lm != null)
        {
            try
            {
                location = lm.getLastKnownLocation(bestProvider);
            }
            catch (SecurityException e)
            {
                L.printException(e);
            }
        }

        return location;
    }

    @Override
    public void onGpsStatusChanged(int event) {
        for(int i = 0; i <  statusList.size();i++)
        {
            statusList.get(i).onGpsStatusChanged(event);
        }
    }
}
