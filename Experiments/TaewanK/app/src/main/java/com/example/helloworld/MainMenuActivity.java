package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button friendButton = findViewById(R.id.findFriendButton);
        Button friendListButton = findViewById(R.id.FriendListButtonXML);

        friendButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainMenuActivity.this, findFriendActivity.class);
                startActivity(i);

            }

        });

        friendListButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this, FriendListActivity.class);
                startActivity(i);
            }
        }) ;

        TextView welcomeMsg = findViewById(R.id.welcomeMessage);

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("email");
        String password = extras.getString("password");
        welcomeMsg.setText("Welcome " + email);






    }
}