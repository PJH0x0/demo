package com.pjh.jedemo;

import android.app.Application;

public class JavaExceptionApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init();
    }
}
