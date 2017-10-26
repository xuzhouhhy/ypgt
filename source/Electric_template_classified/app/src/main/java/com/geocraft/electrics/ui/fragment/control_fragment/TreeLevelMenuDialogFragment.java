package com.geocraft.electrics.ui.fragment.control_fragment;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.base.BaseDialogFragment;
import com.geocraft.electrics.event.ThreeLevelMenuEvent;
import com.geocraft.electrics.event.ThreeLevelMenuPostEvent;
import com.geocraft.electrics.ui.view.UserDefineControlView.BusinessTreeLevelMenu;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanpengfei on 2016/6/8.
 */
@EFragment(R.layout.fragment_threelevel_menu)
public class TreeLevelMenuDialogFragment extends BaseDialogFragment {
    @ViewById
    TextView tvFirst;
    @ViewById
    TextView tvSecond;
    @ViewById
    TextView tvThree;
    @ViewById
    Spinner spFirst;
    @ViewById
    Spinner spSecond;
    @ViewById
    Spinner spThree;
    @ViewById
    Button btnCancel;
    @ViewById
    Button btnOK;

    public String fieldName = "";

    public BusinessTreeLevelMenuListener listener = null;

    private Map<String, Map<String, ArrayList<String>>> mInfo = new HashMap<>();

    Context mContext;

    String mStrFirst;

    String mStrSecond;

    String mStrThird;

    String mFirstValue;

    String mSecondValue;

    String mThirdValue;

    public interface  BusinessTreeLevelMenuListener
    {
        public void OnBusinessTreeLevelMenuEvent(ThreeLevelMenuEvent event);
    }

    public void addBusinessTreeLevelMenuListener(BusinessTreeLevelMenuListener listener)
    {
        this.listener = listener;
    }

    @AfterViews
    void init() {
        initTitle();
        ArrayList<String> mFirstList = new ArrayList<>(mInfo.keySet());
        initSpinnerList(spFirst, mFirstList, mFirstValue);
        spFirst.setOnItemSelectedListener(mFirstListener);
        spSecond.setOnItemSelectedListener(mSecondListener);
        spThree.setOnItemSelectedListener(mThirdListener);
    }

    public void setValue(String firstValue, String secondValue, String thirdValue) {
        mFirstValue = firstValue;
        mSecondValue = secondValue;
        mThirdValue = thirdValue;
    }

    public void setCaption(Context context, String first, String second, String third, Map<String, Map<String, ArrayList<String>>> info) {
        mContext = context;
        mStrFirst = first;
        mStrSecond = second;
        mStrThird = third;
        mInfo = info;
    }


    void initTitle() {
        tvFirst.setText(mStrFirst);
        tvSecond.setText(mStrSecond);
        tvThree.setText(mStrThird);
    }

    void initSpinnerList(Spinner spinner, List<String> items, String selectedValue) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(R.layout.item_single_choice);
        spinner.setAdapter(adapter);
        if (items.contains(selectedValue)) {
            spinner.setSelection(items.indexOf(selectedValue));
        } else {
            spinner.setSelection(0);
            L.w("spFirst not exists" + selectedValue);
        }
    }

    AdapterView.OnItemSelectedListener mFirstListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mFirstValue = parent.getSelectedItem().toString();
            List<String> mSecondList = Utils.getOptionTwoFromOne(mInfo, mFirstValue);
            initSpinnerList(spSecond, mSecondList, mSecondValue);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener mSecondListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mSecondValue = parent.getSelectedItem().toString();
            List<String> mThirdList = Utils.getOptionThreeFromTwo(mInfo, mFirstValue, mSecondValue);
            initSpinnerList(spThree, mThirdList, mThirdValue);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener mThirdListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mThirdValue = parent.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Click
    void btnOK() {
        if(listener != null)
        {
            listener.OnBusinessTreeLevelMenuEvent(new ThreeLevelMenuEvent("ThreeLevelMenu","Ovew",mFirstValue,mSecondValue,mThirdValue));
        }
        this.dismiss();
    }

    @Click
    void btnCancel() {
        this.dismiss();
    }
}
