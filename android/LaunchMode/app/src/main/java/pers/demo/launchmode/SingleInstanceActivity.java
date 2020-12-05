package pers.demo.launchmode;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SingleInstanceActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        Button standard = findViewById(R.id.start_standard);
        standard.setOnClickListener(this);
        Button singleTop = findViewById(R.id.start_single_top);
        singleTop.setOnClickListener(this);
        Button singleTask = findViewById(R.id.start_single_task);
        singleTask.setOnClickListener(this);
        Button singleInstance = findViewById(R.id.start_single_instance);
        singleInstance.setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("pjh", "SingleInstance onNewIntent");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_standard:
                Intent standard = new Intent(this, StandardActivity.class);
                startActivity(standard);
                break;
            case R.id.start_single_top:
                Intent singleTop = new Intent(this, SingleTopActivity.class);
                startActivity(singleTop);
                break;
            case R.id.start_single_task:
                Intent singleTask = new Intent(this, SingleTaskActivity.class);
                startActivity(singleTask);
                break;
            case R.id.start_single_instance:
                Intent singleInstance = new Intent(this, SingleInstanceActivity.class);
                startActivity(singleInstance);
                break;
        }
    }
}