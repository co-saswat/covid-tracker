package com.saswat.mycovidapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WorldFragment extends Fragment {
    private TextView total_case , total_death , total_recovery;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_world, container, false);
        total_case = inflate.findViewById(R.id.text_total_no_case_world);
        total_death = inflate.findViewById(R.id.text_total_no_death_world);
        total_recovery = inflate.findViewById(R.id.text_total_no_recovery_world);
        progressBar = inflate.findViewById(R.id.progress_circular_world);
        getdata();
        return inflate;
    }

    private void getdata() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://corona.lmao.ninja/v2/all";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject object = new JSONObject(response.toString());
                    total_case.setText(object.getString("cases"));
                    total_death.setText(object.getString("deaths"));
                    total_recovery.setText(object.getString("recovered"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Response" , error.toString());
            }

        });

        queue.add(request);
    }
}
