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
import com.geocraft.electrics.event.CombinationMenuPostEvent;
import com.geocraft.electrics.event.ThreeLevelMenuPostEvent;
import com.geocraft.electrics.utils.Utils;
import com.huace.log.logger.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanpengfei on 2016/6/8.
 */
@EFragment(R.layout.fragment_combinationmenu)
public class CombinationMenuDialogFragment extends BaseDialogFragment
{
    @ViewById
    TextView tvFirst;

    @ViewById
    TextView tvSecond;

    @ViewById
    Spinner spFirst;

    @ViewById
    Spinner spSecond;

    @ViewById
    Button btnCancel;

    @ViewById
    Button btnOK;

    private Context mContext;

    private Map<String,ArrayList<String>> mInfo = new HashMap<>();

    String mCaptionFirst;

    String mCaptionSecond;

    String mFirstValue;

    String mSecondValue;


    @AfterViews
    void init(){
        initTitle();
        ArrayList<String> mFirstList = new ArrayList<>(mInfo.keySet());
        initSpinnerList(spFirst, mFirstList, mFirstValue);
        spFirst.setOnItemSelectedListener(mFirstListener);
        spSecond.setOnItemSelectedListener(mSecondListener);
    }

    public void setCaption(Context context,String first,String second,Map<String,ArrayList<String>> info)
    {
        mContext = context;
        mCaptionFirst = first;
        mCaptionSecond = second;
        mInfo = info;
    }

    public void setValue(String firstValue, String secondValue) {
        mFirstValue = firstValue;
        mSecondValue = secondValue;
    }

    void initTitle() {
        tvFirst.setText(mCaptionFirst);
        tvSecond.setText(mCaptionSecond);
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
            //TODO
             List<String> mSecondList = mInfo.get(mFirstValue);
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
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Click
    void btnOK() {
        ElectricApplication.BUS.post(new CombinationMenuPostEvent(mFirstValue, mSecondValue));
        this.dismiss();
    }

    @Click
    void btnCancel() {
        this.dismiss();
    }
}
