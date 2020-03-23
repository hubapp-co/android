package com.company;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.company.hub.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    private EditText efirstName,esecName,eemail,ephone,ePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button breg = (Button) findViewById(R.id.btn_reg);
        Button blogin = (Button) findViewById(R.id.btn_login);
        breg.setOnClickListener(myhandlerReg);
        blogin.setOnClickListener(myhandlerLogin);

        efirstName = (EditText) findViewById(R.id.firstName);
        esecName = (EditText) findViewById(R.id.secName);
        eemail = (EditText) findViewById(R.id.email);
        ephone = (EditText) findViewById(R.id.phone);
        ePassword = (EditText) findViewById(R.id.Password);
    }

        View.OnClickListener myhandlerLogin = new View.OnClickListener() {
            public void onClick(View v) {
                // it was the 1st button
                Toast.makeText(RegisterActivity.this, "Login Here...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        };
        View.OnClickListener myhandlerReg = new View.OnClickListener() {
            public void onClick(View v) {
                // it was the 1st button
                Toast.makeText(RegisterActivity.this, "Registered....", Toast.LENGTH_SHORT).show();
                validateForm();
                finish();
            }
        };


    private void validateForm() {
        String fname = efirstName.getText().toString().trim();
        String lname = esecName.getText().toString().trim();
        String email = eemail.getText().toString().trim();
        String mobile = ephone.getText().toString().trim();
        String pass = ePassword.getText().toString().trim();

        // validating empty name and email
        if (fname.length() == 0 || email.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter your details", Toast.LENGTH_SHORT).show();
            return;
        }

        // validating mobile number
        // it should be of 10 digits length
        if (isValidPhoneNumber(mobile)) {

            Toast.makeText(getApplicationContext(), "valid mobile number", Toast.LENGTH_SHORT).show();
            // requesting for       sms
            requestHub(fname,lname, email, mobile,pass);

        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
        }
    }
    private static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }


    private void requestHub(final String firstName, final String lastName, final String postEmail, final String postPhone, final String postPass) {

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Config.BASE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("reg", response.toString());

                try {
                    JSONObject responseObj = new JSONObject(response);

                    // Parsing json object response
                    // response will be a json object
                    boolean error = responseObj.getBoolean("user");
                    String message = responseObj.getString("message");

                    // checking for error, if not error SMS is initiated
                    // device should receive it shortly
                    if (!error) {
                        // boolean flag saying device is waiting for sms


                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Error: " + message,
                                Toast.LENGTH_LONG).show();
                    }

                    // hiding the progress bar
//                    progressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();


                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Wrong", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {

            /**
             * Passing user parameters to our server
             * @return
             */

                protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                params.put("email", postEmail);
                params.put("password", postPass);


                return params;
            }

        };

        int socketTimeout = 6000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        strReq.setRetryPolicy(policy);

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);
    }


}
