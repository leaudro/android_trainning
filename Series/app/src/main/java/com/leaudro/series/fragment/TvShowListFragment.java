package com.leaudro.series.fragment;

import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.leaudro.series.TvShowActivity_;
import com.leaudro.series.adapter.TvShowsAdapter;
import com.leaudro.series.database.DatabaseHelper;
import com.leaudro.series.model.TvShow;
import com.leaudro.series.service.TvShowsIntentService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.Receiver;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hemobile on 18/11/15.
 */
@EFragment
public class TvShowListFragment extends ListFragment implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    @Bean
    TvShowsAdapter adapter;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<TvShow, Long> daoTvShow;

    private MenuItem menuItem;
    private String text;

    @AfterViews
    void init() {
        fetchData();
    }

    @Receiver(actions = {TvShowsIntentService.ACTION_SHOW_LIST_SAVED})
    void fetchData() {
        List<TvShow> tvShowList = null;
        try {
            if (text != null && text.trim().length() > 0) {
                PreparedQuery<TvShow> preparedQuery = daoTvShow
                        .queryBuilder()
                        .where()
                        .like(TvShow.COLUMN_NAME, text + "%").prepare();
                tvShowList = daoTvShow.query(preparedQuery);
            } else {
                tvShowList = daoTvShow.queryForAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter.setList(tvShowList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Long tvShowId = adapter.getItem(position).getId();
        TvShowActivity_.intent(this).tvShowId(tvShowId).start();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        menuItem.collapseActionView();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        text = s;
        fetchData();
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        this.menuItem = menuItem;
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        text = null;
        return true;
    }
}
