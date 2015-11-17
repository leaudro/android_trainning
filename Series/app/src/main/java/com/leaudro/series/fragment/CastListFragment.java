package com.leaudro.series.fragment;

import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.leaudro.series.adapter.PersonAdapter;
import com.leaudro.series.database.DatabaseHelper;
import com.leaudro.series.model.Person;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.UiThread;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hemobile on 17/11/15.
 */
@EFragment
public class CastListFragment extends ListFragment {

    @FragmentArg
    Long tvShowId;

    @Bean
    PersonAdapter adapter;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Person, Long> daoPerson;

    @AfterViews
    void init() {
        fetchData();
    }

    @Background
    void fetchData() {
        List<Person> persons = null;
        try {
            QueryBuilder<Person, Long> queryBuilder = daoPerson.queryBuilder();

            PreparedQuery<Person> preparedQuery =
                    queryBuilder.where().isNotNull("character_id").prepare();

            persons = daoPerson.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter.setList(persons);
        setListAdapter(adapter);
    }

    @Override
    @UiThread
    public void setListAdapter(ListAdapter adapter) {
        super.setListAdapter(adapter);
    }
}
