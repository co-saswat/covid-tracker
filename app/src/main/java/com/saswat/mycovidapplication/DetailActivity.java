package com.saswat.mycovidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private int positionCountry;
    private TextView tvCountry, tvCase, tvtodayCases, tvDeath, tvtodayDeath, tvRecovered, tvActive, tvCritical ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        tvCountry = (TextView)findViewById(R.id.detail_country);
        tvCase = (TextView)findViewById(R.id.no_cases);
        tvtodayCases = (TextView)findViewById(R.id.no_today_cases);
        tvDeath = (TextView)findViewById(R.id.no_death);
        tvtodayDeath = (TextView)findViewById(R.id.no_today_death);
        tvRecovered = (TextView)findViewById(R.id.no_recovered);
        tvActive = (TextView)findViewById(R.id.no_active);
        tvCritical = (TextView)findViewById(R.id.no_critical);

        tvCountry.setText(CountryFragment.countryModelList.get(positionCountry).getCountry());
        tvCase.setText(CountryFragment.countryModelList.get(positionCountry).getCases());
        tvtodayCases.setText(CountryFragment.countryModelList.get(positionCountry).getTodaycases());
        tvDeath.setText(CountryFragment.countryModelList.get(positionCountry).getDeath());
        tvtodayDeath.setText(CountryFragment.countryModelList.get(positionCountry).getTodaydeaths());
        tvRecovered.setText(CountryFragment.countryModelList.get(positionCountry).getRecovered());
        tvActive.setText(CountryFragment.countryModelList.get(positionCountry).getActivecases());
        tvCritical.setText(CountryFragment.countryModelList.get(positionCountry).getCriticalcases());


    }


}
