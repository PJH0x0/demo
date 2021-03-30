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
        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(TimerViewModel.class);



        startTimerButton.setOnClickListener(mListener);
        resetTimerButton.setOnClickListener(mListener);
    }



    private final View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start_timer:
                    mViewModel.startTiming();
                    break;
                case R.id.reset_timer:
                    mViewModel.getMutableLiveData().setValue(0);
                    break;
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        //mViewModel.getMutableLiveData().postValue(1000);
        Log.d("jonny", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("jonny", "onResume");
        mViewModel.getMutableLiveData().postValue(1000);
        mViewModel.getMutableLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (null == integer) return;
                binding.setTimer(String.valueOf(integer));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        getLifecycle().addObserver(new MyLifecycleObserver());
        Log.d("jonny", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("jonny", "onPause");
    }
}