package com.geocraft.electrics.base;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by hanpengfei on 2016/4/16.
 */
public abstract  class BaseDialogFragment extends DialogFragment
{
    protected     Object objOwner = null;

    public BaseDialogFragment(Object obj)
    {
        super();
        objOwner = obj;
    }

    public BaseDialogFragment()
    {
        super();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  super.onCreateView(inflater, container, savedInstanceState);

        //移除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);

       return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        getDialog().getWindow().setLayout( dm.widthPixels, getDialog().getWindow().getAttributes().height );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
