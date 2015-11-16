package com.helabs.campbrasileiro.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.helabs.campbrasileiro.fragment.ListMatchesFragment_;
import com.helabs.campbrasileiro.fragment.ListTeamsFragment_;

/**
 * Created by hemobile on 16/11/15.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;
    private static final CharSequence[] TITLES = {"Times", "Partidas"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ListTeamsFragment_.builder().build();
            case 1:
                return ListMatchesFragment_.builder().build();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
