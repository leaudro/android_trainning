package com.helabs.adaptertest.fragment;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.helabs.adaptertest.R;
import com.helabs.adaptertest.adapter.CustomAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by hemobile on 13/11/15.
 */

@EFragment(R.layout.frag_array_adapter)
public class BaseAdapterFragment extends Fragment {

    @ViewById
    ListView myList;

    // http://pastebin.com/DTSykZYw

    @ViewById
    EditText edit;

    @ViewById
    TextView empty;

    CustomAdapter adapter;

    @AfterViews
    public void init() {
        adapter = new CustomAdapter(getActivity(), new ArrayList<String>());
        myList.setAdapter(adapter);
        myList.setEmptyView(empty);
    }

    @Click
    public void buttonAdd() {
        if (edit.getText().length() == 0) return;

        adapter.add(edit.getText().toString());
        edit.getText().clear();
    }
}
