package org.pjh.jetpackdemo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class TimerViewModel extends ViewModel {

    MutableLiveData<Integer> mutableLiveData;
    Timer mTimer;
    volatile int currentSecond;
    public  MutableLiveData<Integer> getMutableLiveData() {
        if (null == mutableLiveData) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public void startTiming() {
        if (mTimer == null) {
            currentSecond = 0;
            mTimer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    currentSecond++;
                    mutableLiveData.postValue(currentSecond);
                }
            };
            mTimer.schedule(task, 1000, 1000);
        }

    }
}
