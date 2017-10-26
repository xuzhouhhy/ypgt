package com.geocraft.electrics.utils;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;


import com.geocraft.electrics.app.ElectricApplication_;
import com.huace.log.logger.L;

/**
 * Created by liu on 2015/11/12.
 */
public class DeviceIDManager {
    public static String GetCPUSerial()
    {
        String str = "";
        String strCPU = "";
        String cpuAddress = "0000000000000000";
        try
        {
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    if (str.contains("Serial"))
                    {
                        strCPU = str.substring(str.indexOf(":") + 1,
                                str.length());
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (Exception ex) {
            L.printException(ex);
            cpuAddress = "";
        }
        if (cpuAddress.equals("0000000000000000"))
        {
            cpuAddress = "";
        }
        return cpuAddress;
    }

    public static String GetMac()
    {
        String macSerial = "";
        String str = "";

        try
        {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str;)
            {
                str = input.readLine();
                if (str != null)
                {
                    macSerial = str.trim();
                    break;
                }
            }
        }
        catch (IOException ex)
        {
            macSerial = "";
            L.printException(ex);
        }
        return macSerial;
    }

    public static String GetIMEI()
    {
        String strIMEI = "";
        try
        {
            TelephonyManager TelephonyMgr = (TelephonyManager) ElectricApplication_.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
            strIMEI = TelephonyMgr.getDeviceId();
        }
        catch (Exception ex)
        {
            L.printException(ex);
            strIMEI  = "";
        }
        return strIMEI;
    }

    public static String GetFalseIMEI()
    {
        String m_szDevIDShort = "";
        try
        {
             m_szDevIDShort = "35" + //we make this look like a valid IMEI
             Build.BOARD.length()%10 +
             Build.BRAND.length()%10 +
             Build.CPU_ABI.length()%10 +
             Build.DEVICE.length()%10 +
             Build.DISPLAY.length()%10 +
             Build.HOST.length()%10 +
             Build.ID.length()%10 +
             Build.MANUFACTURER.length()%10 +
             Build.MODEL.length()%10 +
             Build.PRODUCT.length()%10 +
             Build.TAGS.length()%10 +
             Build.TYPE.length()%10 +
             Build.USER.length()%10 ; //13 digits
        }
        catch (Exception ex)
        {
            L.printException(ex);
            m_szDevIDShort = "";
        }
        return m_szDevIDShort;
    }

   // Returns: 00:11:22:33:44:55
    public static String GetFalseWifi()
    {
        String strResult = "";
        try
        {
            WifiManager wm = (WifiManager)ElectricApplication_.getInstance().getSystemService(Context.WIFI_SERVICE);
            strResult = wm.getConnectionInfo().getMacAddress();
        }
        catch (Exception ex)
        {
            L.printException(ex);
            strResult = "";
        }
       return strResult;
    }

    //The BT MAC Address string
    public static String GetBLUETOOTH()
    {
        String strReuslt = "";
        try
        {
            BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
            m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            strReuslt = m_BluetoothAdapter.getAddress();
        }
        catch (Exception ex)
        {
            L.printException(ex);
            strReuslt = "";
        }
        return strReuslt;
    }

    public static String GetDeviceID()
    {
        String strResult = "";
        
        strResult = GetIMEI();
        if (!strResult.isEmpty())
        {
            return strResult;
        }

        strResult = GetBLUETOOTH();
        if (!strResult.isEmpty())
        {
            return strResult;
        }

        strResult = GetFalseIMEI();
        if (!strResult.isEmpty())
        {
            return strResult;
        }
        
        strResult = GetCPUSerial();
        if (!strResult.isEmpty())
        {
            return strResult;
        }

        strResult = GetMac();
        if (!strResult.isEmpty())
        {
            return strResult;
        }

        strResult = GetFalseWifi();
        if (!strResult.isEmpty())
        {
            return strResult;
        }
        return strResult;
    }

}
