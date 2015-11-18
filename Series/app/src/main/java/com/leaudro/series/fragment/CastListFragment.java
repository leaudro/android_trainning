package com.leaudro.series.fragment;

import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.leaudro.series.adapter.PersonAdapter;
import com.leaudro.series.database.DatabaseHelper;
import com.leaudro.series.model.Person;
import com.leaudro.series.model.PersonTvShow;
import com.leaudro.series.service.TvShowInfoIntentService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.Receiver;
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
    Dao<PersonTvShow, Long> daoPersonTvShow;

    @AfterViews
    void init() {
        fetchData();
    }

    @Receiver(actions = {TvShowInfoIntentService.ACTION_SAVE_DONE})
    void fetchData() {
        List<Person> persons = null;
        try {
            QueryBuilder<PersonTvShow, Long> queryBuilder = daoPersonTvShow.queryBuilder();

            PreparedQuery<PersonTvShow> preparedQuery =
                    queryBuilder.where().eq(PersonTvShow.COLUMN_TVSHOW, tvShowId).prepare();

            persons = PersonTvShow.toPersonList(daoPersonTvShow.query(preparedQuery));
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
