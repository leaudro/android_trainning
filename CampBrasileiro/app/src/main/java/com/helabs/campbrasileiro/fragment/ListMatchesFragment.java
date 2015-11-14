package com.helabs.campbrasileiro.fragment;

import android.support.v4.app.Fragment;

import com.helabs.campbrasileiro.R;
import com.helabs.campbrasileiro.adapter.MatchAdapter;
import com.helabs.campbrasileiro.model.Match;
import com.helabs.campbrasileiro.model.Team;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by hemobile on 14/11/15.
 */
@EFragment(R.layout.frag_matches)
public class ListMatchesFragment extends Fragment {

    @Bean
    MatchAdapter adapter;

    @ViewById(android.R.id.list)
    StickyListHeadersListView listView;

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
    public void setListAdapter(StickyListHeadersAdapter adapter) {
        listView.setAdapter(adapter);
    }

    private List<Match> getMatches() {
        ArrayList<Match> matches = new ArrayList<Match>();
        Team america = new Team("América/MG", "http://futebolaovivobr.com/wp-content/uploads/2015/10/Am%C3%A9rica-MG.png");
        Team bahia = new Team("Bahia", "http://futebolaovivobr.com/wp-content/uploads/2015/10/Bahia-EC.png");
        Team botafogo = new Team("Botafogo", "http://futebolaovivobr.com/wp-content/uploads/2015/10/Botafogo-PB.png");
        Team vice = new Team("Vicetória", "http://torcidabahia.com/admin/ckfinder/UserFiles/Image/novo_escudo_do_vitoria.jpg");

        matches.add(new Match(america, 2, botafogo, 1, "Arena Independência", 1));
        matches.add(new Match(bahia, 3, vice, 0, "Arena Fonte Nova", 1));
        matches.add(new Match(bahia, 2, america, 0, "Arena Fonte Nova", 2));
        matches.add(new Match(vice, 0, botafogo, 2, "Lixão", 2));
        matches.add(new Match(botafogo, 2, bahia, 3, "Engenhão", 3));
        matches.add(new Match(america, 4, vice, 0, "Arena Independência", 3));

        return matches;
    }
}
