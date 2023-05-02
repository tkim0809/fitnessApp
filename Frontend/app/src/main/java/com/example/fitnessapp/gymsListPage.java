package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class gymsListPage extends AppCompatActivity {

    ArrayList<gymsModel> gymsModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyms_list_page);

        RecyclerView recyclerView = findViewById(R.id.gymsListRecyclerView);
        String url = "http://coms-309-004.class.las.iastate.edu:8080/gyms/";

        JsonArrayRequest jsonArr = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            //response.getJSONObject(0).get("").toString();
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for(int i = 0; i < response.length(); i++) {

                        gymsModels.add(new gymsModel(response.getJSONObject(i).get("name").toString(), response.getJSONObject(i).get("description").toString(), response.getJSONObject(i).get("locationUrl").toString(), response.getJSONObject(i).get("phoneNumber").toString(), response.getJSONObject(i).get("hoursOfOperation").toString()));

                    }

                    gymsListRecyclerAdapter adapter = new gymsListRecyclerAdapter(gymsListPage.this, gymsModels);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(gymsListPage.this));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error45", Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonArr);




    }

}



