package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.event.CombinationMenuPostEvent;
import com.geocraft.electrics.ui.fragment.control_fragment.CombinationMenuDialogFragment;
import com.geocraft.electrics.ui.fragment.control_fragment.CombinationMenuDialogFragment_;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hanpengfei on 2016/6/8.
 */
@EViewGroup(R.layout.view_combinationmenu)
public class BusinessCombinationMenu extends LinearLayout implements DataInterActionInterface {
    @ViewById
    EditText etText;
    @ViewById
    Button btnMore;

    private CombinationMenuDialogFragment mCombinationMenuDialogFragment;
    public String firstValue = "";
    public String secondValue = "";
    private Context mContext;

    private PropertyDictionay.CombinationMenu combinationMenu = new PropertyDictionay.CombinationMenu();

    public BusinessCombinationMenu(Context context) {
        super(context);
        init(context);
    }

    public BusinessCombinationMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BusinessCombinationMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Click
    void btnMore() {

        mCombinationMenuDialogFragment = new CombinationMenuDialogFragment_();
        mCombinationMenuDialogFragment.setCaption(mContext, combinationMenu.firstCaption, combinationMenu.secondCaption, combinationMenu.combinationMenu);
        mCombinationMenuDialogFragment.setValue(firstValue, secondValue);
        mCombinationMenuDialogFragment.show(((Activity) mContext).getFragmentManager(), "combination");
    }

    @Override
    public void setControlValue(String text) {

    }

    @Override
    public String getControlValue() {
        return secondValue;
    }

    @Override
    public void setControlValue(FieldInfo fieldInfo, String text) {
        combinationMenu = fieldInfo.Dictionay.combinationMenu;
        if (!text.isEmpty()){
            initValue(text);
            etText.setText(secondValue);
        }

    }

    private boolean initValue(String text) {
        firstValue = findKeyOfValue(text);
        if (firstValue != null) {
            secondValue = text;
        } else {
            firstValue = getOptionOne().get(0);
            secondValue = getOptionTwoFromOne(firstValue).get(0);
            L.w("init value failed");
        }
        //TODO
        return false;
    }


    @AfterViews
    void init() {
        etText.setEnabled(false);
    }

    private void init(Context context) {
        mContext = context;
        if (!ElectricApplication.BUS.isRegistered(this)) {
            ElectricApplication.BUS.register(this);
        }
    }

    @Subscribe
    public void onEvent(CombinationMenuPostEvent event) {
        firstValue = event.getFirstValue();
        secondValue = event.getSecondValue();
        etText.setText(secondValue);
    }

    private List<String> getOptionOne() {
        return new ArrayList<>(combinationMenu.combinationMenu.keySet());
    }

    private List<String> getOptionTwoFromOne(String firstValue) {
        return combinationMenu.combinationMenu.get(firstValue);
    }

    private String findKeyOfValue(String text) {
        Iterator key = combinationMenu.combinationMenu.entrySet().iterator();
        for (int i = 0; i < combinationMenu.combinationMenu.size(); i++) {
            Map.Entry item = (Map.Entry) key.next();
            String tempSecondValue = (String) item.getValue();
            if (!text.equals(tempSecondValue)) {
                continue;
            } else {
                return (String) item.getKey();
            }
        }
        return null;
    }

}
