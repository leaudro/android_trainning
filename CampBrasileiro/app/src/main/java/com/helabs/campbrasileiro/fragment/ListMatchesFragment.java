package com.helabs.campbrasileiro.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.helabs.campbrasileiro.R;
import com.helabs.campbrasileiro.adapter.MatchAdapter;
import com.helabs.campbrasileiro.model.Match;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

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
        List<Match> matches = new Select().all().from(Match.class).execute();
        return matches;
    }

    @ItemClick(android.R.id.list)
    public void onListItemClick(int position) {
        Match m = adapter.getItem(position);
        String text = m.getHomeTeamScore() + " x " + m.getAwayTeamScore();
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
