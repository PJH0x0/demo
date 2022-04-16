package com.example.ndkdemo;

import android.content.Context;
import android.util.Log;

public class CallMethodFromJni {
    private static final String TAG = "CallMethodFromJni";
    private Context mContext;
    public static void staticMethod(String fromJni) {
        Log.d(TAG, "CallMethodFromJni.callStaticMethodFromJni: " + fromJni);
    }

    public CallMethodFromJni() {
        Log.d(TAG, "CallMethodFromJni.CallMethodFromJni: CallMethodFromJni() initial");
    }

    public CallMethodFromJni(String fromJni) {
        Log.d(TAG, "CallMethodFromJni.CallMethodFromJni: " + fromJni);
    }

    public void instanceMethod(String fromJni) {
        Log.d(TAG, "CallMethodFromJni.callInstanceMethodFromJni: " + fromJni);
    }

    public void showJniToast() {

    }
}
