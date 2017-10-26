package com.geocraft.electrics.ui.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.AdapterView;

import com.geocraft.electrics.base.BaseController;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.DataSetGroup;
import com.geocraft.electrics.entity.DataSource;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.ui.activity.CommonListActivity;
import com.geocraft.electrics.ui.activity.DeviceShowListActivity_;
import com.geocraft.electrics.ui.activity.RecordActivity_;
import com.geocraft.electrics.utils.ElectricBitmapUtils;
import com.geocraft.electrics.utils.PinYinUtil;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@EBean
public class CommonListController extends BaseController {

    @Bean
    TaskManager mTaskManager;
    @Bean
    DbManager mDbManger;
    //listView绑定的数据列表
    private List<CommonListItem> commonListItems = new ArrayList<>();
    private DataSet mParentDataSet;
    private List<DataSet> mDataSetList = new ArrayList<>();
    //大类，首字母缩写  ZXYH
    private String mFirstType;
    //大类，同时也是当前activity的标题   专线用户
    private String mTitle;
    private String mSecondType;
    private int mLineKey = -1;
    private boolean mIsFirstLevel;
    private boolean mIsSecondLevel;

    public boolean ismIsFirstLevel() {
        return mIsFirstLevel;
    }

    public boolean ismIsSecondLevel() {
        return mIsSecondLevel;
    }

    public String getFirstType() {
        return mFirstType;
    }

    public void setFirstType(String firstType) {
        mFirstType = firstType;
    }

    public void setLineKey(int lineKey) {
        mLineKey = lineKey;
    }

    public String getSecondType() {
        return mSecondType;
    }

    public void setSecondType(String secondType) {
        mSecondType = secondType;
    }

    @AfterInject
    void init() {

    }

    public void obtainParamFromIntent(Context context) {
        mIsFirstLevel = IsPassedFirstType(context);
        mIsSecondLevel = IsPassedSecondType(context);
    }

    public String getTitle() {
        return mTitle;
    }

    private boolean IsPassedFirstType(Context context) {
        return ((CommonListActivity) context).getIntent()
                .hasExtra(Constants.INTENT_DATA_SET_GROUP_NAME);
    }

    public boolean IsGoToThirdLevel(Context context) {
        return ((CommonListActivity) context).getIntent()
                .hasExtra(Constants.INTENT_DATA_GROUP_TO_DATA_NAME);
    }

