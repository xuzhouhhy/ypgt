package common.geocraft.event;

/**
 * 作者  zhouqin
 * 时间 2016/5/11.
 */
public class EventIsAllFileSelected {
    private boolean mIsAllFileSelected;

    public EventIsAllFileSelected(boolean isAllFileSelected) {
        mIsAllFileSelected = isAllFileSelected;
    }

    public boolean isAllFileSelected() {
        return mIsAllFileSelected;
    }
}
