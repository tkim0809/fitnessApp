package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;

import org.w3c.dom.Text;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signUpActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUpButton = findViewById(R.id.signUpButton);
        EditText userName = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText email = findViewById(R.id.email);
        EditText age = findViewById(R.id.age);
        TextView errorMsg = findViewById(R.id.errorMsg);
        Button backToLogin = findViewById(R.id.backLogin);

        backToLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(signUpActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                errorMsg.setText("");
                boolean isValid = true;

                if(userName.getText().toString().equals("") || password.getText().toString().equals("")|| email.getText().toString().equals("")||age.getText().toString().equals("")) {

                    errorMsg.setText(errorMsg.getText() + "\nsomething's missing!");
                    isValid = false;

                } else if (password.getText().toString().length() < 10) {

                    errorMsg.setText(errorMsg.getText() + "\npassword too short");
                    isValid = false;

                }

                if(isValid) {

                    postData(userName.getText().toString(),password.getText().toString(), email.getText().toString(), age.getText().toString());


                    Intent i = new Intent(signUpActivity.this, HomeActivity.class);
                    startActivity(i);


                }


            }
        });

    }

    private void postData(String firstname, String lastname, String email, String password) {

        String url = "http://coms-309-004.class.las.iastate.edu:8080/api/v1/registration";
        RequestQueue queue = Volley.newRequestQueue(signUpActivity.this);
        JSONObject obj = new JSONObject();

        try {

            obj.put("firstName", firstname);
            obj.put("lastName", lastname);
            obj.put("email", email);
            obj.put("password", password);


        } catch(Exception e) {

            System.out.println("ERROR");

        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, obj,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(signUpActivity.this, "Successful", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if( error instanceof ServerError) {
                    Toast.makeText(signUpActivity.this,"Server",Toast.LENGTH_SHORT).show();
                } else if( error instanceof NetworkError) {
                    Toast.makeText(signUpActivity.this,"Network",Toast.LENGTH_SHORT).show();
                    System.out.println(error.getMessage());
                } else if( error instanceof AuthFailureError) {
                    Toast.makeText(signUpActivity.this,"Auth",Toast.LENGTH_SHORT).show();
                } else if( error instanceof ParseError) {
                    Toast.makeText(signUpActivity.this,"Parse",Toast.LENGTH_SHORT).show();
                } else if( error instanceof NoConnectionError) {
                    Toast.makeText(signUpActivity.this,"No Connection",Toast.LENGTH_SHORT).show();
                } else if( error instanceof TimeoutError) {
                    Toast.makeText(signUpActivity.this,"T/O",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(signUpActivity.this,"Unknown",Toast.LENGTH_SHORT).show();

                }
            }
        })
        {

            @Override
            public Map<String, String> getParams() {

                Map<String,String> params=new HashMap<String, String>();
                params.put("username", firstname);
                params.put("password",password);
                params.put("email", email);
                params.put("age", lastname);
                return params;

            }

        };

        queue.add(jsonObjReq);

    }



}