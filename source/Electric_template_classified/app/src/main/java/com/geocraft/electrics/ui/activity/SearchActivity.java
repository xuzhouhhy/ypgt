package com.geocraft.electrics.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.constants.ConstRequestCode;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.constants.Enum;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.GroupAndDataSetName;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.event.BaseEvent;
import com.geocraft.electrics.event.SearchControlEvent;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.ui.view.PreferenceJRDBSView;
import com.geocraft.electrics.ui.view.PreferenceJRDBSView_;
import com.geocraft.electrics.utils.Utils;

import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import common.geocraft.untiltools.ConvertHelper;

/**
 * Created by hanpengfei on 2016/6/11.
 */
@OptionsMenu(R.menu.menu_task_manager)
@EActivity(R.layout.acitivity_search)
public class SearchActivity extends BaseActivity {
	private PropertyDictionay.SearchEntiry searchEntiry = null;

	private boolean mIsPreferenceJRDBS = false;
	private List<DataSet> dataSetJRDBSInOrder = new ArrayList<>();
	private ArrayList<JRDBSInfo> listJRDBS = new ArrayList<>();
	private JRDBSSearchAdapter mAdapterJRDBS;
	private double mLongitude;
	private double mLatitude;

	private List<DataSet> dataSetList = null;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayAdapter<String> adapter;

	private DataSet dataSetTmp = new DataSet();

	public class JRDBSInfo {
		public String mName;
		public Double mDistance;

		public JRDBSInfo(String name, Double distance) {
			mName = name;
			mDistance = distance;
		}

		public JRDBSInfo() {
		}
	}

