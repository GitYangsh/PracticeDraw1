package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class Practice8DrawArcView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF mRectF = new RectF();

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        int arcWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
        int arcHeight = arcWidth / 3 * 2;

        mRectF.left = centerX - arcWidth;
        mRectF.top = centerY - arcHeight;
        mRectF.right = centerX + arcWidth;
        mRectF.bottom = centerY + arcHeight;

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(mRectF, 180, 60, false, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(mRectF, -110, 90, true, mPaint);
        canvas.drawArc(mRectF, 15, 150, false, mPaint);
    }
}
