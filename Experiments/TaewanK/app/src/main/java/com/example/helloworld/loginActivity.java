package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {

    private TextView textUsername, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUsername = findViewById(R.id.text_username);
        textPassword = findViewById(R.id.text_password);

        String username = getIntent().getStringExtra("keyUsername");
        String password = getIntent().getStringExtra("keyPassword");

        textUsername.setText(username);
        textPassword.setText(password);

    }
}