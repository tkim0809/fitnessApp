package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    private EditText username, password;
    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logIn = findViewById(R.id.loginButton);

        logIn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pw = password.getText().toString();

                Intent intent = new Intent(HomeActivity.this, loginActivity.class);
                intent.putExtra("keyUsername", name);
                intent.putExtra("keyPassword", pw);
                startActivity(intent);
            }
        });

    }
}