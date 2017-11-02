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
import com.geocraft.electrics.sr.controller.SrPhotoManagerController;
import com.geocraft.electrics.sr.controller.WellController;
import com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment;
import com.geocraft.electrics.sr.task.InitWellInfoAsyncTask;
import com.geocraft.electrics.sr.task.WellCommitAsyncTask;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    private boolean isGoNext;
    private Map<String, SrPhotoManagerFragment> mPhotoFragments =
            new HashMap<String, SrPhotoManagerFragment>();

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
        isGoNext = true;
        if (!saveFragmentData()) {
            return;
        }
        if (!(boolean) btn_next.getTag()) {
            excuteCommitTask();
        }
        FragmentOption fragmentOption = mController.getNextFragment();
        changeContentView(fragmentOption);
    }

    @Click
    void btn_back() {
        isGoNext = false;
        saveFragmentData();
        FragmentOption fragmentOption = mController.getBack();
        changeContentView(fragmentOption);
    }

    public void initView() {
        this.setTitle(mController.getTitle());
        FragmentOption fragmentOption = mController.getPreFragmentFactory().getFirsFragment();
        changeContentView(fragmentOption);
        updateViewClickable(btn_back, false);
    }

    private boolean changeContentView(FragmentOption fragmentOption) {
        if (null == fragmentOption) {
            return false;
        }
        mFragmentOption = fragmentOption;
        updateFragment(mFragmentOption.getFragment());
        updateBackBtnStatus();
        updateNextBtnStatus();
        return true;
    }

    public void updateNextBtnStatus() {
        String msg;
        if (!mController.isHasNextFragment()) {
            msg = getResources().getString(R.string.btn_confrim);
            btn_next.setTag(false);//是否可继续
        } else {
            msg = getResources().getString(R.string.btn_next);
            btn_next.setTag(true);
        }
        btn_next.setText(msg);
    }

    public void updateBackBtnStatus() {
        if (mController.isHasPreFragment()) {
            updateViewClickable(btn_back, true);
        } else {
            updateViewClickable(btn_back, false);
        }
    }

    private boolean saveFragmentData() {
        return excuteSaveAction(mController.getCurrentDataSet());
    }

    private boolean excuteSaveAction(DataSet dataSet) {
        if (null == mFragmentOption) {
            return false;
        }
        BusinessFragment fragment = mFragmentOption.getFragment();
        if (!fragment.logicCheck()) {
            return false;
        }
        if (fragment instanceof SrPhotoManagerFragment) {
            mPhotoFragments.put(mFragmentOption.getNameKey(),
                    (SrPhotoManagerFragment) fragment);
        }
        fragment.getValue(dataSet);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onDataSynEvent(CheckFragmentEvent event) {
        mController.updateFragmentStatus(event.getFragmentNameKey(), event.isChecked());
        updateNextBtnStatus();
    }

    private void updateViewClickable(View view, boolean isEnable) {
        view.setEnabled(isEnable);
        view.setClickable(isEnable);
    }

    private void updateFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.id_content, fragment);
        transaction.commit();
    }

    public List<SrPhotoManagerController.PhotoItemInfo> getPhotoInfoList() {
        List<SrPhotoManagerController.PhotoItemInfo> photoItemInfoList = new ArrayList<>();
        Iterator iter = mPhotoFragments.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            SrPhotoManagerFragment photoFragment = (SrPhotoManagerFragment) entry.getValue();
            if (photoFragment != null) {
                photoItemInfoList.addAll(photoFragment.getTaskPhotoList());
            }
        }
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

    public boolean isGoNext() {
        return isGoNext;
    }


}
