package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class likedGymsPage extends AppCompatActivity {

    ArrayList<likedGymsModel> likedGymsModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_gyms_page);

        Bundle extras = getIntent().getExtras();
        int adapterPosition = extras.getInt("likedAdapterPosition");
        System.out.println(adapterPosition);

        String url = "http://coms-309-004.class.las.iastate.edu:8080/gyms/" + adapterPosition + "/users?type=like";

        RecyclerView recyclerView = findViewById(R.id.likedgymsRecyclerview);

        JsonArrayRequest jsonArr = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            //response.getJSONObject(0).get("").toString();
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for(int i = 0; i < response.length(); i++) {

                        likedGymsModels.add(new likedGymsModel(response.getString(i)));
                        System.out.println(response.getString(i));
                    }

                    likedGymsRecyclerAdapter adapter = new likedGymsRecyclerAdapter(likedGymsPage.this, likedGymsModels);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(likedGymsPage.this));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMsg = "Error: " + error.getMessage();
                Log.e("MyApp", errorMsg);
                Toast.makeText(getApplicationContext(), "Error4523", Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonArr);




    }
}