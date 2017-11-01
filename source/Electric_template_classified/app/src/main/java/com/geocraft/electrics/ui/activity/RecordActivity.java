package com.geocraft.electrics.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.base.BusinessFragment;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.sr.controller.SrPhotoManagerController;
import com.geocraft.electrics.sr.event.RenameEvent;
import com.geocraft.electrics.sr.fragment.SrPhotoManagerFragment;
import com.geocraft.electrics.task.InitRecordInfoAsyncTask;
import com.geocraft.electrics.task.RecordCommitAsyncTask;
import com.geocraft.electrics.ui.adapter.RecordFragmentAdapter;
import com.geocraft.electrics.ui.controller.RecordController;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.T;

/**
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
@EActivity(R.layout.activity_record)
@OptionsMenu(R.menu.menu_new_task)
public class RecordActivity extends BaseActivity implements
        RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    public BusinessFragment mBasicDataFragment;
    public BusinessFragment mNecessaryDataFragment;
    public BusinessFragment mCoordinateFragment;
    @Bean
    RecordController mController;
    @ViewById
    RadioGroup radioGroupTab;
    @ViewById
    RadioButton radioButtonNecessary;
    @ViewById
    RadioButton radioButtonBasic;
    @ViewById
    RadioButton radioButtonPhoto;
    @ViewById
    RadioButton radioButtonCoordinate;
    @ViewById
    ViewPager viewPagerRecord;
    SrPhotoManagerFragment mPhotoFragment;
    RecordFragmentAdapter mAdapter;

    @AfterViews
    void init() {

        mController.initIntentData(this);

        InitRecordInfoAsyncTask initRecordInfoAsyncTask = new InitRecordInfoAsyncTask(this,
                mController);
        initRecordInfoAsyncTask.execute(mController);
    }


    public void initView() {
        radioButtonNecessary.setChecked(true);
        radioGroupTab.setOnCheckedChangeListener(this);
        mAdapter = new RecordFragmentAdapter(getSupportFragmentManager());
        mAdapter.setFragment(mNecessaryDataFragment, mBasicDataFragment, mPhotoFragment, mCoordinateFragment);
        viewPagerRecord.setOffscreenPageLimit(mAdapter.getOffscreenPageLimit());
        viewPagerRecord.setAdapter(mAdapter);
        viewPagerRecord.addOnPageChangeListener(this);

        mController.decideRadioButtonPhotoVisibility(this);
        mController.decideRadioButtonCoordinateVisibility(this);
        mController.decideRadioButtonNecessaryVisibility(this);
        mController.decideRadioButtonBasicVisibility(this);
        this.setTitle(mController.getTitle());
    }

    @OptionsItem
    void actionTaskCommit() {
        getValueFromFragment();
        boolean isContinueCheck = mController.checkDataValidity(this, getPhotoInfoList());
        if (isContinueCheck) {
            // TODO: 2017/10/24 原对象方法一直返回true,并且mNecessaryDataFragment为null没考虑，故注释掉
//			if (mNecessaryDataFragment.logicCheck()) {
//
//			}
            RecordCommitAsyncTask recordCommitAsyncTask = new RecordCommitAsyncTask(this,
                    mController);
            recordCommitAsyncTask.execute(mController);
        }
    }

    public List<SrPhotoManagerController.PhotoItemInfo> getPhotoInfoList() {
        List<SrPhotoManagerController.PhotoItemInfo> photoItemInfoList = new ArrayList<>();
        if (mPhotoFragment != null) {
            photoItemInfoList = mPhotoFragment.getTaskPhotoList();
        }
        return photoItemInfoList;
    }

    private void getValueFromFragment() {
        if (mController.getCurrentDataSet() != null) {
            if (mNecessaryDataFragment != null) {
                mNecessaryDataFragment.getValue(mController.getCurrentDataSet());
            }
            if (mBasicDataFragment != null) {
                mBasicDataFragment.getValue(mController.getCurrentDataSet());
            }
            if (mCoordinateFragment != null) {
                mCoordinateFragment.getValue(mController.getCurrentDataSet());
            }
        }
    }

    public void initFragment() {
        RecordController.DataFragmentGroup dataFragmentGroup = mController.getDataFragmentGroup();
        if (dataFragmentGroup != null) {
            mNecessaryDataFragment = dataFragmentGroup.mNecessaryDataFragment;
            mBasicDataFragment = dataFragmentGroup.mBasicDataFragment;
            mPhotoFragment = dataFragmentGroup.mPhotoManagerFragment;
            mCoordinateFragment = dataFragmentGroup.mCoordinateFragment;
        }
    }

    public RecordController getController() {
        return mController;
    }

    private void changeFragment(int i) {
        int j = 0;
        viewPagerRecord.getAdapter().getCount();
        switch (i) {
            default:
            case R.id.radioButtonNecessary: {
                j = 0;
            }
            break;
            case R.id.radioButtonBasic: {
                if (mController.isShowNecessary()) {
                    j = 1;
                } else {
                    j = 0;
                }

            }
            break;
            case R.id.radioButtonPhoto: {
                if (mController.isShowNecessary()) {
                    if (mController.isShowBasic()) {
                        j = 2;
                    } else {
                        j = 1;
                    }
                } else {
                    if (mController.isShowBasic()) {
                        j = 1;
                    } else {
                        j = 0;
                    }
                }

            }
            break;
            case R.id.radioButtonCoordinate: {
                if (mController.isShowNecessary()) {
                    if (mController.isShowBasic()) {
                        if (mController.isShowPhoto()) {
                            j = 3;
                        } else {
                            j = 2;
                        }
                    } else {
                        if (mController.isShowPhoto()) {
                            j = 2;
                        } else {
                            j = 1;
                        }
                    }
                } else {
                    if (mController.isShowBasic()) {
                        if (mController.isShowPhoto()) {
                            j = 2;
                        } else {
                            j = 1;
                        }
                    } else {
                        if (mController.isShowPhoto()) {
                            j = 1;
                        } else {
                            j = 0;
                        }
                    }
                }
            }
            break;
        }

        viewPagerRecord.setCurrentItem(j);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        changeFragment(checkedId);
    }


    public void setRadioButtonPhotoVisibility(int visibility) {
        radioButtonPhoto.setVisibility(visibility);
    }

    public void setRadioButtonCoordinateVisibility(int visibility) {
        radioButtonCoordinate.setVisibility(visibility);
    }

    public void setRadioButtonBasicVisibility(int visibility) {
        radioButtonBasic.setVisibility(visibility);
    }

    public void setRadioButtonNecessaryVisibility(int visibility) {
        radioButtonNecessary.setVisibility(visibility);
    }

    @Override
    protected void onDestroy() {
        //mController.clearPhotoCache();
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        int i = viewPagerRecord.getCurrentItem();

        switch (i) {
            default:
            case 0: {
                if (mController.isShowNecessary()) {
                    radioButtonNecessary.setChecked(true);
                    break;
                } else if (mController.isShowBasic()) {
                    radioButtonBasic.setChecked(true);
                    break;
                } else if (mController.isShowPhoto()) {
                    radioButtonPhoto.setChecked(true);
                    break;
                } else if (mController.isShowCoordinate()) {
                    radioButtonCoordinate.setChecked(true);
                    break;
                }
            }
            break;
            case 1: {
                if (mController.isShowBasic()) {
                    radioButtonBasic.setChecked(true);
                    break;
                } else if (mController.isShowPhoto()) {
                    radioButtonPhoto.setChecked(true);
                    break;
                } else if (mController.isShowCoordinate()) {
                    radioButtonCoordinate.setChecked(true);
                    break;
                }
            }
            break;
            case 2: {
                if (mController.isShowPhoto()) {
                    radioButtonPhoto.setChecked(true);
                    break;
                } else if (mController.isShowCoordinate()) {
                    radioButtonCoordinate.setChecked(true);
                    break;
                }
            }
            break;
            case 3: {
                if (mController.isShowCoordinate()) {
                    radioButtonCoordinate.setChecked(true);
                    break;
                }
            }
            break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void initData(DataSet dataSet) {
        mBasicDataFragment.initData(false, dataSet);
        mNecessaryDataFragment.initData(false, dataSet);
        mCoordinateFragment.initData(false, dataSet);
    }

    public void refreshViewPagerRecord() {
        try {
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            L.printException(e);
        }
    }

    public BusinessFragment getNecessaryDataFragment() {
        return this.mNecessaryDataFragment;
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

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onDataSynEvent(RenameEvent event) {
        if (event.isIsSuccess()) {
            T.show(this, getString(R.string.rename_photo_success), Toast.LENGTH_SHORT);
        }else {
            T.show(this, getString(R.string.rename_photo_fail), Toast.LENGTH_SHORT);
        }
    }
}
