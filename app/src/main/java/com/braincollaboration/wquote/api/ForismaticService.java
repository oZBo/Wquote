package com.braincollaboration.wquote.api;

import com.braincollaboration.wquote.model.Quote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by evhenii on 05.09.17.
 */

public interface ForismaticService {

    @GET("?method=getQuote&key=457653&format=json&lang")
    Call<Quote> getRandomQuote(@Query("lang") String lang);

}
