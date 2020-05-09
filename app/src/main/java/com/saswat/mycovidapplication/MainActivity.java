package com.saswat.mycovidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navLister);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_container,new WorldFragment())
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLister
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_world :
                    selectedFragment = new WorldFragment();
                    break;
                case R.id.nav_country:
                    selectedFragment = new CountryFragment();
                    break;
                case R.id.nav_states:
                    selectedFragment = new StatesFragment();
                    break;
                case R.id.nav_help:
                    selectedFragment = new HelpFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_container,selectedFragment)
                    .commit();
            return true;
        }
    };

}
