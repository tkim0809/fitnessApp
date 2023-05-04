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



        EditText gymNameEdit = findViewById(R.id.gym_name);
        EditText gymDescriptionEdit = findViewById(R.id.gym_description);
        EditText gymLocationEdit = findViewById(R.id.gym_location);
        EditText gymPhoneNumberEdit = findViewById(R.id.gym_phoneNumber);
        EditText gymHoursEdit = findViewById(R.id.gym_hours);

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

                String gymName = gymNameEdit.getText().toString();
                String gymDescription = gymDescriptionEdit.getText().toString();
                String gymLocation = gymLocationEdit.getText().toString();
                String gymPhoneNumber = gymPhoneNumberEdit.getText().toString();
                String gymHours = gymHoursEdit.getText().toString();


                String url = "http://coms-309-004.class.las.iastate.edu:8080/gyms/addGym";
                RequestQueue queue = Volley.newRequestQueue(addgymsPage.this);
                JSONObject obj = new JSONObject();

                try {

                    obj.put("name", gymName);
                    obj.put("description", gymDescription);
                    obj.put("locationUrl", gymLocation);
                    obj.put("phoneNumber", gymPhoneNumber);
                    obj.put("hoursOfOperation", gymHours);

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

                                    String toCompare = response.get("status").toString();
                                    System.out.println("To compare: " + toCompare);

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

                queue.add(jsonObjReq);
            }
        });



    }


}