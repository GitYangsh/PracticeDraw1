package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class Practice5DrawOvalView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF mRectF = new RectF();

    public Practice5DrawOvalView(Context context) {
        super(context);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawOval() 方法画椭圆
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        int ovalWidth = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
        int ovalHeight = ovalWidth / 2;

        mRectF.left = centerX - ovalWidth;
        mRectF.top = centerY - ovalHeight;
        mRectF.right = centerX + ovalWidth;
        mRectF.bottom = centerY + ovalHeight;
        canvas.drawOval(mRectF, mPaint);
    }
}
