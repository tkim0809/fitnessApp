package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.Logic.profileEditLogic;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.Network.updateRequests;

import org.json.JSONException;

public class editProfilePage extends AppCompatActivity implements IView{
    public void setUserId(String userId) {
        this.userId = userId;
    }

    String userId = UserInfo.getUserID();

    Button back;
    Button save;
    EditText username;

    EditText gender;
    EditText age;
    EditText weight;
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_page);
        back =findViewById(R.id.BackToProfileBtn);
        save = findViewById(R.id.savePFBtn);
        gender = findViewById(R.id.edGenderTxt);
        age = findViewById(R.id.edAgeTxt);
        weight = findViewById(R.id.edWeightTxt);
        email = findViewById(R.id.edEmailTxt);
        username = findViewById(R.id.edUNTxt);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfile = new Intent(editProfilePage.this,profilePage.class);
                startActivity(toProfile);
            }
        });
        IServerRequest serverRequest = new updateRequests();
        final profileEditLogic logic = new profileEditLogic(editProfilePage.this, serverRequest);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo.setHasProfile(true);
                String inputGender = gender.getText().toString();
                String inputAge = age.getText().toString();
                String inputWeight = weight.getText().toString();
                String inputEmail = email.getText().toString();
                String inputUsername = username.getText().toString();

                try {
                    logic.editProfile(inputUsername,inputGender,inputAge,inputWeight,inputEmail,userId);
                    logic.onSuccess();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                Intent intent = new Intent(editProfilePage.this,profilePage.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void showText(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}