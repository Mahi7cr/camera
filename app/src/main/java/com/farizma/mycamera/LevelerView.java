package com.farizma.mycamera;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class LevelerView extends View {
    private static final int LINE_LENGTH = 300;
    private static final int LINE_WIDTH = 10;
    private static final int TEXT_SIZE = 30;

    private Paint linePaint;
    private Paint textPaint;
    private Path linePath;
    private float pitchDegrees = 0;

    public LevelerView(Context context) {
        super(context);
        init();
    }

    public LevelerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(LINE_WIDTH);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(TEXT_SIZE);

        linePath = new Path();
    }

    public void setPitchDegrees(float degrees) {
        pitchDegrees = degrees;
        invalidate(); // Redraw the view with the new orientation
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw the horizontal line
        canvas.drawLine(centerX - LINE_LENGTH / 2, centerY, centerX + LINE_LENGTH / 2, centerY, linePaint);

        // Calculate the end points of the green line
        float greenLineStartX = centerX;
        float greenLineStartY = centerY;
        float greenLineEndX = centerX + LINE_LENGTH / 2;
        float greenLineEndY = centerY - (float) (LINE_LENGTH / 2 * Math.tan(Math.toRadians(pitchDegrees)));

        // Draw the green line
        linePaint.setColor(Color.GREEN);
        canvas.drawLine(greenLineStartX, greenLineStartY, greenLineEndX, greenLineEndY, linePaint);

        // Draw the text showing the pitch angle
        String text = "Pitch: " + pitchDegrees + "°";
        float textWidth = textPaint.measureText(text);
        canvas.drawText(text, centerX - textWidth / 2, centerY - LINE_LENGTH / 2 - 20, textPaint);
    }
}
