package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class Practice10HistogramView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String[] mItemNames = new String[] {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
    float[] mItemPercents = new float[] {1f, 2f, 2f, 18f, 28f, 33f, 16f};
    RectF mRectF = new RectF();
    RectF mDrawRectF = new RectF();
    Rect mTextRect = new Rect();
    String mTitle = "直方图";

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        int fontSize = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());
        int width = getWidth() - padding * 2;
        int height = getHeight() - padding * 2;

        mRectF.left = padding;
        mRectF.top = padding;
        mRectF.right = width + padding;
        mRectF.bottom = height + padding / 3;

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(mRectF.left, mRectF.top, mRectF.left, mRectF.bottom, mPaint);
        canvas.drawLine(mRectF.left, mRectF.bottom, mRectF.right, mRectF.bottom, mPaint);

        float itemWidth = width / (mItemNames.length + (mItemNames.length + 1) / 4f);
        float spaceWidth = itemWidth / 4;
        float maxPercent = 0f;
        float maxHeight = mRectF.height() * 0.9f;
        for (int i = 0; i < mItemPercents.length; i++) {
            if (maxPercent < mItemPercents[i]) {
                maxPercent = mItemPercents[i];
            }
        }

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(fontSize);
        for (int i = 0; i < mItemNames.length; i++) {
            mPaint.setColor(Color.parseColor("#72B916"));
            mDrawRectF.left = mRectF.left + spaceWidth * ( i + 1 ) + itemWidth * i;
            mDrawRectF.top = mRectF.bottom - maxHeight * (mItemPercents[i] / maxPercent);
            mDrawRectF.right = mDrawRectF.left + itemWidth;
            mDrawRectF.bottom = mRectF.bottom - mPaint.getStrokeWidth() / 2;
            canvas.drawRect(mDrawRectF, mPaint);

            mPaint.setColor(Color.WHITE);
            mPaint.getTextBounds(mItemNames[i], 0, mItemNames[i].length(), mTextRect);
            mDrawRectF.top = mDrawRectF.bottom + mTextRect.height();
            mDrawRectF.left = mDrawRectF.left +  (itemWidth - mTextRect.width()) / 2;
            canvas.drawText(mItemNames[i], mDrawRectF.left, mDrawRectF.top, mPaint);

        }

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize((int)(fontSize * 1.5));
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mTextRect);
        float x = mRectF.left + (mRectF.width() - mTextRect.width()) / 2;
        float y = mRectF.bottom + mTextRect.height() * 2;
        canvas.drawText(mTitle, x,  y, mPaint);
    }
}
