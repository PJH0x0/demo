package org.pjh.jetpackdemo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.pjh.jetpackdemo.model.RequestService;
import org.pjh.jetpackdemo.model.Utils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimerViewModel extends ViewModel {

    MutableLiveData<Integer> mutableLiveData;
    Timer mTimer;
    int currentSecond;

    public  MutableLiveData<Integer> getMutableLiveData() {
        if (null == mutableLiveData) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public void startTiming() {
        if (mTimer == null) {
            mTimer = new Timer();
        }
        currentSecond = 0;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                currentSecond++;
                mutableLiveData.postValue(currentSecond);
            }
        };
        mTimer.schedule(task, 1000, 1000);
    }
    public void stopTiming() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void startLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String email = "215469819@qq.com";
                String password = Utils.getMaskPassword("12345678pjh");
                Log.d("jonny", password);
                String mImei = "863408023058028";
                String pkgName = "com.ape.onelogin";
                try {
                    RequestService.getInstance().loginEmail(mImei, pkgName, email, password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
