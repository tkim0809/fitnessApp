package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        TextView welcomeMsg = findViewById(R.id.welcomeMessage);

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("email");
        String password = extras.getString("password");
        welcomeMsg.setText("Welcome " + email);




    }
}