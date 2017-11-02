package com.geocraft.electrics.sr.event;

/**
 * Created by zhongshibu02 on 2017/11/1.
 */

public class RenameEvent {

    private boolean mIsSuccess;

    public RenameEvent(boolean result) {
        mIsSuccess = result;
    }

    public boolean isIsSuccess() {
        return mIsSuccess;
    }
}
