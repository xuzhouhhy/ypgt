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

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 可选项frament管理
 */
@EBean
public class BasicFragmentFactory {
    private final static String NAME_KEY_MARK = "&";
    private final String KEY_GY_JKXLTZXX_BASE = "GY_JKXLTZXX_BASE";
    private final String KEY_GY_JTXL_BGXJ = "GY_JTXL_BGXJ";
    private final String KEY_GY_JTXL_BYQ = "GY_JTXL_BYQ";
    private final String KEY_GY_JTXL_DLQ = "GY_JTXL_DLQ";
    private final String KEY_GY_JTXL_DX = "GY_JTXL_DX";
    private final String KEY_GY_JTXL_FHKG = "GY_JTXL_FHKG";
    private final String KEY_GY_JTXL_GLKG = "GY_JTXL_GLKG";
    private final String KEY_GY_JTXL_GZZSQ = "GY_JTXL_GZZSQ";
    private final String KEY_GY_JTXL_HD = "GY_JTXL_HD";
    private final String KEY_GY_JTXL_JKBLZ = "GY_JTXL_JKBLZ";
    private final String KEY_GY_JTXL_JYZ = "GY_JTXL_JYZ";
    private final String KEY_GY_JTXL_KSRDQ = "GY_JTXL_KSRDQ";
    private final String KEY_GY_JTXL_KYQK = "GY_JTXL_KYQK";
    private final String KEY_GY_JTXL_LX = "GY_JTXL_LX";
    private final String KEY_GY_JTXL_NZXJ = "GY_JTXL_NZXJ";
    private final String KEY_GY_JTXL_SFKYFW = "GY_JTXL_SFKYFW";
    private final String KEY_GY_JTXL_SKQK = "GY_JTXL_SKQK";
    private final String KEY_GY_JTXL_TGXLHS = "GY_JTXL_TGXLHS";
    private final String KEY_GY_JTXL_XLBLQ = "GY_JTXL_XLBLQ";
    //DL
    private final String KEY_GY_DLXL_Base = "GY_DLXL_Base";
    private final String KEY_GY_DLXL_DJX = "GY_DLXL_DJX";
    private final String KEY_GY_DLXL_DLJ = "GY_DLXL_DLJ";
    private final String KEY_GY_DLXL_DLZJT = "GY_DLXL_DLZJT";
    private final String KEY_GY_DLXL_FZX = "GY_DLXL_FZX";
    private final String KEY_GY_DLXL_GXJBXX = "GY_DLXL_GXJBXX";
    private final String KEY_GY_DLXL_GZZSQ = "GY_DLXL_GZZSQ";
    private final String KEY_GY_DLXL_HWG = "GY_DLXL_HWG";
    private final String KEY_GY_DLXL_KYQK = "GY_DLXL_KYQK";
    private final String KEY_GY_DLXL_SKQK = "GY_DLXL_SKQK";
    private final String KEY_GY_DLXL_TSPDBYQ = "GY_DLXL_TSPDBYQ";
    private final String KEY_GY_DLXL_XSBDZ = "GY_DLXL_XSBDZ";

