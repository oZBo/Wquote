package com.braincollaboration.wquote.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by evhenii on 08.09.17.
 */

public class AlphaAnimationImageView extends android.support.v7.widget.AppCompatImageView {

    private final static long ANIM_DURATION = 750;

    private AlphaAnimation fadeIn;
    private AlphaAnimation fadeOut;

    public AlphaAnimationImageView(Context context) {
        this(context, null);
    }

    public AlphaAnimationImageView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AlphaAnimationImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAnimations();
    }

    public void startAnimation() {
        startAnimation(fadeOut);
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
                startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
