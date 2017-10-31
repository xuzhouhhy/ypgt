package com.geocraft.electrics.sr;

import android.content.res.Resources;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
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
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_DLHGQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_FHKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_FZX;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_GLKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_GZZSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_MX;
import com.geocraft.electrics.ui.fragment.GY_fragment.FZX_fragment.GY_FZX_ZPSJ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_BLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_DLHGQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_DLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_DYHGQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_FHKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_GLKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_GZZSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_HWG;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_JG;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_MX;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_OTHER;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_OTHER_;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_PHOTO;
import com.geocraft.electrics.ui.fragment.GY_fragment.HWG_fragment.GY_HWG_PHOTO_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_BGXJ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_BGXJ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_BYQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_BYQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_Base;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_Base_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_DLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_DLQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_DX;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_DX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_FHKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_FHKG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GLKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GLKG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GZZSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GZZSQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_HD;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_HD_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_JKBLZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_JKBLZ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_JYZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_JYZ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KSRDQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KSRDQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KYQK;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KYQK_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_LX;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_LX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_NZXJ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_NZXJ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_SFKYFW;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_SFKYFW_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_SKQK;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_SKQK_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_TGXLHS;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_TGXLHS_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_XLBLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_XLBLQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DW;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYTP;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYWGBCZZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK1;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK2;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK3;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK4;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK5;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_DYZKK6;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_FHKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_PDBYQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_XSBDZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.XSBDZ_fragment.GY_XSBDZ_ZPSJ;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 可选项frament管理
 */
@EBean
public class BasicFragmentFactory {

    public final static String KEY_GY_PRE_BASE = "Well_PreFragment";
    public final static String KEY_GY_JKXLTZXX_BASE = "GY_JKXLTZXX_BASE";
    public final static String KEY_GY_DLXL_Base = "GY_DLXL_Base";
    //JK
    private final static String NAME_KEY_MARK = "&";
    private final static String KEY_GY_JTXL_BGXJ = "GY_JTXL_BGXJ";
    private final static String KEY_GY_JTXL_BYQ = "GY_JTXL_BYQ";
    private final static String KEY_GY_JTXL_DLQ = "GY_JTXL_DLQ";
    private final static String KEY_GY_JTXL_DX = "GY_JTXL_DX";
    private final static String KEY_GY_JTXL_FHKG = "GY_JTXL_FHKG";
    private final static String KEY_GY_JTXL_GLKG = "GY_JTXL_GLKG";
    private final static String KEY_GY_JTXL_GZZSQ = "GY_JTXL_GZZSQ";
    private final static String KEY_GY_JTXL_HD = "GY_JTXL_HD";
    private final static String KEY_GY_JTXL_JKBLZ = "GY_JTXL_JKBLZ";
    private final static String KEY_GY_JTXL_JYZ = "GY_JTXL_JYZ";
    private final static String KEY_GY_JTXL_KSRDQ = "GY_JTXL_KSRDQ";
    private final static String KEY_GY_JTXL_KYQK = "GY_JTXL_KYQK";
    private final static String KEY_GY_JTXL_LX = "GY_JTXL_LX";
    private final static String KEY_GY_JTXL_NZXJ = "GY_JTXL_NZXJ";
    private final static String KEY_GY_JTXL_SFKYFW = "GY_JTXL_SFKYFW";
    private final static String KEY_GY_JTXL_SKQK = "GY_JTXL_SKQK";
    private final static String KEY_GY_JTXL_TGXLHS = "GY_JTXL_TGXLHS";
    private final static String KEY_GY_JTXL_XLBLQ = "GY_JTXL_XLBLQ";

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
    private final static String KEY_GY_HWG_FHKG = "GY_HWG_FHKG";;//">环网柜（负荷开关）</string>
    private final static String KEY_GY_HWG_GLKG = "GY_HWG_GLKG";;//环网柜（隔离开关）</string>
    private final static String KEY_GY_HWG_DYHGQ = "GY_HWG_DYHGQ";;//环网柜（电压互感器）</string>
    private final static String KEY_GY_HWG_DLHGQ = "GY_HWG_DLHGQ";//环网柜（电流互感器）</string>
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

