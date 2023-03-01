package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    public static String userName = "";
    public static String email = "";

    private EditText username, password;
    private Button logIn;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logIn = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signUpButton);

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, signUpActivity.class);
                startActivity(intent);
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pw = password.getText().toString();
                userName = name;
                String url="";

                JsonArrayRequest jsonArr = new JsonArrayRequest(Request.Method.GET, url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {

                                    for (int i = 0; i < response.length(); i++) {

                                        JSONObject user = response.getJSONObject(i);

                                        String BEUsername = user.getString("userName");
                                        String BEPassword = user.getString("password");

                                        if (name.equals(BEUsername) && pw.equals(BEPassword)) {

                                            if (name.equals("user") && pw.equals("user")) {

                                                System.out.println("Login is successful");
                                                Intent intent = new Intent(HomeActivity.this, loginActivity.class);
                                                intent.putExtra("keyUsername", name);
                                                intent.putExtra("keyPassword", pw);
                                                startActivity(intent);
                                                break;

                                            } else {

                                                System.out.println("Login failed");

                                            }

                                        }

                                    }

                                } catch (JSONException e) {

                                    e.printStackTrace();

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
                );



            }
        });



    }
}