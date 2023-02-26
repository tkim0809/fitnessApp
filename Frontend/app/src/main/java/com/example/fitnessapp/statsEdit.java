package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.Logic.StatsUpdateLogic;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.Network.updateStatsRequests;

import org.json.JSONException;

//Edit the exercises "name", "rep" and "max" and send it to the backend.
public class statsEdit extends AppCompatActivity implements IView{
    TextView exName;
    TextView exRep;
    TextView exMax;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_edit);
        exName = findViewById(R.id.exciseNameTxt);
        exRep = findViewById(R.id.exciseMaxTxt);
        exMax = findViewById(R.id.exciseRepTxt);
        saveBtn = findViewById(R.id.saveExBtn);
        IServerRequest upDateStatsRQ = new updateStatsRequests();
        final StatsUpdateLogic statsLogic = new StatsUpdateLogic(statsEdit.this,upDateStatsRQ);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = exName.getText().toString();
                    String rep = exRep.getText().toString();
                    String max = exMax.getText().toString();
                    statsLogic.addExercise(name,rep,max);
                    statsLogic.onSuccess();
                }catch (JSONException e){
                    statsLogic.onError();
                }

            }
        });
    }



    @Override
    public void showText(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}