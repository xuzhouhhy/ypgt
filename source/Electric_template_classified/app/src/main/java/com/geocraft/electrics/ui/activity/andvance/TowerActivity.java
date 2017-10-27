package com.geocraft.electrics.ui.activity.andvance;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.advance.TowerMainFragment;
import com.geocraft.electrics.ui.fragment.business_basic_fragment.advance.TowerMainFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

/**
 */
@EActivity(R.layout.activity_tower)
@OptionsMenu(R.menu.menu_new_task)
public class TowerActivity extends BaseActivity {

    @Bean
    TowerController mController;
    @ViewById
    Button btn_back;
    @ViewById
    Button btn_next;

    private FragmentManager mFm = null;
    private FragmentTransaction mTransaction = null;
    private int mFragemntIndex;
    private BusinessFragment mBasicDataFragment;
    private BasicFragmentFactory.DataFragment mDataFragment;

    @AfterViews
    void init() {
        mController.initIntentData(this);
        InitTowerInfoAsyncTask initRecordInfoAsyncTask = new InitTowerInfoAsyncTask(this,
                mController);
        initRecordInfoAsyncTask.execute(mController);
        updateBtnViewStatus(btn_back, false);
    }

    @Click
    void btn_next() {
        mFragemntIndex++;
        changeFragment(mFragemntIndex);
    }

    @Click
    void btn_back() {
        mFragemntIndex--;
        changeFragment(mFragemntIndex);
    }

    public void initView() {
        this.setTitle(mController.getTitle());
        changeFragment(mFragemntIndex);
    }

    private void changeFragment(int index) {
        if (index < 0) {
            return;
        }
        saveFragmentData();
        mFm = getSupportFragmentManager();
        mTransaction = mFm.beginTransaction();
        mDataFragment = mController.getDataFragment(index);
        if (null == mDataFragment) {
            return;
        }
        mBasicDataFragment = mDataFragment.mFragment;
        if (!mBasicDataFragment.isAdded()) {
            mTransaction.add(R.id.id_content, mBasicDataFragment);
        } else {
            mTransaction.replace(R.id.id_content, mBasicDataFragment);
        }
        mTransaction.commit();
        updateBtnViewStatus(btn_back, true);
    }

    public void initMainFragment() {
        mFm = getSupportFragmentManager();
        mTransaction = mFm.beginTransaction();
        TowerMainFragment towerMainFragment = new TowerMainFragment_();
        if (!towerMainFragment.isAdded()) {
            mTransaction.add(R.id.id_content, towerMainFragment);
        } else {
            mTransaction.replace(R.id.id_content, towerMainFragment);
        }
        mTransaction.commit();
    }

    private void updateBtnViewStatus(Button button, boolean IsUsed) {
        button.setEnabled(false);
        button.setClickable(false);
    }

    private void saveFragmentData() {
        if (mDataFragment != null) {
            mController.setsCurrentDataSet(mDataFragment.mDatasetName);
            getValueFromFragment();
        }
    }

    private void getValueFromFragment() {
        if (null == mDataFragment || null == mBasicDataFragment) {
            return;
        }
        DataSet dataSet = mController.getCurrentDataSet(mDataFragment.mDatasetName);
        if (null == dataSet) {
            return;
        }
        mBasicDataFragment.getValue(dataSet);
    }

    @OptionsItem
    void actionTaskCommit() {
        // TODO: 2017/10/25
    }

    public TowerController getController() {
        return mController;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ConstRequestCode.REQUEST_CODE_FINISH_RECORD_ACTIVITY: {
                this.finish();
                break;
            }
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
