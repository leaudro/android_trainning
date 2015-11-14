package com.helabs.campbrasileiro.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.helabs.campbrasileiro.R;
import com.helabs.campbrasileiro.model.Team;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hemobile on 14/11/15.
 */
@EViewGroup(R.layout.item_team)
public class TeamItemView extends LinearLayout {

    @ViewById
    TextView text;

    @ViewById
    ImageView image;

    public TeamItemView(Context context) {
        super(context);
    }

    public TeamItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @AfterViews
    public void init() {
        setOrientation(HORIZONTAL);
    }

    public void bind(Team team) {
        text.setText(team.getName());
        Picasso.with(getContext())
                .load(team.getUrlLogo())
                .placeholder(R.mipmap.ic_launcher)
                .resize(100, 100)
                .into(image);
    }
}
