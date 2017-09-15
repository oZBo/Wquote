package com.braincollaboration.wquote.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;

import com.braincollaboration.wquote.R;
import com.braincollaboration.wquote.utils.AnimationUtil;
import com.braincollaboration.wquote.utils.Constants;

/**
 * Created by evhenii on 06.09.17.
 */

public class AnimationTextView extends android.support.v7.widget.AppCompatTextView {

    private final static long ANIM_DURATION = 750;
    private final static int TEXT_STYLE_REGULAR = 1;
    private final static int TEXT_STYLE_BOLD = 2;

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
        initTypeface(attrs);
    }

    public void updateTextValue(String value) {
        if (value != null) {
            currentValue = value;
            startAnimation(fromCenterToLeft);
        }
    }

    private void initTypeface(@Nullable AttributeSet attrs) {
        Typeface face = Typeface.createFromAsset(getContext().getAssets(), Constants.CUSTOM_FONT_REGULAR);
        if (attrs != null) {
            final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AnimationTextView);
            int textStyle = a.getInteger(R.styleable.AnimationTextView_text_style, TEXT_STYLE_REGULAR);
            switch (textStyle) {
                case TEXT_STYLE_REGULAR:
                    face = Typeface.createFromAsset(getContext().getAssets(), Constants.CUSTOM_FONT_REGULAR);
                    break;
                case TEXT_STYLE_BOLD:
                    face = Typeface.createFromAsset(getContext().getAssets(), Constants.CUSTOM_FONT_BOLD);
                    break;
            }
            a.recycle();
        }
        setTypeface(face);
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
                setText(currentValue);
                startAnimation(fromRightToCenter);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
