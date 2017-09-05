package com.braincollaboration.wquote.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.braincollaboration.wquote.R;
import com.braincollaboration.wquote.api.ApiUtils;
import com.braincollaboration.wquote.model.Quote;
import com.braincollaboration.wquote.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.check_service_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiUtils.getForismaticService().getRandomQuote().enqueue(new Callback<List<Quote>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Quote>> call, @NonNull Response<List<Quote>> response) {
                        Log.i(Constants.LOG_TAG, "Quote Author = " + response.body().get(0).getQuoteAuthor());
                        Log.i(Constants.LOG_TAG, "Quote = " + response.body().get(0).getQuoteText());
                        Log.i(Constants.LOG_TAG, "Link = " + response.body().get(0).getQuoteLink());
                    }

                    @Override
                    public void onFailure(Call<List<Quote>> call, Throwable t) {

                    }
                });
            }
        });
    }
}