    public boolean isPassedQueryField(Context context) {
        return ((CommonListActivity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_QUERY_FIELD);
    }

    public boolean isPassedQueryValue(Context context) {
        return ((CommonListActivity) context).getIntent().hasExtra(Constants.INTENT_DATA_SET_QUERY_VALUE);
    }

    public String getQueryFieldFromIntent(Context context) {
        return ((CommonListActivity) context).getIntent().getStringExtra(Constants.INTENT_DATA_SET_QUERY_FIELD);
    }

    public String getQueryValueFromIntent(Context context) {
        return ((CommonListActivity) context).getIntent().getStringExtra(Constants.INTENT_DATA_SET_QUERY_VALUE);
    }

    public String getSecondTypeFromIntent(Context context) {
        return ((CommonListActivity) context).getIntent().getStringExtra(Constants.INTENT_DATA_SET_NAME);
    }

    public int getLineKeyFromIntent(Context context) {
        return ((CommonListActivity) context).getIntent().getIntExtra(Constants.INTENT_DATA_SET_KEY, 0);
    }

    private boolean IsPassedSecondType(Context context) {
        boolean isSecondeType = ((CommonListActivity) context).getIntent().hasExtra(
                Constants.INTENT_DATA_SET_NAME);
        if (!isSecondeType) {
            isSecondeType = IsGoToThirdLevel(context);
        }
        return isSecondeType;
    }

    public String getTitleFromIntent(Context context) {
        return ((CommonListActivity) context).getIntent()
                .getStringExtra(Constants.INTENT_DATA_SET_GROUP_NAME);
    }

    /**
     * 初始化某一条专线用户列表下的所有dataset
     */
    public void initChildDataSetItems() {
        commonListItems.clear();
        DataSource dataSource = mTaskManager.getDataSource();
        if (dataSource != null) {
            DataSet parentDataSet = dataSource.getDataSetByName(mFirstType, mSecondType);
            mParentDataSet = parentDataSet;
            if (parentDataSet == null) {
                return;
            }
            mTitle = parentDataSet.Alias + parentDataSet.GetFieldNameByName(parentDataSet.First) + ":" + parentDataSet.GetFieldValueByName(parentDataSet.First);
            initChildDataSetList(parentDataSet);
        }
    }

    /**
     * 初始化
     */
    public void initGroupItems() {
        commonListItems.clear();
        DataSource dataSource = mTaskManager.getDataSource();
        if (dataSource != null) {
            DataSetGroup dataSetGroup = dataSource.getGroupByName(mFirstType);
            if (dataSetGroup == null) {
                return;
            }
            mTitle = dataSetGroup.Alias;
            initCommonList(dataSetGroup);
        }
    }

    /**
     * 初始化三级level数据
     */
    public void initThridLevelData(Context context) {
        setFirstType(getTitleFromIntent(context));
        initGroupItems();
        if (commonListItems.size() == 0) {
            return;
        }
        String secondeLevelName = null;
        for (CommonListItem item : commonListItems) {
            if (item.mIsGoToThridLevel) {
                secondeLevelName = item.mName;
                break;
            }
        }
        if (null == secondeLevelName) {
            return;
        }
        setSecondType(secondeLevelName);
        initChildDataSetItems();
    }

    private void initChildDataSetList(DataSet parentDataSet) {
        if (parentDataSet != null) {
            for (int i = 0; i < parentDataSet.DataSets.size(); i++) {
                DataSet childDataSet = parentDataSet.DataSets.get(i);
                if (childDataSet == null) {
                    continue;
                }
                if (childDataSet.IsVisible) {
                    mDataSetList.add(childDataSet);
                    CommonListItem tempCommonListItem = new CommonListItem();
                    tempCommonListItem.mBitmap = ElectricBitmapUtils.getItemBitmap(
                            ConstPath.getIconPath() + childDataSet.Mark);
                    tempCommonListItem.mAlias = childDataSet.Alias;
                    tempCommonListItem.mName = childDataSet.Name;
                    commonListItems.add(tempCommonListItem);
                }
            }
        } else {
            L.w("dataSetGroup is null");
        }
    }

    private void initCommonList(DataSetGroup dataSetGroup) {
        if (dataSetGroup != null) {
            for (int i = 0; i < dataSetGroup.DataSets.size(); i++) {
                DataSet dataSet = dataSetGroup.DataSets.get(i);
                if (dataSet == null) {
                    continue;
                }
                if (dataSet.isShowInDeviceList()) {
                    continue;
                }
                if (dataSet.IsVisible) {
                    mDataSetList.add(dataSet);
                    CommonListItem tempCommonListItem = new CommonListItem();
                    tempCommonListItem.mBitmap = ElectricBitmapUtils.getItemBitmap(
                            ConstPath.getIconPath() + dataSet.Mark);
                    tempCommonListItem.mAlias = dataSet.Alias;
                    tempCommonListItem.mName = dataSet.Name;
                    tempCommonListItem.mIsGoToThridLevel = dataSet.isShowInDeviceList();
                    commonListItems.add(tempCommonListItem);
                }
            }
        } else {
            L.w("dataSetGroup is null");
        }
    }

    public List<CommonListItem> getItems() {
        return commonListItems;
    }

    public DataSet getCurrentDataSet() {
        return mParentDataSet;
    }

    public void openDeviceShowListActivity(AdapterView<?> parent, View view, int position) {

        DataSet dataSet = mDataSetList.get(position);
        if (dataSet == null) {
            return;
        }

        String secondTypeName = ((CommonListItem) parent.getAdapter().getItem(position)).mName;
        String firstType = PinYinUtil.getFirstSpell(mFirstType);
        Intent intent = new Intent(view.getContext(), DeviceShowListActivity_.class);
        if (mLineKey > 0) {
            mParentDataSet.PrimaryKey = mLineKey;
            mParentDataSet = mDbManger.queryByPrimaryKey(mParentDataSet, false);
            String temp = mParentDataSet.GetFieldValueByName(dataSet.ValueField);
            if (!temp.isEmpty()) {
                intent.putExtra(Constants.INTENT_DATA_SET_QUERY_VALUE, temp);
                intent.putExtra(Constants.INTENT_DATA_SET_PARENT_KEY, mLineKey);
            } else {
                L.e("外键字段值为空");
                intent.putExtra(Constants.INTENT_DATA_SET_QUERY_VALUE, "");
                intent.putExtra(Constants.INTENT_DATA_SET_PARENT_KEY, mLineKey);
            }
        } else {
            intent.putExtra(Constants.INTENT_DATA_SET_QUERY_VALUE, "");
        }

        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, firstType);
        intent.putExtra(Constants.INTENT_DATA_SET_NAME, secondTypeName);
        view.getContext().startActivity(intent);
    }

    public void openParentRecordActivity(Context context) {
        Intent intent = new Intent(context, RecordActivity_.class);
        intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, mFirstType);
        intent.putExtra(Constants.INTENT_DATA_SET_NAME, mSecondType);
        intent.putExtra(Constants.INTENT_DATA_SET_KEY, mLineKey);
        intent.putExtra(Constants.INTENT_DATA_IS_FROM_EDIT_PARENT, true);
        L.i("commonlist跳转Record");
        ((CommonListActivity) context).startActivityForResult(intent, ConstRequestCode.REQUEST_CODE_EDIT_PARENT_DATA_SET);
    }

    public class CommonListItem {
        public Bitmap mBitmap;
        public String mAlias;
        public String mName;
        private boolean mIsGoToThridLevel;
    }
}
