package org.pjh.jetpackdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.pjh.jetpackdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    TimerViewModel mViewModel;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Log.d("jonny", "onCreate");
        binding.setTimer("start");
        final TextView timerCountTextView = findViewById(R.id.timer_count);
        Button startTimerButton = findViewById(R.id.start_timer);
        Button resetTimerButton = findViewById(R.id.reset_timer);
        Button loginButton = findViewById(R.id.login);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TimerViewModel.class);

        //mViewModel.getMutableLiveData().setValue(1000);

        startTimerButton.setOnClickListener(mListener);
        resetTimerButton.setOnClickListener(mListener);
        loginButton.setOnClickListener(mListener);
    }



    private final View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start_timer:
                    mViewModel.startTiming();
                    break;
                case R.id.reset_timer:
                    mViewModel.stopTiming();
                    mViewModel.getMutableLiveData().setValue(0);
                    break;
                case R.id.login:
                    mViewModel.startLogin();
                    break;
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        //mViewModel.getMutableLiveData().postValue(1000);
        Log.d("jonny", "onStart");
        mViewModel.getMutableLiveData().setValue(1001);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLifecycle().addObserver(new MyLifecycleObserver(getLifecycle()));
        Log.d("jonny", "onResume");
        //mViewModel.getMutableLiveData().postValue(1000);
        mViewModel.getMutableLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("jonny", "onChanged1: " + integer);
                if (null == integer) return;
                binding.setTimer(String.valueOf(integer));

            }
        });


        mViewModel.getMutableLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("jonny", "onChanged2:" + integer);
                if (integer.intValue() == 1001) {
                    mViewModel.getMutableLiveData().setValue(1002);
                }
            }
        });
        mViewModel.getMutableLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("jonny", "onChanged3: " + integer);
            }
        });
        //mViewModel.getMutableLiveData().setValue(1002);

//        try {
//            Thread.sleep(2000);
//            Log.d("jonny", "sleep down");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mViewModel.getMutableLiveData().setValue(2000);
        //mViewModel.getMutableLiveData().setValue(3000);
        Log.d("jonny", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("jonny", "onPause");
    }
}