    private List<FragmentOption> mJKXLFragments = new ArrayList<FragmentOption>();//架空线路
    private List<FragmentOption> mDLXLFragments = new ArrayList<FragmentOption>();//电缆线路
    private Resources mResources = ElectricApplication_.getInstance().
            getApplicationContext().getResources();

    /**
     * 判断当前fragment是否为基础必填界面
     *
     * @param fragmentKey 界面标识key
     */
    public static boolean isBaseFragment(String fragmentKey) {
        if (fragmentKey.equals(KEY_GY_JKXLTZXX_BASE) ||
                fragmentKey.equals(KEY_GY_DLXL_Base)) {
            return true;
        }
        return false;
    }

    public void initFragments(WellType wellType, DataSet dataset) {
        if (wellType == WellType.JK) {
            initJKFramentDtatas(dataset);
        } else if (wellType == WellType.DL) {
            initDLFramentDtatas(dataset);
        }
    }

    public List<FragmentOption> getJKFramentItems() {
        if (null != mJKXLFragments && mJKXLFragments.size() > 0) {
            return mJKXLFragments;
        }
        //mJKXLFragments.add(generateGY_JTXL_Base());
        mJKXLFragments.add(generateGY_JTXL_BGXJ());
        mJKXLFragments.add(generateGY_JTXL_BYQ());
        mJKXLFragments.add(generateGY_JTXL_DLQ());
        mJKXLFragments.add(generateGY_JTXL_DX());
        mJKXLFragments.add(generateGY_JTXL_FHKG());
        mJKXLFragments.add(generateGY_JTXL_GLKG());
        mJKXLFragments.add(generateGY_JTXL_GZZSQ());
        mJKXLFragments.add(generateGY_JTXL_HD());
        mJKXLFragments.add(generateGY_JTXL_JKBLZ());
        mJKXLFragments.add(generateGY_JTXL_JYZ());
        mJKXLFragments.add(generateGY_JTXL_KSRDQ());
        mJKXLFragments.add(generateGY_JTXL_KYQK());
        mJKXLFragments.add(generateGY_JTXL_LX());
        mJKXLFragments.add(generateGY_JTXL_NZXJ());
        mJKXLFragments.add(generateGY_JTXL_SFKYFW());
        mJKXLFragments.add(generateGY_JTXL_SKQK());
        mJKXLFragments.add(generateGY_JTXL_TGXLHS());
        mJKXLFragments.add(generateGY_JTXL_XLBLQ());
        for (int i = 0; i < mJKXLFragments.size(); i++) {
            FragmentOption option = mJKXLFragments.get(i);
            option.setDatasetName(Enum.GY_JKXLTZXX);
            if (isBaseFragment(option.getFramentNameKey())) {
                option.setChecked(true);
            }
        }
        return mJKXLFragments;
    }

