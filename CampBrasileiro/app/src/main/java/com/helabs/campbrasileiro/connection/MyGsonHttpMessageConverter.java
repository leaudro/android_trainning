package com.helabs.campbrasileiro.connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by hemobile on 16/11/15.
 */
public class MyGsonHttpMessageConverter extends GsonHttpMessageConverter {

    public MyGsonHttpMessageConverter() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        setGson(gson);
    }
}
