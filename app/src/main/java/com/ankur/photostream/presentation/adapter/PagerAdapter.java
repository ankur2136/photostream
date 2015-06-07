package com.ankur.photostream.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ankur.photostream.presentation.fragment.PhotoListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static final int PAGES = 3;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
        case 0:
            return PhotoListFragment.newInstance(null);
        case 1:
            return PhotoListFragment.newInstance(null);
        case 2:
            return PhotoListFragment.newInstance(null);
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
        case 0:
            return "Photos";
        case 1:
            return "Videos";
        case 2:
            return "Albums";
        }
        return null;
    }

}
