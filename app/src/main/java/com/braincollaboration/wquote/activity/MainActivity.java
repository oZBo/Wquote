package com.braincollaboration.wquote.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.braincollaboration.wquote.R;
import com.braincollaboration.wquote.api.ApiUtils;
import com.braincollaboration.wquote.model.BackgroundImageSource;
import com.braincollaboration.wquote.model.LanguageType;
import com.braincollaboration.wquote.model.Quote;
import com.braincollaboration.wquote.utils.Constants;
import com.braincollaboration.wquote.widget.AlphaAnimationImageView;
import com.braincollaboration.wquote.widget.AnimationTextView;
import com.braincollaboration.wquote.widget.ColorAnimationRelativeLayout;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ColorAnimationRelativeLayout parentLayout;
    private AnimationTextView quoteText, quoteAuthor;
    private AlphaAnimationImageView openQuoteImage, closeQuoteImage;
    private Button refreshBtn;
    private float x1, x2;
    static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        configureRefreshButton();
    }

    private void initWidgets() {
        parentLayout = (ColorAnimationRelativeLayout) findViewById(R.id.parent_layout);

        Typeface face = Typeface.createFromAsset(getAssets(), Constants.CUSTOM_FONT_REGULAR);
        quoteText = (AnimationTextView) findViewById(R.id.quote_text);
        quoteText.setTypeface(face);

        face = Typeface.createFromAsset(getAssets(), Constants.CUSTOM_FONT_BOLD);
        quoteAuthor = (AnimationTextView) findViewById(R.id.quote_author);
        quoteAuthor.setTypeface(face);

        openQuoteImage = (AlphaAnimationImageView) findViewById(R.id.open_quote_image);

        closeQuoteImage = (AlphaAnimationImageView) findViewById(R.id.close_quote_image);

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
                    updateQuoteUI(response.body().getQuoteText(), response.body().getQuoteAuthor());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Quote> call, @NonNull Throwable t) {
                Log.e(Constants.LOG_TAG, t.getMessage());
            }
        });
    }

    private void updateQuoteUI(String quoteTextValue, String quoteAuthorValue) {
        int i = new Random().nextInt(AlphaAnimationImageView.getBackgroundSrcSize());
        Log.d("LOG_TAG", Integer.toString(quoteTextValue.length()));
        Log.d("LOG_TAG", Float.toString(quoteText.getTextSize()));
        openQuoteImage.startAnimation(BackgroundImageSource.TOP, i);
        closeQuoteImage.startAnimation(BackgroundImageSource.BOTTOM, i);
        quoteText.updateTextValue(quoteTextValue);
        quoteAuthor.updateTextValue(quoteAuthorValue);
        parentLayout.updateBackgroundColor();
    }

}
