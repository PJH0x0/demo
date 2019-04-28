package com.example.ndkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        tv.setText(StaticJniTest.getPackageName());
        tv.setText(DynamicJniTest.stringFromDynamicJni());
        tv.setText(String.valueOf(DynamicJniTest.nativeAdd(3, 5)));
        DynamicJniTest.callStaticMethodFromJni();
        DynamicJniTest.callInstanceMethodFromJni();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
