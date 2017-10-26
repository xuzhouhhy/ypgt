package com.geocraft.electrics.base;

import android.view.View;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.db.DbManager;
import com.geocraft.electrics.db.DbManager_;
import com.geocraft.electrics.entity.DataSet;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.manager.TaskManager;
import com.geocraft.electrics.manager.TaskManager_;
import com.geocraft.electrics.ui.activity.RecordActivity;
import com.geocraft.electrics.ui.fragment.control_fragment.AdministrationDialogFragment;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessAddress;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessAdministrator;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessCombinationMenu;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessIDEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessManager;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessScanEdit;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSpinner;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者  zhouqin
 * 时间 2016/6/7.
 */
@EFragment(R.layout.fragment_business)
public class BusinessFragment extends BaseFragment {

	protected DataSet mDataSet;
	protected Boolean mIsNew;
	protected LinearLayout mLinearLayout;

	protected TaskManager taskManager = TaskManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());
	protected DbManager   dbManager = DbManager_.getInstance_(ElectricApplication_.getInstance().getApplicationContext());

	@AfterViews
	protected void init() {
		mIsNew = ((RecordActivity) this.getActivity()).getController().isCreateRecord();
		mDataSet = ((RecordActivity) this.getActivity()).getController().getCurrentDataSet();
		initData(mIsNew, mDataSet);

	}

	public boolean logicCheck() {
		return true;
	}

	public void initData(boolean isNew, DataSet dataSet) {
		try {
			if (mLinearLayout != null) {
				List<View> childViewGroup = new ArrayList<>();
				for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
					View viewTemp = mLinearLayout.getChildAt(i);
					childViewGroup.add(viewTemp);
				}
				List<FieldInfo> fieldInfoList = dataSet.FieldInfos;
				for (int i = 0; i < fieldInfoList.size(); i++) {
					FieldInfo fieldInfo = fieldInfoList.get(i);
					if (fieldInfo == null) {
						continue;
					}
					for (View view : childViewGroup) {
						if (view.getTag() == null) {
							continue;
						}
						if (view.getTag().toString().equalsIgnoreCase(fieldInfo.Alias)) {

							L.i(fieldInfo.Alias);
							switch (fieldInfo.OperateCode) {
								case PropertyDictionay.OperateCode.Type_BaseText: {
									if (isNew) {
										((BusinessEditText) view).setControlValue(fieldInfo, fieldInfo.Default);
									} else {
										((BusinessEditText) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									break;
								}
								case PropertyDictionay.OperateCode.Type_MenuList: {
									if (isNew) {
										((BusinessSpinner) view).setControlValue(fieldInfo, fieldInfo.Default);
									} else {
										((BusinessSpinner) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									break;
								}
								case PropertyDictionay.OperateCode.Type_Search: {
									if (isNew) {
										((BusinessSearch) view).setControlValue(fieldInfo, fieldInfo.Default);
									} else {
										((BusinessSearch) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									((BusinessSearch) view).searchEntiry.uniqueFlag = dataSet.Name;
									break;
								}
								case PropertyDictionay.OperateCode.Type_CombinationMenu: {
									if (isNew) {
										((BusinessCombinationMenu) view).setControlValue(fieldInfo, fieldInfo.Default);
									} else {
										((BusinessCombinationMenu) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									break;
								}
								case PropertyDictionay.OperateCode.Type_ThreeLevelMenu: {

									if(!fieldInfo.Dictionay.threeLevelMenu.fileName.contains(File.separator)){
										fieldInfo.Dictionay.threeLevelMenu.fileName = ConstPath.getTaskRootFolder() + taskManager.getTaskInfo().getTaskName() + "/模板/" + fieldInfo.Dictionay.threeLevelMenu.fileName;
									}
									L.i("三级菜单路径："+fieldInfo.Dictionay.threeLevelMenu.fileName);
									if (isNew) {
										((BusinessTreeLevelMenu) view).setControlValue(fieldInfo, fieldInfo.Default);
									} else {
										((BusinessTreeLevelMenu) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									break;
								}
								case PropertyDictionay.OperateCode.Type_Scan: {
									if (isNew) {
										((BusinessScanEdit) view).setControlValue(fieldInfo, fieldInfo.Default);
									} else {
										((BusinessScanEdit) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									break;
								}
								case PropertyDictionay.OperateCode.Type_Administrator:{
									if(isNew){
										((BusinessAdministrator) view).setControlValue(fieldInfo, fieldInfo.Default);
									}
									else{
										((BusinessAdministrator) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									break;
								}
								case PropertyDictionay.OperateCode.Type_Manager:{
									if(isNew){
										((BusinessManager) view).setControlValue(fieldInfo, fieldInfo.Default);
									}
									else{
										((BusinessManager) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									break;
								}
								case PropertyDictionay.OperateCode.Type_Address:{
									if(isNew){
										((BusinessAddress) view).setControlValue(fieldInfo, fieldInfo.Default);
									}
									else{
										((BusinessAddress) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									break;
								}
								case PropertyDictionay.OperateCode.Type_BusinessID:{
									if(isNew){
										((BusinessIDEditText) view).setControlValue(fieldInfo, fieldInfo.Default);
									}
									else{
										((BusinessIDEditText) view).setControlValue(fieldInfo, fieldInfo.Value);
									}
									break;
								}
								default:
									break;
							}
							break;
						}
					}
				}
			}
		}catch (Exception e){
			L.printException(e);
		}
	}

	public void getValue(DataSet dataSet) {
		if (mLinearLayout != null) {
			List<View> childViewGroup = new ArrayList<>();
			for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
				View viewTemp = mLinearLayout.getChildAt(i);
				childViewGroup.add(viewTemp);
			}
			List<FieldInfo> fieldInfoList = dataSet.FieldInfos;
			for (int i = 0; i < fieldInfoList.size(); i++) {
				FieldInfo fieldInfo = fieldInfoList.get(i);
				if (fieldInfo == null) {
					continue;
				}
				for (View view : childViewGroup) {
					if (view.getTag() == null) {
						continue;
					}
					if (view.getTag().toString().equalsIgnoreCase(fieldInfo.Alias)) {
						switch (fieldInfo.OperateCode) {
							case PropertyDictionay.OperateCode.Type_BaseText: {
								fieldInfo.Value = ((BusinessEditText) view).getControlValue();
								break;
							}
							case PropertyDictionay.OperateCode.Type_MenuList: {
								fieldInfo.Value = ((BusinessSpinner) view).getControlValue();
								break;
							}
							case PropertyDictionay.OperateCode.Type_Search: {
								fieldInfo.Value = ((BusinessSearch) view).getControlValue();
								break;
							}
							case PropertyDictionay.OperateCode.Type_CombinationMenu: {
								fieldInfo.Value = ((BusinessCombinationMenu) view).getControlValue();
								break;
							}
							case PropertyDictionay.OperateCode.Type_ThreeLevelMenu: {
								fieldInfo.Value = ((BusinessTreeLevelMenu) view).getControlValue();
								break;
							}
							case PropertyDictionay.OperateCode.Type_Scan: {
								fieldInfo.Value = ((BusinessScanEdit) view).getControlValue();
								break;
							}
							case PropertyDictionay.OperateCode.Type_Administrator:
							{
								fieldInfo.Value = ((BusinessAdministrator) view).getControlValue();
								break;
							}
							case PropertyDictionay.OperateCode.Type_Manager:
							{
								fieldInfo.Value = ((BusinessManager) view).getControlValue();
								break;
							}
							case PropertyDictionay.OperateCode.Type_Address:
							{
								fieldInfo.Value = ((BusinessAddress) view).getControlValue();
								break;
							}
							case PropertyDictionay.OperateCode.Type_BusinessID:
							{
								fieldInfo.Value = ((BusinessIDEditText) view).getControlValue();
								break;
							}
							default:
								break;
						}
						break;
					}
				}
			}
		}
	}

	@Override
	public void onDestroyView() {
		if (mLinearLayout != null) {
			List<View> childViewGroup = new ArrayList<>();
			for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
				View viewTemp = mLinearLayout.getChildAt(i);
				childViewGroup.add(viewTemp);
			}
			List<FieldInfo> fieldInfoList = mDataSet.FieldInfos;
			for (int i = 0; i < fieldInfoList.size(); i++) {
				FieldInfo fieldInfo = fieldInfoList.get(i);
				if (fieldInfo == null) {
					continue;
				}
				for (View view : childViewGroup) {
					if (view.getTag() == null) {
						continue;
					}
					if (view.getTag().toString().equalsIgnoreCase(fieldInfo.Alias)) {
						if(fieldInfo.OperateCode == PropertyDictionay.OperateCode.Type_Scan) {
							try {
								((BusinessScanEdit) view).finalize();
							}
							catch (Throwable e)
							{
								L.printException(e);
							}
							}
						}
						break;
					}
				}
			}

		super.onDestroyView();
		}
	}

