package com.company;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.company.hub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class BottomProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //grid
    GridView androidGridView;

    boolean doubleBackToExit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_profile);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Create your buttons and set their onClickListener to "this"
        RelativeLayout b1 = (RelativeLayout) findViewById(R.id.rel_about);
        b1.setOnClickListener(this);
        RelativeLayout b2 = (RelativeLayout) findViewById(R.id.rel_policy);
        b2.setOnClickListener(this);
        RelativeLayout b3 = (RelativeLayout) findViewById(R.id.rel_contact);
        b3.setOnClickListener(this);
        RelativeLayout b4 = (RelativeLayout) findViewById(R.id.rel_rate);
        b4.setOnClickListener(this);
        RelativeLayout b5 = (RelativeLayout) findViewById(R.id.rel_share);
        b5.setOnClickListener(this);
        RelativeLayout b6 = (RelativeLayout) findViewById(R.id.rel_faqs);
        b6.setOnClickListener(this);
        RelativeLayout terms = (RelativeLayout) findViewById(R.id.rel_terms);
        terms.setOnClickListener(this);
        RelativeLayout sett = (RelativeLayout) findViewById(R.id.rel_settings);
        sett.setOnClickListener(this);
        RelativeLayout refund = (RelativeLayout) findViewById(R.id.rel_refund);
        refund.setOnClickListener(this);
        RelativeLayout log = (RelativeLayout) findViewById(R.id.rel_logout);
        log.setOnClickListener(this);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        Intent home = new Intent(BottomProfileActivity.this, BottomMainActivity.class);
                        startActivity(home);
                        finish();
                        break;

                    case R.id.foody:
//
                        Intent intent2 = new Intent(BottomProfileActivity.this, EventsActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);

                        break;

                    case R.id.review:

                        Intent notif = new Intent(BottomProfileActivity.this, FeedPostActivity.class);
                        notif.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(notif);

                        break;

                    case R.id.search:

                        Intent intent1 = new Intent(BottomProfileActivity.this, MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);

                        break;

                    case R.id.profile:
                        Intent intent4 = new Intent(BottomProfileActivity.this, BottomProfileActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent4);

                        break;
                }
                return true;
            }
        });

    }








    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExit) {
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            doubleBackToExit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExit = false;
                }
            }, 3 * 1000);
        }
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.rel_about:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://triciel.com/about-us.html"));
                startActivity(intent);
                break;
            case R.id.rel_policy:
                Intent policy = new Intent();
                policy.setAction(Intent.ACTION_VIEW);
                policy.addCategory(Intent.CATEGORY_BROWSABLE);
                policy.setData(Uri.parse("http://triciel.com/mobile-application.html"));
                startActivity(policy);
                break;
            case R.id.rel_contact:
                Intent terms = new Intent();
                terms.setAction(Intent.ACTION_VIEW);
                terms.addCategory(Intent.CATEGORY_BROWSABLE);
                terms.setData(Uri.parse("http://triciel.com/contact-us.html"));
                startActivity(terms);
                break;

            case R.id.rel_faqs:
                Intent faq = new Intent();
                faq.setAction(Intent.ACTION_VIEW);
                faq.addCategory(Intent.CATEGORY_BROWSABLE);
                faq.setData(Uri.parse("http://triciel.com/our-process.html"));
                startActivity(faq);
                break;
            case R.id.rel_rate:
                Uri uri = Uri.parse("market://details?id=" + "com.meteoragaming.desertrider");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + "com.meteoragaming.desertrider")));
                }
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.meteoragaming.desertrider")));
                break;
            case R.id.rel_share:
//                try {
//                    Intent i = new Intent(Intent.ACTION_SEND);
//                    i.setType("text/plain");
//                    i.putExtra(Intent.EXTRA_SUBJECT, "Welcome To Truckit");
//                    String sAux = "\nRecommend you to Use this Truckit application for easy shipment.\n\n";
//                    sAux = sAux + ConfigURL.BASE_URL+"/ \n\n";
//                    i.putExtra(Intent.EXTRA_TEXT, sAux);
//                    startActivity(Intent.createChooser(i, "choose one"));
//                } catch(Exception e) {
//                    //e.toString();
//                }
                break;

            case R.id.rel_terms:
                Intent fed = new Intent(BottomProfileActivity.this, FeedBackActivity.class);
                startActivity(fed);
                finish();
                break;


            case R.id.rel_settings:
                Intent sett = new Intent(BottomProfileActivity.this, SettingsPrefActivity.class);
                startActivity(sett);
                finish();
                break;
            case R.id.rel_refund:

                break;

            case R.id.rel_logout:
                Intent lgout = new Intent(BottomProfileActivity.this, LoginActivity.class);
                startActivity(lgout);
                finish();
                break;
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
