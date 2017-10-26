package common.geocraft.event;

import java.util.ArrayList;

/**
 * 作者  zhouqin
 * 时间 2016/5/7.
 */
public class EventGetFile {
    private ArrayList<String> mFileFullPathList;

    public EventGetFile(ArrayList<String> fileFullPath) {
        mFileFullPathList = fileFullPath;
    }

    public ArrayList<String> getFileFullPath() {
        return mFileFullPathList;
    }
}
