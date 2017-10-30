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
import com.geocraft.electrics.event.ThreeLevelMenuEvent;
import com.geocraft.electrics.event.ThreeLevelMenuPostEvent;
import com.geocraft.electrics.ui.fragment.control_fragment.TreeLevelMenuDialogFragment;

import com.geocraft.electrics.ui.fragment.control_fragment.TreeLevelMenuDialogFragment_;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hanpengfei on 2016/6/8.
 */
@EViewGroup(R.layout.view_treelevelmenu)
public class BusinessTreeLevelMenu extends LinearLayout implements DataInterActionInterface,TreeLevelMenuDialogFragment.BusinessTreeLevelMenuListener
{
    @ViewById
    EditText etText;
    @ViewById
    Button   btnMore;

    private TreeLevelMenuDialogFragment mThreeLevelMenuDialogFragment;

    private Context mContext;
    public  String  mFirstValue = "";
    public  String  mSecondValue = "";
    public  String  mThirdValue = "";

    private PropertyDictionay.ThreeLevelMenu threeLevelMenu = new PropertyDictionay.ThreeLevelMenu();

    public BusinessTreeLevelMenu(Context context)
    {
        super(context);
        init(context);
    }

    public BusinessTreeLevelMenu(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public BusinessTreeLevelMenu(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context);
    }

    @Click
    void btnMore() {
        mThreeLevelMenuDialogFragment = new TreeLevelMenuDialogFragment_();
        mThreeLevelMenuDialogFragment.setCaption(mContext, threeLevelMenu.firstCaption, threeLevelMenu.secondCaption,
                threeLevelMenu.thirdCaption, threeLevelMenu.menu);
        mThreeLevelMenuDialogFragment.setValue(mFirstValue, mSecondValue, mThirdValue);
        mThreeLevelMenuDialogFragment.addBusinessTreeLevelMenuListener(this);
        mThreeLevelMenuDialogFragment.show(((Activity) mContext).getFragmentManager(), "threelevel");
    }



    @Override
    public int geteControlType() {
        return PropertyDictionay.OperateCode.Type_ThreeLevelMenu;
    }

    @Override
    public void setControlValue(String text) {
        etText.setText(text);
    }

    @Override
    public String getControlValue() {
        return etText.getText().toString();
    }

    @Override
    public void setControlValue(FieldInfo fieldInfo, String text) {
        threeLevelMenu = Utils.loadThreeLevelMenuFromFile(fieldInfo.Dictionay.threeLevelMenu.fileName);
        etText.setText(text);
        if (!text.isEmpty()){
            if (!initValue(text)) {
                L.w("init value failed");
                initValueDefault();
            }
        }
    }


    private boolean initValue(String text) {
        Iterator key = threeLevelMenu.menu.entrySet().iterator();
        for (int i = 0; i < threeLevelMenu.menu.size(); i++) {
            Map.Entry item = (Map.Entry) key.next();
            Map<String, ArrayList<String>> tempFirst = (Map) item.getValue();
            Iterator key2 = tempFirst.entrySet().iterator();
            for (int j = 0; j < tempFirst.size(); j++) {
                Map.Entry item2 = (Map.Entry) key2.next();
                ArrayList<String> tempSecondList = (ArrayList<String>) item2.getValue();
                for (int k=0;k<tempSecondList.size();k++){
                    String tempThird = tempSecondList.get(k);
                    if (!tempThird.equals(text)) {
                        continue;
                    } else {
                        mThirdValue = text;
                        mSecondValue = (String) item2.getKey();
                        mFirstValue = (String) item.getKey();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void initValueDefault() {
        mFirstValue =  threeLevelMenu.menu.keySet().toArray()[0].toString();
        mSecondValue = Utils.getOptionTwoFromOne(threeLevelMenu.menu, mFirstValue).get(0);
        mThirdValue = Utils.getOptionThreeFromTwo(threeLevelMenu.menu, mFirstValue, mSecondValue).get(0);
    }

    private void init(Context context) {
        mContext = context;
    }

    @AfterViews
    void init(){
        etText.setEnabled(false);
    }

    @Override
    public void OnBusinessTreeLevelMenuEvent(ThreeLevelMenuEvent event) {
        etText.setText(event.thirdValue);
        mFirstValue = event.firstValue;
        mSecondValue = event.secondValue;
        mThirdValue = event.thirdValue;
    }
}
