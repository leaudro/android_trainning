package com.leaudro.series.fragment;

import android.support.v4.app.Fragment;

import com.j256.ormlite.dao.Dao;
import com.leaudro.series.R;
import com.leaudro.series.adapter.EpisodesAdapter;
import com.leaudro.series.database.DatabaseHelper;
import com.leaudro.series.model.Episode;
import com.leaudro.series.service.TvShowIntentService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by hemobile on 17/11/15.
 */
@EFragment(R.layout.frag_list_episodes)
public class EpisodesListFragment extends Fragment {

    @FragmentArg
    long tvShowId;

    @Bean
    EpisodesAdapter adapter;

    @ViewById(android.R.id.list)
    StickyListHeadersListView listView;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Episode, Long> episodeDao;

    @AfterViews
    public void init() {
        listView.setDivider(null);
        fetchData();
    }

    @Receiver(actions = {TvShowIntentService.ACTION_SAVE_DONE})
    void fetchData() {
        List<Episode> episodes = null;
        try {
            episodes = episodeDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter.setList(episodes);
        setListAdapter(adapter);
    }

    @UiThread
    public void setListAdapter(StickyListHeadersAdapter adapter) {
        listView.setAdapter(adapter);
    }
}