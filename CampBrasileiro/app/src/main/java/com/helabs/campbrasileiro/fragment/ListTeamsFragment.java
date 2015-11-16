package com.helabs.campbrasileiro.fragment;

import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.helabs.campbrasileiro.adapter.TeamAdapter;
import com.helabs.campbrasileiro.model.Team;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.List;

@EFragment
public class ListTeamsFragment extends ListFragment {

    @Bean
    TeamAdapter adapter;

    @AfterViews
    public void init() {
        fetchData();
    }

    @Background
    void fetchData() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adapter.setList(getTeams());
        setListAdapter(adapter);
    }

    @UiThread
    @Override
    public void setListAdapter(ListAdapter adapter) {
        super.setListAdapter(adapter);
    }

    private List<Team> getTeams() {
        List<Team> teams = new Select().all().from(Team.class).execute();
        return teams;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String text = adapter.getItem(position).getName();
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}