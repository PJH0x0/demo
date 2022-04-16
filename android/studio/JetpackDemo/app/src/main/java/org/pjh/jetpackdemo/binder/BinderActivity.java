package org.pjh.jetpackdemo.binder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import org.pjh.jetpackdemo.R;

public class BinderActivity extends AppCompatActivity {
    Proxy mProxy;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("jonny", "serviceConnected " + service);

            mProxy = new Proxy(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mProxy = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        Intent intent = new Intent(this, MainService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (null != mProxy) {
                    mProxy.sayHello();
                }
            }
        }, 2000);
    }

    class Proxy implements IMainService {
        IBinder mRemote;
        public Proxy(IBinder remote) {
            mRemote = remote;
        }


        @Override
        public void sayHello() {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                mRemote.transact(HELLO, data, reply, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                data.recycle();
                reply.recycle();
            }
        }
    }
}