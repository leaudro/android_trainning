package com.leaudro.series.connection;

import com.leaudro.series.model.Cast;
import com.leaudro.series.model.Episode;
import com.leaudro.series.model.TvShow;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.List;

/**
 * Created by hemobile on 17/11/15.
 */
@Rest(rootUrl = "http://api.tvmaze.com", converters = GsonHttpMessageConverter.class)
public interface RestConnection {

    @Get("/shows/{id}")
    TvShow getTvShowInfo(long id);

    @Get("/shows/{id}/episodes")
    List<Episode> getTvShowEpisodes(long id);

    @Get("/shows/{id}/cast")
    List<Cast> getTvShowCast(long id);
}