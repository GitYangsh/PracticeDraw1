package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class Practice9DrawPathView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF mRectF = new RectF();
    Path mPath = new Path();

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        int halfWidth = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        int halfHeight = (int)(halfWidth * 0.9);
        int radius = halfWidth / 2;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int startY = centerY - (halfHeight - radius);

        mPath.moveTo(centerX, startY);
        mRectF.left = centerX - halfWidth;
        mRectF.top = startY - radius;
        mRectF.right = centerX;
        mRectF.bottom = startY + radius;
        mPath.addArc(mRectF, 0, -225);
        mPath.lineTo(centerX, centerY + halfHeight);

        mPath.moveTo(centerX, startY);
        mRectF.left = centerX;
        mRectF.top = startY - radius;
        mRectF.right = centerX + halfWidth;
        mRectF.bottom = startY + radius;
        mPath.arcTo(mRectF, -180, 225);
        mPath.lineTo(centerX, centerY + halfHeight);
        canvas.drawPath(mPath, mPaint);
    }
}
