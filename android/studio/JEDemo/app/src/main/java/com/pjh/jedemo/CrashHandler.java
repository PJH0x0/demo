package com.pjh.jedemo;

import android.util.Log;

import androidx.annotation.NonNull;

public class CrashHandler implements Thread.UncaughtExceptionHandler{
    private CrashHandler(){}
    public void init() {
        //Thread.setDefaultUncaughtExceptionHandler(this);
        //Thread.setUncaughtExceptionPreHandler(this);
    }
    private static CrashHandler mInstance;
    public static CrashHandler getInstance(){
        if (null == mInstance) {
            mInstance = new CrashHandler();
        }
        return mInstance;
    }
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.d("PJH", "Self Exception");
    }
}
