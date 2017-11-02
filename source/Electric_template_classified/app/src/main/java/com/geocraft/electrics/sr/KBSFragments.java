package com.geocraft.electrics.sr;

import android.content.res.Resources;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_BLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_BLQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_DDXSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_DDXSQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_DLHGQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_DLHGQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_DLQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_DLQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_DYHGQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_DYHGQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_GLKG;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_GLKG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_GYG;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_GYG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_GZZSQ;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_GZZSQ_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_JG;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_JG_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_OTHER;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_OTHER_;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_PHOTO;
import com.geocraft.electrics.ui.fragment.GY_fragment.KBS_fragment.GY_KBS_PHOTO_;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 可选项frament管理
 */
@EBean
public class KBSFragments {
    //KBS
    private final static String KEY_GY_KBS_BLQ = "GY_KBS_BLQ";
    private final static String KEY_GY_KBS_DDXSQ = "GY_KBS_DDXSQ";
    private final static String KEY_GY_KBS_DLHGQ = "GY_KBS_DLHGQ";
    private final static String KEY_GY_KBS_DLQ = "GY_KBS_DLQ";
    private final static String KEY_GY_KBS_DYHGQ = "GY_KBS_DYHGQ";
    private final static String KEY_GY_KBS_GLKG = "GY_KBS_GLKG";
    private final static String KEY_GY_KBS_GYG = "GY_KBS_GYG";
    private final static String KEY_GY_KBS_GZZSQ = "GY_KBS_GZZSQ";
    private final static String KEY_GY_KBS_JG = "GY_KBS_JG";
    private final static String KEY_GY_KBS_OTHER = "GY_KBS_OTHER";
    private final static String KEY_GY_KBS_PHOTO = "GY_KBS_PHOTO";

    private List<FragmentOption> mKBSragments = new ArrayList<FragmentOption>();//开闭所
    private Resources mResources = ElectricApplication_.getInstance().
            getApplicationContext().getResources();


    public List<FragmentOption> getKBSFramentItems() {
        if (null != mKBSragments && mKBSragments.size() > 0) {
            return mKBSragments;
        }
        mKBSragments.add(generateGY_KBS_BLQ());
        mKBSragments.add(generateGY_KBS_DDXSQ());
        mKBSragments.add(generateGY_KBS_DLHGQ());
        mKBSragments.add(generateGY_KBS_DLQ());
        mKBSragments.add(generateGY_KBS_DYHGQ());
        mKBSragments.add(generateGY_KBS_GLKG());
        mKBSragments.add(generateGY_KBS_GYG());
        mKBSragments.add(generateGY_KBS_GZZSQ());
        mKBSragments.add(generateGY_KBS_JG());
        mKBSragments.add(generateGY_KBS_OTHER());
        mKBSragments.add(generateGY_KBS_PHOTO());

        for (int i = 0; i < mKBSragments.size(); i++) {
            FragmentOption option = mKBSragments.get(i);
            option.setDatasetName(Enum.GY_DLXLTZXX);
            if (CommonFragment.isBaseFragment(option.getNameKey())) {
                option.setChecked(true);
            }
        }
        return mKBSragments;
    }


    public void initKBSFramentDtatas(DataSet dataset) {
        getKBSFramentItems();
        String checkedFragmentkeyValue = dataset.
                GetFieldValueByName(Enum.GY_JKXLTZXX_FIELD_COLLECTOBJECT);
        CommonFragment.initCheckedFragmentkeyValue(checkedFragmentkeyValue, mKBSragments);
    }


    // =======================================fragment 生成============================
    private FragmentOption generateGY_KBS_BLQ() {
        GY_KBS_BLQ fragment = new GY_KBS_BLQ_();
        String fragmentName = mResources.getString(R.string.GY_KBS_BLQ);
        return new FragmentOption(KEY_GY_KBS_BLQ, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_DDXSQ() {
        GY_KBS_DDXSQ fragment = new GY_KBS_DDXSQ_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_DDXSQ);
        return new FragmentOption(KEY_GY_KBS_DDXSQ, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_DLHGQ() {
        GY_KBS_DLHGQ fragment = new GY_KBS_DLHGQ_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_DLHGQ);
        return new FragmentOption(KEY_GY_KBS_DLHGQ, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_DLQ() {
        GY_KBS_DLQ fragment = new GY_KBS_DLQ_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_DLQ);
        return new FragmentOption(KEY_GY_KBS_DLQ, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_DYHGQ() {
        GY_KBS_DYHGQ fragment = new GY_KBS_DYHGQ_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_DYHGQ);
        return new FragmentOption(KEY_GY_KBS_DYHGQ, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_GLKG() {
        GY_KBS_GLKG fragment = new GY_KBS_GLKG_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_GLKG);
        return new FragmentOption(KEY_GY_KBS_GLKG, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_GYG() {
        GY_KBS_GYG fragment = new GY_KBS_GYG_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_GYG);
        return new FragmentOption(KEY_GY_KBS_GYG, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_GZZSQ() {
        GY_KBS_GZZSQ fragment = new GY_KBS_GZZSQ_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_GZZSQ);
        return new FragmentOption(KEY_GY_KBS_GZZSQ, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_JG() {
        GY_KBS_JG fragment = new GY_KBS_JG_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_JG);
        return new FragmentOption(KEY_GY_KBS_JG, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_OTHER() {
        GY_KBS_OTHER fragment = new GY_KBS_OTHER_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_other);
        return new FragmentOption(KEY_GY_KBS_OTHER, fragmentName, "", fragment);
    }

    private FragmentOption generateGY_KBS_PHOTO() {
        SrPhotoManagerFragment fragment = new
                com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment_();
        String fragmentName = mResources.getString(R.string.GY_KBS_TITLE_photo);
        return new FragmentOption(KEY_GY_KBS_PHOTO, fragmentName, "", fragment);
    }

}
