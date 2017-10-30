package com.geocraft.electrics.sr.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.event.CheckFragmentEvent;
import com.geocraft.electrics.sr.FragmentOption;
import com.geocraft.electrics.sr.WellDatasets;
import com.geocraft.electrics.sr.controller.WellController;
import com.geocraft.electrics.sr.fragment.WellMainFragment;
import com.geocraft.electrics.sr.fragment.WellMainFragment_;
import com.geocraft.electrics.sr.fragment.Well_PreFragment;
import com.geocraft.electrics.sr.fragment.Well_PreFragment_;
import com.geocraft.electrics.sr.task.InitWellInfoAsyncTask;
import com.geocraft.electrics.sr.task.WellCommitAsyncTask;
import com.geocraft.electrics.ui.controller.PhotoManagerController;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 井号采集
 *
 * @author kingdon
 */
@EActivity(R.layout.activity_well)
public class WellActivity extends BaseActivity {

    @Bean
    WellController mController;
    @ViewById
    Button btn_back;
    @ViewById
    Button btn_next;

    private FragmentOption mFragmentOption;
    private WellMainFragment mMainFragment;
    private Well_PreFragment mPreFragment;

    @AfterViews
    void init() {
        if (!ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.register(this);
        }
        mController.initIntentData(this);
        InitWellInfoAsyncTask initWellInfoAsyncTask = new InitWellInfoAsyncTask(this,
                mController);
        initWellInfoAsyncTask.execute(mController);
    }

    @Click
    void btn_next() {
        if (!saveFragmentData()) {
            return;
        }
        if (!(boolean) btn_next.getTag()) {
            excuteCommitTask();
        }
        if (mController.getFramgmentIndex() == -2) {
            addMainFragment();
        } else {
            FragmentOption fragmentOption;
            if (mController.getFramgmentIndex() == -1) {
                fragmentOption = mController.getFirstDataFragment();
            } else {
                fragmentOption = mController.getNextCheckedDataFragment();
            }
            changeContentView(fragmentOption);
        }
    }

    @Click
    void btn_back() {
        saveFragmentData();
        int curFragmentIndex = mController.getFramgmentIndex();
        if (curFragmentIndex == 0) {
            addMainFragment();
        } else if (curFragmentIndex == -1) {
            addPreFragment();
        } else {
            FragmentOption fragmentOption = mController.getPreCheckedDataFragment();
            changeContentView(fragmentOption);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onDataSynEvent(CheckFragmentEvent event) {
        updateNextBtnStatus();
    }

    public void initView() {
        this.setTitle(mController.getTitle());
        addPreFragment();
    }

    private boolean changeContentView(FragmentOption fragmentOption) {
        if (null == fragmentOption) {
            return false;
        }
        mFragmentOption = fragmentOption;
        updateFragment(mFragmentOption.getFragment(),
                mFragmentOption.getDatasetName());
        updateViewClickable(btn_back, true);
        updateNextBtnStatus();
        return true;
    }

    public void updateNextBtnStatus() {
        String msg;
        if (!mController.isHasNextDatasetOption()) {
            msg = getResources().getString(R.string.btn_confrim);
            btn_next.setTag(false);//是否可继续
        } else {
            msg = getResources().getString(R.string.btn_next);
            btn_next.setTag(true);
        }
        btn_next.setText(msg);
    }

    public void addPreFragment() {
        mController.setFramgmentIndex(-2);
        mController.setCurrentDataSet(WellDatasets.getMainDatasetName(
                mController.getWellType()));
        mPreFragment = new Well_PreFragment_();
        updateFragment(mPreFragment, WellDatasets.getMainDatasetName(
                mController.getWellType()));
        updateNextBtnStatus();
        updateViewClickable(btn_back, false);
    }

    public void addMainFragment() {
        mController.setFramgmentIndex(-1);
        mController.setCurrentDataSet(WellDatasets.getMainDatasetName(
                mController.getWellType()));
        mMainFragment = new WellMainFragment_();
        updateFragment(mMainFragment, WellDatasets.getMainDatasetName(
                mController.getWellType()));
        updateViewClickable(btn_back, true);
        updateNextBtnStatus();
    }

    private void updateFragment(Fragment fragment, String datasetName) {
        mController.setCurrentDataSet(datasetName);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.id_content, fragment);
        transaction.commit();
    }

    private void updateViewClickable(View view, boolean isEnable) {
        view.setEnabled(isEnable);
        view.setClickable(isEnable);
    }

    private boolean saveFragmentData() {
        if (mController.getFramgmentIndex() == -2) {
            return excuteSaveAction(mController.getCurrentDataSet(), mPreFragment);
        } else if (mController.getFramgmentIndex() == -1) {
            mMainFragment.getValue(mController.getCurrentDataSet());
        } else {
            return getValueFromFragment();
        }
        return true;
    }

    private boolean getValueFromFragment() {
        if (null == mFragmentOption || !mFragmentOption.isChecked()) {
            return false;
        }
        DataSet dataSet = mController.getCurrentDataSet(
                mFragmentOption.getDatasetName());
        if (null == dataSet) {
            return false;
        }
        return excuteSaveAction(dataSet, mFragmentOption.getFragment());
    }

    private boolean excuteSaveAction(DataSet dataSet, BusinessFragment fragment) {
        if (!fragment.logicCheck()) {
            return false;
        }
        fragment.getValue(dataSet);
        return true;
    }

    public List<PhotoManagerController.PhotoItemInfo> getPhotoInfoList() {
        List<PhotoManagerController.PhotoItemInfo> photoItemInfoList = new ArrayList<>();
//        if (mPhotoFragment != null) {
//            photoItemInfoList = mPhotoFragment.getTaskPhotoList();
//        }
        return photoItemInfoList;
    }

    private void excuteCommitTask() {
        WellCommitAsyncTask commitAsyncTask = new WellCommitAsyncTask(this, mController);
        commitAsyncTask.execute(mController);
    }

    public WellController getController() {
        return mController;
    }

    @Override
    protected void onDestroy() {
        if (ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.unregister(this);
        }
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
