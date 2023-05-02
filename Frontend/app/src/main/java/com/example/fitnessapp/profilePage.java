package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Profile UI class
 */
public class profilePage extends AppCompatActivity {
    public void setUserId(String userId) {
        this.userId = userId;
    }

    String userId = UserInfo.getUserID();
    Button edit,back;
    TextView gender;
    TextView age;
    TextView email;
    TextView weight;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        gender = findViewById(R.id.genderTxt);
        age = findViewById(R.id.ageTxt);
        email = findViewById(R.id.emailTxt);
        weight = findViewById(R.id.weightTxt);
        username =findViewById(R.id.userNameTxt);
        back =findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profilePage.this,NewUserMenu.class);
                startActivity(i);
            }
        });
        makeJsonObjReq();
        edit = findViewById(R.id.editPBtn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEditPf = new Intent(profilePage.this,editProfilePage.class);
                startActivity(toEditPf);
            }
        });

    }

    /**
     * This method makes and displays request to the server for user profile information
     */
    private void makeJsonObjReq() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                "http://coms-309-004.class.las.iastate.edu:8080/profile/"+userId, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            username.setText(response.get("userName").toString());

                            gender.setText(response.get("gender").toString());
                            age.setText(response.get("age").toString());
                            email.setText(response.get("email").toString());
                            weight.setText(response.get("weight").toString());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().getRequestQueue().add(jsonObjReq);
    }
}