package com.braincollaboration.wquote.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;

import com.braincollaboration.wquote.R;
import com.braincollaboration.wquote.utils.AnimationUtil;

/**
 * Created by evhenii on 06.09.17.
 */

public class AnimationTextView extends android.support.v7.widget.AppCompatTextView {

    private final static long ANIM_DURATION = 750;

    private Animation fromRightToCenter, fromCenterToLeft;
    private String currentValue;

    public AnimationTextView(Context context) {
        this(context, null);
    }

    public AnimationTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AnimationTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAnimations();
    }

    public void updateTextValue(String value) {
        if (value != null) {
            currentValue = value;
            startAnimation(fromCenterToLeft);
        }
    }

    private void initAnimations() {
        fromCenterToLeft = AnimationUtil.getOutToLeftAnimation();
        fromCenterToLeft.setDuration(ANIM_DURATION);
        fromRightToCenter = AnimationUtil.getInFromRightAnimation();
        fromRightToCenter.setDuration(ANIM_DURATION);
        fromCenterToLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                if (getId() == R.id.quote_text) {
//                    if (currentValue.length() > 190) {
//                        setTextSize(18);
//                    } else {
//                        setTextSize(25);
//                    }
//                }
                setText(currentValue);
                startAnimation(fromRightToCenter);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