	protected TaskManager taskManager = TaskManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());

	@ViewById
	EditText etSearch;
	@ViewById
	ImageView ivDeleteText;
	@ViewById
	ListView lvSearch;
	@Bean
	DbManager dbManager;

	@AfterViews
	void init() {
		Intent intent = getIntent();
		searchEntiry = (PropertyDictionay.SearchEntiry) intent.getSerializableExtra(Constants.INTENT_DATA_SEARCHENTITRY);
		if (intent.hasExtra(Constants.INTENT_DATA_PREFERENCE_JRDBS)) {
			mIsPreferenceJRDBS = true;
			mLongitude = intent.getDoubleExtra(Constants.INTENT_DATA_JRDBS_LONGITUDE, 0);
			mLatitude = intent.getDoubleExtra(Constants.INTENT_DATA_JRDBS_LATITUDE, 0);
		} else {
			mIsPreferenceJRDBS = false;
		}
		//获取DataSet
		GroupAndDataSetName groupAndDataSetName = new GroupAndDataSetName();
		groupAndDataSetName = Utils.getGourpAndDataSetName(searchEntiry.tableName);
		dataSetTmp = taskManager.getDataSource().getDataSetByName(groupAndDataSetName.groupName, groupAndDataSetName.dataSetName);

		//设置标题
		setTitle(getTitle() + "  " + dataSetTmp.Alias);

		etSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() == 0) {
					ivDeleteText.setVisibility(View.GONE);
				} else {
					ivDeleteText.setVisibility(View.VISIBLE);
				}
				if(mIsPreferenceJRDBS){
					doJRDBSInThread();
				}else {
					doSomethingInThread();
				}
			}
		});

		lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,final View view, int position, long id) {
				try {
					//解决Item会被多次点击的问题
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						L.printException(e);
					}

					if (mIsPreferenceJRDBS){
						dataSetTmp = (DataSet)dataSetJRDBSInOrder.get(position).clone();
						if (((PreferenceJRDBSView) view).isDistanceValid()){
							ElectricApplication.BUS.post(new SearchControlEvent(searchEntiry.uniqueFlag, ((PreferenceJRDBSView) view).getName().toString(), dataSetTmp));
							finish();
						}
						else {
							AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
							builder.setTitle(R.string.app_tip);
							builder.setIcon(R.mipmap.ic_tip);
							builder.setMessage(R.string.app_distance);
							builder.setNegativeButton(R.string.app_no, null);
							builder.setInverseBackgroundForced(true);
							builder.setPositiveButton(R.string.app_yes, new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									if (which == DialogInterface.BUTTON_POSITIVE) {
										ElectricApplication.BUS.post(new SearchControlEvent(searchEntiry.uniqueFlag, ((PreferenceJRDBSView) view).getName().toString(), dataSetTmp));
										finish();
									} else {
										return;
									}
								}
							}).show();
						}
					}else {
						L.i(searchEntiry.uniqueFlag);
						dataSetTmp = (DataSet) dataSetList.get(position).clone();
						ElectricApplication.BUS.post(new SearchControlEvent(searchEntiry.uniqueFlag, ((TextView) view).getText().toString(), dataSetTmp));
						finish();
					}
				} catch (CloneNotSupportedException e) {
					L.printException(e);
					finish();
				}


			}
		});

		etSearch.setText(searchEntiry.filedValue);

		if (mIsPreferenceJRDBS) {
			mAdapterJRDBS = new JRDBSSearchAdapter();
			lvSearch.setAdapter(mAdapterJRDBS);
		} else {
			adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
			lvSearch.setAdapter(adapter);
		}

		//注册Event
		if (!ElectricApplication.BUS.isRegistered(this)) {
			ElectricApplication.BUS.register(this);
		}

		if (mIsPreferenceJRDBS) {
			doJRDBSInThread();
		} else {
			doSomethingInThread();
		}
	}

	void doJRDBSInThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				//先清空List
				listJRDBS.clear();
				if (dataSetList != null) {
					dataSetList.clear();
				}

				if (searchEntiry.fieldName.isEmpty()) {
					dataSetList = dbManager.queryAll(dataSetTmp, true);
				} else {
					dataSetList = dbManager.queryByKeyword(
							dataSetTmp, searchEntiry.fieldName, etSearch.getText().toString(), true);
				}


				for (DataSet dataSet : dataSetList) {
					JRDBSInfo temp = new JRDBSInfo();
					temp.mName = dataSet.GetFieldValueByName(searchEntiry.fieldName);
					String longitude = dataSet.GetFieldValueByName(Enum.ACCESSPOINT_FIELD_JD_XZB);
					String latitude = dataSet.GetFieldValueByName(Enum.ACCESSPOINT_FIELD_WD_YZB);
					temp.mDistance = Utils.GetDis(ConvertHelper.ConverDouble(latitude), ConvertHelper.ConverDouble(longitude),
							mLatitude, mLongitude);
					listJRDBS.add(temp);
				}

				for (int i = 0; i < listJRDBS.size(); i++) {
					int min = i;
					for (int j = i + 1; j < listJRDBS.size(); j++) {
						if (listJRDBS.get(min).mDistance > listJRDBS.get(j).mDistance) {
							min = j;
						}
					}
					if (i != min) {
						JRDBSInfo temp = new JRDBSInfo(listJRDBS.get(i).mName, listJRDBS.get(i).mDistance);
						listJRDBS.set(i, new JRDBSInfo(listJRDBS.get(min).mName, listJRDBS.get(min).mDistance));
						listJRDBS.set(min, temp);
					}
				}
				for (int i = 0; i < listJRDBS.size(); i++) {
					String tempName = listJRDBS.get(i).mName;
					for (int j = 0; j < dataSetList.size(); j++) {
						if (tempName.equals(dataSetList.get(j).GetFieldValueByName(searchEntiry.fieldName))) {
							dataSetJRDBSInOrder.add(dataSetList.get(j));
							break;
						}
					}
				}
				ElectricApplication.BUS.post(new UpdateEvent("UpdateEvent", "SearchOver"));
			}
		}).run();
	}

	void doSomethingInThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				//先清空List
				list.clear();
				if (dataSetList != null) {
					dataSetList.clear();
				}

				if (searchEntiry.fieldName.isEmpty()) {
					dataSetList = dbManager.queryAll(dataSetTmp, true);
				} else {
					dataSetList = dbManager.queryByKeyword(
							dataSetTmp, searchEntiry.fieldName, etSearch.getText().toString(), true);
				}

				for (DataSet dataSet : dataSetList) {
					//循环Map值
					String value = "";
					value = dataSet.GetFieldValueByName(searchEntiry.fieldName);
					list.add(value);
				}
				ElectricApplication.BUS.post(new UpdateEvent("UpdateEvent", "SearchOver"));
			}
		}).run();
	}

	private class UpdateEvent extends BaseEvent {
		public UpdateEvent(String name, String msg) {
			super(name, msg);
		}
	}

	@Subscribe
	public void onEventMainThread(UpdateEvent event) {
		if (mIsPreferenceJRDBS) {
			mAdapterJRDBS.notifyDataSetChanged();
		} else {
			adapter.notifyDataSetChanged();
		}

	}

	@Click
	void ivDeleteText() {
		etSearch.setText("");
	}

	@OptionsItem
	void actionNewProject() {
		Intent intent = new Intent(this, RecordActivity_.class);
		intent.putExtra(Constants.INTENT_DATA_SET_GROUP_NAME, dataSetTmp.GroupName);
		intent.putExtra(Constants.INTENT_DATA_SET_NAME, dataSetTmp.Name);
		intent.putExtra(Constants.INTENT_DATA_IS_CREATE_RECORD, true);
		intent.putExtra(Constants.INTENT_DATA_IS_CURRENT_DATASET, false);

		startActivityForResult(intent, ConstRequestCode.REQUEST_CODE_OPEN_RECORDACTIVITY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		etSearch.setText("");
		if (mIsPreferenceJRDBS) {
			doJRDBSInThread();
		} else {
			doSomethingInThread();
		}
	}

	public class JRDBSSearchAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return listJRDBS.size();
		}

		@Override
		public Object getItem(int position) {
			return listJRDBS.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			PreferenceJRDBSView preferenceJRDBSView;
			if (convertView == null) {
				preferenceJRDBSView = PreferenceJRDBSView_.build(parent.getContext());
			} else {
				preferenceJRDBSView = (PreferenceJRDBSView) convertView;
			}
			JRDBSInfo temp = listJRDBS.get(position);
			preferenceJRDBSView.bind(temp.mName, temp.mDistance + "");
			return preferenceJRDBSView;
		}
	}
}
