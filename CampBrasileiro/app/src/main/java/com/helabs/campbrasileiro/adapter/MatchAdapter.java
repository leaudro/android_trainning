package com.helabs.campbrasileiro.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.helabs.campbrasileiro.model.Match;
import com.helabs.campbrasileiro.view.MatchItemView;
import com.helabs.campbrasileiro.view.MatchItemView_;

import org.androidannotations.annotations.EBean;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

@EBean
public class MatchAdapter extends AABaseAdapter<Match> implements StickyListHeadersAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = MatchItemView_.build(context);
        }

        ((MatchItemView) convertView).bind(getItem(position));

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(context);
        }

        ((TextView) convertView).setText(String.format("Rodada %d", getItem(position).getRound()));
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getRound();
    }
}