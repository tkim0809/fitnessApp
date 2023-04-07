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

public class milestoneProgress extends AppCompatActivity {

    ArrayList<milestoneModel> milestoneModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone_progress);

        String url = "http://coms-309-004.class.las.iastate.edu:8080/AllMilestones";

        RecyclerView recyclerView = findViewById(R.id.milestoneRecyclerV);
        JsonArrayRequest jsonArr = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            //response.getJSONObject(0).get("").toString();
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for(int i = 0; i < response.length(); i++) {

                        milestoneModels.add(new milestoneModel(response.getJSONObject(i).get("workoutName").toString(), response.getJSONObject(i).get("workoutWeights").toString(),
                                response.getJSONObject(i).get("workoutReps").toString(),response.getJSONObject(i).get("workoutSets").toString()));


                    }

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

        milestone_recycler_adapter adapter = new milestone_recycler_adapter(this, milestoneModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



}