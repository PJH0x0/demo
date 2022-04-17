package com.pjh.jnidemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;


public class MainActivity extends Activity {

    public static final String TAG = "JNIDemo_Java";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native_static_jni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        Log.d(TAG, "stringFromJNI: " + stringFromJNI());
        Log.d(TAG, "StaticJniTest.getPackageName: " + StaticJniTest.getPackageName());
        Log.d(TAG, "DynamicJniTest.stringFromDynamicJni: " + DynamicJniTest.stringFromDynamicJni());
        Log.d(TAG, "DynamicJniTest.nativeAdd: " + String.valueOf(DynamicJniTest.nativeAdd(3, 5)));
        CallMethodFromJni jni = new CallMethodFromJni();
        DynamicJniTest.callStaticMethodFromJni();
        DynamicJniTest.callInstanceMethodFromJni();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
