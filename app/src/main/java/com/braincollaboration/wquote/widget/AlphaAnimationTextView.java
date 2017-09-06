package com.braincollaboration.wquote.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by evhenii on 06.09.17.
 */

public class AlphaAnimationTextView extends android.support.v7.widget.AppCompatTextView {

    private final static long ANIM_DURATION = 1000;

    private AlphaAnimation fadeIn;
    private AlphaAnimation fadeOut;
    private String currentValue;


    public AlphaAnimationTextView(Context context) {
        this(context, null);
    }

    public AlphaAnimationTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AlphaAnimationTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAnimations();
    }

    public void updateTextValue(String value) {
        if (value != null) {
            currentValue = value;
            startAnimation(fadeOut);
        }
    }

    private void initAnimations() {
        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(ANIM_DURATION);
        fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(ANIM_DURATION);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setText(currentValue);
                startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
