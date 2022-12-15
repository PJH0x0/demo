package com.example.nedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nedemo.recyclerview.CrashItem;
import com.example.nedemo.recyclerview.CrashItemAdapter;
import com.example.nedemo.recyclerview.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CrasherActivity extends AppCompatActivity {
    static {
        System.loadLibrary("native-exception-lib");
    }
    public static final String KEY_CRASH_NATIVE_THREAD = "crash_native_thread";
    private SlideRecyclerView mCrashRecyclerView;
    private List<CrashItem> mCrashItems = new ArrayList<>();
    private CrashItemAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crasher);
        Intent intent = getIntent();
        final boolean crashInNativeThread = intent.getBooleanExtra(KEY_CRASH_NATIVE_THREAD, false);
        mCrashRecyclerView = findViewById(R.id.crasher_list);
        mCrashRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_inset));
        mCrashRecyclerView.addItemDecoration(itemDecoration);
        mCrashItems = NativeExceptionFunc.createCrashItems(this);
        mAdapter = new CrashItemAdapter(this, mCrashItems);
        mCrashRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((RecyclerView.Adapter adapter, View v, int position)->{
            String crashType = mCrashItems.get(position).getCrashType();
            Log.d("PJH", "crash in natieve thread = " + crashInNativeThread);
            NativeExceptionFunc.nativeCrash(crashType, crashInNativeThread);
        });
    }
}