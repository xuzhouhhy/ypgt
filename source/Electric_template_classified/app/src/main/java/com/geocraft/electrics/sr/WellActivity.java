package com.geocraft.electrics.sr;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.fragment.WellMainFragment;
import com.geocraft.electrics.sr.fragment.WellMainFragment_;

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
public class WellActivity extends BaseActivity {

    @Bean
    WellController mController;
    @ViewById
    Button btn_back;
    @ViewById
    Button btn_next;

    private FragmentManager mFm = null;
    private FragmentTransaction mTransaction = null;
    private int mFragemntIndex;
    private BusinessFragment mBasicDataFragment;
    private BasicFragmentFactory.DataFragment mDataFragment;
    private WellMainFragment mWellMainFragment;
    private boolean mIsNext;

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
        mIsNext = true;
        if (changeContentView(mFragemntIndex)) {
            mFragemntIndex++;
        }
    }

    @Click
    void btn_back() {
        mIsNext = false;
        mFragemntIndex = mFragemntIndex - 2;
        if (mFragemntIndex < 0) {
            addMainFragment();
        } else {
            if (!changeContentView(mFragemntIndex)) {
                mFragemntIndex++;
            }
        }
        if (mFragemntIndex < 0) {
            mFragemntIndex = 0;
        }
    }

    public void initView() {
        this.setTitle(mController.getTitle());
    }

    private boolean changeContentView(int index) {
        if (index < 0) {
            return false;
        }
        saveFragmentData();
        mDataFragment = mController.getDataFragment(index);
        if (null == mDataFragment) {
            return false;
        }
        mController.setsCurrentDataSet(mDataFragment.mDatasetName);
        mBasicDataFragment = mDataFragment.mFragment;
        updateFrament(mBasicDataFragment);
        updateBtnViewStatus(btn_back, true);
        return true;
    }

    public void addMainFragment() {
        mWellMainFragment = new WellMainFragment_();
        updateFrament(mWellMainFragment);
        updateBtnViewStatus(btn_back, false);
    }

    private void updateFrament(Fragment fragment) {
        mFm = getSupportFragmentManager();
        mTransaction = mFm.beginTransaction();
        mTransaction.replace(R.id.id_content, fragment);
        mTransaction.commit();
    }

    private void updateBtnViewStatus(Button button, boolean isEnable) {
        button.setEnabled(isEnable);
        button.setClickable(isEnable);
    }

    private void saveFragmentData() {
        if (mFragemntIndex == 0 && mIsNext) {
            mWellMainFragment.getValue();
        } else {
            if (mDataFragment != null) {
                getValueFromFragment();
            }
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

    public WellController getController() {
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

    public boolean isNext() {
        return mIsNext;
    }
}
