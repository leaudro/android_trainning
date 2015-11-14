package com.helabs.adaptertest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.helabs.adaptertest.R;
import com.helabs.adaptertest.model.Planet;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.item_planet)
public class PlanetItemView extends LinearLayout {

    @ViewById
    TextView text;

    @ViewById
    ImageView image;

    public PlanetItemView(Context context) {
        super(context);
    }

    public PlanetItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @AfterViews
    public void init() {
        setOrientation(HORIZONTAL);
    }

    public void bind(Planet planet) {
        text.setText(planet.getName());
        image.setImageResource(planet.getImgRes());
    }
}
