package common.geocraft.entity;

/**
 * 作者  zhouqin
 * 时间 2016/5/6.
 */
public class FileItem {


    private boolean mIsFile;//true表示是文件，false表示是文件夹
    private String mDirPath;
    private String mFileName;
    private boolean mIsSelected;

    public FileItem(boolean isFile, String dirPath, String fileName) {
        mIsFile = isFile;
        mFileName = fileName;
        mDirPath = dirPath;
        mIsSelected = false;
    }

    public FileItem() {
        mIsFile = false;
        mFileName = "";
        mDirPath = "";
        mIsSelected = false;
    }

    public boolean isFile() {
        return mIsFile;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFile(boolean isFile) {
        mIsFile = isFile;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public String getDirPath() {
        return mDirPath;
    }

    public void setDirPath(String dirPath) {
        mDirPath = dirPath;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }
}
