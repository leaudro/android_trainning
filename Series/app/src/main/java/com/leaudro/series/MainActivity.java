package com.leaudro.series;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.leaudro.series.service.TvShowsIntentService_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity {

    private static final long ONE_HOUR = 60 * 60 * 1000;

    @Pref
    MyPrefs_ prefs;

    @AfterViews
    public void init() {
        long lastUpdate = prefs.lastUpdate().getOr(0L);
        long oneHourAgo = System.currentTimeMillis() - ONE_HOUR;
        if (lastUpdate < oneHourAgo) {
            Toast.makeText(this, "Atualizando...", Toast.LENGTH_SHORT).show();
            TvShowsIntentService_.intent(this).fetchAndSaveData().start();
        }
    }
}