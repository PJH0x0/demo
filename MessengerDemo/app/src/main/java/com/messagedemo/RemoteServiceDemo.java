package com.messagedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class RemoteServiceDemo extends Service {
    private static final String TAG = "RemoteServiceDemo";
    public RemoteServiceDemo() {
    }

    Messenger mMessager = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgFromClient) {
            int result = msgFromClient.arg1 + msgFromClient.arg2;
            Message msg = Message.obtain();
            msg.what = result;
            try {
                msgFromClient.replyTo.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    });

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() called with: intent = [" + intent + "]");
        return  mMessager.getBinder();
    }
}
