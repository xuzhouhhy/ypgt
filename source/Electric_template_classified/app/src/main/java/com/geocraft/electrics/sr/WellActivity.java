package com.geocraft.electrics.sr;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.event.CheckFragmentEvent;
import com.geocraft.electrics.sr.fragment.WellMainFragment;
import com.geocraft.electrics.sr.fragment.WellMainFragment_;
import com.geocraft.electrics.sr.task.InitWellInfoAsyncTask;
import com.geocraft.electrics.sr.task.WellCommitAsyncTask;
import com.geocraft.electrics.ui.controller.PhotoManagerController;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
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
    private BasicFragmentFactory.FragmentDatasetOption mFragmentDatasetOption;
    private WellMainFragment mWellMainFragment;
    private boolean mIsNext;

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
        mIsNext = true;
        saveFragmentData();
        BasicFragmentFactory.FragmentDatasetOption fragmentDatasetOption = null;
        if (mController.getFramgmentIndex() == -1) {
            fragmentDatasetOption = mController.getFirstDataFragment();
        } else {
            fragmentDatasetOption = mController.getNextCheckedDataFragment();
        }
        changeContentView(fragmentDatasetOption);
    }

    @Click
    void btn_back() {
        mIsNext = false;
        saveFragmentData();
        BasicFragmentFactory.FragmentDatasetOption fragmentDatasetOption =
                mController.getPreCheckedDataFragment();
        if (mController.getFramgmentIndex() == -1 || null == fragmentDatasetOption) {
            addMainFragment();
        } else {
            changeContentView(fragmentDatasetOption);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onDataSynEvent(CheckFragmentEvent event) {
        updateNextBtnStatus();
    }

    public void initView() {
        this.setTitle(mController.getTitle());
        addMainFragment();
    }

    private boolean changeContentView(BasicFragmentFactory.FragmentDatasetOption
                                              fragmentDatasetOption) {
        if (null == fragmentDatasetOption) {
            return false;
        }
        mFragmentDatasetOption = fragmentDatasetOption;
        updateFragment(mFragmentDatasetOption.getFragment(),
                mFragmentDatasetOption.getDatasetName());
        updateBtnViewStatus(btn_back, true);
        updateNextBtnStatus();
        return true;
    }

    public void updateNextBtnStatus() {
        String msg;
        if (mController.getCheckedFragmentSize() == mController.getFramgmentIndex() + 1) {
            msg = getResources().getString(R.string.btn_confrim);
        } else {
            msg = getResources().getString(R.string.btn_next);
            updateBtnViewStatus(btn_next, true);
        }
        btn_next.setText(msg);
    }

    public void addMainFragment() {
        // TODO: 2017/10/28 设置当前dataset
        mController.setFramgmentIndex(-1);
        mController.setCurrentDataSet(WellDatasets.getMainDatasetName(
                mController.getWellType()));
        mWellMainFragment = new WellMainFragment_();
        updateFragment(mWellMainFragment, WellDatasets.getMainDatasetName(
                mController.getWellType()));
        updateNextBtnStatus();
        updateBtnViewStatus(btn_back, false);
    }

    private void updateFragment(Fragment fragment, String datasetName) {
        mController.setCurrentDataSet(datasetName);
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
        if (mController.getFramgmentIndex() == -1) {
            mWellMainFragment.getValue();
        } else {
            getValueFromFragment();
        }
    }

    private void getValueFromFragment() {
        if (null == mFragmentDatasetOption || !mFragmentDatasetOption.isChecked()) {
            return;
        }
        DataSet dataSet = mController.getCurrentDataSet(
                mFragmentDatasetOption.getDatasetName());
        if (null == dataSet) {
            return;
        }
        mFragmentDatasetOption.getFragment().getValue(dataSet);
    }

    public List<PhotoManagerController.PhotoItemInfo> getPhotoInfoList() {
        List<PhotoManagerController.PhotoItemInfo> photoItemInfoList = new ArrayList<>();
//        if (mPhotoFragment != null) {
//            photoItemInfoList = mPhotoFragment.getTaskPhotoList();
//        }
        return photoItemInfoList;
    }

    @OptionsItem
    void actionTaskCommit() {
        saveFragmentData();
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


    public boolean isNext() {
        return mIsNext;
    }
}
