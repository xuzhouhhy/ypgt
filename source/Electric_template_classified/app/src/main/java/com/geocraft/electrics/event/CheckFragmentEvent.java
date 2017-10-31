package com.geocraft.electrics.event;

/**
 * @author kingdon
 */
public class CheckFragmentEvent {
    private int mFragmentIndex;
    private boolean mIsChecked;

    public CheckFragmentEvent(int fragmentIndex, boolean isChecked) {
        mFragmentIndex = fragmentIndex;
        mIsChecked = isChecked;
    }

    public int getFragmentIndex() {
        return mFragmentIndex;
    }

    public boolean isChecked() {
        return mIsChecked;
    }
}
