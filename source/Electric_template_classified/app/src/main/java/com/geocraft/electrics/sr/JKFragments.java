package com.geocraft.electrics.sr;

import android.content.res.Resources;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment;
import com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_BG;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_BGXJ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_BGXJ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_BG_;
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
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GLDZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GLDZ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GLKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GLKG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GTLB;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GTLB_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GZZSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_GZZSQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_HD;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_HD_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_JKBLZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_JKBLZ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_JYZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_JYZ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KP;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KP_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KSRDQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KSRDQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KYQK;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_KYQK_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_LX;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_LXZZ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_LXZZ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_LX_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_NZXJ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_NZXJ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_SFKYFW;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_SFKYFW_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_SKQK;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_SKQK_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_TGXLHS;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_TGXLHS_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_XJ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_XJ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_XLBLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.JTXL_fragment.GY_JTXL_XLBLQ_;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 可选项frament管理
 */
@EBean
public class JKFragments {

    public final static String KEY_GY_JKXLTZXX_BASE = "GY_JKXLTZXX_BASE";
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
    private final static String KEY_GY_JTXL_PHOTO = "GY_JTXL_PHOTO";
    private final static String KEY_GY_JTXL_F_GTLB= "GY_DLXLTZXX_F_GTLB";//杆塔类别</string>
    private final static String KEY_GY_JTXL_F_DP= "GY_DLXLTZXX_F_DP";//底（卡）盘</string>
    private final static String KEY_GY_JTXL_F_JYZ= "GY_DLXLTZXX_F_JYZ";//绝缘子</string>
    private final static String KEY_GY_JTXL_F_HD= "GY_DLXLTZXX_F_HD";//横担</string>
    private final static String KEY_GY_JTXL_F_BG= "GY_DLXLTZXX_F_BG";//抱箍</string>
    private final static String KEY_GY_JTXL_F_DLQ= "GY_DLXLTZXX_F_DLQ";//断路器</string>
    private final static String KEY_GY_JTXL_F_GLDZ= "GY_DLXLTZXX_F_GLDZ";//隔离道闸</string>
    private final static String KEY_GY_JTXL_F_XLBLQ= "GY_DLXLTZXX_F_XLBLQ";//线路避雷器</string>
    private final static String KEY_GY_JTXL_F_GZZSQ= "GY_DLXLTZXX_F_GZZSQ";//故障指示器</string>
    private final static String KEY_GY_JTXL_F_XJ= "GY_DLXLTZXX_F_XJ";//线夹</string>
    private final static String KEY_GY_JTXL_F_BYQ= "GY_DLXLTZXX_F_BYQ";//变压器</string>
    private final static String KEY_GY_JTXL_F_LXZZ= "GY_DLXLTZXX_F_LXZZ";//拉线装置</string>


    private List<FragmentOption> mJKXLFragments = new ArrayList<FragmentOption>();//架空线路
    private Resources mResources = ElectricApplication_.getInstance().
            getApplicationContext().getResources();


    public List<FragmentOption> getJKFramentItems() {
        if (null != mJKXLFragments && mJKXLFragments.size() > 0) {
            return mJKXLFragments;
        }
        mJKXLFragments.add(generateGY_JKXI_F_GTLB());
        //底（卡）盘</string>
        mJKXLFragments.add(generateGY_JKXI_F_KP());
        //绝缘子</string>
        mJKXLFragments.add(generateGY_JKXI_F_JYZ());

        //横担</string>
        mJKXLFragments.add(generateGY_JKXI_F_HD());

        //抱箍</string>
        mJKXLFragments.add(generateGY_JKXI_F_BG());

        //断路器</string>
        mJKXLFragments.add(generateGY_JKXI_F_DLQ());

        //隔离道闸</string>
        mJKXLFragments.add(generateGY_JKXI_F_GLDZ());

        //线路避雷器</string>
        mJKXLFragments.add(generateGY_JKXI_F_XLBLQ());

        //故障指示器</string>
       mJKXLFragments.add(generateGY_JKXI_F_GZZSQ());

        //线夹</string>
       mJKXLFragments.add(generateGY_JKXI_F_XJ());

        //变压器</string>
        mJKXLFragments.add(generateGY_JKXI_F_BYQ());

        //拉线装置</string>
        mJKXLFragments.add(generateGY_JKXI_F_LXZZ());



//        //mJKXLFragments.add(generateGY_JTXL_Base());
//        mJKXLFragments.add(generateGY_JTXL_PHOTO());
//        mJKXLFragments.add(generateGY_JTXL_BGXJ());
//        mJKXLFragments.add(generateGY_JTXL_BYQ());
//        mJKXLFragments.add(generateGY_JTXL_DLQ());
//        mJKXLFragments.add(generateGY_JTXL_DX());
//        mJKXLFragments.add(generateGY_JTXL_FHKG());
//        mJKXLFragments.add(generateGY_JTXL_GLKG());
//        mJKXLFragments.add(generateGY_JTXL_GZZSQ());
//        mJKXLFragments.add(generateGY_JTXL_HD());
//        mJKXLFragments.add(generateGY_JTXL_JKBLZ());
//        mJKXLFragments.add(generateGY_JTXL_JYZ());
//        mJKXLFragments.add(generateGY_JTXL_KSRDQ());
//        mJKXLFragments.add(generateGY_JTXL_KYQK());
//        mJKXLFragments.add(generateGY_JTXL_LX());
//        mJKXLFragments.add(generateGY_JTXL_NZXJ());
//        mJKXLFragments.add(generateGY_JTXL_SFKYFW());
//        mJKXLFragments.add(generateGY_JTXL_SKQK());
//        mJKXLFragments.add(generateGY_JTXL_TGXLHS());
//        mJKXLFragments.add(generateGY_JTXL_XLBLQ());
        for (int i = 0; i < mJKXLFragments.size(); i++) {
            FragmentOption option = mJKXLFragments.get(i);
            option.setDatasetName(Enum.GY_JKXLTZXX);
            if (CommonFragment.isBaseFragment(option.getNameKey())) {
                option.setChecked(true);
            }
        }
        return mJKXLFragments;
    }


