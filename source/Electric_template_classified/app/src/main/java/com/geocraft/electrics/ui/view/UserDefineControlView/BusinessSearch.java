package com.geocraft.electrics.ui.view.UserDefineControlView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication;
import com.geocraft.electrics.constants.Constants;
import com.geocraft.electrics.entity.FieldInfo;
import com.geocraft.electrics.entity.PropertyDictionay;
import com.geocraft.electrics.event.SearchControlEvent;
import com.geocraft.electrics.ui.activity.SearchActivity_;
import com.geocraft.electrics.ui.inter.DataInterActionInterface;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by hanpengfei on 2016/6/8.
 */
@EViewGroup(R.layout.view_search)
public class BusinessSearch extends LinearLayout implements DataInterActionInterface
{
    @ViewById
    EditText etText;
    @ViewById
    Button   btnMore;

    public PropertyDictionay.SearchEntiry searchEntiry = new PropertyDictionay.SearchEntiry();

    public BusinessSearch(Context context)
    {
        super(context);
    }

    public BusinessSearch(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public BusinessSearch(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public int geteControlType() {
        return PropertyDictionay.OperateCode.Type_Search;
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
        searchEntiry = fieldInfo.Dictionay.searchEntiry;
        etText.setText(text);

        if(searchEntiry.editable)
        {
            etText.setEnabled(true);
        }
    }

    @AfterViews
    void init()
    {
        btnMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity)getContext(), SearchActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.INTENT_DATA_SEARCHENTITRY, searchEntiry);
                intent.putExtras(bundle);
                ((Activity)getContext()).startActivity(intent);
            }
        });
    }
}
