package com.leaudro.series.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leaudro.series.R;
import com.leaudro.series.model.Episode;
import com.leaudro.series.view.EpisodeItemView;
import com.leaudro.series.view.EpisodeItemView_;

import org.androidannotations.annotations.EBean;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by hemobile on 17/11/15.
 */
@EBean
public class EpisodesAdapter extends AABaseAdapter<Episode> implements StickyListHeadersAdapter {


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = EpisodeItemView_.build(context);
        }
        ((EpisodeItemView) convertView).bind(getItem(position));
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(context);
        }
        TextView textView = (TextView) convertView;
        textView.setText(String.format("Season %s", getItem(position).getSeason()));
        textView.setBackgroundResource(R.color.colorPrimaryDark);
        textView.setTextColor(Color.WHITE);
        textView.setPadding(16, 16, 16, 16);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getSeason();
    }
}
