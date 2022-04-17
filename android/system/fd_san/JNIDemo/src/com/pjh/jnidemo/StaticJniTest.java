package com.pjh.jnidemo;

public class StaticJniTest {
    static {
        System.loadLibrary("native_static_jni");
    }

    public static native String getPackageName();



}
