package com.saswat.mycovidapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class CountryFragment extends Fragment {
    private EditText editText_search;
    ListView listView_country;
    ProgressBar progressBar_country;

    public static List<CountryModel> countryModelList = new ArrayList<>();
    private CountryModel countryModel;
    private MyCustomAdaptor customAdaptor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_country, container, false);
        //Activity Call
        editText_search = inflate.findViewById(R.id.edtext_search_bar_country);
        listView_country = inflate.findViewById(R.id.listview_item_country);
        progressBar_country = inflate.findViewById(R.id.progress_circular_world);


        //setting onClickListener in list_view
        listView_country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(),DetailActivity.class).putExtra("position",i));
            }
        });

        //search bar
        editText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                customAdaptor.getFilter().filter(charSequence);
                customAdaptor.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Volley call
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = "https://corona.lmao.ninja/v2/countries";


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               progressBar_country.setVisibility(View.GONE);
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String countryName = jsonObject.getString("country");
                        String cases = jsonObject.getString("cases");
                        String todayCases = jsonObject.getString("todayCases");
                        String death = jsonObject.getString("deaths");
                        String todayDeaths = jsonObject.getString("todayDeaths");
                        String recovered = jsonObject.getString("recovered");
                        String active = jsonObject.getString("active");
                        String critical = jsonObject.getString("critical");

                        JSONObject object = jsonObject.getJSONObject("countryInfo");
                        String flagurl = object.getString("flag");

                        countryModel = new CountryModel(flagurl,countryName,cases,death,todayCases
                                ,todayDeaths,recovered,active,critical);
                        countryModelList.add(countryModel);

                    }
                    customAdaptor = new MyCustomAdaptor(getActivity(),countryModelList);
                    listView_country.setAdapter(customAdaptor);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 progressBar_country.setVisibility(View.GONE);
                Log.d("Error Response" , error.toString());
            }
        });

        requestQueue.add(request);
        
        return inflate;
    }


}
