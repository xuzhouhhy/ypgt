package com.geocraft.electrics.sr;

import android.content.res.Resources;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment;
import com.geocraft.electrics.sr.spacer.GY_HWG_spacerFragment;
import com.geocraft.electrics.sr.spacer.GY_HWG_spacerFragment_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_Base;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_Base_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_DJX;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_DJX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_DLJ;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_DLJ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_DLZJT;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_DLZJT_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_FZX;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_FZX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_GJXX;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_GJXX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_GXJBXX;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_GXJBXX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_GZZSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_GZZSQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_HWG;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_HWG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_KYQK;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_KYQK_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_SKQK;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_SKQK_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_TSPDBYQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_TSPDBYQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_XSBDZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.DLXL_fragment.GY_DLXL_XSBDZ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_BLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_BLQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_DLHGQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_DLHGQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_FHKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_FHKG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_FZX;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_FZX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_GLKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_GLKG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_GZZSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_GZZSQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_MX;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_MX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.GY_DLFJX.GY_DLFJX_BLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.GY_DLFJX.GY_DLFJX_BLQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.GY_DLFJX.GY_DLFJX_DLFJX;
import com.geocraft.electrics.ui.fragment.GY_fragment.GY_DLFJX.GY_DLFJX_DLFJX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.GY_DLFJX.GY_DLFJX_DLHGQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.GY_DLFJX.GY_DLFJX_DLHGQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.GY_DLFJX.GY_DLFJX_GZZSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.GY_DLFJX.GY_DLFJX_GZZSQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_BLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_BLQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_DLHGQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_DLHGQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_DLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_DLQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_DYHGQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_DYHGQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_FHKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_FHKG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_GLKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_GLKG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_GZZSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_GZZSQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_HWG;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_HWG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_JG;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_JG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_MX;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_MX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_OTHER;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_OTHER_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DW;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DW_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYTP;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYTP_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYWGBCZZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYWGBCZZ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK1;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK1_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK2;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK2_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK3;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK3_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK4;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK4_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK5;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK5_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK6;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK6_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_FHKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_FHKG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_PDBYQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_PDBYQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_XSBDZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_XSBDZ_;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 可选项frament管理
 */