    public void initJKFramentDtatas(DataSet dataset) {
        getJKFramentItems();
        String checkedFragmentkeyValue = dataset.
                GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_COLLECTOBJECT);
        CommonFragment.initCheckedFragmentkeyValue(checkedFragmentkeyValue, mJKXLFragments);
    }


    // =======================================jk fragment 生成============================
    //杆塔类别</string>
    private FragmentOption generateGY_JKXI_F_GTLB() {
        GY_JTXL_GTLB fragment = new GY_JTXL_GTLB_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_GTLB);
        return new FragmentOption(KEY_GY_JTXL_F_GTLB,
                fragmentName, "", fragment);
    }

    //底（卡）盘</string>
    private FragmentOption generateGY_JKXI_F_KP() {
        GY_JTXL_KP fragment = new GY_JTXL_KP_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_DP);
        return new FragmentOption(KEY_GY_JTXL_F_DP,
                fragmentName, "", fragment);
    }

    //绝缘子</string>
    private FragmentOption generateGY_JKXI_F_JYZ() {
        GY_JTXL_JYZ fragment = new GY_JTXL_JYZ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_JYZ);
        return new FragmentOption(KEY_GY_JTXL_F_JYZ,
                fragmentName, "", fragment);
    }

    //横担</string>
    private FragmentOption generateGY_JKXI_F_HD() {
        GY_JTXL_HD fragment = new GY_JTXL_HD_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_HD);
        return new FragmentOption(KEY_GY_JTXL_F_HD,
                fragmentName, "", fragment);
    }

    //抱箍</string>
    private FragmentOption generateGY_JKXI_F_BG() {
        GY_JTXL_BG fragment = new GY_JTXL_BG_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_BG);
        return new FragmentOption(KEY_GY_JTXL_F_BG,
                fragmentName, "", fragment);
    }

    //断路器</string>
    private FragmentOption generateGY_JKXI_F_DLQ() {
        GY_JTXL_DLQ fragment = new GY_JTXL_DLQ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_DLQ);
        return new FragmentOption(KEY_GY_JTXL_F_DLQ,
                fragmentName, "", fragment);
    }

    //隔离道闸</string>
    private FragmentOption generateGY_JKXI_F_GLDZ() {
        GY_JTXL_GLDZ fragment = new GY_JTXL_GLDZ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_GLDZ);
        return new FragmentOption(KEY_GY_JTXL_F_GLDZ,
                fragmentName, "", fragment);
    }

    //线路避雷器</string>
    private FragmentOption generateGY_JKXI_F_XLBLQ() {
        GY_JTXL_XLBLQ fragment = new GY_JTXL_XLBLQ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_XLBLQ);
        return new FragmentOption(KEY_GY_JTXL_F_XLBLQ,
                fragmentName, "", fragment);
    }

    //故障指示器</string>
    private FragmentOption generateGY_JKXI_F_GZZSQ() {
        GY_JTXL_GZZSQ fragment = new GY_JTXL_GZZSQ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_GZZSQ);
        return new FragmentOption(KEY_GY_JTXL_F_GZZSQ,
                fragmentName, "", fragment);
    }

    //线夹</string>
    private FragmentOption generateGY_JKXI_F_XJ() {
        GY_JTXL_XJ fragment = new GY_JTXL_XJ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_XJ);
        return new FragmentOption(KEY_GY_JTXL_F_XJ,
                fragmentName, "", fragment);
    }

    //变压器</string>
    private FragmentOption generateGY_JKXI_F_BYQ() {
        GY_JTXL_BYQ fragment = new GY_JTXL_BYQ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_BYQ);
        return new FragmentOption(KEY_GY_JTXL_F_BYQ,
                fragmentName, "", fragment);
    }

    //拉线装置</string>
    private FragmentOption generateGY_JKXI_F_LXZZ() {
        GY_JTXL_LXZZ fragment = new GY_JTXL_LXZZ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_F_LXZZ);
        return new FragmentOption(KEY_GY_JTXL_F_LXZZ,
                fragmentName, "", fragment);
    }


    private FragmentOption generateGY_JTXL_PHOTO() {
        SrPhotoManagerFragment fragment = new SrPhotoManagerFragment_();
        String fragmentName = mResources.getString(R.string.well_jk_photo_title);
        return new FragmentOption(KEY_GY_JTXL_PHOTO,
                fragmentName, "", fragment);
    }

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


}
