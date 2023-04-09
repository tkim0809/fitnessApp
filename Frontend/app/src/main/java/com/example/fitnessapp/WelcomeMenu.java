package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_menu);

        Button friendButton = findViewById(R.id.findFriendButton);
        Button friendListButton = findViewById(R.id.FriendListButtonXML);
        Button signOutButton = findViewById(R.id.signOut);
        Button menuButton = findViewById(R.id.menu);

        menuButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeMenu.this, userMenu.class);
                startActivity(intent);
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeMenu.this, LoginPage.class);
                startActivity(intent);
            }
        });




        friendButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent i = new Intent(WelcomeMenu.this, findFriendActivity.class);
                startActivity(i);

            }

        });

        friendListButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeMenu.this, friendList.class);
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