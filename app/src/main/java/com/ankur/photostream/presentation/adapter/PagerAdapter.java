package com.ankur.photostream.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static final int  PAGES = 2;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
        case 0:
            return new Fragment();
        case 1:
            return new Fragment();
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
        }
        return null;
    }

}
