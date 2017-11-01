package com.geocraft.electrics.sr.activity;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.task.RecordCommitAsyncTask;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

/**
 * Created by zhongshibu02 on 2017/11/1.
 */
@EActivity(R.layout.activity_child_line)
@OptionsMenu(R.menu.menu_new_task)
public class AddChildLineActivity extends BaseActivity {


    @OptionsItem
    void actionTaskCommit() {

    }

}
