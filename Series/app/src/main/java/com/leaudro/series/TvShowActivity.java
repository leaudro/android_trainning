package com.leaudro.series;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.leaudro.series.adapter.TvShowPagerAdapter;
import com.leaudro.series.service.TvShowIntentService_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_tvshow)
public class TvShowActivity extends AppCompatActivity {

    public static final long TV_SHOW_ID = 1369;

    @ViewById
    ViewPager pager;

    private TvShowPagerAdapter adapter;

    @AfterViews
    public void init() {
        adapter = new TvShowPagerAdapter(getSupportFragmentManager(), TV_SHOW_ID);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1, false);

//        Intent intent = new Intent(this, TvShowIntentService_.class);
//        intent.setAction("fetchAndSaveData");
//        startService(intent);
        TvShowIntentService_.intent(this).fetchAndSaveData(TV_SHOW_ID).start();
    }
}