package com.leaudro.series.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.leaudro.series.fragment.CastListFragment_;
import com.leaudro.series.fragment.EpisodesListFragment_;
import com.leaudro.series.fragment.TvShowInfoFragment_;

public class TvShowPagerAdapter extends FragmentPagerAdapter {

    private static final CharSequence[] TITLE = {"Episodes", "Info", "Cast"};
    private long tvShowId;

    public TvShowPagerAdapter(FragmentManager fm, long tvShowId) {
        super(fm);
        this.tvShowId = tvShowId;
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EpisodesListFragment_.builder().tvShowId(tvShowId).build();
            case 1:
                return TvShowInfoFragment_.builder().tvShowId(tvShowId).build();
            case 2:
                return CastListFragment_.builder().tvShowId(tvShowId).build();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position];
    }
}
