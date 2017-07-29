package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class Practice11PieChartView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String[] mItemNames = new String[] {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
    String[] mItemColors = new String[] {"#FFFFFF", "#9C27B0", "#9E9E9E", "#009688", "#2196F3", "#F44336", "#FFC107"};
    float[] mItemPercents = new float[] {1f, 2f, 2f, 18f, 28f, 33f, 16f};
    RectF mRectF = new RectF();
    RectF mOutRectF = new RectF();
    Rect mTextRect = new Rect();
    Path mLinePath = new Path();
    String mTitle = "饼图";

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        int textSize = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());
        int textPadding = padding / 5;
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = width < height ? width / 2 - padding : height / 2 - padding;

        mRectF.left = centerX - radius;
        mRectF.top = centerY - radius;
        mRectF.right = centerX + radius;
        mRectF.bottom = centerY + radius;

        mOutRectF.left = mRectF.left - padding / 4;
        mOutRectF.top = mRectF.top - padding / 4;
        mOutRectF.right = mRectF.right - padding / 4;
        mOutRectF.bottom = mRectF.bottom - padding / 4;

        float spaceAngle = 1f;
        float startAngle = 0f;
        float sweepAngle;
        int lineLength1 = 30;
        int lineLength2 = 50;
        for (int i = 0; i < mItemNames.length; i++) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.parseColor(mItemColors[i]));
            sweepAngle = mItemPercents[i] / 100 * 360 - spaceAngle;
            double angle = startAngle + sweepAngle / 2;
            double radians = angle * Math.PI / 180;
            double cosRadians = Math.cos(radians);
            double sinRadians = Math.sin(radians);
            float lineStartX;
            float lineStartY;
            if (mItemNames[i].equals("L")) {
                canvas.drawArc(mOutRectF, startAngle, sweepAngle, true, mPaint);
                lineStartX = mOutRectF.centerX() + (float)(radius * cosRadians);
                lineStartY = mOutRectF.centerY() + (float)(radius * sinRadians);
            } else {
                canvas.drawArc(mRectF, startAngle, sweepAngle, true, mPaint);
                lineStartX = centerX + (float)(radius * cosRadians);
                lineStartY = centerY + (float)(radius * sinRadians);
            }

            mLinePath.moveTo(lineStartX, lineStartY);
            mPaint.getTextBounds(mItemNames[i], 0, mItemNames[i].length(), mTextRect);
            float lineCosOffset = (float) Math.abs(lineLength1 * cosRadians);
            float lineSinOffset = (float) Math.abs(lineLength1 * sinRadians);
            float textX;
            float textY;
            if (angle < 90) {
                mLinePath.rLineTo(lineCosOffset, lineSinOffset);
                mLinePath.rLineTo(lineLength2, 0);
                textX = lineStartX + lineCosOffset + lineLength2 + textPadding;
                textY = lineStartY + lineSinOffset + mTextRect.height() / 2;
            } else if (angle < 180) {
                mLinePath.rLineTo(-lineCosOffset, lineSinOffset);
                mLinePath.rLineTo(-lineLength2, 0);
                textX = lineStartX - lineCosOffset - lineLength2 - textPadding - mTextRect.width();
                textY = lineStartY + lineSinOffset + mTextRect.height() / 2;
            } else if (angle < 270) {
                mLinePath.rLineTo(-lineCosOffset, -lineSinOffset);
                mLinePath.rLineTo(-lineLength2, 0);
                textX = lineStartX - lineCosOffset - lineLength2 - textPadding - mTextRect.width();
                textY = lineStartY - lineSinOffset + mTextRect.height() / 2;
            } else {
                mLinePath.rLineTo(lineCosOffset, -lineSinOffset);
                mLinePath.rLineTo(lineLength2, 0);
                textX = lineStartX + lineCosOffset + lineLength2 + textPadding;
                textY = lineStartY - lineSinOffset + mTextRect.height() / 2;
            }

            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(3);
            mPaint.setColor(Color.parseColor("#C0C0C0"));
            canvas.drawPath(mLinePath, mPaint);
            mLinePath.rewind();

            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(textSize);
            canvas.drawText(mItemNames[i], textX, textY, mPaint);

            startAngle += sweepAngle + spaceAngle;
        }

        mPaint.setTextSize((int)(textSize * 1.5));
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mTextRect);
        float x = mRectF.left + (mRectF.width() - mTextRect.width()) / 2;
        float y = mRectF.bottom + mTextRect.height() * 1.5f;
        canvas.drawText(mTitle, x,  y, mPaint);
    }
}
