package org.pjh.jetpackdemo;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.LinkedHashMap;

public class MyLifecycleObserver implements LifecycleObserver {
    public static final String TAG = "MyLifecycleObserver";
    private Lifecycle mRegistry;
    public MyLifecycleObserver(Lifecycle registry) {
        mRegistry = registry;
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        Log.d(TAG, "create: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        Log.d(TAG, "start1: ");
        //mRegistry.removeObserver(this);
        mRegistry.addObserver(new MyLifecycleObserverChild());
        Log.d(TAG, "start2: ");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume(LifecycleOwner owner) {
        Log.d(TAG, "resume: " + owner);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        Log.d(TAG, "destroy: ");
    }

}
