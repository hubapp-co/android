package com.company;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.company.hub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
public class SplashActivity extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 2000;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.splashscreen_layout);


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                            Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                        }

        }, SPLASH_DISPLAY_LENGTH);
    }

}
