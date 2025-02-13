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


/**
 * This class is used to list all the user's friends
 */
public class friendList extends AppCompatActivity {

    ArrayList<friendModel> friendModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        RecyclerView recyclerView = findViewById(R.id.friendlistRecyclerV);

        String url = "http://coms-309-004.class.las.iastate.edu:8080/" + "api/friends/" + UserInfo.getUserIdFromLogin() + "/friends";


        /**
         * sends json GET method and gets response as json array to lists all the user's friends
         */
        JsonArrayRequest jsonArr = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            //response.getJSONObject(0).get("").toString();
            @Override
            public void onResponse(JSONArray response) {

                String firstName = "";

                try {

                    for(int i = 0; i < response.length(); i++) {

                        friendModels.add(new friendModel(response.getString(i), firstName));

                    }

                    friendListRecyclerAdapter adapter = new friendListRecyclerAdapter(friendList.this, friendModels);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(friendList.this));

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