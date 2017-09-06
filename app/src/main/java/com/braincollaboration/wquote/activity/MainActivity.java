package com.braincollaboration.wquote.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.braincollaboration.wquote.R;
import com.braincollaboration.wquote.api.ApiUtils;
import com.braincollaboration.wquote.model.LanguageType;
import com.braincollaboration.wquote.model.Quote;
import com.braincollaboration.wquote.utils.Constants;
import com.braincollaboration.wquote.widget.AlphaAnimationTextView;
import com.braincollaboration.wquote.widget.ColorAnimationRelativeLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ColorAnimationRelativeLayout parentLayout;
    private AlphaAnimationTextView quoteText;
    private AlphaAnimationTextView quoteAuthor;
    private Button refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        configureRefreshButton();
    }

    private void initWidgets() {
        parentLayout = (ColorAnimationRelativeLayout) findViewById(R.id.parent_layout);
        quoteText = (AlphaAnimationTextView) findViewById(R.id.quote_text);
        quoteAuthor = (AlphaAnimationTextView) findViewById(R.id.quote_author);
        refreshBtn = (Button) findViewById(R.id.check_service_button);
    }

    private void configureRefreshButton() {
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshQuote(LanguageType.RU);
            }
        });
    }

    private void refreshQuote(LanguageType langType) {
        ApiUtils.getForismaticService().getRandomQuote(langType.name().toLowerCase()).enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(@NonNull Call<Quote> call, @NonNull Response<Quote> response) {
                if (response.isSuccessful()) {
                    quoteText.updateTextValue(response.body().getQuoteText());
                    quoteAuthor.updateTextValue(response.body().getQuoteAuthor());
                    parentLayout.updateBackgroundColor();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Quote> call, @NonNull Throwable t) {
                Log.i(Constants.LOG_TAG, t.getMessage());
            }
        });
    }

}
