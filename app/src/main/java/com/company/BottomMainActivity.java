package com.company;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.company.hub.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;


public class BottomMainActivity extends AppCompatActivity {


    boolean doubleBackToExit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.feed_recycler_item);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.home:
                                Intent home = new Intent(BottomMainActivity.this,BottomMainActivity.class);
                                startActivity(home);
                                finish();
                                break;

                            case R.id.foody:
//
                                Intent intent2 = new Intent(BottomMainActivity.this, EventsActivity.class);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent2);

                                break;

                            case R.id.review:

                                Intent notif = new Intent(BottomMainActivity.this, FeedPostActivity.class);
                                notif.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(notif);

                                break;

                            case R.id.search:

                                Intent intent1 = new Intent(BottomMainActivity.this, MainActivity.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent1);

                                break;

                            case R.id.profile:
                                Intent intent4 = new Intent(BottomMainActivity.this, BottomProfileActivity.class);
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
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }


}