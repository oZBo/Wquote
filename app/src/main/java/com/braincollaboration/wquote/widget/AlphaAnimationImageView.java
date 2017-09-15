package com.braincollaboration.wquote.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.braincollaboration.wquote.R;
import com.braincollaboration.wquote.model.BackgroundImageSource;
import com.braincollaboration.wquote.utils.AnimationUtil;

/**
 * Created by evhenii on 08.09.17.
 */

public class AlphaAnimationImageView extends android.support.v7.widget.AppCompatImageView {

    private final static long ANIM_DURATION = 750;
    private BackgroundImageSource imageSrc;
    private int pare;
    private final static int[] backgroundSrc1 = {R.drawable.b11, R.drawable.b21, R.drawable.b31, R.drawable.b41, R.drawable.b51};
    private final static int[] backgroundSrc2 = {R.drawable.b12, R.drawable.b22, R.drawable.b32, R.drawable.b42, R.drawable.b52};

    public static int getBackgroundSrcSize() {
        return backgroundSrc1.length;
    }

    private Animation fadeIn;
    private Animation fadeOut;

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

    public void startAnimation(BackgroundImageSource src, int i) {
        imageSrc = src;
        pare = i;
        startAnimation(fadeOut);
    }

    private void initAnimations() {
        fadeIn = AnimationUtil.getInFromRightAnimation();
        fadeIn.setDuration(ANIM_DURATION);
        fadeOut = AnimationUtil.getOutToLeftAnimation();
        fadeOut.setDuration(ANIM_DURATION);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                @DrawableRes int resIde;
                if (imageSrc == BackgroundImageSource.TOP) {
                    resIde = backgroundSrc1[pare];
                } else {
                    resIde = backgroundSrc2[pare];
                }
                setImageResource(resIde);
                startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
