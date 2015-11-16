package com.helabs.campbrasileiro.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.helabs.campbrasileiro.model.Team;
import com.helabs.campbrasileiro.view.TeamItemView;
import com.helabs.campbrasileiro.view.TeamItemView_;

import org.androidannotations.annotations.EBean;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

@EBean
public class TeamAdapter extends AABaseAdapter<Team> implements StickyListHeadersAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = TeamItemView_.build(context);
        }

        ((TeamItemView) convertView).bind(getItem(position));

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) convertView;
        if (textView == null) {
            textView = new TextView(context);
            textView.setBackgroundColor(Color.BLACK);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(10, 10, 10, 10);
        }

        textView.setText(String.valueOf(getItem(position).getName().charAt(0)));
        return textView;
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getName().charAt(0);
    }
}