package com.helabs.adaptertest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.helabs.adaptertest.R;
import com.helabs.adaptertest.model.Planet;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class PlanetsAdapter extends BaseAdapter {

    @RootContext
    Context context;

    private List<Planet> list;

    @Override
    public int getCount() {
        return getList().size();
    }

    @Override
    public Object getItem(int position) {
        return getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_planet, null);
        }
        Planet item = (Planet) getItem(position);

        TextView textView = (TextView) convertView.findViewById(R.id.text);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);

        textView.setText(item.getName());
        imageView.setImageResource(item.getImgRes());

        return convertView;
    }

    public void setList(List<Planet> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Planet> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }

        return list;
    }
}