package com.leaudro.series;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.leaudro.series.adapter.TvShowPagerAdapter;
import com.leaudro.series.service.TvShowIntentService;
import com.leaudro.series.service.TvShowIntentService_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_tvshow)
public class TvShowActivity extends AppCompatActivity {

    public static final long TV_SHOW_ID = 251;

    @ViewById
    ViewPager pager;

    @ViewById
    View loading;

    private TvShowPagerAdapter adapter;

    @AfterViews
    public void init() {
        adapter = new TvShowPagerAdapter(getSupportFragmentManager(), TV_SHOW_ID);
//        Intent intent = new Intent(this, TvShowIntentService_.class);
//        intent.setAction("fetchAndSaveData");
//        startService(intent);
        TvShowIntentService_.intent(this).fetchAndSaveData(TV_SHOW_ID).start();
    }

    @Receiver(actions = {TvShowIntentService.ACTION_SAVE_DONE})
    public void actionServiceDone() {
        setupAdapter();
    }

    @UiThread
    void setupAdapter() {
        loading.setVisibility(View.GONE);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1, false);
    }
}