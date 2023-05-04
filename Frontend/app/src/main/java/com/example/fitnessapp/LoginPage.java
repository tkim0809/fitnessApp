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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used to check if login information is valid
 */
public class LoginPage extends AppCompatActivity {

    public static String userName = "";
    //public static String email = "";

    public static String userId;
    private EditText Email, password;
    private Button logIn;
    private Button signUp, backToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logIn = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signUpButton);

        System.out.println(UserInfo.getUserID());


        /**
         * When "sign up" button is pressed, moves to sign up screen
         */
        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, signUpActivity.class);
                startActivity(intent);
            }
        });

        /**
         * when "log in" button is pressed.
         */
        logIn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                String email = Email.getText().toString(); // user text for email address
                String pw = password.getText().toString(); // user text for password
                //userName = name;
                String url="http://coms-309-004.class.las.iastate.edu:8080/login";
                RequestQueue queue = Volley.newRequestQueue(LoginPage.this);
                JSONObject obj = new JSONObject();

                try {

                    obj.put("email", email);
                    obj.put("password", pw);


                } catch(Exception e) {

                    System.out.println("ERROR23");

                }

                /**
                 * sends json POST method request with 2 json objects, "email" and "password"
                 * And if given email and password is valid, gets response as "result"
                 */
                JsonObjectRequest jsonArr = new JsonObjectRequest(Request.Method.POST, url,
                        obj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                //System.out.println(response.toString());

                                try {
                                    if (response.getBoolean("result")) {

                                        userId = response.getString("user_id");

                                        Toast.makeText(getApplicationContext(), "login successful", Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(LoginPage.this, NewUserMenu.class);
                                        i.putExtra("email", email);
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