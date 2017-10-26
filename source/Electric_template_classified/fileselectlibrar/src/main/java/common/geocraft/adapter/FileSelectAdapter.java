package common.geocraft.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.geocraft.Constants;
import common.geocraft.entity.FileItem;
import common.geocraft.event.EventExistFile;
import common.geocraft.event.EventExistSelection;
import common.geocraft.event.EventIsAllFileSelected;
import common.geocraft.fileselectlibrary.FileSelectFragment;
import common.geocraft.fileselectlibrary.FileSelectItemView;
import common.geocraft.fileselectlibrary.R;

/**
 * 作者  zhouqin
 * 时间 2016/5/7.
 */
public class FileSelectAdapter extends BaseAdapter {
    private Context mContext;
    private List<FileItem> mFileItems;
    private File mCurrentParent;
    private ArrayList<String> mSuffix;
    private FileSelectFragment.SelectionMode mSelectionMode;


    public FileSelectAdapter(Context context, String currentParent, ArrayList<String> suffix, FileSelectFragment.SelectionMode mode) {
        mContext = context;
        mFileItems = new ArrayList<>();
        mSelectionMode = mode;
        mCurrentParent = new File(currentParent);
        mSuffix = suffix;
        RefreshFileList();
    }

    @Override
    public int getCount() {
        return mFileItems.size();
    }

    @Override
    public FileItem getItem(int position) {
        return mFileItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FileSelectItemView fileSelectItemView;
        if (convertView == null) {
            fileSelectItemView = new FileSelectItemView(mContext);
        } else {
            fileSelectItemView = (FileSelectItemView) convertView;
        }

        if (position % 2 == 0) {
            fileSelectItemView.setBackgroundResource(R.drawable.select_iv_bg_even);
        } else {
            fileSelectItemView.setBackgroundResource(R.drawable.selector_iv_bg_odd);
        }
        fileSelectItemView.SetFileName(getItem(position).getFileName());
        fileSelectItemView.SetOnCheckChangeListener(null);
        fileSelectItemView.SetChecked(getItem(position).isSelected());
        fileSelectItemView.SetOnCheckChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                getItem(position).setSelected(isChecked);
                EventBus.getDefault().post(new EventIsAllFileSelected(IsAllFileSelected()));
                EventBus.getDefault().post(new EventExistSelection(IsAnySelected()));
                //点到勾选时候，选择状态才会发生改变
                if (isChecked) {
                    if (getItem(position).isFile()) {
                        SetDictionaryNoneSelected();
                    } else {
                        SetAllItemsFileNotSelected();
                        SetDictionarySingleSelected(position);
                        EventBus.getDefault().post(new EventIsAllFileSelected(IsAllFileSelected()));
                    }
                }
                notifyDataSetChanged();
            }
        });

        if (mFileItems.get(position).isFile()) {
            fileSelectItemView.SetImageViewIcon(R.mipmap.ic_file);
        } else {
            fileSelectItemView.SetImageViewIcon(R.mipmap.ic_directory);
        }
        return fileSelectItemView;
    }

    public FileSelectFragment.SelectionMode getSelectionMode() {
        return mSelectionMode;
    }

    public void setSelectionMode(FileSelectFragment.SelectionMode selectionMode) {
        mSelectionMode = selectionMode;
        RefreshFileList();
    }

    public boolean IsAllFileSelected() {

        for (int i = 0; i < mFileItems.size(); i++) {
            if (mFileItems.get(i).isFile()) {
                if (!mFileItems.get(i).isSelected()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean IsAnySelected() {
        for (int i = 0; i < mFileItems.size(); i++) {
            if (mFileItems.get(i).isSelected()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> GetSelectedFullPath() {
        ArrayList<String> returnList = new ArrayList<>();
        for (int i = 0; i < mFileItems.size(); i++) {
            if (mFileItems.get(i).isSelected()) {
                String temp = mFileItems.get(i).getDirPath() + "/" + mFileItems.get(i).getFileName();
                returnList.add(temp);
            }
        }
        return returnList;
    }

    public void SetAllItemsFileSelected() {
        for (int i = 0; i < mFileItems.size(); i++) {
            if (mFileItems.get(i).isFile()) {
                mFileItems.get(i).setSelected(true);
            }
        }
    }

    public void SetAllItemsFileNotSelected() {
        for (int i = 0; i < mFileItems.size(); i++) {
            if (mFileItems.get(i).isFile()) {
                mFileItems.get(i).setSelected(false);
            }
        }
    }

    public boolean ExistsFile() {
        for (int i = 0; i < mFileItems.size(); i++) {
            if (mFileItems.get(i).isFile()) {
                return true;
            }
        }
        return false;
    }

    public void SetDictionarySingleSelected(int position) {
        for (int i = 0; i < mFileItems.size(); i++) {
            if (!mFileItems.get(i).isFile()) {
                if (i == position) {
                    mFileItems.get(i).setSelected(true);
                } else {
                    mFileItems.get(i).setSelected(false);
                }
            }
        }
    }

    public void SetDictionaryNoneSelected() {
        for (int i = 0; i < mFileItems.size(); i++) {
            if (!mFileItems.get(i).isFile()) {
                mFileItems.get(i).setSelected(false);
            }
        }
    }

    public File GetCurrentFileParent() {
        return mCurrentParent;
    }

    public boolean SetCurrentFilePath(File currentParent) {
        boolean pathChange;
        if (currentParent.isDirectory()) {
            this.mCurrentParent = currentParent;
            pathChange = true;
        } else {
            pathChange = false;
        }
        return pathChange;
    }

    public File getCurrentParent() {
        return mCurrentParent;
    }

    /**
     * 刷新文件列表
     */
    public void RefreshFileList() {

        if (mCurrentParent.isDirectory()) {

            mFileItems.clear();
            File[] fileList = mCurrentParent.listFiles();
            FileItem fileItemTemp;
            for (File file : fileList) {
                fileItemTemp = new FileItem();
                if (file.isDirectory()) {
                    fileItemTemp.setFile(false);
                    fileItemTemp.setFileName(file.getName());
                    fileItemTemp.setDirPath(mCurrentParent.getAbsolutePath());
                    mFileItems.add(fileItemTemp);
                } else if (file.isFile()) {
                    if (file.getName().contains(".")) {
                        String currentSuffix = file.getName().substring(file.getName().lastIndexOf("."));

                        if (mSuffix.contains(currentSuffix) && (mSelectionMode == FileSelectFragment.SelectionMode.File || mSelectionMode == null)) {
                            fileItemTemp.setFile(true);
                            fileItemTemp.setFileName(file.getName());
                            fileItemTemp.setDirPath(mCurrentParent.getAbsolutePath());
                            mFileItems.add(fileItemTemp);
                        }
                    }
                }
            }
            EventBus.getDefault().post(new EventExistFile(ExistsFile()));
            EventBus.getDefault().post(new EventExistSelection(IsAnySelected()));
        }
    }

}
