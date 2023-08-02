package com.farizma.mycamera;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GridView extends View {
    private final int GRID_LINE_COUNT = 3;
    private final int GRID_LINE_COLOR = Color.WHITE;

    private Paint gridPaint;

    public GridView(Context context) {
        this(context, null);
    }

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setColor(GRID_LINE_COLOR);
        gridPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int gridWidth = width / GRID_LINE_COUNT;
        int gridHeight = height / GRID_LINE_COUNT;

        // Draw horizontal grid lines
        for (int i = 1; i < GRID_LINE_COUNT; i++) {
            int y = i * gridHeight;
            canvas.drawLine(0, y, width, y, gridPaint);
        }

        // Draw vertical grid lines
        for (int i = 1; i < GRID_LINE_COUNT; i++) {
            int x = i * gridWidth;
            canvas.drawLine(x, 0, x, height, gridPaint);
        }
    }
}
