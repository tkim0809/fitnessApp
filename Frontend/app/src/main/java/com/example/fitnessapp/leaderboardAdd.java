package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class leaderboardAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_add);

        EditText addpushupsEditText = findViewById(R.id.addpushupsEditText);
        Button addpushupsButton = findViewById(R.id.addpushupsBut);

        String pushups = addpushupsEditText.getText().toString();

        addpushupsButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String url = "http://coms-309-004.class.las.iastate.edu:8080/Milestones/new";
                RequestQueue queue = Volley.newRequestQueue(leaderboardAdd.this);
                JSONObject obj = new JSONObject();

                try {

                    obj.put("pushups", pushups);

                } catch(Exception e) {

                    System.out.println("JSON OBJECT ERROR");

                }

                JsonObjectRequest jsonArr = new JsonObjectRequest(Request.Method.POST, url,
                        obj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {



                                try {
                                    if (response.getBoolean("add")) {

                                        Toast.makeText(getApplicationContext(), "add successful", Toast.LENGTH_SHORT).show();


                                        Intent i = new Intent(leaderboardAdd.this, leaderboard.class);
                                        startActivity(i);



                                    } else {

                                        Toast.makeText(getApplicationContext(), "login error", Toast.LENGTH_SHORT).show();

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
                }
                );

                queue.add(jsonArr);
            }
        });

    }
}