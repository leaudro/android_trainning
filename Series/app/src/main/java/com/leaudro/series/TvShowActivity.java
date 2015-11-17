package com.leaudro.series;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.leaudro.series.adapter.TvShowPagerAdapter;
import com.leaudro.series.connection.RestConnection;
import com.leaudro.series.database.DatabaseHelper;
import com.leaudro.series.model.Cast;
import com.leaudro.series.model.Episode;
import com.leaudro.series.model.Person;
import com.leaudro.series.model.TvShow;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

@EActivity(R.layout.activity_tvshow)
public class TvShowActivity extends AppCompatActivity {

    private static final long TV_SHOW_ID = 1369;

    @RestService
    RestConnection connection;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<TvShow, Long> daoTvShow;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Episode, Long> daoEpisodes;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Person, Long> daoPerson;

    @ViewById
    ViewPager pager;

    @ViewById
    View loading;

    private TvShowPagerAdapter adapter;

    @AfterViews
    public void init() {
        adapter = new TvShowPagerAdapter(getSupportFragmentManager(), TV_SHOW_ID);
        fetchAndSaveData();
    }

    @Background
    void fetchAndSaveData() {
        final TvShow tvShowInfo = connection.getTvShowInfo(TV_SHOW_ID);
        final List<Episode> tvShowEpisodes = connection.getTvShowEpisodes(TV_SHOW_ID);
        final List<Cast> tvShowCast = connection.getTvShowCast(TV_SHOW_ID);

        try {
            TransactionManager.callInTransaction(daoEpisodes.getConnectionSource(), new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    daoTvShow.createOrUpdate(tvShowInfo);
                    for (Episode episode : tvShowEpisodes) {
                        daoEpisodes.createOrUpdate(episode);
                    }
                    for (Cast cast : tvShowCast) {
                        Person person = cast.getPerson();
                        daoPerson.createOrUpdate(person.getCharacter());
                        daoPerson.createOrUpdate(person);
                    }
                    return null;
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
        setupAdapter();
    }

    @UiThread
    void setupAdapter() {
        loading.setVisibility(View.GONE);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1, false);
    }
}