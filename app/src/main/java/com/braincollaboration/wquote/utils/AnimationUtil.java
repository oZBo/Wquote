package com.braincollaboration.wquote.utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by evhenii on 08.09.17.
 */

public class AnimationUtil {

    public static Animation getInFromRightAnimation() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    public static Animation getOutToLeftAnimation() {
        Animation outToLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outToLeft.setDuration(500);
        outToLeft.setInterpolator(new AccelerateInterpolator());
        return outToLeft;
    }

    public static Animation getInFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    public static Animation getOutToRightAnimation() {
        Animation outToRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outToRight.setDuration(500);
        outToRight.setInterpolator(new AccelerateInterpolator());
        return outToRight;
    }

}
