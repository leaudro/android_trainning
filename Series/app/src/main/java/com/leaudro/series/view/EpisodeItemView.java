package com.leaudro.series.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.leaudro.series.R;
import com.leaudro.series.model.Episode;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hemobile on 17/11/15.
 */
@EViewGroup(R.layout.item_episode)
public class EpisodeItemView extends FrameLayout {

    @ViewById
    ImageView image;

    @ViewById
    TextView textName, textEpisode, textSummary;

    public EpisodeItemView(Context context) {
        super(context);
    }

    public EpisodeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(Episode item) {
        textName.setText(item.getName());
        textEpisode.setText(String.format("S%02dE%02d", item.getSeason(), item.getNumber()));
        textSummary.setText(Html.fromHtml(item.getSummary()));
        Picasso.with(getContext())
                .load(item.getImage().getMedium())
                .into(image);
    }
}
