package com.helabs.campbrasileiro.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.helabs.campbrasileiro.model.Team;
import com.helabs.campbrasileiro.view.TeamItemView;
import com.helabs.campbrasileiro.view.TeamItemView_;

import org.androidannotations.annotations.EBean;

@EBean
public class TeamAdapter extends AABaseAdapter<Team> {

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = TeamItemView_.build(context);
        }

        ((TeamItemView) convertView).bind(getItem(position));

        return convertView;
    }

}