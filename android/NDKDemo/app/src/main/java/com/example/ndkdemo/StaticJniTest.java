package com.example.ndkdemo;

public class StaticJniTest {
    static {
        System.loadLibrary("native-lib");
    }

    public static native String getPackageName();



}
