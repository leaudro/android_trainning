package com.helabs.campbrasileiro;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.activeandroid.ActiveAndroid;
import com.helabs.campbrasileiro.adapter.MainPagerAdapter;
import com.helabs.campbrasileiro.model.Match;
import com.helabs.campbrasileiro.model.Team;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    ViewPager pager;

    MainPagerAdapter adapter;

    @AfterViews
    public void init() {
        fetchData();
    }

    @Background
    void fetchData() {
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

        ActiveAndroid.beginTransaction();
        try {
            america.save();
            bahia.save();
            botafogo.save();
            vice.save();
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
    }

}