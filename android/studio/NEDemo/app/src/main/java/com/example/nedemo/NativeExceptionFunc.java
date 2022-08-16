package com.example.nedemo;

public class NativeExceptionFunc {
    static {
        System.loadLibrary("native-exception-lib");
    }

    public static native String stringFromDynamicJni();
    public static native int nativeAdd(int a, int b);
    public static native void callStaticMethodFromJni();
    public static native void callInstanceMethodFromJni();
    public static native void nativeRegisterSignal();
    public static native void nativeKillSelf();
    public static native void nativeCrash();
}
