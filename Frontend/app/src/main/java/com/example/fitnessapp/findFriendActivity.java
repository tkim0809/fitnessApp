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

/**
 * This class is used to find a friend with given email address by user
 */
public class findFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        EditText emailEditText = findViewById(R.id.friendEmail);
        Button addFriendButton = findViewById(R.id.addFriendButton);
        Button backToMenuBtn = findViewById(R.id.backToMenu);
        Button addGymsBtn = findViewById(R.id.addgymsButton);



        /**
         * when "back" button is pressed
         */
        backToMenuBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(findFriendActivity.this, NewUserMenu.class);
                startActivity(i);
            }
        });

        addGymsBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent i = new Intent(findFriendActivity.this, addgymsPage.class);
                startActivity(i);
            }
        });


        String email = emailEditText.getText().toString();

        /**
         * when "add" button is pressed
         */
        addFriendButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String userName = "";
                userName += UserInfo.getUserID();

                String url = "http://coms-309-004.class.las.iastate.edu:8080/api/friends/" + userName +"/add";
                RequestQueue queue = Volley.newRequestQueue(findFriendActivity.this);
                JSONObject obj = new JSONObject();

                try {

                    obj.put("email", email);
                    obj.put("userID", userName);


                } catch (Exception e) {

                    System.out.println("ERROR");

                }

                /**
                 * sends json object "email" to the server and if valid/has data in the server, gets response
                 */
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        url, obj,
                        new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {


                                try {

                                    String toCompare = response.get("message").toString();

                                    if (toCompare.equals("success")) {

                                        Toast.makeText(findFriendActivity.this, "Successful added to friend list", Toast.LENGTH_LONG).show();

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