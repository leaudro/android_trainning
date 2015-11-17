package com.helabs.campbrasileiro;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.activeandroid.ActiveAndroid;
import com.helabs.campbrasileiro.adapter.MainPagerAdapter;
import com.helabs.campbrasileiro.connection.RestConnection;
import com.helabs.campbrasileiro.model.Match;
import com.helabs.campbrasileiro.model.MatchesListResponse;
import com.helabs.campbrasileiro.model.Team;
import com.helabs.campbrasileiro.model.TeamListResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    ViewPager pager;

    @ViewById
    TabLayout tabs;

    @ViewById
    Toolbar toolbar;

    @ViewById
    View loading;

    @RestService
    RestConnection connection;

    MainPagerAdapter adapter;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
        loading.setVisibility(View.VISIBLE);
        tabs.setVisibility(View.GONE);
        fetchData();
    }

    @Background
    void fetchData() {
        TeamListResponse teams = connection.getTeams();
        MatchesListResponse matches = connection.getMatches();

        ActiveAndroid.beginTransaction();
        try {
            for (Team team : teams.getTeams()) {
                team.save();
            }

            for (Match match : matches.getMatches()) {
                Team awayTeam = match.getAwayTeam();
                match.setAwayTeam(awayTeam.getById());
                Team homeTeam = match.getHomeTeam();
                match.setHomeTeam(homeTeam.getById());
                match.save();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ActiveAndroid.setTransactionSuccessful();
        }
        ActiveAndroid.endTransaction();

        setupViewPager();
    }

    @UiThread
    void setupViewPager() {
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabs.setupWithViewPager(pager);

        tabs.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }
}