@EBean
public class DLFragments {
    public final static String KEY_GY_DLXL_Base = "GY_DLXL_Base";
    //Dl
    private final static String KEY_GY_DLXL_DJX = "GY_DLXL_DJX";
    private final static String KEY_GY_DLXL_DLJ = "GY_DLXL_DLJ";
    private final static String KEY_GY_DLXL_DLZJT = "GY_DLXL_DLZJT";
    private final static String KEY_GY_DLXL_FZX = "GY_DLXL_FZX";
    private final static String KEY_GY_DLXL_GXJBXX = "GY_DLXL_GXJBXX";
    private final static String KEY_GY_DLXL_GZZSQ = "GY_DLXL_GZZSQ";
    private final static String KEY_GY_DLXL_HWG = "GY_DLXL_HWG";
    private final static String KEY_GY_DLXL_KYQK = "GY_DLXL_KYQK";
    private final static String KEY_GY_DLXL_SKQK = "GY_DLXL_SKQK";
    private final static String KEY_GY_DLXL_TSPDBYQ = "GY_DLXL_TSPDBYQ";
    private final static String KEY_GY_DLXL_XSBDZ = "GY_DLXL_XSBDZ";
    //环网柜
    private final static String KEY_GY_HWG_HWG = "GY_HWG_HWG";
    private final static String KEY_GY_HWG_JG = "GY_HWG_JG";//环网柜（间隔）
    private final static String KEY_GY_HWG_DLQ = "GY_HWG_DLQ";//环网柜（断路器）</string>
    private final static String KEY_GY_HWG_FHKG = "GY_HWG_FHKG";
    private final static String KEY_GY_HWG_GLKG = "GY_HWG_GLKG";
    ;//">环网柜（负荷开关）</string>
    private final static String KEY_GY_HWG_DYHGQ = "GY_HWG_DYHGQ";
    ;//环网柜（隔离开关）</string>
    private final static String KEY_GY_HWG_DLHGQ = "GY_HWG_DLHGQ";//环网柜（电流互感器）</string>
    ;//环网柜（电压互感器）</string>
    private final static String KEY_GY_HWG_BLQ = "GY_HWG_BLQ";//环网柜（避雷器）</string>
    private final static String KEY_GY_HWG_GZZSQ = "GY_HWG_GZZSQ";//环网柜（故障指示器）</string>
    private final static String KEY_GY_HWG_MX = "GY_HWG_MX";//环网柜（母线）</string>
    private final static String KEY_GY_HWG_OTHER = "GY_HWG_OTHER";//其他）</string>
    private final static String KEY_GY_HWG_PHOTO = "GY_HWG_PHOTO";//照片</string>
    //分支箱
    private final static String KEY_GY_FZX_FZX = "GY_FZX_FZX";//分支箱（基本信息）</string>
    private final static String KEY_GY_FZX_GLKG = "GY_FZX_GLKG";//">分支箱（隔离开关）</string>
    private final static String KEY_GY_FZX_FHKG = "GY_FZX_FHKG";//">分支箱（负荷开关）</string>
    private final static String KEY_GY_FZX_DLHGQ = "GY_FZX_DLHGQ";//">分支箱（电流互感器）</string>
    private final static String KEY_GY_FZX_BLQ = "GY_FZX_BLQ";//">分支箱（避雷器）</string>
    private final static String KEY_GY_FZX_GZZSQ = "GY_FZX_GZZSQ";//">分支箱（故障指示器）</string>
    private final static String KEY_GY_FZX_MX = "GY_FZX_MX";//">分支箱（母线）</string>
    private final static String KEY_GY_FZX_ZPSJ = "GY_FZX_ZPSJ";//
    //箱式变电站站
    private final static String KEY_GY_XSBDZ_XSBDZ = "GY_XSBDZ_XSBDZ";//箱式变电站（基本信息）</string>
    private final static String KEY_GY_XSBDZ_FHKG = "GY_XSBDZ_FHKG";//">箱式变电站（负荷开关）</string>
    private final static String KEY_GY_XSBDZ_PDBYQ = "GY_XSBDZ_PDBYQ";//">箱式变电站（配电变压器）</string>
    private final static String KEY_GY_XSBDZ_DW = "GY_XSBDZ_DW";//">箱式变电站（档位）</string>
    private final static String KEY_GY_XSBDZ_DYTP = "GY_XSBDZ_DYTP";//">箱式变电站（低压铜牌）</string>
    private final static String KEY_GY_XSBDZ_DYZKK = "GY_XSBDZ_DYZKK";//">箱式变电站（低压总空开）</string>
    private final static String KEY_GY_XSBDZ_DYKK1 = "GY_XSBDZ_DYKK1";//">箱式变电站（低压空开1）</string>
    private final static String KEY_GY_XSBDZ_DYKK2 = "GY_XSBDZ_DYKK2";//">箱式变电站（低压空开2）</string>
    private final static String KEY_GY_XSBDZ_DYKK3 = "GY_XSBDZ_DYKK3";//">箱式变电站（低压空开3）</string>
    private final static String KEY_GY_XSBDZ_DYKK4 = "GY_XSBDZ_DYKK4";//">箱式变电站（低压空开4）</string>
    private final static String KEY_GY_XSBDZ_DYKK5 = "GY_XSBDZ_DYKK5";//">箱式变电站（低压空开5）</string>
    private final static String KEY_GY_XSBDZ_DYKK6 = "XSBDZ_DYKK6";//">箱式变电站（低压空开6）</string>
    private final static String KEY_GY_XSBDZ_DYWGBCZZ = "GY_XSBDZ_DYWGBCZZ";//">箱式变电站（低压无功补偿装置）</string>
    private final static String KEY_GY_XSBDZ_ZPSJ = "KEY_GY_XSBDZ_ZPSJ";//PHOTO
    private final static String KEY_GY_HWG_HWG_JG_XQ = "KEY_GY_HWG_HWG_XQ";//环网柜间隔详情

