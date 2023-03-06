package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class findFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        EditText emailEditText = findViewById(R.id.friendEmail);
        Button findButton = findViewById(R.id.findFriendButton);
        Button backToMenuBtn = findViewById(R.id.backToMenu);

        backToMenuBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(findFriendActivity.this, WelcomeMenu.class);
                startActivity(i);
            }
        });


        String email = emailEditText.getText().toString();

        findButton.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View view) {

                String url = "http://coms-309-004.class.las.iastate.edu:8080/login";
                RequestQueue queue = Volley.newRequestQueue(findFriendActivity.this);
                JSONObject obj = new JSONObject();

                try {

                    obj.put("email", email);


                } catch (Exception e) {

                    System.out.println("ERROR");

                }

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        url, obj,
                        new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {


                                try {

                                    if (response.getBoolean("email")) {

                                        String friendNumber = "Hello";
                                        Toast.makeText(findFriendActivity.this, "Successful added to friend list", Toast.LENGTH_LONG).show();
                                        //Intent i = new Intent(findFriendActivity.this, FriendListActivity.class);
                                        //i.putExtra("friendNumber", friendNumber);
                                        //i.putExtra("testing", "testing value");
                                        //i.putExtra("email", "email");

                                    }

                                } catch (JSONException e) {


                                    throw new RuntimeException(e);

                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof ServerError) {
                            Toast.makeText(findFriendActivity.this, "Server", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(findFriendActivity.this, "Network", Toast.LENGTH_SHORT).show();
                            System.out.println(error.getMessage());
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(findFriendActivity.this, "Auth", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(findFriendActivity.this, "Parse", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(findFriendActivity.this, "No Connection", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(findFriendActivity.this, "T/O", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(findFriendActivity.this, "Unknown", Toast.LENGTH_SHORT).show();

                        }
                    }
                }) {

                    @Override
                    public Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<String, String>();
                        return params;

                    }

                };

                queue.add(jsonObjReq);

            }


        });


    }
}