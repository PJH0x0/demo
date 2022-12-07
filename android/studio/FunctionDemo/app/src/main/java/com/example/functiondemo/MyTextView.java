package com.example.jedemo;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyTextView extends TextView {
    Context mContext;
    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    public void setTypeface(@Nullable Typeface tf) {
        //super.setTypeface(tf);
        String manufacturer = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(manufacturer) && manufacturer.equals("Xiaomi")) {
            int sdkVersion = Build.VERSION.SDK_INT;
            if (sdkVersion == Build.VERSION_CODES.P || sdkVersion == Build.VERSION_CODES.Q || sdkVersion == Build.VERSION_CODES.S) {
                Object textPaintObj = ReflectionUtil.getFiledObj(this,"android.widget.TextView", "mTextPaint", null);
                Object mLayout  = ReflectionUtil.getFiledObj(this,"android.widget.TextView", "mLayout", null);
                if (textPaintObj instanceof TextPaint) {
                    TextPaint mTextPaint = (TextPaint) textPaintObj;
                    Log.d("PJH", "TextPaint = " + mTextPaint);
                    if (mTextPaint.getTypeface() != tf) {
                        mTextPaint.setTypeface(tf);

                        if (mLayout != null) {
                            ReflectionUtil.invokeMethod(this, "android.widget.TextView", "nullLayouts", null);
                            requestLayout();
                            invalidate();
                        }
                    }
                }

            } else {
                super.setTypeface(tf);
            }
        } else {
            super.setTypeface(tf);
        }

    }
}
