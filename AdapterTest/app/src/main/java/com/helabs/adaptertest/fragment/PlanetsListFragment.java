package com.helabs.adaptertest.fragment;

import android.support.v4.app.ListFragment;

import com.helabs.adaptertest.R;
import com.helabs.adaptertest.adapter.PlanetsAdapter;
import com.helabs.adaptertest.model.Planet;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemobile on 13/11/15.
 */

@EFragment
public class PlanetsListFragment extends ListFragment {

    @Bean
    PlanetsAdapter adapter;

    @AfterViews
    public void init() {
        simulateHeavyProcessing();
    }

    @UiThread
    void setupList() {
        adapter.setList(getPlanets());
        setListAdapter(adapter);
    }

    @Background
    void simulateHeavyProcessing() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setupList();
    }

    private List<Planet> getPlanets() {
        ArrayList<Planet> planets = new ArrayList<Planet>();
        planets.add(new Planet("Mercúrio", R.drawable.merkur));
        planets.add(new Planet("Venus", R.drawable.venera));
        planets.add(new Planet("Terra", R.drawable.zemlja));
        planets.add(new Planet("Marte", R.drawable.mars));
        planets.add(new Planet("Jupiter", R.drawable.jupiter));
        planets.add(new Planet("Saturno", R.drawable.saturn));
        planets.add(new Planet("Urano", R.drawable.uran));
        planets.add(new Planet("Netuno", R.drawable.neptun));
        planets.add(new Planet("Plutão", R.drawable.pluton));

        return planets;
    }
}