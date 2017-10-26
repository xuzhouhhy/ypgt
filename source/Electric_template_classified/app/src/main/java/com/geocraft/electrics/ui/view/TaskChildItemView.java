package com.geocraft.electrics.ui.view;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geocraft.electrics.R;
import com.geocraft.electrics.entity.TaskInfo;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * 作者  zhouqin
 * 时间 2016/6/4.
 */
@EViewGroup(R.layout.itemview_exbandablelistview_exband)
public class TaskChildItemView extends LinearLayout {
    @ViewById
    TextView txtCollector;
    @ViewById
    TextView txtCollectTime;
    @ViewById
    TextView txtTemplate;
    @ViewById
    TextView txtDescription;
    @ViewById
    Button btnOpenTask;
    @ViewById
    Button btnDeleteTask;

    private Context mContext;

    public TaskChildItemView(Context context) {
        super(context);
        mContext = context;
    }

    public void bind(TaskInfo taskInfo) {
        txtCollector.setText(taskInfo.getCollector());
        txtCollectTime.setText(taskInfo.getCollectTime());
        txtTemplate.setText(taskInfo.getTemplateName());
        txtDescription.setText(taskInfo.getDescription());
    }

    public void setBtnDeleteOnClickListener(OnClickListener listener) {
        this.btnDeleteTask.setOnClickListener(listener);
    }

    public void setBtnOpenOnClickListener(OnClickListener btnOpenOnClickListener) {
        this.btnOpenTask.setOnClickListener(btnOpenOnClickListener);

    }
}
