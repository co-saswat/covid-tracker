package com.saswat.mycovidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StatesFragment extends Fragment  {
    private TextView tvcases_india, tvdeath_india, tvrecoverey_india;


    EditText serachstr_india;
    RecyclerView recyclerView;

    public static List<StatesModel> statesModelList = new ArrayList<>();
    private StatesModel statesModel;
    private MyCustomAdaptorState myCustomAdaptorState;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_states, container, false);
        //Active Calls
        tvcases_india = inflate.findViewById(R.id.india_covid_cases);
        tvdeath_india = inflate.findViewById(R.id.india_covid_death);
        tvrecoverey_india = inflate.findViewById(R.id.india_covid_recovered);

        serachstr_india = inflate.findViewById(R.id.edtext_search_bar_india_states);
        recyclerView = inflate.findViewById(R.id.covid_india_state);


        //Volley Call 1
        getDataIndia();
        //Volley Call 2
        getDataState();


        //Search Bar
        serachstr_india.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myCustomAdaptorState.getFilter().filter(charSequence);
                myCustomAdaptorState.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return inflate;
    }


    private void getDataIndia() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://corona.lmao.ninja/v2/countries/india";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject object = new JSONObject(response.toString());
                    tvcases_india.setText(object.getString("cases"));
                    tvdeath_india.setText(object.getString("deaths"));
                    tvrecoverey_india.setText(object.getString("recovered"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Error Response", error.toString());
            }

        });

        queue.add(request);
    }

    private void getDataState() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String Url = "http://covid19-india-adhikansh.herokuapp.com/states";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("state");
                    for (int i = 0; i < array.length(); i++) {
                        statesModel = new StatesModel();
                        JSONObject object = array.getJSONObject(i);
                        statesModel.setStateName(object.getString("name"));
                        statesModel.setStateCases(object.getString("confirmed"));
                        statesModelList.add(statesModel);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    myCustomAdaptorState = new MyCustomAdaptorState(getActivity(), statesModelList);
                    recyclerView.setAdapter(myCustomAdaptorState);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onResponse", error.toString());
            }
        });
        queue.add(stringRequest);
    }


}


