package common.geocraft.fileselectlibrary;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.*;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;

import common.geocraft.Constants;
import common.geocraft.adapter.FileSelectAdapter;
import common.geocraft.entity.FileItem;
import common.geocraft.event.EventExistFile;
import common.geocraft.event.EventExistSelection;
import common.geocraft.event.EventGetFile;
import common.geocraft.event.EventIsAllFileSelected;
import common.geocraft.untiltools.SDCardManager;
import common.geocraft.untiltools.T;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link .} interface
 * to handle interaction events.
 * Use the {@link FileSelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FileSelectFragment extends Fragment {
    private String mRootPath;
    private ArrayList<String> mSuffix;
    private Context mContext;
    private ListView mListViewFileList;
    private TextView mTextViewCurrentPath;

    private Button mButtonBack;
    private Button mButtonSelect;
    private Button mButtonOK;
    private SelectionMode mSelectionMode;
    private int mVisibility ;

    public enum SelectionMode{
        Folder,File;
    }
    public FileSelectFragment() {
        // Required empty public constructor
    }

    public void setBackVisibility(int visibility){
        mVisibility = visibility;
    }
    //public void setPathText

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public SelectionMode getSelectionMode() {
        return mSelectionMode;
    }

    public void setSelectionMode(SelectionMode selectionMode) {
        mSelectionMode = selectionMode;
    }

    public static FileSelectFragment newInstance(String rootPath, ArrayList<String> suffix) {
        KLog.init(BuildConfig.LOG_DEBUG);
        File file = new File(rootPath);

        if (file.exists()) {
            FileSelectFragment fragment = new FileSelectFragment();
            Bundle args = new Bundle();
            args.putString(Constants.rootPath, file.getAbsolutePath());
            args.putStringArrayList(Constants.suffix, suffix);
            fragment.setArguments(args);
            return fragment;
        } else {
            KLog.e(Constants.TagFileSelect, "path not exists");
            FileSelectFragment fragment = new FileSelectFragment();
            Bundle args = new Bundle();
            File temp = new File(SDCardManager.GetFirstSDCardPath());
            args.putString(Constants.rootPath, temp.getAbsolutePath());
            args.putStringArrayList(Constants.suffix, suffix);
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (getArguments() != null) {
            mRootPath = getArguments().getString(Constants.rootPath);
            mSuffix = getArguments().getStringArrayList(Constants.suffix);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(EventIsAllFileSelected eventIsAllFileSelected) {
        if (eventIsAllFileSelected.isAllFileSelected()) {
            mButtonSelect.setText(getString(R.string.selectNone));
        } else {
            mButtonSelect.setText(getString(R.string.selectAll));
        }
    }

    @Subscribe
    public void onEvent(EventExistFile eventExistFile) {
        if (eventExistFile.isExistsFile()) {
            mButtonSelect.setVisibility(View.VISIBLE);
        } else {
            mButtonSelect.setVisibility(View.INVISIBLE);
        }
    }

    @Subscribe
    public void onEvent(EventExistSelection eventExistSelection) {
        mButtonOK.setEnabled(eventExistSelection.isExistSelection());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = container.getContext();
        View view = inflater.inflate(R.layout.file_select_view, container, false);
        InitView(view, mContext);
        return view;
    }

    private void InitView(View view, Context context) {
        mTextViewCurrentPath = (TextView) view.findViewById(R.id.textViewCurrentPath);
        mListViewFileList = (ListView) view.findViewById(R.id.listViewFileList);
        mButtonBack = (Button) view.findViewById(R.id.buttonBack);
        mButtonOK = (Button) view.findViewById(R.id.buttonOK);
        mButtonSelect = (Button) view.findViewById(R.id.buttonSelect);
        mTextViewCurrentPath.setText(mRootPath);
        mListViewFileList.setOnItemClickListener(new OnListItemClickListener());
        mListViewFileList.setAdapter(new FileSelectAdapter(context, mRootPath, mSuffix,mSelectionMode));
        mButtonBack.setOnClickListener(new OnClickBackListener());
        mButtonSelect.setOnClickListener(new OnClickSelectListener());
        mButtonOK.setOnClickListener(new OnClickOKListener());
        mButtonBack.setVisibility(mVisibility);
    }

    private final class OnListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            try {
                FileItem temp = ((FileSelectAdapter) parent.getAdapter()).getItem(position);
                File tempFile = new File(temp.getDirPath(), temp.getFileName());
                if (!temp.isFile() && tempFile.canRead()) {
                    ((FileSelectAdapter) parent.getAdapter()).SetCurrentFilePath(tempFile);
                    mTextViewCurrentPath.setText(tempFile.getAbsolutePath());
                    ((FileSelectAdapter) parent.getAdapter()).RefreshFileList();
                    ((FileSelectAdapter) parent.getAdapter()).notifyDataSetChanged();
                } else if (temp.isFile()) {
                   T.showShort(mContext,getString(R.string.FolderRequired));
                } else if (!tempFile.canRead()) {
                    T.showShort(mContext, getString(R.string.noAuthority));
                }
            } catch (Exception e) {
                e.printStackTrace();
                KLog.e(Constants.TagFileSelect, e.getMessage());
            }
        }
    }

    private final class OnClickOKListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new EventGetFile(((FileSelectAdapter) mListViewFileList.getAdapter()).GetSelectedFullPath()));
        }
    }

    private final class OnClickSelectListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                if (mButtonSelect.getText().toString().equals(getString(R.string.selectAll))) {
                    ((FileSelectAdapter) mListViewFileList.getAdapter()).SetAllItemsFileSelected();
                    ((FileSelectAdapter) mListViewFileList.getAdapter()).SetDictionaryNoneSelected();
                    EventBus.getDefault().post(new EventIsAllFileSelected(((FileSelectAdapter) mListViewFileList.getAdapter()).IsAllFileSelected()));
                    EventBus.getDefault().post(new EventExistSelection(true));
                    ((FileSelectAdapter) mListViewFileList.getAdapter()).notifyDataSetChanged();
                } else if (mButtonSelect.getText().toString().equals(getString(R.string.selectNone))) {
                    ((FileSelectAdapter) mListViewFileList.getAdapter()).SetAllItemsFileNotSelected();
                    EventBus.getDefault().post(new EventIsAllFileSelected(((FileSelectAdapter) mListViewFileList.getAdapter()).IsAllFileSelected()));
                    EventBus.getDefault().post(new EventExistSelection(false));
                    ((FileSelectAdapter) mListViewFileList.getAdapter()).notifyDataSetChanged();
                }
            } catch (Exception e) {
                KLog.e(Constants.TagFileSelect, "onCLickSelect");
            }

        }
    }

    private final class OnClickBackListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            File currentParent = new File(mTextViewCurrentPath.getText().toString());
            if (currentParent.getParentFile() != null) {
                ((FileSelectAdapter) mListViewFileList.getAdapter()).SetCurrentFilePath(currentParent.getParentFile());
                mTextViewCurrentPath.setText(((FileSelectAdapter) mListViewFileList.getAdapter()).getCurrentParent().getAbsolutePath());
                ((FileSelectAdapter) mListViewFileList.getAdapter()).RefreshFileList();
                ((FileSelectAdapter) mListViewFileList.getAdapter()).notifyDataSetChanged();
            } else {
                T.showShort(mContext, getString(R.string.reachRootDir));
            }
        }
    }
}
