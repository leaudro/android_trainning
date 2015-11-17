package com.leaudro.series.fragment;

import android.support.v4.app.Fragment;

import com.leaudro.series.R;
import com.leaudro.series.adapter.EpisodesAdapter;
import com.leaudro.series.connection.RestConnection;
import com.leaudro.series.model.Episode;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

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

    @RestService
    RestConnection connection;

    @AfterViews
    public void init() {
        fetchData();
        listView.setDivider(null);
    }

    @Background
    void fetchData() {
        List<Episode> episodes = connection.getTvShowEpisodes(tvShowId);
        adapter.setList(episodes);
        setListAdapter(adapter);
    }

    @UiThread
    public void setListAdapter(StickyListHeadersAdapter adapter) {
        listView.setAdapter(adapter);
    }
}