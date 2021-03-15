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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("jonny", "onCreate");
        DataBindingUtil.setContentView(this, R.layout.activity_main);

        MutableLiveData<String> liveData = new MutableLiveData<>();
        //ActivityMainBinding binding = DataBindingUtil
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("jonny", s);
            }
        });
        liveData.setValue("12312321");
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        ViewModelProvider provider = new ViewModelProvider(this, factory);
        ViewModel model = provider.get(ViewModel.class);
        LifecycleObserver

    }

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