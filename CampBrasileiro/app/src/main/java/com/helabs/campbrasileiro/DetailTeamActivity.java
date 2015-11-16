package com.helabs.campbrasileiro;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.helabs.campbrasileiro.fragment.ListMatchesFragment_;
import com.helabs.campbrasileiro.model.Team;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;


@EActivity(R.layout.activity_detail_team)
public class DetailTeamActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @Extra
    long teamId;

    Team team;

    @AfterViews
    void init() {
        setSupportActionBar(toolbar);

        team = Team.load(Team.class, teamId);
        setTitle(team.getName());
        loadTeamLogo();

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        Fragment fragment = ListMatchesFragment_.builder().teamId(teamId).build();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }

    @OptionsItem(android.R.id.home)
    void onBack() {
        onBackPressed();
    }

    @Background
    void loadTeamLogo() {
        try {
            int size = android.R.dimen.app_icon_size;
            Bitmap logo = Picasso.with(this)
                    .load(team.getUrlLogo())
                    .resizeDimen(size, size)
                    .get();
            setLogo(logo);
        } catch (IOException e) {
            setLogo(R.mipmap.ic_launcher);
        }
    }

    @UiThread
    void setLogo(Bitmap logo) {
        toolbar.setLogo(new BitmapDrawable(getResources(), logo));
    }

    @UiThread
    void setLogo(int logoResId) {
        toolbar.setLogo(logoResId);
    }
}