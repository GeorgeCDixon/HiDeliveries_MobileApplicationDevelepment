package com.example.hifooddeliveries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ComplaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        findViewById(R.id.btn_back).setOnClickListener(view->{
            finish();
        });
    }
}