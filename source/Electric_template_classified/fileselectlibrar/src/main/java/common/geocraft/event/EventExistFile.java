package common.geocraft.event;

/**
 * 作者  zhouqin
 * 时间 2016/5/11.
 */
public class EventExistFile {
    private boolean mExistsFile;

    public EventExistFile(boolean existsFile) {
        mExistsFile = existsFile;
    }

    public boolean isExistsFile() {
        return mExistsFile;
    }
}