    //电缆分接箱
    private final static String KEY_GY_DLFJX_BLQ = "GY_DLFJX_BLQ";
    private final static String KEY_GY_DLFJX_DLFJX = "GY_DLFJX_DLFJX";
    private final static String KEY_GY_DLFJX_DLHGQ = "GY_DLFJX_DLHGQ";
    private final static String KEY_GY_DLFJX_GZZSQ = "GY_DLFJX_GZZSQ";
    private final static String KEY_GY_DLFJX_ZPSJ = "GY_DLFJX_ZPSJ";
    //工井信息
    private final static String KEY_GY_DLXL_GJXX = "GY_DLXL_GJXX";


    private List<FragmentOption> mDLXLFragments = new ArrayList<FragmentOption>();//电缆线路
    private Resources mResources = ElectricApplication_.getInstance().
            getApplicationContext().getResources();


    //二级 环网柜
    public List<FragmentOption> getSecondLevelItems_HWG() {
        List<FragmentOption> fragmentOptions = new ArrayList<FragmentOption>();
        fragmentOptions.add(getGY_HWG_HWG());//环网柜（基本信息）</string>
        fragmentOptions.add(getGY_HWG_HWG_SACER());//环网柜（基本信息）</string>
        fragmentOptions.add(getGY_HWG_PHOTO());//环网柜(照片)
        //  fragmentOptions.add(getGY_HWG_JG());//环网柜（间隔）</string>
        fragmentOptions.add(getGY_HWG_DLQ());//环网柜（断路器）</string>
        fragmentOptions.add(getGY_HWG_GLKG());//环网柜（隔离开关）</string>
        fragmentOptions.add(getGY_HWG_FHKG());//环网柜（负荷开关）</string>
        fragmentOptions.add(getGY_HWG_DYHGQ());//环网柜（电压互感器）</string>
        fragmentOptions.add(getGY_HWG_DLHGQ());//环网柜（电流互感器）</string>
        fragmentOptions.add(getGY_HWG_BLQ());//环网柜（避雷器）</string>
        fragmentOptions.add(getGY_HWG_GZZSQ());//环网柜（故障指示器）</string>
        fragmentOptions.add(getGY_HWG_MX());//环网柜（母线）</string>
        int count = fragmentOptions.size();
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                continue;
            }
            fragmentOptions.get(i).setParentNameKey(KEY_GY_HWG_HWG);
        }
        return fragmentOptions;
    }

    //二级 分支箱
    public List<FragmentOption> getSecondLevelItems_FZX() {
        List<FragmentOption> fragmentOptions = new ArrayList<FragmentOption>();
        fragmentOptions.add(getGY_DLXL_FZX());//
        fragmentOptions.add(getGY_FZX_FZX());//分支箱（基本信息）</string>
        fragmentOptions.add(getGY_FZX_GLKG());//分支箱（隔离开关）</string>
        fragmentOptions.add(getGY_FZX_FHKG());//分支箱（负荷开关）</string>
        fragmentOptions.add(getGY_FZX_DLHGQ());//分支箱（电流互感器）</string>
        fragmentOptions.add(getGY_FZX_BLQ());//分支箱（避雷器）</string>
        fragmentOptions.add(getGY_FZX_GZZSQ());//分支箱（故障指示器）</string>
        fragmentOptions.add(getGY_FZX_MX());//分支箱（母线）</string>
        fragmentOptions.add(getGY_FZX_ZPSJ());//分支箱（照片）</string>
        int count = fragmentOptions.size();
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                continue;
            }
            fragmentOptions.get(i).setParentNameKey(KEY_GY_DLXL_FZX);
        }
        return fragmentOptions;
    }

    //二级 箱式变电站
    public List<FragmentOption> getSecondLevelItems_XSBDZ() {
        List<FragmentOption> fragmentOptions = new ArrayList<FragmentOption>();
        fragmentOptions.add(getGY_DLXL_XSBDZ());//
        fragmentOptions.add(getGY_XSBDZ_XSBDZ());//箱式变电站（基本信息）</string>
        fragmentOptions.add(getGY_XSBDZ_FHKG());//箱式变电站（负荷开关）</string>
        fragmentOptions.add(getGY_XSBDZ_PDBYQ());//箱式变电站（配电变压器）</string>
        fragmentOptions.add(getGY_XSBDZ_DW());//箱式变电站（档位）</string>
        fragmentOptions.add(getGY_XSBDZ_DYTP());//箱式变电站（低压铜牌）</string>
        fragmentOptions.add(getGY_XSBDZ_DYZKK());//箱式变电站（低压总空开）</string>
        fragmentOptions.add(getGY_XSBDZ_DYKK1());//箱式变电站（低压空开1）</string>
        fragmentOptions.add(getGY_XSBDZ_DYKK2());//箱式变电站（低压空开2）</string>
        fragmentOptions.add(getGY_XSBDZ_DYKK3());//箱式变电站（低压空开3）</string>
        fragmentOptions.add(getGY_XSBDZ_DYZKK4());//箱式变电站（低压空开4）</string>
        fragmentOptions.add(getGY_XSBDZ_DYZKK5());//箱式变电站（低压空开5）</string>
        fragmentOptions.add(getGY_XSBDZ_DYZKK6());//箱式变电站（低压空开6）</string>
        fragmentOptions.add(getGY_XSBDZ_DYWGBCZZ());//箱式变电站（低压无功补偿装置）</string>
        fragmentOptions.add(getGY_XSBDZ_ZPSJ());//箱式变电站（照片信息）</string>
        int count = fragmentOptions.size();
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                continue;
            }
            fragmentOptions.get(i).setParentNameKey(KEY_GY_DLXL_XSBDZ);
        }
        return fragmentOptions;
    }

    //二级 电缆分接箱
    public List<FragmentOption> getSecondLevelItems_DLFJX() {
        List<FragmentOption> fragmentOptions = new ArrayList<FragmentOption>();
        fragmentOptions.add(getGY_DLFJX_DLFJX());//电缆分接箱（基本信息）
        fragmentOptions.add(getGY_DLFJX_BLQ());//电缆分接箱（避雷器）
        fragmentOptions.add(getGY_DLFJX_DLHGQ());//电缆分接箱（电流互感器）
        fragmentOptions.add(getGY_DLFJX_GZZSQ());//电缆分接箱（故障指示器）
        fragmentOptions.add(getGY_DLFJX_ZPSJ());//箱式变电站（档位）</string>
        int count = fragmentOptions.size();
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                continue;
            }
            fragmentOptions.get(i).setParentNameKey(KEY_GY_DLFJX_DLFJX);
        }
        return fragmentOptions;
    }


    public List<FragmentOption> getDLFramentItems() {
        if (null != mDLXLFragments && mDLXLFragments.size() > 0) {
            return mDLXLFragments;
        }

//        mDLXLFragments.add(getGY_DLXL_DJX());
//        mDLXLFragments.add(getGY_DLXL_DLJ());
//        mDLXLFragments.add(getGY_DLXL_DLZJT());
//        mDLXLFragments.add(getGY_DLXL_GXJBXX());
//        mDLXLFragments.add(getGY_DLXL_GZZSQ());
//        mDLXLFragments.add(getGY_DLXL_KYQK());
//        mDLXLFragments.add(getGY_DLXL_SKQK());
//        mDLXLFragments.add(getGY_DLXL_TSPDBYQ());
        //公井信息
        mDLXLFragments.add(getGY_DLXL_GJXX());
        //环网柜
        mDLXLFragments.addAll(getSecondLevelItems_HWG());
        //分支箱
        mDLXLFragments.addAll(getSecondLevelItems_FZX());
        //箱式变电站
        mDLXLFragments.addAll(getSecondLevelItems_XSBDZ());
        //电缆分接箱
        mDLXLFragments.addAll(getSecondLevelItems_DLFJX());
        //
        for (int i = 0; i < mDLXLFragments.size(); i++) {
            FragmentOption option = mDLXLFragments.get(i);
            option.setDatasetName(Enum.GY_DLXLTZXX);
            if (CommonFragment.isBaseFragment(option.getNameKey())) {
                option.setChecked(true);
            }
        }
        return mDLXLFragments;
    }


    public void initDLFramentDtatas(DataSet dataset) {
        getDLFramentItems();
        String checkedFragmentkeyValue = dataset.
                GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_COLLECTOBJECT);
        CommonFragment.initCheckedFragmentkeyValue(checkedFragmentkeyValue, mDLXLFragments);
    }


    // =======================================DL: fragment 生成============================

    private FragmentOption getGY_DLXL_Base() {
        GY_DLXL_Base fragment = new GY_DLXL_Base_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_Base);
        return new FragmentOption(
                KEY_GY_DLXL_Base, fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_DJX() {
        GY_DLXL_DJX fragment = new GY_DLXL_DJX_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_DJX);
        return new FragmentOption(KEY_GY_DLXL_DJX,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_DLJ() {
        GY_DLXL_DLJ fragment = new GY_DLXL_DLJ_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_DLJ);
        return new FragmentOption(KEY_GY_DLXL_DLJ,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_DLZJT() {
        GY_DLXL_DLZJT fragment = new GY_DLXL_DLZJT_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_DLZJT);
        return new FragmentOption(KEY_GY_DLXL_DLZJT,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_FZX() {
        GY_DLXL_FZX fragment = new GY_DLXL_FZX_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_FZX);
        return new FragmentOption(KEY_GY_DLXL_FZX,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_GXJBXX() {
        GY_DLXL_GXJBXX fragment = new GY_DLXL_GXJBXX_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_GXJBXX);
        return new FragmentOption(KEY_GY_DLXL_GXJBXX,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_GZZSQ() {
        GY_DLXL_GZZSQ fragment = new GY_DLXL_GZZSQ_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_GZZSQ);
        return new FragmentOption(KEY_GY_DLXL_GZZSQ,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_HWG() {
        GY_DLXL_HWG fragment = new GY_DLXL_HWG_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_HWG);
        return new FragmentOption(KEY_GY_DLXL_HWG,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_KYQK() {
        GY_DLXL_KYQK fragment = new GY_DLXL_KYQK_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_KYQK);
        return new FragmentOption(KEY_GY_DLXL_KYQK,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_SKQK() {
        GY_DLXL_SKQK fragment = new GY_DLXL_SKQK_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_SKQK);
        return new FragmentOption(KEY_GY_DLXL_SKQK,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_TSPDBYQ() {
        GY_DLXL_TSPDBYQ fragment = new GY_DLXL_TSPDBYQ_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_TSPDBYQ);
        return new FragmentOption(KEY_GY_DLXL_TSPDBYQ,
                fragmentName, "", fragment);
    }

    private FragmentOption getGY_DLXL_XSBDZ() {
        GY_DLXL_XSBDZ fragment = new GY_DLXL_XSBDZ_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_XSBDZ);
        return new FragmentOption(KEY_GY_DLXL_XSBDZ,
                fragmentName, "", fragment);
    }

    //环网柜

    //    环网柜（基本信息）
    private FragmentOption getGY_HWG_HWG() {
        GY_HWG_HWG fragment = new GY_HWG_HWG_();
        String fragmentName = mResources.getString(R.string.GY_HWG_HWG);
        return new FragmentOption(KEY_GY_HWG_HWG, fragmentName, "", fragment);
    }

    private FragmentOption getGY_HWG_HWG_SACER() {
        GY_HWG_spacerFragment fragment = new GY_HWG_spacerFragment_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_JG);
        return new FragmentOption(KEY_GY_HWG_HWG_JG_XQ, fragmentName, "", fragment);
    }

    //   环网柜（间隔）
    private FragmentOption getGY_HWG_JG() {
        GY_HWG_JG fragment = new GY_HWG_JG_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_JG);
        return new FragmentOption(KEY_GY_HWG_JG, fragmentName, "", fragment);
    }

    //环网柜（断路器）
    private FragmentOption getGY_HWG_DLQ() {
        GY_HWG_DLQ fragment = new GY_HWG_DLQ_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_DLQ);
        return new FragmentOption(KEY_GY_HWG_DLQ, fragmentName, "", fragment);
    }

    //">环网柜（负荷开关）</string>
    private FragmentOption getGY_HWG_FHKG() {
        GY_HWG_FHKG fragment = new GY_HWG_FHKG_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_FHKG);
        return new FragmentOption(KEY_GY_HWG_FHKG, fragmentName, "", fragment);
    }

    //环网柜（隔离开关）</string>
    private FragmentOption getGY_HWG_GLKG() {
        GY_HWG_GLKG fragment = new GY_HWG_GLKG_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_GLKG);
        return new FragmentOption(KEY_GY_HWG_GLKG, fragmentName, "", fragment);
    }

    //环网柜（电压互感器）</string>
    private FragmentOption getGY_HWG_DYHGQ() {
        GY_HWG_DYHGQ fragment = new GY_HWG_DYHGQ_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_DYHGQ);
        return new FragmentOption(KEY_GY_HWG_DYHGQ, fragmentName, "", fragment);
    }

    //环网柜（电流互感器）</string>
    private FragmentOption getGY_HWG_DLHGQ() {
        GY_HWG_DLHGQ fragment = new GY_HWG_DLHGQ_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_DLHGQ);
        return new FragmentOption(KEY_GY_HWG_DLHGQ, fragmentName, "", fragment);
    }

    //环网柜（避雷器）</string>
    private FragmentOption getGY_HWG_BLQ() {
        GY_HWG_BLQ fragment = new GY_HWG_BLQ_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_BLQ);
        return new FragmentOption(KEY_GY_HWG_BLQ, fragmentName, "", fragment);
    }

    //环网柜（故障指示器）</string>
    private FragmentOption getGY_HWG_GZZSQ() {
        GY_HWG_GZZSQ fragment = new GY_HWG_GZZSQ_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_GZZSQ);
        return new FragmentOption(KEY_GY_HWG_GZZSQ, fragmentName, "", fragment);
    }

    //环网柜（母线）</string>
    private FragmentOption getGY_HWG_MX() {
        GY_HWG_MX fragment = new GY_HWG_MX_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_MX);
        return new FragmentOption(KEY_GY_HWG_MX, fragmentName, "", fragment);
    }

    //其他</string>
    private FragmentOption getGY_HWG_OTHER() {
        GY_HWG_OTHER fragment = new GY_HWG_OTHER_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_OTHER);
        return new FragmentOption(KEY_GY_HWG_OTHER, fragmentName, "", fragment);
    }

    //环网柜照片</string>
    private FragmentOption getGY_HWG_PHOTO() {
        SrPhotoManagerFragment fragment = new com.geocraft.electrics.sr.fragment.
                SrPhotoManagerFragment_();
        List<String> tags = new ArrayList<String>();
        tags.add("环网柜:全貌照片");
        tags.add("环网柜:标识牌照片");
        tags.add("环网柜:铭牌照片");
        fragment.setTags(tags);
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_PHOTO);
        return new FragmentOption(KEY_GY_HWG_PHOTO, fragmentName, "", fragment);
    }

    //分支箱
    //分支箱（基本信息）</string>
    private FragmentOption getGY_FZX_FZX() {
        GY_FZX_FZX fragment = new GY_FZX_FZX_();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_JBXX);
        return new FragmentOption(KEY_GY_FZX_FZX,
                fragmentName, "", fragment);
    }

    //">分支箱（隔离开关）</string>
    private FragmentOption getGY_FZX_GLKG() {
        GY_FZX_GLKG fragment = new GY_FZX_GLKG_();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_GLKG);
        return new FragmentOption(KEY_GY_FZX_GLKG, fragmentName, "", fragment);
    }

    //">分支箱（负荷开关）</string>
    private FragmentOption getGY_FZX_FHKG() {
        GY_FZX_FHKG fragment = new GY_FZX_FHKG_();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_FHKG);
        return new FragmentOption(KEY_GY_FZX_FHKG, fragmentName, "", fragment);
    }

    //">分支箱（电流互感器）</string>
    private FragmentOption getGY_FZX_DLHGQ() {
        GY_FZX_DLHGQ fragment = new GY_FZX_DLHGQ_();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_DLHGQ);
        return new FragmentOption(KEY_GY_FZX_DLHGQ, fragmentName, "", fragment);
    }

    //">分支箱（避雷器）</string>
    private FragmentOption getGY_FZX_BLQ() {
        GY_FZX_BLQ fragment = new GY_FZX_BLQ_();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_BLQ);
        return new FragmentOption(KEY_GY_FZX_BLQ, fragmentName, "", fragment);
    }

    //">分支箱（故障指示器）</string>
    private FragmentOption getGY_FZX_GZZSQ() {
        GY_FZX_GZZSQ fragment = new GY_FZX_GZZSQ_();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_GZZSQ);
        return new FragmentOption(KEY_GY_FZX_GZZSQ, fragmentName, "", fragment);
    }

    //">分支箱（母线）</string>
    private FragmentOption getGY_FZX_MX() {
        GY_FZX_MX fragment = new GY_FZX_MX_();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_MX);
        return new FragmentOption(KEY_GY_FZX_MX, fragmentName, "", fragment);
    }

    //分支箱照片
    private FragmentOption getGY_FZX_ZPSJ() {
        SrPhotoManagerFragment fragment = new com.geocraft.electrics.sr.fragment.
                SrPhotoManagerFragment_();
        List<String> tags = new ArrayList<String>();
        tags.add("分支箱:分支箱全貌照片");
        tags.add("分支箱:标识牌照片");
        tags.add("分支箱:铭牌照片");
        tags.add("分支箱:柜门照片");
        fragment.setTags(tags);
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_ZPSJ);
        return new FragmentOption(KEY_GY_FZX_ZPSJ, fragmentName, "", fragment);
    }

    //箱式变电站站
    //箱式变电站（基本信息）</string>
    private FragmentOption getGY_XSBDZ_XSBDZ() {
        GY_XSBDZ_XSBDZ fragment = new GY_XSBDZ_XSBDZ_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_JBXX);
        return new FragmentOption(KEY_GY_XSBDZ_XSBDZ, fragmentName, "", fragment);
    }

    //">箱式变电站（负荷开关）</string>
    private FragmentOption getGY_XSBDZ_FHKG() {
        GY_XSBDZ_FHKG fragment = new GY_XSBDZ_FHKG_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_FHKG);
        return new FragmentOption(KEY_GY_XSBDZ_FHKG, fragmentName, "", fragment);
    }

    //">箱式变电站（配电变压器）</string>
    private FragmentOption getGY_XSBDZ_PDBYQ() {
        GY_XSBDZ_PDBYQ fragment = new GY_XSBDZ_PDBYQ_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_PDBYQ);
        return new FragmentOption(KEY_GY_XSBDZ_PDBYQ, fragmentName, "", fragment);
    }

    //">箱式变电站（档位）</string>
    private FragmentOption getGY_XSBDZ_DW() {
        GY_XSBDZ_DW fragment = new GY_XSBDZ_DW_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DW);
        return new FragmentOption(KEY_GY_XSBDZ_DW, fragmentName, "", fragment);
    }

    //">箱式变电站（低压铜牌）</string>
    private FragmentOption getGY_XSBDZ_DYTP() {
        GY_XSBDZ_DYTP fragment = new GY_XSBDZ_DYTP_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYTP);
        return new FragmentOption(KEY_GY_XSBDZ_DYTP, fragmentName, "", fragment);
    }

    //">箱式变电站（低压总空开）</string>
    private FragmentOption getGY_XSBDZ_DYZKK() {
        GY_XSBDZ_DYZKK fragment = new GY_XSBDZ_DYZKK_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYZKK);
        return new FragmentOption(KEY_GY_XSBDZ_DYZKK, fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开1）</string>
    private FragmentOption getGY_XSBDZ_DYKK1() {
        GY_XSBDZ_DYZKK1 fragment = new GY_XSBDZ_DYZKK1_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK1);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK1, fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开2）</string>
    private FragmentOption getGY_XSBDZ_DYKK2() {
        GY_XSBDZ_DYZKK2 fragment = new GY_XSBDZ_DYZKK2_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK2);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK2, fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开3）</string>
    private FragmentOption getGY_XSBDZ_DYKK3() {
        GY_XSBDZ_DYZKK3 fragment = new GY_XSBDZ_DYZKK3_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK3);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK3,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开4）</string>
    private FragmentOption getGY_XSBDZ_DYZKK4() {
        GY_XSBDZ_DYZKK4 fragment = new GY_XSBDZ_DYZKK4_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK4);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK4,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开5）</string>
    private FragmentOption getGY_XSBDZ_DYZKK5() {
        GY_XSBDZ_DYZKK5 fragment = new GY_XSBDZ_DYZKK5_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK5);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK5,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开6）</string>
    private FragmentOption getGY_XSBDZ_DYZKK6() {
        GY_XSBDZ_DYZKK6 fragment = new GY_XSBDZ_DYZKK6_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK6);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK6,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压无功补偿装置）</string>
    private FragmentOption getGY_XSBDZ_DYWGBCZZ() {
        GY_XSBDZ_DYWGBCZZ fragment = new GY_XSBDZ_DYWGBCZZ_();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYWGBCZZ);
        return new FragmentOption(KEY_GY_XSBDZ_DYWGBCZZ,
                fragmentName, "", fragment);
    }

    //">照片
    private FragmentOption getGY_XSBDZ_ZPSJ() {
        SrPhotoManagerFragment fragment = new
                com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment_();
        List<String> tags = new ArrayList<String>();
        tags.add("箱式变电站:全貌照片");
        tags.add("箱式变电站:箱变铭牌照片");
        tags.add("箱式变电站:低压总空开照片");
        tags.add("箱式变电站:低压空气开关照片1");
        tags.add("箱式变电站:低压空气开关照片2");
        tags.add("箱式变电站:低压空气开关照片3");
        tags.add("箱式变电站:低压空气开关照片4");
        tags.add("箱式变电站:低压空气开关照片5");
        tags.add("箱式变电站:低压空气开关照片6");
        tags.add("箱式变电站:低压空气开关照片7");
        tags.add("箱式变电站:低压无功补偿装置铭牌照片");
        fragment.setTags(tags);
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_ZPSJ);
        return new FragmentOption(KEY_GY_XSBDZ_ZPSJ, fragmentName, "", fragment);
    }

    //-----电缆分接箱

    //电缆分接箱（基本信息）
    private FragmentOption getGY_DLFJX_DLFJX() {
        GY_DLFJX_DLFJX fragment = new GY_DLFJX_DLFJX_();
        String fragmentName = mResources.getString(R.string.GY_DLFZX);
        return new FragmentOption(KEY_GY_DLFJX_DLFJX, fragmentName, "", fragment);
    }

    //电缆分接箱（避雷器）
    private FragmentOption getGY_DLFJX_BLQ() {
        GY_DLFJX_BLQ fragment = new GY_DLFJX_BLQ_();
        String fragmentName = mResources.getString(R.string.GY_DLFZX_TITLE_BLQ);
        return new FragmentOption(KEY_GY_DLFJX_DLHGQ, fragmentName, "", fragment);
    }

    //电缆分接箱（电流互感器）
    private FragmentOption getGY_DLFJX_DLHGQ() {
        GY_DLFJX_DLHGQ fragment = new GY_DLFJX_DLHGQ_();
        String fragmentName = mResources.getString(R.string.GY_DLFZX_TITLE_DLHGQ);
        return new FragmentOption(KEY_GY_DLFJX_BLQ, fragmentName, "", fragment);
    }

    //电缆分接箱（故障指示器）
    private FragmentOption getGY_DLFJX_GZZSQ() {
        GY_DLFJX_GZZSQ fragment = new GY_DLFJX_GZZSQ_();
        String fragmentName = mResources.getString(R.string.GY_DLFZX_TITLE_GZZSQ);
        return new FragmentOption(KEY_GY_DLFJX_GZZSQ, fragmentName, "", fragment);
    }

    //电缆分接箱电缆分接箱(照片)
    private FragmentOption getGY_DLFJX_ZPSJ() {
        SrPhotoManagerFragment fragment = new
                com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment_();
        List<String> tags = new ArrayList<String>();
        tags.add("电缆分接箱:电缆分接箱全貌照片");
        tags.add("电缆分接箱:标识牌照片");
        tags.add("电缆分接箱:铭牌照片");
        fragment.setTags(tags);
        String fragmentName = mResources.getString(R.string.GY_DLFJXTZXX_F_PHOTO);
        return new FragmentOption(KEY_GY_DLFJX_ZPSJ, fragmentName, "", fragment);
    }

    //工井信息
    private FragmentOption getGY_DLXL_GJXX() {
        GY_DLXL_GJXX fragment = new GY_DLXL_GJXX_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_GJXX);
        return new FragmentOption(KEY_GY_DLXL_GJXX,
                fragmentName, "", fragment);
    }
}
