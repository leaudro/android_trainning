package com.helabs.campbrasileiro.connection;

import com.helabs.campbrasileiro.model.TeamListResponse;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by hemobile on 16/11/15.
 */
@Rest(rootUrl = "http://mockbrasileirao.herokuapp.com/api", converters = GsonHttpMessageConverter.class)
public interface RestConnection {

    @Get("/teams")
    TeamListResponse getTeams();
}
