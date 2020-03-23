package com.company;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.hub.R;
import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Button login = (Button) findViewById(R.id.btn_request_login);
        Button  flogin = (Button) findViewById(R.id.glog);
        Button  glogin = (Button) findViewById(R.id.flog);
        Button  regEmail = (Button) findViewById(R.id.btn_request_reg);
        regEmail.setOnClickListener(myhandlerReg);
        login.setOnClickListener(myhandler1);
        flogin.setOnClickListener(myhandler2);
        glogin.setOnClickListener(myhandler3);

    }
    View.OnClickListener myhandler1 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 1st button
            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, BottomMainActivity.class));
            finish();
        }
    };
    View.OnClickListener myhandlerReg = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 1st button
            Toast.makeText(LoginActivity.this, "Register Here....", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        }
    };
    View.OnClickListener myhandler2 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
            Toast.makeText(LoginActivity.this, "Gmail Login Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, BottomMainActivity.class));
            finish();
        }
    };

    View.OnClickListener myhandler3 = new View.OnClickListener() {
        public void onClick(View v) {
            // it was the 2nd button
            Toast.makeText(LoginActivity.this, "Facebook Login Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, BottomMainActivity.class));
            finish();
        }
    };


}
