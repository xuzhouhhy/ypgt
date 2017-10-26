package com.geocraft.electrics.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.geocraft.electrics.R;
import com.geocraft.electrics.base.BaseActivity;
import com.geocraft.electrics.constants.ConstPath;
import com.geocraft.electrics.ui.controller.ImportController;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import common.geocraft.event.EventGetFile;
import common.geocraft.fileselectlibrary.FileSelectFragment;


/**
 * Created by hanpengfei on 2016/6/6.
 */
@EActivity(R.layout.activity_import)
public class ImportActivity extends BaseActivity {
	private FragmentManager mFragmentManager;
	private FileSelectFragment mFileSelectFragment;

	@OptionsItem(android.R.id.home)
	void home() {
		finish();
	}

	@AfterViews
	void init() {
		if (!EventBus.getDefault().isRegistered(this)) {
			EventBus.getDefault().register(this);
		}

		mFragmentManager = getSupportFragmentManager();
		mFileSelectFragment = (FileSelectFragment) mFragmentManager.findFragmentById(R.id.lvContainer);
		if (mFileSelectFragment == null) {
			ArrayList<String> suffix = new ArrayList<>();
			suffix.add(".xls");
			mFileSelectFragment = FileSelectFragment.newInstance(ConstPath.getImportPath(), suffix);
			mFileSelectFragment.setSelectionMode(FileSelectFragment.SelectionMode.File);
			mFileSelectFragment.setBackVisibility(View.GONE);
			FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

			fragmentTransaction.add(R.id.lvContainer, mFileSelectFragment);
			fragmentTransaction.commit();


		}
	}

	@Subscribe
	public void onEvent(EventGetFile eventGetFile) {
		ArrayList<String> arrayList = eventGetFile.getFileFullPath();
		final ImportController controller = new ImportController(this, arrayList);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.app_tip);
		builder.setIcon(R.mipmap.ic_tip);
		builder.setMessage(R.string.import_tip);
		builder.setNegativeButton(R.string.app_no, null);
		builder.setInverseBackgroundForced(true);
		builder.setPositiveButton(R.string.app_yes, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (which == DialogInterface.BUTTON_POSITIVE) {
					controller.execute();
				}
			}
		}).show();
	}
}
