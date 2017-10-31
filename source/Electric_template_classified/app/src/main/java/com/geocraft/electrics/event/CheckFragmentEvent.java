package com.geocraft.electrics.event;

/**
 * @author kingdon
 */
public class CheckFragmentEvent {
    private String mFragmentNameKey;
    private boolean mIsChecked;

    public CheckFragmentEvent(String fragmentNameKey, boolean isChecked) {
        mFragmentNameKey = fragmentNameKey;
        mIsChecked = isChecked;
    }

    public String getFragmentNameKey() {
        return mFragmentNameKey;
    }

    public void setFragmentNameKey(String fragmentNameKey) {
        mFragmentNameKey = fragmentNameKey;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }
}
