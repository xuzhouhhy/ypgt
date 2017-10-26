package com.geocraft.electrics.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * 作者  zhouqin
 * 时间 2016/6/6.
 */
public class RecordFragmentAdapter extends FragmentStatePagerAdapter {

	private Fragment mFragmentCalculateBoxNecessary;
	private Fragment mFragmentCalculateBoxBasic;
	private Fragment mFragmentCoordinate;
	private Fragment mFragmentPhoto;

	public RecordFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			default:
			case 0: {
				if (mFragmentCalculateBoxNecessary != null) {
					return mFragmentCalculateBoxNecessary;
				} else if (mFragmentCalculateBoxBasic != null) {
					return mFragmentCalculateBoxBasic;
				} else if (mFragmentPhoto != null) {
					return mFragmentPhoto;
				} else if (mFragmentCoordinate != null) {
					return mFragmentCoordinate;
				} else {
					return null;
				}
			}

			case 1: {
				if (mFragmentCalculateBoxBasic != null) {
					return mFragmentCalculateBoxBasic;
				} else if (mFragmentPhoto != null) {
					return mFragmentPhoto;
				} else if (mFragmentCoordinate != null) {
					return mFragmentCoordinate;
				} else {
					return null;
				}
			}

			case 2: {
				if (mFragmentPhoto != null) {
					return mFragmentPhoto;
				} else if (mFragmentCoordinate != null) {
					return mFragmentCoordinate;
				} else {
					return null;
				}
			}

			case 3: {
				if (mFragmentCoordinate != null) {
					return mFragmentCoordinate;
				} else {
					return null;
				}
			}
		}
	}

	public int getOffscreenPageLimit() {
		return (getCount() - 1) > 0 ? (getCount() - 1) : 0;
	}

	@Override
	public int getCount() {
		int i = 0;
		if (mFragmentCalculateBoxNecessary != null) {
			i++;
		}
		if (mFragmentCalculateBoxBasic != null) {
			i++;
		}
		if (mFragmentCoordinate != null) {
			i++;
		}
		if (mFragmentPhoto != null) {
			i++;
		}
		return i;
	}

	public void setFragment(Fragment fragmentCalculateBoxNecessary, Fragment fragmentCalculateBoxBasic,
	                        Fragment fragmentPhoto, Fragment fragmentCoordinate) {
		mFragmentCalculateBoxBasic = fragmentCalculateBoxBasic;
		mFragmentCalculateBoxNecessary = fragmentCalculateBoxNecessary;
		mFragmentCoordinate = fragmentCoordinate;
		mFragmentPhoto = fragmentPhoto;
	}
}
