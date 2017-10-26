package com.geocraft.electrics.ui.activity.andvance;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.advance.HWGBasicFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import java.util.HashMap;
import java.util.Map;

@EFragment(R.layout.activity_tower_fragment)
public class mainFragment extends Fragment {
    private FragmentTransaction mTransaction;
    private FragmentManager mFm;
    private Map<Integer, BusinessFragment> mapFMs =
            new HashMap<Integer, BusinessFragment>();
    private int mIndex = 0;

    @AfterViews
    protected void init() {
    }

    private boolean changeFragment(int index) {
        mFm = getFragmentManager();
        mTransaction = mFm.beginTransaction();
        BusinessFragment fragment = mapFMs.get(index);

        if (null == fragment) {
            return false;
        }
        mTransaction.hide(this);
        mTransaction.add(R.id.id_content, fragment);
        mTransaction.addToBackStack(null);
        mTransaction.commit();
        return true;
    }

    private void nextFragment() {
        if (changeFragment(mIndex)) {
            mIndex++;
        }
    }

    private void saveFragementValue() {
        // TODO: 2017/10/25
    }
}
