package com.braincollaboration.wquote.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.braincollaboration.wquote.R;
import com.braincollaboration.wquote.api.ApiUtils;
import com.braincollaboration.wquote.model.Quote;
import com.braincollaboration.wquote.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.check_service_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiUtils.getForismaticService().getRandomQuote("en").enqueue(new Callback<Quote>() {
                    @Override
                    public void onResponse(Call<Quote> call, Response<Quote> response) {
                        Log.i(Constants.LOG_TAG, "Quote Author = " + response.body().getQuoteAuthor());
                        Log.i(Constants.LOG_TAG, "Quote = " + response.body().getQuoteText());
                        Log.i(Constants.LOG_TAG, "Link = " + response.body().getQuoteLink());
                    }

                    @Override
                    public void onFailure(Call<Quote> call, Throwable t) {
                        Log.i(Constants.LOG_TAG, t.getMessage());
                    }
                });
            }
        });
    }
}
