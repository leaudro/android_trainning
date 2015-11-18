package com.leaudro.series.fragment;

import android.support.v4.app.ListFragment;

import com.j256.ormlite.dao.Dao;
import com.leaudro.series.adapter.TvShowsAdapter;
import com.leaudro.series.database.DatabaseHelper;
import com.leaudro.series.model.TvShow;
import com.leaudro.series.service.TvShowsIntentService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.Receiver;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hemobile on 18/11/15.
 */
@EFragment
public class TvShowListFragment extends ListFragment {

    @Bean
    TvShowsAdapter adapter;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<TvShow, Long> daoTvShow;

    @AfterViews
    void init() {
        fetchData();
    }

    @Receiver(actions = {TvShowsIntentService.ACTION_SHOW_LIST_SAVED})
    void fetchData() {
        List<TvShow> tvShowList = null;
        try {
            tvShowList = daoTvShow.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter.setList(tvShowList);
        setListAdapter(adapter);
    }
}
