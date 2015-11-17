package com.helabs.campbrasileiro.connection;

import com.helabs.campbrasileiro.model.MatchesListResponse;
import com.helabs.campbrasileiro.model.TeamListResponse;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;

/**
 * Created by hemobile on 16/11/15.
 */
@Rest(rootUrl = "http://mockbrasileirao.herokuapp.com/api", converters = MyGsonHttpMessageConverter.class)
public interface RestConnection {

    @Get("/teams")
    TeamListResponse getTeams();

    @Get("/matches")
    MatchesListResponse getMatches();
}
