package com.example.nedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

public class MainActivity extends AppCompatActivity {


    // Used to load the 'native-lib' library on application startup.
    Button mRegisterSignal;
    Button mKillSelf;
    Button mNullPointer;
    AppCompatCheckBox mCrashInNativeThreadCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegisterSignal = findViewById(R.id.register_signal);
        mRegisterSignal.setOnClickListener(this::onClick);
        findViewById(R.id.select_crash_type).setOnClickListener(this::onClick);
        mCrashInNativeThreadCheckBox = findViewById(R.id.crash_in_native_thread);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.register_signal) {
            NativeExceptionFunc.nativeRegisterSignal();
        } else if (id == R.id.select_crash_type) {
            Intent intent = new Intent(this, CrasherActivity.class);
            intent.putExtra(CrasherActivity.KEY_CRASH_NATIVE_THREAD, mCrashInNativeThreadCheckBox.isChecked());
            startActivity(intent);
        }
    }

}
