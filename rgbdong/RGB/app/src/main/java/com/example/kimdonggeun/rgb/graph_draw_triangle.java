package com.example.kimdonggeun.rgb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class graph_draw_triangle extends View {
        private int r,g,b;

    public graph_draw_triangle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = this.getWidth();
        int centerY = this.getHeight();

        Paint mPaint = new Paint();
        mPaint.setColor(Color.rgb(getR(),getG(),getB()));
        mPaint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(0 , 0); //위쪽 모서리
        path.lineTo(centerX, centerY);//오른쪽 모서리
        path.lineTo(0 , centerY); //왼쪽 모서리
        path.lineTo(0 , 0);//MoveTo랑 경로가 같아야됨

        path.close();

        canvas.drawPath(path,mPaint);
    }

}
