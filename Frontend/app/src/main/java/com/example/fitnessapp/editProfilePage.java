package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.Logic.profileEditLogic;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.Network.updateRequests;

import org.json.JSONException;

public class editProfilePage extends AppCompatActivity implements IView{
    Button back;
    Button save;
    TextView gender;
    TextView age;
    TextView weight;
    TextView email;


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
                String inputGender = gender.getText().toString();
                String inputAge = age.getText().toString();
                String inputWeight = weight.getText().toString();
                String inputEmail = email.getText().toString();
                try {
                    logic.editProfile(inputGender,inputAge,inputWeight,inputEmail);
                    logic.onSuccess();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }



    @Override
    public void showText(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}