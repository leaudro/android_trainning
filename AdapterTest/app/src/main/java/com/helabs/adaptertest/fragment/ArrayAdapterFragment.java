package com.helabs.adaptertest.fragment;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.helabs.adaptertest.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by hemobile on 13/11/15.
 */

@EFragment(R.layout.frag_array_adapter)
public class ArrayAdapterFragment extends Fragment {

    @ViewById
    ListView myList;

    @ViewById
    EditText edit;

    @ViewById
    TextView empty;

    ArrayAdapter adapter;

    @AfterViews
    public void init() {
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1);
        myList.setAdapter(adapter);
        myList.setEmptyView(empty);
    }

    @Click
    public void buttonAdd() {
        if (edit.getText().length() == 0) return;

        adapter.insert(edit.getText().toString(), 0);
        edit.getText().clear();
    }
}