    private List<FragmentOption> mJKXLFragments = new ArrayList<FragmentOption>();//架空线路
    private List<FragmentOption> mDLXLFragments = new ArrayList<FragmentOption>();//电缆线路
    private Resources mResources = ElectricApplication_.getInstance().
            getApplicationContext().getResources();

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
        mJKXLFragments.add(generateGY_JTXL_Base());
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
        }
        return mJKXLFragments;
    }

    public List<FragmentOption> getDLFramentItems() {
        if (null != mDLXLFragments && mDLXLFragments.size() > 0) {
            return mDLXLFragments;
        }
        mDLXLFragments.add(getGY_DLXL_Base());
        mDLXLFragments.add(getGY_DLXL_DJX());
        mDLXLFragments.add(getGY_DLXL_DLJ());
        mDLXLFragments.add(getGY_DLXL_DLZJT());
        mDLXLFragments.add(getGY_DLXL_FZX());
        mDLXLFragments.add(getGY_DLXL_GXJBXX());
        mDLXLFragments.add(getGY_DLXL_GZZSQ());
        mDLXLFragments.add(getGY_DLXL_HWG());
        mDLXLFragments.add(getGY_DLXL_KYQK());
        mDLXLFragments.add(getGY_DLXL_SKQK());
        mDLXLFragments.add(getGY_DLXL_TSPDBYQ());
        mDLXLFragments.add(getGY_DLXL_XSBDZ());

        for (int i = 0; i < mDLXLFragments.size(); i++) {
            FragmentOption option = mDLXLFragments.get(i);
            option.setDatasetName(Enum.GY_DLXLTZXX);
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
        if (null == keyArry) {
            return;
        }
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
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JKXLTZXX_BASE,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_BGXJ() {
        GY_JTXL_BGXJ fragment = new GY_JTXL_BGXJ_();
        String fragmentName = mResources.getString(R.string.GY_JKXI_BGXJ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_BGXJ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_BYQ() {
        GY_JTXL_BYQ fragment = new GY_JTXL_BYQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_BYQ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_BYQ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_DLQ() {
        GY_JTXL_DLQ fragment = new GY_JTXL_DLQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_DLQ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_DLQ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_DX() {
        GY_JTXL_DX fragment = new GY_JTXL_DX_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_DX);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_DX,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_FHKG() {
        GY_JTXL_FHKG fragment = new GY_JTXL_FHKG_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_FHKG);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_FHKG,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_GLKG() {
        GY_JTXL_GLKG fragment = new GY_JTXL_GLKG_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_GLKG);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_GLKG,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_GZZSQ() {
        GY_JTXL_GZZSQ fragment = new GY_JTXL_GZZSQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_GZZSQ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_GZZSQ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_HD() {
        GY_JTXL_HD fragment = new GY_JTXL_HD_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_HD);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_HD,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_JKBLZ() {
        GY_JTXL_JKBLZ fragment = new GY_JTXL_JKBLZ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_JKBLZ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_JKBLZ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_JYZ() {
        GY_JTXL_JYZ fragment = new GY_JTXL_JYZ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_JYZ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_JYZ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_KSRDQ() {
        GY_JTXL_KSRDQ fragment = new GY_JTXL_KSRDQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_KSRDQ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_KSRDQ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_KYQK() {
        GY_JTXL_KYQK fragment = new GY_JTXL_KYQK_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_KYQK);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_KYQK,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_LX() {
        GY_JTXL_LX fragment = new GY_JTXL_LX_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_LX);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_LX,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_NZXJ() {
        GY_JTXL_NZXJ fragment = new GY_JTXL_NZXJ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_NZXJ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_NZXJ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_SFKYFW() {
        GY_JTXL_SFKYFW fragment = new GY_JTXL_SFKYFW_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_SFKYFW);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_SFKYFW,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_SKQK() {
        GY_JTXL_SKQK fragment = new GY_JTXL_SKQK_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_SKQK);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_SKQK,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_TGXLHS() {
        GY_JTXL_TGXLHS fragment = new GY_JTXL_TGXLHS_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_TGXLHS);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_TGXLHS,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption generateGY_JTXL_XLBLQ() {
        GY_JTXL_XLBLQ fragment = new GY_JTXL_XLBLQ_();
        String fragmentName = mResources.getString(R.string.GY_JTXL_XLBLQ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_JTXL_XLBLQ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    // =======================================DL: fragment 生成============================

    private FragmentOption getGY_DLXL_Base() {
        GY_DLXL_Base fragment = new GY_DLXL_Base_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_Base);
        FragmentOption fragmentOption = new FragmentOption(
                KEY_GY_DLXL_Base, fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_DJX() {
        GY_DLXL_DJX fragment = new GY_DLXL_DJX_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_DJX);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_DJX,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_DLJ() {
        GY_DLXL_DLJ fragment = new GY_DLXL_DLJ_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_DLJ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_DLJ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_DLZJT() {
        GY_DLXL_DLZJT fragment = new GY_DLXL_DLZJT_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_DLZJT);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_DLZJT,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_FZX() {
        GY_DLXL_FZX fragment = new GY_DLXL_FZX_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_FZX);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_FZX,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_GXJBXX() {
        GY_DLXL_GXJBXX fragment = new GY_DLXL_GXJBXX_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_GXJBXX);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_GXJBXX,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_GZZSQ() {
        GY_DLXL_GZZSQ fragment = new GY_DLXL_GZZSQ_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_GZZSQ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_GZZSQ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_HWG() {
        GY_DLXL_HWG fragment = new GY_DLXL_HWG_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_HWG);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_HWG,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_KYQK() {
        GY_DLXL_KYQK fragment = new GY_DLXL_KYQK_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_KYQK);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_KYQK,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_SKQK() {
        GY_DLXL_SKQK fragment = new GY_DLXL_SKQK_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_SKQK);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_SKQK,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_TSPDBYQ() {
        GY_DLXL_TSPDBYQ fragment = new GY_DLXL_TSPDBYQ_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_TSPDBYQ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_TSPDBYQ,
                fragmentName, "", fragment);
        return fragmentOption;
    }

    private FragmentOption getGY_DLXL_XSBDZ() {
        GY_DLXL_XSBDZ fragment = new GY_DLXL_XSBDZ_();
        String fragmentName = mResources.getString(R.string.GY_DLXL_XSBDZ);
        FragmentOption fragmentOption = new FragmentOption(KEY_GY_DLXL_XSBDZ,
                fragmentName, "", fragment);
        return fragmentOption;
    }
}