    public List<FragmentOption> getDLFramentItems() {
        if (null != mDLXLFragments && mDLXLFragments.size() > 0) {
            return mDLXLFragments;
        }
        //mDLXLFragments.add(getGY_DLXL_Base());
        mDLXLFragments.add(getGY_DLXL_DJX());
        mDLXLFragments.add(getGY_DLXL_DLJ());
        mDLXLFragments.add(getGY_DLXL_DLZJT());

        mDLXLFragments.add(getGY_DLXL_GXJBXX());
        mDLXLFragments.add(getGY_DLXL_GZZSQ());

        //环网柜
        mDLXLFragments.add(getGY_DLXL_HWG());
        mDLXLFragments.add(getGY_HWG_HWG());//环网柜（基本信息）</string>
        mDLXLFragments.add(getGY_HWG_JG());//环网柜（间隔）</string>
        mDLXLFragments.add(getGY_HWG_DLQ());//环网柜（断路器）</string>
        mDLXLFragments.add(getGY_HWG_FHKG());//环网柜（负荷开关）</string>
        mDLXLFragments.add(getGY_HWG_GLKG());//环网柜（隔离开关）</string>
        mDLXLFragments.add(getGY_HWG_DYHGQ());//环网柜（电压互感器）</string>
        mDLXLFragments.add(getGY_HWG_DLHGQ());//环网柜（电流互感器）</string>
        mDLXLFragments.add(getGY_HWG_BLQ());//环网柜（避雷器）</string>
        mDLXLFragments.add(getGY_HWG_GZZSQ());//环网柜（故障指示器）</string>
        mDLXLFragments.add(getGY_HWG_MX());//环网柜（母线）</string>

        //分支箱
        mDLXLFragments.add(getGY_DLXL_FZX());//
        mDLXLFragments.add(getGY_FZX_FZX());//分支箱（基本信息）</string>
        mDLXLFragments.add(getGY_FZX_GLKG());//分支箱（隔离开关）</string>
        mDLXLFragments.add(getGY_FZX_FHKG());//分支箱（负荷开关）</string>
        mDLXLFragments.add(getGY_FZX_DLHGQ());//分支箱（电流互感器）</string>
        mDLXLFragments.add(getGY_FZX_BLQ());//分支箱（避雷器）</string>
        mDLXLFragments.add(getGY_FZX_GZZSQ());//分支箱（故障指示器）</string>
        mDLXLFragments.add(getGY_FZX_MX());//分支箱（母线）</string>
        mDLXLFragments.add(getGY_FZX_ZPSJ());//分支箱（照片）</string>


        mDLXLFragments.add(getGY_DLXL_KYQK());
        mDLXLFragments.add(getGY_DLXL_SKQK());
        mDLXLFragments.add(getGY_DLXL_TSPDBYQ());

        //箱式变电站
        mDLXLFragments.add(getGY_DLXL_XSBDZ());//
        mDLXLFragments.add(getGY_XSBDZ_XSBDZ());//箱式变电站（基本信息）</string>
        mDLXLFragments.add(getGY_XSBDZ_FHKG());//箱式变电站（负荷开关）</string>
        mDLXLFragments.add(getGY_XSBDZ_PDBYQ());//箱式变电站（配电变压器）</string>
        mDLXLFragments.add(getGY_XSBDZ_DW());//箱式变电站（档位）</string>
        mDLXLFragments.add(getGY_XSBDZ_DYTP());//箱式变电站（低压铜牌）</string>
        mDLXLFragments.add(getGY_XSBDZ_DYZKK());//箱式变电站（低压总空开）</string>
        mDLXLFragments.add(getGY_XSBDZ_DYKK1());//箱式变电站（低压空开1）</string>
        mDLXLFragments.add(getGY_XSBDZ_DYKK2());//箱式变电站（低压空开2）</string>
        mDLXLFragments.add(getGY_XSBDZ_DYKK3());//箱式变电站（低压空开3）</string>
        mDLXLFragments.add(getGY_XSBDZ_DYZKK4());//箱式变电站（低压空开4）</string>
        mDLXLFragments.add(getGY_XSBDZ_DYZKK5());//箱式变电站（低压空开5）</string>
        mDLXLFragments.add(getGY_XSBDZ_DYZKK6());//箱式变电站（低压空开6）</string>
        mDLXLFragments.add(getGY_XSBDZ_DYWGBCZZ());//箱式变电站（低压无功补偿装置）</string>
        mDLXLFragments.add(getGY_XSBDZ_ZPSJ());//箱式变电站（照片信息）</string>

        for (int i = 0; i < mDLXLFragments.size(); i++) {
            FragmentOption option = mDLXLFragments.get(i);
            option.setDatasetName(Enum.GY_DLXLTZXX);
            if (isBaseFragment(option.getFramentNameKey())) {
                option.setChecked(true);
            }
        }
        return mDLXLFragments;
    }

