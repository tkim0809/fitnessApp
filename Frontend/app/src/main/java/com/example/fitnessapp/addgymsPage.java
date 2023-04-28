package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class addgymsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgyms_page);



        EditText gymName = findViewById(R.id.gym_name);
        EditText gymDescription = findViewById(R.id.gym_description);
        EditText gymLocation = findViewById(R.id.gym_location);
        Button addgymsBtn = findViewById(R.id.addgymsBtn);
        Button addgymsListBtn = findViewById(R.id.addgyms_list);

        addgymsListBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent i = new Intent(addgymsPage.this, gymsListPage.class);
                startActivity(i);
            }

        });



        addgymsBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String url = "";
                RequestQueue queue = Volley.newRequestQueue(addgymsPage.this);
                JSONObject obj = new JSONObject();

                try {

                    obj.put("gymName", gymName);
                    obj.put("gymDescription", gymDescription);
                    obj.put("gymLocation", gymLocation);


                } catch (Exception e) {

                    System.out.println("ERROR");

                }

                /**
                 * sends json object "email" to the server and if valid/has data in the server, gets response
                 */
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, obj,
                        new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {


                                try {

                                    String toCompare = response.get("message").toString();

                                    if (toCompare.equals("success")) {

                                        Toast.makeText(addgymsPage.this, "Successful added to friend list", Toast.LENGTH_LONG).show();

                                    }

                                } catch (JSONException e) {

                                    throw new RuntimeException(e);

                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof ServerError) {
                            Toast.makeText(addgymsPage.this, "Server", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(addgymsPage.this, "Network", Toast.LENGTH_SHORT).show();
                            System.out.println(error.getMessage());
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(addgymsPage.this, "Auth", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(addgymsPage.this, "Parse", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(addgymsPage.this, "No Connection", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(addgymsPage.this, "T/O", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(addgymsPage.this, "Unknown", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });



    }


}