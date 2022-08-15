package com.example.javaexception;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button mBinderLeak;
    Button mBinderOverflow;
    Button mUncaughtExceptionChildThreadButton;
    Button mNotificationSystemUICrash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinderLeak = findViewById(R.id.binder_leak);
        mBinderLeak.setOnClickListener(this::onClick);
        mBinderOverflow = findViewById(R.id.binder_overflow);
        mBinderOverflow.setOnClickListener(this::onClick);
        mUncaughtExceptionChildThreadButton = findViewById(R.id.uncaught_exception_child);
        mUncaughtExceptionChildThreadButton.setOnClickListener(this::onClick);
        mNotificationSystemUICrash = findViewById(R.id.notification_systemui_crash);
        mNotificationSystemUICrash.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.binder_leak) {
            triggerWithContent(MainActivity.this);
        } else if (id == R.id.binder_overflow) {
            Intent intent = new Intent(this, RemoteActivity.class);
            //intent.setPackage("com.example.javaexception");
            byte[] bytes = new byte[1024*1024];
            intent.putExtra("overflowtest", bytes);
            startActivity(intent);
        } else if (id == R.id.uncaught_exception_child) {
            new Thread(()->{
                throw new RuntimeException("子线程异常");
            }).start();
        } else if (id == R.id.notification_systemui_crash) {
            startNotification();
            Process.sendSignal(Process.myPid(), 9);
        }
    }

    private void startNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test_notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8))
                .setContentTitle("测试Title")
                .setContentText("测试内容")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("test_notification", "channel_name", importance);
        channel.setDescription("channel_description");
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, builder.build());
    }

    public void triggerWithContent(final Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        final Handler handler = new Handler(Looper.myLooper());
        new Thread(new Runnable() {
            Uri uri = Uri.parse("content://sms/sent");
            @Override
            public void run() {
                while (true) {
                    ContentObserver contentObserver = new MyObserver(handler);
                    contentResolver.registerContentObserver(uri, true, contentObserver);
                }

            }
        }).start();
    }



    class MyObserver extends ContentObserver {
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            this.onChange(selfChange, null);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
        }
    }
}