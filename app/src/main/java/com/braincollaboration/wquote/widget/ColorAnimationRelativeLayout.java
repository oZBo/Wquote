package com.braincollaboration.wquote.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.braincollaboration.wquote.R;
import com.braincollaboration.wquote.utils.ViewUtils;

import java.util.Random;

/**
 * Created by evhenii on 06.09.17.
 */

public class ColorAnimationRelativeLayout extends RelativeLayout {

    private final static long ANIM_DURATION = 1500;

    public ColorAnimationRelativeLayout(Context context) {
        this(context, null);
    }

    public ColorAnimationRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ColorAnimationRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void updateBackgroundColor() {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), ViewUtils.getViewBackgroundColor(this), getRandomColor());
        colorAnimation.setDuration(ANIM_DURATION);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                setBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }

    private int getRandomColor() {
        int[] androidColors = getResources().getIntArray(R.array.colorsList);
        return androidColors[new Random().nextInt(androidColors.length)];
    }

}
