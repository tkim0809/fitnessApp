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

/**
 * This class is used to list all leaderboards progress of all users
 */
public class leaderboard extends AppCompatActivity {

    ArrayList<leaderboardModel> leaderboardModels = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        RecyclerView recyclerView = findViewById(R.id.leaderboard_recyclerview);

        String url = "http://coms-309-004.class.las.iastate.edu:8080/leaderboards";
        Button addpushupsButton = findViewById(R.id.addpushupsButton);

        /**
         * sends json GET method, gets response as json array of username and number of pushups and lists on the screen
         */
        JsonArrayRequest jsonArr = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            //response.getJSONObject(0).get("").toString();
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for(int i = 0; i < response.length(); i++) {

                        leaderboardModels.add(new leaderboardModel(response.getJSONObject(i).get("username").toString(), response.getJSONObject(i).get("pushups").toString()));


                    }


                    leaderboard_recycler_adapter adapter = new leaderboard_recycler_adapter(leaderboard.this, leaderboardModels);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(leaderboard.this));

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




    }
}