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

        String url = "http://coms-309-004.class.las.iastate.edu:8080/AllMilestones/39";

        RecyclerView recyclerView = findViewById(R.id.milestoneRecyclerV);
        JsonArrayRequest jsonArr = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            //response.getJSONObject(0).get("").toString();
            @Override
            public void onResponse(JSONArray response) {

                try {

                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                    for(int i = 0; i < response.length(); i++) {

                        milestoneModels.add(new milestoneModel(response.getJSONObject(i).get("milestoneName").toString(), response.getJSONObject(i).get("milestoneWeight").toString(),
                                response.getJSONObject(i).get("milestoneReps").toString(),response.getJSONObject(i).get("milestoneSets").toString()));

                    }

                    milestone_recycler_adapter adapter = new milestone_recycler_adapter(milestoneProgress.this, milestoneModels);

                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(milestoneProgress.this));
                    milestone_recycler_adapter.setOnItemClickListener(new milestone_recycler_adapter.OnItemClickListener() {

                        @Override
                        public void onItemClick(int position) {

                            milestoneModels.remove(position);
                            adapter.notifyItemRemoved(position);

                        }

                    });

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "login error45", Toast.LENGTH_SHORT).show();
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