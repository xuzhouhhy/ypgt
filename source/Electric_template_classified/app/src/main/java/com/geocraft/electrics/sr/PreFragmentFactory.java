package com.geocraft.electrics.sr;

import android.content.res.Resources;

import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.sr.fragment.WellMainFragment;
import com.geocraft.electrics.sr.fragment.WellMainFragment_;
import com.geocraft.electrics.sr.fragment.Well_PreFragment;
import com.geocraft.electrics.sr.fragment.Well_PreFragment_;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 可选项frament管理
 */
@EBean
public class PreFragmentFactory {
    private final static String KEY_WELL_MAIN = "WELL_MAIN";
    private final static String KEY_WELL_PRE = "WELL_PRE";
    private int mFramgmentIndex;
    private List<FragmentOption> mFragmentOptions = new ArrayList<FragmentOption>();
    private Resources mResources = ElectricApplication_.getInstance().
            getApplicationContext().getResources();

    public List<FragmentOption> initPreFramentItems() {
        if (null != mFragmentOptions && mFragmentOptions.size() > 0) {
            return mFragmentOptions;
        }
        mFragmentOptions.add(generate_WELL_PRE());
        mFragmentOptions.add(generate_WELL_MAIN());
        return mFragmentOptions;
    }

    private FragmentOption generate_WELL_PRE() {
        Well_PreFragment fragment = new Well_PreFragment_();
        String fragmentName = "";
        return new FragmentOption(KEY_WELL_PRE, fragmentName, "", fragment);
    }

    private FragmentOption generate_WELL_MAIN() {
        WellMainFragment fragment = new WellMainFragment_();
        String fragmentName = "";
        return new FragmentOption(KEY_WELL_MAIN, fragmentName, "", fragment);
    }

    public FragmentOption getFirsFragment() {
        int index = 0;
        FragmentOption fragmentOption = getFragmentOption(index);
        if (fragmentOption != null) {
            return fragmentOption;
        }
        return null;
    }

    public FragmentOption getNextCheckedFragment() {
        int index = mFramgmentIndex + 1;
        FragmentOption fragmentOption = getFragmentOption(index);
        if (fragmentOption != null) {
            mFramgmentIndex = index;
            return fragmentOption;
        }
        return null;
    }

    public FragmentOption getPreCheckedFragment() {
        int index = mFramgmentIndex - 1;
        FragmentOption fragmentOption = getFragmentOption(index);
        if (fragmentOption != null) {
            mFramgmentIndex = index;
            return fragmentOption;
        }
        return null;
    }

    public FragmentOption getFragmentOption(int framgmentIndex) {
        if (framgmentIndex < 0 || framgmentIndex > mFragmentOptions.size() - 1) {
            return null;
        }
        return mFragmentOptions.get(framgmentIndex);
    }

    public boolean isHasNextFragmentOption() {
        return mFramgmentIndex < mFragmentOptions.size() - 1;
    }

    public int getFramgmentIndex() {
        return mFramgmentIndex;
    }

    public void setNextFramgmentIndexOfNext() {
        mFramgmentIndex = mFragmentOptions.size();
    }
}