package com.helabs.adaptertest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hemobile on 13/11/15.
 */
public class CustomAdapter extends BaseAdapter {

    private Context context;

    private List<String> list;

    public CustomAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = View.inflate(context, android.R.layout.simple_list_item_1, null);
        }

        ((TextView) convertView).setText(getItem(position).toString());

        return convertView;
    }

    public void add(String string) {
        list.add(0,string);
        notifyDataSetChanged();
    }
}