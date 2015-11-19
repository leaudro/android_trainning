package com.leaudro.series.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.leaudro.series.R;
import com.leaudro.series.model.TvShow;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hemobile on 18/11/15.
 */
@EViewGroup(R.layout.item_tvshow)
public class TvShowItemView extends FrameLayout {

    @ViewById
    ImageView image;

    @ViewById
    TextView textTitle, textSummary;

    public TvShowItemView(Context context) {
        super(context);
    }

    public TvShowItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(TvShow tvShow) {

        if (tvShow != null) {
            textTitle.setText(tvShow.getName());
            textSummary.setText(Html.fromHtml(tvShow.getSummary()));
            Picasso.with(getContext())
                    .load(tvShow.getImage().getMedium())
                    .placeholder(R.drawable.placeholder)
                    .into(image);
        }
    }
}