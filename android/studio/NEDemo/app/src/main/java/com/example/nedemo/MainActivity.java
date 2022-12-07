package com.example.nedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    // Used to load the 'native-lib' library on application startup.
    Button mRegisterSignal;
    Button mKillSelf;
    Button mNullPointer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegisterSignal = findViewById(R.id.register_signal);
        mRegisterSignal.setOnClickListener(this::onClick);
        mKillSelf = findViewById(R.id.kill_self);
        mKillSelf.setOnClickListener(this::onClick);
        findViewById(R.id.null_pointer).setOnClickListener(this::onClick);
        findViewById(R.id.abort).setOnClickListener(this::onClick);
        findViewById(R.id.sigsegv_non_null).setOnClickListener(this::onClick);
        findViewById(R.id.functional_nullptr).setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.register_signal) {
            NativeExceptionFunc.nativeRegisterSignal();
        } else if (id == R.id.kill_self) {
            NativeExceptionFunc.nativeCrash("exit");
        } else if (id == R.id.null_pointer) {
            NativeExceptionFunc.nativeCrash("call-null");
        } else if (id == R.id.abort) {
            NativeExceptionFunc.nativeCrash("abort");
        } else if (id == R.id.sigsegv_non_null) {
            NativeExceptionFunc.nativeCrash("SIGSEGV-non-null");
        } else if (id == R.id.functional_nullptr) {
            NativeExceptionFunc.nativeCrash("functional-nullptr");
        }
    }

}
