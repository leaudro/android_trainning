package com.leaudro.series.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.leaudro.series.model.TvShow;
import com.leaudro.series.view.TvShowItemView;
import com.leaudro.series.view.TvShowItemView_;

import org.androidannotations.annotations.EBean;

/**
 * Created by hemobile on 17/11/15.
 */
@EBean
public class TvShowsAdapter extends AABaseAdapter<TvShow> {

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = TvShowItemView_.build(context);
        }
        ((TvShowItemView) convertView).bind(getItem(position));
        return convertView;
    }
}
