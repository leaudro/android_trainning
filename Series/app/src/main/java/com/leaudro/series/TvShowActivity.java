package com.leaudro.series;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.leaudro.series.adapter.TvShowPagerAdapter;
import com.leaudro.series.service.TvShowIntentService_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_tvshow)
public class TvShowActivity extends AppCompatActivity {

    public static final long TV_SHOW_ID = 161;
    //    private static final long ONE_HOUR = 60 * 60 * 1000;
    private static final long ONE_HOUR = 10 * 1000;

    @ViewById
    ViewPager pager;

    @Pref
    MyPrefs_ prefs;

    private TvShowPagerAdapter adapter;

    @AfterViews
    public void init() {
        adapter = new TvShowPagerAdapter(getSupportFragmentManager(), TV_SHOW_ID);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1, false);

//        Intent intent = new Intent(this, TvShowIntentService_.class);
//        intent.setAction("fetchAndSaveData");
//        startService(intent);
        long lastUpdate = prefs.lastUpdate().getOr(0L);
        long oneHourAgo = System.currentTimeMillis() - ONE_HOUR;
        if (lastUpdate < oneHourAgo) {
            Toast.makeText(this, "Atualizando...", Toast.LENGTH_SHORT).show();
            TvShowIntentService_.intent(this).fetchAndSaveData(TV_SHOW_ID).start();
        }
    }
}