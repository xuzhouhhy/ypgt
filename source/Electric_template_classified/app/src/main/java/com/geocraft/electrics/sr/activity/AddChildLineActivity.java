package com.geocraft.electrics.sr.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.sr.controller.AddChildLineController;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessAddress;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessAdministrator;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessCombinationMenu;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessConcatSpinner;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessIDEditText;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessManager;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessScanEdit;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSearch;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessSpinner;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;
import com.geocraft.electrics.ui.view.UserDefineControlView.EditTextDatetimeExpand;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongshibu02 on 2017/11/1.
 */
@EActivity(R.layout.activity_child_line)
@OptionsMenu(R.menu.menu_new_task)
public class AddChildLineActivity extends BaseActivity {

    @Bean
    AddChildLineController mController;

    @ViewById
    LinearLayout linearLayoutRoot;
    @ViewById
    BusinessEditText edtName;
    @ViewById
    BusinessEditText F_LINENUMBER;
    @ViewById
    BusinessEditText F_LINESTART;
    @ViewById
    BusinessEditText F_LINEEND;
    @ViewById
    BusinessConcatSpinner F_LINEZCSX;
    @ViewById
    BusinessEditText F_LINETYRQ;
    @ViewById
    BusinessEditText edt_consturction;
    @ViewById
    BusinessEditText F_whbd;
    @ViewById
    BusinessEditText edtDeviceOwer;

    @AfterViews
    void init(){
        mController.initData();
    }

    @OptionsItem
    void actionTaskCommit() {
//        getValue();
//        boolean isContinueCheck = mController.checkDataValidity(this);
    }

    private void getValue() {
        if (linearLayoutRoot != null) {
            List<View> childViewGroup = new ArrayList<>();
            for (int i = 0; i < linearLayoutRoot.getChildCount(); i++) {
                View viewTemp = linearLayoutRoot.getChildAt(i);
                childViewGroup.add(viewTemp);
            }
            List<FieldInfo> fieldInfoList = mController.getDataSet().FieldInfos;
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
                        switch (((DataInterActionInterface) view).geteControlType()) {
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
                            case PropertyDictionay.OperateCode.Type_Administrator: {
                                fieldInfo.Value = ((BusinessAdministrator) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_Manager: {
                                fieldInfo.Value = ((BusinessManager) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_Address: {
                                fieldInfo.Value = ((BusinessAddress) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_BusinessID: {
                                fieldInfo.Value = ((BusinessIDEditText) view).getControlValue();
                                break;
                            }
                            case PropertyDictionay.OperateCode.Type_dateTime: {
                                fieldInfo.Value = ((EditTextDatetimeExpand) view).getControlValue();
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

}
