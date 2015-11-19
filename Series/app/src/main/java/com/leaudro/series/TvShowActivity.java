package com.leaudro.series;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.leaudro.series.adapter.TvShowPagerAdapter;
import com.leaudro.series.service.TvShowInfoIntentService_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_tvshow)
public class TvShowActivity extends AppCompatActivity {

    //    private static final long ONE_HOUR = 60 * 60 * 1000;
    private static final long ONE_HOUR = 10 * 1000;

    @Extra
    Long tvShowId;
    
    @ViewById
    ViewPager pager;

    @Pref
    MyPrefs_ prefs;

    private TvShowPagerAdapter adapter;

    @AfterViews
    public void init() {
        adapter = new TvShowPagerAdapter(getSupportFragmentManager(), tvShowId);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1, false);
        pager.setOffscreenPageLimit(2);

        TvShowInfoIntentService_.intent(this).fetchAndSaveData(tvShowId).start();
    }
}