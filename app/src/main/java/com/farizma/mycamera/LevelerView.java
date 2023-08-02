package com.farizma.mycamera;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class LevelerView extends View {

    private float pitchDegrees;
    private boolean isHorizontallyAligned;

    public LevelerView(Context context) {
        super(context);
        init();
    }

    public LevelerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LevelerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        pitchDegrees = 0.0f;
        isHorizontallyAligned = false;
    }

    public void setPitchDegrees(float pitchDegrees) {
        this.pitchDegrees = pitchDegrees;
        this.isHorizontallyAligned = pitchDegreesIsHorizontallyAligned();
        startRotationAnimation();
    }

    private void startRotationAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, pitchDegrees);
        animator.setDuration(500); // Set the animation duration here (in milliseconds)
        animator.setInterpolator(new LinearInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                pitchDegrees = animatedValue;
                invalidate(); // Redraw the view with the updated pitchDegrees value
            }
        });

        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int indicatorSize = 200;
        int indicatorMargin = 40;

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // Draw a green horizontal line (representing level) at the center of the view
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        canvas.drawLine(centerX - indicatorMargin, centerY, centerX + indicatorMargin, centerY, paint);

        // Calculate the endpoint of the indicator line based on the pitchDegrees
        float angleRadians = (float) Math.toRadians(pitchDegrees);
        float indicatorX = centerX + (indicatorSize / 2) * (float) Math.sin(angleRadians);
        float indicatorY = centerY - (indicatorSize / 2) * (float) Math.cos(angleRadians);

        // Draw the indicator line
        int indicatorColor = isHorizontallyAligned ? Color.GREEN : Color.RED;
        paint.setColor(indicatorColor);
        canvas.drawLine(centerX, centerY, indicatorX, indicatorY, paint);
    }
    private boolean pitchDegreesIsHorizontallyAligned() {
        // Threshold value to consider the device as horizontally aligned
        float threshold = 2.0f;
        return Math.abs(pitchDegrees) < threshold;
    }
}
