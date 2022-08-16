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
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.register_signal) {
            NativeExceptionFunc.nativeRegisterSignal();
        } else if (id == R.id.kill_self) {
            NativeExceptionFunc.nativeKillSelf();
        }
    }

}