    private void initJKFramentDtatas(DataSet dataset) {
        getJKFramentItems();
        String checkedFragmentkeyValue = dataset.
                GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_COLLECTOBJECT);
        initCheckedFragmentkeyValue(checkedFragmentkeyValue, mJKXLFragments);
    }

    private void initDLFramentDtatas(DataSet dataset) {
        getDLFramentItems();
        String checkedFragmentkeyValue = dataset.
                GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_COLLECTOBJECT);
        initCheckedFragmentkeyValue(checkedFragmentkeyValue, mDLXLFragments);
    }

    private void initCheckedFragmentkeyValue(String checkedFragmentkeyValue,
                                             List<FragmentOption> fragmentOptions) {
        if (null == checkedFragmentkeyValue || checkedFragmentkeyValue.isEmpty()) {
            return;
        }
        String[] keyArry = checkedFragmentkeyValue.split(NAME_KEY_MARK);
        for (int i = 0; i < fragmentOptions.size(); i++) {
            FragmentOption option = fragmentOptions.get(i);
            String fragmentNameKey = option.getFramentNameKey();
            for (String key : keyArry) {
                if (key.equals(fragmentNameKey)) {
                    option.setChecked(true);
                }
            }
        }
    }

    // =======================================jk fragment 生成============================

    private FragmentOption generateGY_JTXL_Base() {
        GY_JTXL_Base fragment = new GY_JTXL_Base_();
        String fragmentName = mResources.getString(R.string.well_jk_base);
        return new FragmentOption(KEY_GY_JKXLTZXX_BASE,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_BGXJ() {
        GY_JTXL_BGXJ fragment = new GY_JTXL_BGXJ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_BGXJ);
        return new FragmentOption(KEY_GY_JTXL_BGXJ,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_BYQ() {
        GY_JTXL_BYQ fragment = new GY_JTXL_BYQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_BYQ);
        return new FragmentOption(KEY_GY_JTXL_BYQ,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_DLQ() {
        GY_JTXL_DLQ fragment = new GY_JTXL_DLQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_DLQ);
        return new FragmentOption(KEY_GY_JTXL_DLQ,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_DX() {
        GY_JTXL_DX fragment = new GY_JTXL_DX_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_DX);
        return new FragmentOption(KEY_GY_JTXL_DX,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_FHKG() {
        GY_JTXL_FHKG fragment = new GY_JTXL_FHKG_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_FHKG);
        return new FragmentOption(KEY_GY_JTXL_FHKG,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_GLKG() {
        GY_JTXL_GLKG fragment = new GY_JTXL_GLKG_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_GLKG);
        return new FragmentOption(KEY_GY_JTXL_GLKG,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_GZZSQ() {
        GY_JTXL_GZZSQ fragment = new GY_JTXL_GZZSQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_GZZSQ);
        return new FragmentOption(KEY_GY_JTXL_GZZSQ,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_HD() {
        GY_JTXL_HD fragment = new GY_JTXL_HD_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_HD);
        return new FragmentOption(KEY_GY_JTXL_HD,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_JKBLZ() {
        GY_JTXL_JKBLZ fragment = new GY_JTXL_JKBLZ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_JKBLZ);
        return new FragmentOption(KEY_GY_JTXL_JKBLZ,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_JYZ() {
        GY_JTXL_JYZ fragment = new GY_JTXL_JYZ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_JYZ);
        return new FragmentOption(KEY_GY_JTXL_JYZ,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_KSRDQ() {
        GY_JTXL_KSRDQ fragment = new GY_JTXL_KSRDQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_KSRDQ);
        return new FragmentOption(KEY_GY_JTXL_KSRDQ,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_KYQK() {
        GY_JTXL_KYQK fragment = new GY_JTXL_KYQK_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_KYQK);
        return new FragmentOption(KEY_GY_JTXL_KYQK,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_LX() {
        GY_JTXL_LX fragment = new GY_JTXL_LX_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_LX);
        return new FragmentOption(KEY_GY_JTXL_LX,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_NZXJ() {
        GY_JTXL_NZXJ fragment = new GY_JTXL_NZXJ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_NZXJ);
        return new FragmentOption(KEY_GY_JTXL_NZXJ,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_SFKYFW() {
        GY_JTXL_SFKYFW fragment = new GY_JTXL_SFKYFW_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_SFKYFW);
        return new FragmentOption(KEY_GY_JTXL_SFKYFW,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_SKQK() {
        GY_JTXL_SKQK fragment = new GY_JTXL_SKQK_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_SKQK);
        return new FragmentOption(KEY_GY_JTXL_SKQK,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_TGXLHS() {
        GY_JTXL_TGXLHS fragment = new GY_JTXL_TGXLHS_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_TGXLHS);
        return new FragmentOption(KEY_GY_JTXL_TGXLHS,
                fragmentName, "", fragment);
    }

    private FragmentOption generateGY_JTXL_XLBLQ() {
        GY_JTXL_XLBLQ fragment = new GY_JTXL_XLBLQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_XLBLQ);
        return new FragmentOption(KEY_GY_JTXL_XLBLQ,
                fragmentName, "", fragment);
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
    //环网柜

//    环网柜（基本信息）
    private FragmentOption getGY_HWG_HWG() {
        GY_HWG_HWG fragment = new GY_HWG_HWG();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_JBXX);
        return new FragmentOption(KEY_GY_HWG_HWG,
                fragmentName, "", fragment);
    }

    //   环网柜（间隔）
    private FragmentOption getGY_HWG_JG() {
        GY_HWG_JG fragment = new GY_HWG_JG();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_JG);
        return new FragmentOption(KEY_GY_HWG_JG,
                fragmentName, "", fragment);
    }

    //环网柜（断路器）
    private FragmentOption getGY_HWG_DLQ() {
        GY_HWG_DLQ fragment = new GY_HWG_DLQ();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_DLQ);
        return new FragmentOption(KEY_GY_HWG_DLQ,
                fragmentName, "", fragment);
    }

    //">环网柜（负荷开关）</string>
    private FragmentOption getGY_HWG_FHKG() {
        GY_HWG_FHKG fragment = new GY_HWG_FHKG();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_FHKG);
        return new FragmentOption(KEY_GY_HWG_FHKG,
                fragmentName, "", fragment);
    }

    //环网柜（隔离开关）</string>
    private FragmentOption getGY_HWG_GLKG() {
        GY_HWG_GLKG fragment = new GY_HWG_GLKG();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_GLKG);
        return new FragmentOption(KEY_GY_HWG_GLKG,
                fragmentName, "", fragment);
    }

    //环网柜（电压互感器）</string>
    private FragmentOption getGY_HWG_DYHGQ() {
        GY_HWG_DYHGQ fragment = new GY_HWG_DYHGQ();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_DYHGQ);
        return new FragmentOption(KEY_GY_HWG_DYHGQ,
                fragmentName, "", fragment);
    }

    //环网柜（电流互感器）</string>
    private FragmentOption getGY_HWG_DLHGQ() {
        GY_HWG_DLHGQ fragment = new GY_HWG_DLHGQ();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_DLHGQ);
        return new FragmentOption(KEY_GY_HWG_DLHGQ,
                fragmentName, "", fragment);
    }

    //环网柜（避雷器）</string>
    private FragmentOption getGY_HWG_BLQ() {
        GY_HWG_BLQ fragment = new GY_HWG_BLQ();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_BLQ);
        return new FragmentOption(KEY_GY_HWG_BLQ,
                fragmentName, "", fragment);
    }

    //环网柜（故障指示器）</string>
    private FragmentOption getGY_HWG_GZZSQ() {
        GY_HWG_GZZSQ fragment = new GY_HWG_GZZSQ();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_GZZSQ);
        return new FragmentOption(KEY_GY_HWG_GZZSQ,
                fragmentName, "", fragment);
    }

    //环网柜（母线）</string>
    private FragmentOption getGY_HWG_MX() {
        GY_HWG_MX fragment = new GY_HWG_MX();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_MX);
        return new FragmentOption(KEY_GY_HWG_MX,
                fragmentName, "", fragment);
    }

    //其他</string>
    private FragmentOption getGY_HWG_OTHER() {
        GY_HWG_OTHER fragment = new GY_HWG_OTHER_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_OTHER);
        return new FragmentOption(KEY_GY_HWG_OTHER,
                fragmentName, "", fragment);
    }
    //照片</string>
    private FragmentOption getGY_HWG_PHOTO() {
        GY_HWG_PHOTO fragment = new GY_HWG_PHOTO_();
        String fragmentName = mResources.getString(R.string.GY_HWG_TITLE_PHOTO);
        return new FragmentOption(KEY_GY_HWG_PHOTO,
                fragmentName, "", fragment);
    }

    //分支箱
    //分支箱（基本信息）</string>
    private FragmentOption getGY_FZX_FZX() {
        GY_FZX_FZX fragment = new GY_FZX_FZX();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_JBXX);
        return new FragmentOption(KEY_GY_FZX_FZX,
                fragmentName, "", fragment);
    }

    //">分支箱（隔离开关）</string>
    private FragmentOption getGY_FZX_GLKG() {
        GY_FZX_GLKG fragment = new GY_FZX_GLKG();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_GLKG);
        return new FragmentOption(KEY_GY_FZX_GLKG,
                fragmentName, "", fragment);
    }

    //">分支箱（负荷开关）</string>
    private FragmentOption getGY_FZX_FHKG() {
        GY_FZX_FHKG fragment = new GY_FZX_FHKG();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_FHKG);
        return new FragmentOption(KEY_GY_FZX_FHKG,
                fragmentName, "", fragment);
    }

    //">分支箱（电流互感器）</string>
    private FragmentOption getGY_FZX_DLHGQ() {
        GY_FZX_DLHGQ fragment = new GY_FZX_DLHGQ();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_DLHGQ);
        return new FragmentOption(KEY_GY_FZX_DLHGQ,
                fragmentName, "", fragment);
    }

    //">分支箱（避雷器）</string>
    private FragmentOption getGY_FZX_BLQ() {
        GY_FZX_BLQ fragment = new GY_FZX_BLQ();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_BLQ);
        return new FragmentOption(KEY_GY_FZX_BLQ,
                fragmentName, "", fragment);
    }

    //">分支箱（故障指示器）</string>
    private FragmentOption getGY_FZX_GZZSQ() {
        GY_FZX_GZZSQ fragment = new GY_FZX_GZZSQ();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_GZZSQ);
        return new FragmentOption(KEY_GY_FZX_GZZSQ,
                fragmentName, "", fragment);
    }

    //">分支箱（母线）</string>
    private FragmentOption getGY_FZX_MX() {
        GY_FZX_MX fragment = new GY_FZX_MX();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_MX);
        return new FragmentOption(KEY_GY_FZX_MX,
                fragmentName, "", fragment);
    }

    //照片
    private FragmentOption getGY_FZX_ZPSJ() {
        GY_FZX_ZPSJ fragment = new GY_FZX_ZPSJ();
        String fragmentName = mResources.getString(R.string.GY_FZX_TITLE_ZPSJ);
        return new FragmentOption(KEY_GY_FZX_ZPSJ,
                fragmentName, "", fragment);
    }

    //箱式变电站站
    //箱式变电站（基本信息）</string>
    private FragmentOption getGY_XSBDZ_XSBDZ() {
        GY_XSBDZ_XSBDZ fragment = new GY_XSBDZ_XSBDZ();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_JBXX);
        return new FragmentOption(KEY_GY_XSBDZ_XSBDZ,
                fragmentName, "", fragment);
    }

    //">箱式变电站（负荷开关）</string>
    private FragmentOption getGY_XSBDZ_FHKG() {
        GY_XSBDZ_FHKG fragment = new GY_XSBDZ_FHKG();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_FHKG);
        return new FragmentOption(KEY_GY_XSBDZ_FHKG,
                fragmentName, "", fragment);
    }

    //">箱式变电站（配电变压器）</string>
    private FragmentOption getGY_XSBDZ_PDBYQ() {
        GY_XSBDZ_PDBYQ fragment = new GY_XSBDZ_PDBYQ();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_PDBYQ);
        return new FragmentOption(KEY_GY_XSBDZ_PDBYQ,
                fragmentName, "", fragment);
    }

    //">箱式变电站（档位）</string>
    private FragmentOption getGY_XSBDZ_DW() {
        GY_XSBDZ_DW fragment = new GY_XSBDZ_DW();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DW);
        return new FragmentOption(KEY_GY_XSBDZ_DW,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压铜牌）</string>
    private FragmentOption getGY_XSBDZ_DYTP() {
        GY_XSBDZ_DYTP fragment = new GY_XSBDZ_DYTP();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYTP);
        return new FragmentOption(KEY_GY_XSBDZ_DYTP,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压总空开）</string>
    private FragmentOption getGY_XSBDZ_DYZKK() {
        GY_XSBDZ_DYZKK fragment = new GY_XSBDZ_DYZKK();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYZKK);
        return new FragmentOption(KEY_GY_XSBDZ_DYZKK,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开1）</string>
    private FragmentOption getGY_XSBDZ_DYKK1() {
        GY_XSBDZ_DYZKK1 fragment = new GY_XSBDZ_DYZKK1();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK1);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK1,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开2）</string>
    private FragmentOption getGY_XSBDZ_DYKK2() {
        GY_XSBDZ_DYZKK2 fragment = new GY_XSBDZ_DYZKK2();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK2);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK2,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开3）</string>
    private FragmentOption getGY_XSBDZ_DYKK3() {
        GY_XSBDZ_DYZKK3 fragment = new GY_XSBDZ_DYZKK3();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK3);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK3,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开4）</string>
    private FragmentOption getGY_XSBDZ_DYZKK4() {
        GY_XSBDZ_DYZKK4 fragment = new GY_XSBDZ_DYZKK4();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK4);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK4,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开5）</string>
    private FragmentOption getGY_XSBDZ_DYZKK5() {
        GY_XSBDZ_DYZKK5 fragment = new GY_XSBDZ_DYZKK5();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK5);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK5,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压空开6）</string>
    private FragmentOption getGY_XSBDZ_DYZKK6() {
        GY_XSBDZ_DYZKK6 fragment = new GY_XSBDZ_DYZKK6();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYKK6);
        return new FragmentOption(KEY_GY_XSBDZ_DYKK6,
                fragmentName, "", fragment);
    }

    //">箱式变电站（低压无功补偿装置）</string>
    private FragmentOption getGY_XSBDZ_DYWGBCZZ() {
        GY_XSBDZ_DYWGBCZZ fragment = new GY_XSBDZ_DYWGBCZZ();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_DYWGBCZZ);
        return new FragmentOption(KEY_GY_XSBDZ_DYWGBCZZ,
                fragmentName, "", fragment);
    }

    //">照片
    private FragmentOption getGY_XSBDZ_ZPSJ() {
        GY_XSBDZ_ZPSJ fragment = new GY_XSBDZ_ZPSJ();
        String fragmentName = mResources.getString(R.string.GY_XSBDZ_TITLE_ZPSJ);
        return new FragmentOption(KEY_GY_XSBDZ_ZPSJ,
                fragmentName, "", fragment);
    }


}
