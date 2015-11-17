package com.leaudro.series.fragment;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.leaudro.series.R;
import com.leaudro.series.database.DatabaseHelper;
import com.leaudro.series.model.TvShow;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;

/**
 * Created by hemobile on 17/11/15.
 */
@EFragment(R.layout.frag_tvshow_info)
public class TvShowInfoFragment extends Fragment {

    @FragmentArg
    Long tvShowId;

    @ViewById
    ImageView image;

    @ViewById
    TextView textTitle, textSummary;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<TvShow, Long> daoTvShow;

    private TvShow tvShow;

    @AfterViews
    void init() {
        try {
            tvShow = daoTvShow.queryForId(tvShowId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (tvShow != null) {
            textTitle.setText(tvShow.getName());
            textSummary.setText(Html.fromHtml(tvShow.getSummary()));
            Picasso.with(getActivity())
                    .load(tvShow.getImage().getMedium())
                    .into(image);
        }
    }
}
