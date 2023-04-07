package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class leaderboard extends AppCompatActivity {

    ArrayList<leaderboardModel> leaderboardModels = new ArrayList<>();
    RecyclerView recyclerView = findViewById(R.id.leaderboard_recyclerview);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        String url = "http://coms-309-004.class.las.iastate.edu:8080/AllMilestones";
        Button addpushupsButton = findViewById(R.id.addpushupsButton);

        JsonArrayRequest jsonArr = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            //response.getJSONObject(0).get("").toString();
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for(int i = 0; i < response.length(); i++) {

                        leaderboardModels.add(new leaderboardModel(response.getJSONObject(i).get("username").toString(), response.getJSONObject(i).get("pushups").toString()));


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

        addpushupsButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent i = new Intent(leaderboard.this, leaderboardAdd.class);
                startActivity(i);
            }
        });


        leaderboard_recycler_adapter adapter = new leaderboard_recycler_adapter(this, leaderboardModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}