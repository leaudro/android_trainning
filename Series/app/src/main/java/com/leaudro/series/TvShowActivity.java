package com.leaudro.series;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.j256.ormlite.dao.Dao;
import com.leaudro.series.adapter.TvShowPagerAdapter;
import com.leaudro.series.connection.RestConnection;
import com.leaudro.series.database.DatabaseHelper;
import com.leaudro.series.model.TvShow;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.sql.SQLException;

@EActivity(R.layout.activity_tvshow)
public class TvShowActivity extends AppCompatActivity {

    private static final long TV_SHOW_ID = 1369;

    @RestService
    RestConnection connection;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<TvShow, Long> daoTvShow;

    @ViewById
    ViewPager pager;

    @ViewById
    PagerTabStrip tabStrip;

    private TvShowPagerAdapter adapter;

    @AfterViews
    public void init() {
        adapter = new TvShowPagerAdapter(getSupportFragmentManager(), TV_SHOW_ID);
        fetchAndSaveData();
    }

    @Background
    void fetchAndSaveData() {
        TvShow tvShowInfo = connection.getTvShowInfo(TV_SHOW_ID);
        try {
            daoTvShow.createOrUpdate(tvShowInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setupAdapter();
    }

    @UiThread
    void setupAdapter() {
        pager.setAdapter(adapter);
    }
}