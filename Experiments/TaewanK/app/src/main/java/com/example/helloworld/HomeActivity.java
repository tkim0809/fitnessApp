package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    private EditText name, surname;
    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name = findViewById(R.id.username);
        surname = findViewById(R.id.password);
        logIn = findViewById(R.id.loginButton);

        logIn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String password = surname.getText().toString();

                Intent intent = new Intent(HomeActivity.this, loginActivity.class);
                intent.putExtra("keyUsername", username);
                intent.putExtra("keyPassword", password);
                startActivity(intent);
            }
        });

    }
}