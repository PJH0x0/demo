package com.pjh.jnidemo;

public class DynamicJniTest {
    static {
        System.loadLibrary("native_dynamic_jni");
    }

    public static native String stringFromDynamicJni();
    public static native int nativeAdd(int a, int b);
    public static native void callStaticMethodFromJni();
    public static native void callInstanceMethodFromJni();
}
