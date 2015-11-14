package com.helabs.campbrasileiro.fragment;

import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;

import com.helabs.campbrasileiro.adapter.MatchAdapter;
import com.helabs.campbrasileiro.model.Match;
import com.helabs.campbrasileiro.model.Team;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemobile on 14/11/15.
 */
@EFragment
public class ListMatchesFragment extends ListFragment {

    @Bean
    MatchAdapter adapter;

    @AfterViews
    public void init() {
        fetchData();
    }

    @Background
    void fetchData() {
        adapter.setList(getMatches());
        setListAdapter(adapter);
    }

    @UiThread
    @Override
    public void setListAdapter(ListAdapter adapter) {
        super.setListAdapter(adapter);
    }

    private List<Match> getMatches() {
        ArrayList<Match> matches = new ArrayList<Match>();
        Team america = new Team("América/MG", "http://futebolaovivobr.com/wp-content/uploads/2015/10/Am%C3%A9rica-MG.png");
        Team bahia = new Team("Bahia", "http://futebolaovivobr.com/wp-content/uploads/2015/10/Bahia-EC.png");
        Team botafogo = new Team("Botafogo", "http://futebolaovivobr.com/wp-content/uploads/2015/10/Botafogo-PB.png");
        Team vice = new Team("Vicetória", "http://torcidabahia.com/admin/ckfinder/UserFiles/Image/novo_escudo_do_vitoria.jpg");

        matches.add(new Match(america, 2, botafogo, 1, "Arena Independência"));
        matches.add(new Match(bahia, 3, vice, 0, "Arena Fonte Nova"));
        matches.add(new Match(bahia, 2, america, 0, "Arena Fonte Nova"));
        matches.add(new Match(vice, 0, botafogo, 2, "Lixão"));
        matches.add(new Match(botafogo, 2, bahia, 3, "Engenhão"));
        matches.add(new Match(america, 4, vice, 0, "Arena Independência"));

        return matches;
    }
}
