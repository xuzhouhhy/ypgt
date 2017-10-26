package com.geocraft.electrics.ui.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.huace.log.logger.L;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@EViewGroup(R.layout.itemview_exbandablelistview_basic)
public class TaskParentItemView extends LinearLayout {
    @ViewById
    TextView txtTaskName;
    public TaskParentItemView(Context context) {
        super(context);
    }
    public void bind(String Name) {
        try {
            txtTaskName.setText(Name);
        } catch (Exception e) {
            L.printException(e);
        }
    }

}
