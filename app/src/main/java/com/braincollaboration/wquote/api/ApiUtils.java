package com.braincollaboration.wquote.api;

/**
 * Created by evhenii on 05.09.17.
 */

public class ApiUtils {

    private final static String BASE_URL = "https://api.forismatic.com/api/1.0/";

    public static ForismaticService getForismaticService() {
        return RetrofitClient.getClient(BASE_URL).create(ForismaticService.class);
    }

}
