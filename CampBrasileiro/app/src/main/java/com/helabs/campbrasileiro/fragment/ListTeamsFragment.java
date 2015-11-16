package com.helabs.campbrasileiro.fragment;

import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;

import com.activeandroid.query.Select;
import com.helabs.campbrasileiro.adapter.TeamAdapter;
import com.helabs.campbrasileiro.model.Team;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
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
        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(new Team("América/MG", "http://futebolaovivobr.com/wp-content/uploads/2015/10/Am%C3%A9rica-MG.png"));
        teams.add(new Team("Bahia", "http://futebolaovivobr.com/wp-content/uploads/2015/10/Bahia-EC.png"));
        teams.add(new Team("Botafogo", "http://futebolaovivobr.com/wp-content/uploads/2015/10/Botafogo-PB.png"));
        teams.add(new Team("Vicetória", "http://torcidabahia.com/admin/ckfinder/UserFiles/Image/novo_escudo_do_vitoria.jpg"));

        for (Team team : teams) {
            team.save();
        }

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
}