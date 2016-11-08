package com.miracleshed.hpb;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 横向进度条（带动画）
 * Created by MiraclesHed on 2016/11/8.
 */

public class HorizontalProgressBar extends View {
    private String TAG = this.getClass().getSimpleName();
    private int DEFAULT_MAX_PROGRESS = 100;//默认最大进度为100
    private int DEFAULT_ANIM_DURATION = 1000;//默认动画时长1秒
    private int DEFAULT_HEIGHT = 8;//默认高度
    private int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#3F51B5");//默认背景颜色
    private int DEFAULT_PROGRESS_COLOR = Color.parseColor("#FF4081");//默认进度颜色

    private int mBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    private int mProgressColor = DEFAULT_PROGRESS_COLOR;
    private float mMaxProgress = DEFAULT_MAX_PROGRESS;
    private float mCurrentProgress = 0;
    private float mProgress = 0;
    private int mAnimDuration = DEFAULT_ANIM_DURATION;
    private int mWidth;
    private int mHeight;

    private Paint mPaint;

    public HorizontalProgressBar(Context context) {
        this(context, null);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            if (index == R.styleable.HorizontalProgressBar_backgroundColor) {
                mBackgroundColor = typedArray.getColor(index, DEFAULT_BACKGROUND_COLOR);

            } else if (index == R.styleable.HorizontalProgressBar_progressColor) {
                mProgressColor = typedArray.getColor(index, DEFAULT_PROGRESS_COLOR);

            } else if (index == R.styleable.HorizontalProgressBar_maxProgress) {
                mMaxProgress = typedArray.getInt(index, DEFAULT_MAX_PROGRESS);

            } else if (index == R.styleable.HorizontalProgressBar_progress) {
                mProgress = typedArray.getInt(index, 0);
                if (mProgress > mMaxProgress) {
                    mProgress = mMaxProgress;
                }
                mCurrentProgress = mProgress;

            } else if (index == R.styleable.HorizontalProgressBar_animDuration) {
                mAnimDuration = typedArray.getInt(index, DEFAULT_ANIM_DURATION);

            }
        }
        typedArray.recycle();

        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //高度为wrap_content时，设置为默认高度
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = DEFAULT_HEIGHT;
        }

        mWidth = widthSize;
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mBackgroundColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
        mPaint.setColor(mProgressColor);
        canvas.drawRect(0, 0, mWidth * mCurrentProgress / mMaxProgress, mHeight, mPaint);
    }

    private void startProgressWithAnim() {
        ValueAnimator valueAnim = ValueAnimator.ofFloat(mCurrentProgress, mProgress);
        valueAnim.setDuration(mAnimDuration);
        valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentProgress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnim.start();
    }

    /**
     * 设置当前进度值
     *
     * @param progress
     */
    public void setProgress(float progress) {
        mProgress = progress;
        startProgressWithAnim();
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
        invalidate();
    }

    public int getProgressColor() {
        return mProgressColor;
    }

    public void setProgressColor(int mProgressColor) {
        this.mProgressColor = mProgressColor;
        invalidate();
    }

    public float getMaxProgress() {
        return mMaxProgress;
    }

    /**
     * 设置最大进度值
     *
     * @param mMaxProgress
     */
    public void setMaxProgress(float mMaxProgress) {
        this.mMaxProgress = mMaxProgress;
        invalidate();
    }

    public float getProgress() {
        return mProgress;
    }

    public int getAnimDuration() {
        return mAnimDuration;
    }

    public void setAnimDuration(int mAnimDuration) {
        this.mAnimDuration = mAnimDuration;
    }

}
