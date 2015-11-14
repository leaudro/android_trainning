package com.helabs.campbrasileiro.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.helabs.campbrasileiro.model.Match;
import com.helabs.campbrasileiro.view.MatchItemView;
import com.helabs.campbrasileiro.view.MatchItemView_;

import org.androidannotations.annotations.EBean;

@EBean
public class MatchAdapter extends AABaseAdapter<Match> {

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = MatchItemView_.build(context);
        }

        ((MatchItemView) convertView).bind(getItem(position));

        return convertView;
    }
}