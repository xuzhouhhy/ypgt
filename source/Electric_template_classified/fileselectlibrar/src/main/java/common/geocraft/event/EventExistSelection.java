package common.geocraft.event;

/**
 * 作者  zhouqin
 * 时间 2016/5/11.
 */
public class EventExistSelection {
    private boolean mExistSelection;

    public EventExistSelection(boolean existSelection) {
        mExistSelection = existSelection;
    }

    public boolean isExistSelection() {
        return mExistSelection;
    }
}
