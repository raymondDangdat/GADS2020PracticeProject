package com.example.gadspracticeproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.gadspracticeproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Handler handler;
        handler = new Handler(getMainLooper());
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        handler.removeCallbacks(this);
                        Intent intent = new Intent(MainActivity.this, LeaderBoardActivity.class);
                        startActivity(intent);
                    }
                }, 3000
        );
    }
}