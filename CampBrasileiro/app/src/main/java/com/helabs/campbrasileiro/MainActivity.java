package com.helabs.campbrasileiro;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.activeandroid.ActiveAndroid;
import com.helabs.campbrasileiro.adapter.MainPagerAdapter;
import com.helabs.campbrasileiro.connection.RestConnection;
import com.helabs.campbrasileiro.model.Match;
import com.helabs.campbrasileiro.model.Team;
import com.helabs.campbrasileiro.model.TeamListResponse;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    ViewPager pager;

    @ViewById
    TabLayout tabs;

    @ViewById
    Toolbar toolbar;

    @RestService
    RestConnection connection;

    MainPagerAdapter adapter;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
        fetchData();
    }

    @Background
    void fetchData() {
        ArrayList<Match> matches = new ArrayList<Match>();

        TeamListResponse teams = connection.getTeams();

        ActiveAndroid.beginTransaction();
        try {
            for (Team team : teams.getTeams()) {
                team.save();
            }
            for (Match match : matches) {
                match.save();
            }
        } catch (Exception e) {
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
    }

}