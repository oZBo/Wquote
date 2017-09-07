package com.braincollaboration.wquote.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.braincollaboration.wquote.utils.ViewUtils;
import com.braincollaboration.wquote.utils.randomcolor.RandomColor;

/**
 * Created by evhenii on 06.09.17.
 */

public class ColorAnimationRelativeLayout extends RelativeLayout {

    private ValueAnimator colorAnimation;
    private RandomColor randomColor;

    public ColorAnimationRelativeLayout(Context context) {
        this(context, null);
    }

    public ColorAnimationRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ColorAnimationRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        randomColor = new RandomColor();
    }

    public void updateBackgroundColor() {
        colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), ViewUtils.getViewBackgroundColor(this), getRandomColor());
        colorAnimation.setDuration(2000); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    private int getRandomColor() {
        return randomColor.randomColor(randomColor.pickHue(RandomColor.Color.BLUE.toString()), RandomColor.SaturationType.RANDOM, RandomColor.Luminosity.LIGHT);
    }

}
