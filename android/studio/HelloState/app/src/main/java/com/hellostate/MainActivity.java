package com.hellostate;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelloState helloState = new HelloState("Hello State");
        helloState.start();
        helloState.sendMessage(helloState.obtainMessage(HelloState.CMD_1));
        helloState.sendMessage(helloState.obtainMessage(HelloState.CMD_2));
    }


    /**
     * 同步消息发送测试
     */
    public static final int CMD_SYNCHRONIZED = 1001;
    public static final int CMD_ASYNCHRONIZED = 1002;
    public static final String TAG = MainActivity.class.getName();
    HandlerThread mHandlerThread = new HandlerThread("new thread");
    WorkHandler mWorkHandler = null;
    @SuppressLint("HandlerLeak")
    Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, "MainHandler->msg: " + msg.what);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "MainHandler->SleepComplete ");
        }
    };
    private boolean synchronized_cmd = false;
    @Override
    protected void onResume() {
        super.onResume();
        mHandlerThread.start();
        mWorkHandler = new WorkHandler(mHandlerThread.getLooper());
        mWorkHandler.setMainHandler(mMainHandler);
        //如果不加这个同步和异步会混乱掉
        if (synchronized_cmd) {
            mWorkHandler.sendMessage(Message.obtain(mWorkHandler, CMD_SYNCHRONIZED));
        } else {
            mWorkHandler.sendMessage(Message.obtain(mWorkHandler, CMD_ASYNCHRONIZED));
        }

    }

    public static class WorkHandler extends Handler {
        Handler mMainHandler;
        public WorkHandler(Looper looper) {
            super(looper);
        }

        public void setMainHandler(Handler mainHandler) {
            mMainHandler = mainHandler;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, "WorkHandler->msg: " + msg.what);
            switch (msg.what) {
                case CMD_SYNCHRONIZED:
                    //1. 发送一个普通的同步Message
                    sendMessage(Message.obtain(this, HelloState.CMD_1));
                    //2. 睡眠500ms,模仿代码执行
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "Sleep complete");
                    //3. 发送一个队列头的Message
                    sendMessageAtFrontOfQueue(Message.obtain(this, HelloState.CMD_2));
                    break;
                case CMD_ASYNCHRONIZED:
                    //1. 发送两个异步的Message,用于测试Handler每次只会处理一个消息
                    mMainHandler.sendMessage(Message.obtain(mMainHandler, HelloState.CMD_1));
                    mMainHandler.sendMessage(Message.obtain(mMainHandler, HelloState.CMD_3));
                    //2. 睡眠500ms,模仿代码执行
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "Sleep complete");
                    //3. 发送一个队列头的Message
                    sendMessageAtFrontOfQueue(Message.obtain(this, HelloState.CMD_2));
                    break;
            }
        }
    }
}
