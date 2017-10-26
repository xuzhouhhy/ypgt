package com.geocraft.electrics.ui.fragment;

import android.annotation.SuppressLint;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseDialogFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hanpengfei on 2016/6/6.
 */
@EFragment(R.layout.fragment_importexportresult)
public class ImportExportResultDialogFragment extends BaseDialogFragment
{
    public  String message = "";
    @ViewById
    TextView tvMessage;

    public ImportExportResultDialogFragment()
    {

    }

    @AfterViews
    void init()
    {
        tvMessage.setText(message);
    }
}
