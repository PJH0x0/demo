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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Log.d("jonny", "onCreate");
        binding.setTimer("start");
        final TextView timerCountTextView = findViewById(R.id.timer_count);
        Button startTimerButton = findViewById(R.id.start_timer);
        Button resetTimerButton = findViewById(R.id.reset_timer);
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(TimerViewModel.class);
        mViewModel.getMutableLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (null == integer) return;
                binding.setTimer(String.valueOf(integer));
            }
        });
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
        Log.d("jonny", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("jonny", "onResume");
    }
}