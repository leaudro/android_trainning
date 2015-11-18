package com.leaudro.series.service;

import android.content.Intent;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.leaudro.series.connection.RestConnection;
import com.leaudro.series.database.DatabaseHelper;
import com.leaudro.series.model.Cast;
import com.leaudro.series.model.Episode;
import com.leaudro.series.model.Person;
import com.leaudro.series.model.TvShow;

import org.androidannotations.annotations.EIntentService;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ServiceAction;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.support.app.AbstractIntentService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by hemobile on 18/11/15.
 */
@EIntentService
public class TvShowIntentService extends AbstractIntentService {

    public static final String ACTION_SAVE_DONE = "ACTION_SAVE_DONE";

    @RestService
    RestConnection connection;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<TvShow, Long> daoTvShow;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Episode, Long> daoEpisodes;

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Person, Long> daoPerson;

    public TvShowIntentService() {
        super("TvShowIntentService");
    }

    @ServiceAction
    void fetchAndSaveData(long tvShowId) {
        final TvShow tvShowInfo = connection.getTvShowInfo(tvShowId);
        final List<Episode> tvShowEpisodes = connection.getTvShowEpisodes(tvShowId);
        final List<Cast> tvShowCast = connection.getTvShowCast(tvShowId);

        try {
            TransactionManager.callInTransaction(daoEpisodes.getConnectionSource(), new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    daoTvShow.createOrUpdate(tvShowInfo);
                    for (Episode episode : tvShowEpisodes) {
                        daoEpisodes.createOrUpdate(episode);
                    }
                    for (Cast cast : tvShowCast) {
                        Person person = cast.getPerson();
                        daoPerson.createOrUpdate(person.getCharacter());
                        daoPerson.createOrUpdate(person);
                    }
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(ACTION_SAVE_DONE);
        sendBroadcast(intent);
    }

}
