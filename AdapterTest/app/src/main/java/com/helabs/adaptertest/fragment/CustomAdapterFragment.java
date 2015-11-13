package com.helabs.adaptertest.fragment;

import android.support.v4.app.Fragment;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.helabs.adaptertest.R;
import com.helabs.adaptertest.adapter.CustomAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hemobile on 13/11/15.
 */

@EFragment(R.layout.list)
public class CustomAdapterFragment extends Fragment {

    @ViewById
    ListView myList;

    BaseAdapter adapter;

    @AfterViews
    public void init() {
        List<String> list = new ArrayList();

        for (int i = 0; i < 10; i++) {
            list.add(getString(R.string.list_item_x,i));
        }

        adapter = new CustomAdapter(getActivity(), list);
        myList.setAdapter(adapter);
    }

}
