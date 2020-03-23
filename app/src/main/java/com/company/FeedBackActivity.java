package com.company;


import android.os.Bundle;

import com.company.hub.R;

import androidx.appcompat.app.AppCompatActivity;

public class FeedBackActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}