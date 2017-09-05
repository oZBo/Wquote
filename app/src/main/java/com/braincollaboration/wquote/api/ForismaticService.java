package com.braincollaboration.wquote.api;

import com.braincollaboration.wquote.model.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by evhenii on 05.09.17.
 */

public interface ForismaticService {

    @GET("?method=getQuote&key=457653&format=json&lang=en")
    Call<List<Quote>> getRandomQuote();

}
