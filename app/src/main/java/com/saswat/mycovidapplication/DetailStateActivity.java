package com.saswat.mycovidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailStateActivity extends AppCompatActivity {
    int position;
    private TextView stateNameIndia,stateCases,stateDeath,stateRecovered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_state);
        stateNameIndia = (TextView)findViewById(R.id.detail_state);
        stateCases = (TextView)findViewById(R.id.no_cases_state);
        stateRecovered = (TextView)findViewById(R.id.no_recovered_state);
        stateDeath = (TextView)findViewById(R.id.no_death_state);

        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);

        stateNameIndia.setText(StatesFragment.statesModelList.get(position).getStateName());
        stateCases.setText(StatesFragment.statesModelList.get(position).getStateCases());
        stateRecovered.setText(StatesFragment.statesModelList.get(position).getStateRecovered());
        stateDeath.setText(StatesFragment.statesModelList.get(position).getStateDeath());

    }
}
