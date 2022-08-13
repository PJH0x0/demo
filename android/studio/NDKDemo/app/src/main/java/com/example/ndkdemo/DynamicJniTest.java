package com.example.ndkdemo;

public class DynamicJniTest {
    static {
        System.loadLibrary("native-dynamic-lib");
    }

    public static native String stringFromDynamicJni();
    public static native int nativeAdd(int a, int b);
    public static native void callStaticMethodFromJni();
    public static native void callInstanceMethodFromJni();
    public static native void nativeRegisterSignal();
    public static native void nativeCrash();
}
