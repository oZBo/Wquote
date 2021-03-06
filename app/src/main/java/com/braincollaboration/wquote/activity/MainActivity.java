package com.braincollaboration.wquote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.braincollaboration.wquote.R;
import com.braincollaboration.wquote.api.ApiUtils;
import com.braincollaboration.wquote.model.BackgroundImageSource;
import com.braincollaboration.wquote.model.LanguageType;
import com.braincollaboration.wquote.model.Quote;
import com.braincollaboration.wquote.utils.Constants;
import com.braincollaboration.wquote.utils.InternetUtil;
import com.braincollaboration.wquote.utils.SharedPreferenceHelper;
import com.braincollaboration.wquote.widget.AlphaAnimationImageView;
import com.braincollaboration.wquote.widget.AnimationTextView;
import com.braincollaboration.wquote.widget.ButtonProgressBar;
import com.braincollaboration.wquote.widget.ColorAnimationRelativeLayout;
import com.crashlytics.android.Crashlytics;

import java.util.Random;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ColorAnimationRelativeLayout parentLayout;
    private AnimationTextView quoteText, quoteAuthor;
    private ToggleSwitch langSwitcher;
    private AlphaAnimationImageView openQuoteImage, closeQuoteImage;
    private ButtonProgressBar refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFabric();
        setContentView(R.layout.activity_main);
        initWidgets();
        configureRefreshButton();
        loadQuoteFromPrefs();
    }

    private void loadQuoteFromPrefs() {
        String savedQuote = SharedPreferenceHelper.getString(this, SharedPreferenceHelper.KEY_QOUTE, getString(R.string.default_quote));
        String savedAuthor = SharedPreferenceHelper.getString(this, SharedPreferenceHelper.KEY_AUTHOR, getString(R.string.default_quote_author));
        quoteText.setText(savedQuote);
        quoteAuthor.setText(savedAuthor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_menu_button:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
            case R.id.share_action:
                makeStringToShare();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initFabric() {
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
    }

    private void initWidgets() {
        parentLayout = (ColorAnimationRelativeLayout) findViewById(R.id.parent_layout);
        langSwitcher = (ToggleSwitch) findViewById(R.id.lang_switch);
        refreshBtn = (ButtonProgressBar) findViewById(R.id.next_quote_button);

        int i = new Random().nextInt(AlphaAnimationImageView.getBackgroundSrcSize());
        openQuoteImage = (AlphaAnimationImageView) findViewById(R.id.open_quote_image);
        openQuoteImage.setImageResource(AlphaAnimationImageView.getBackgroundSrc1()[i]);

        closeQuoteImage = (AlphaAnimationImageView) findViewById(R.id.close_quote_image);
        closeQuoteImage.setImageResource(AlphaAnimationImageView.getBackgroundSrc2()[i]);

        quoteText = (AnimationTextView) findViewById(R.id.quote_text);
        quoteAuthor = (AnimationTextView) findViewById(R.id.quote_author);
    }

    private void configureRefreshButton() {
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performUpdateQuoteClick();
            }
        });
    }

    private void performUpdateQuoteClick() {
        int lang = langSwitcher.getCheckedTogglePosition();
        if (InternetUtil.isInternetConnectionAvailable(this)) {
            refreshQuote(lang == 0 ? LanguageType.RU : LanguageType.EN);
            refreshBtn.startLoader();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshBtn.stopLoader();
                    refreshBtn.reset();
                }
            }, 1500);
        }
    }

    private void refreshQuote(LanguageType langType) {
        ApiUtils.getForismaticService().getRandomQuote(langType.name().toLowerCase()).enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(@NonNull Call<Quote> call, @NonNull Response<Quote> response) {
                if (response.isSuccessful()) {
                    updateQuoteUI(response.body().getQuoteText(), response.body().getQuoteAuthor());
                    savedQuoteToPrefs(response.body().getQuoteText(), response.body().getQuoteAuthor());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Quote> call, @NonNull Throwable t) {
                Log.e(Constants.LOG_TAG, t.getMessage());
            }
        });
    }

    private void savedQuoteToPrefs(String quote, String author) {
        SharedPreferenceHelper.putString(this, SharedPreferenceHelper.KEY_QOUTE, quote);
        SharedPreferenceHelper.putString(this, SharedPreferenceHelper.KEY_AUTHOR, author);
    }

    private void updateQuoteUI(String quoteTextValue, String quoteAuthorValue) {
        int i = new Random().nextInt(AlphaAnimationImageView.getBackgroundSrcSize());
        openQuoteImage.startAnimation(BackgroundImageSource.TOP, i);
        closeQuoteImage.startAnimation(BackgroundImageSource.BOTTOM, i);
        quoteText.updateTextValue(quoteTextValue);
        quoteAuthor.updateTextValue(quoteAuthorValue);
        parentLayout.updateBackgroundColor();
    }

    private void makeStringToShare() {
        String messageText = quoteText.getText().toString();
        messageText += "\n\n";
        messageText += quoteAuthor.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, messageText);
        String chosenTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.createChooser(intent, chosenTitle);
        startActivity(chosenIntent);
    }

}
