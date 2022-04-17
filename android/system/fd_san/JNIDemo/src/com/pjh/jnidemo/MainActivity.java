package com.pjh.jnidemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity {

    public static final String TAG = "JNIDemo_Java";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native_static_jni");
    }

    Button mButton;
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
        DynamicJniTest.callStaticMethodFromJni();
        DynamicJniTest.callInstanceMethodFromJni();
        mButton = findViewById(R.id.fdsan_test);
        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DynamicJniTest.nativeFdsanTest();
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
