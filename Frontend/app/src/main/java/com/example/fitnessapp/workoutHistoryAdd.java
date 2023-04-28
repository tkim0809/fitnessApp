package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.Logic.workoutHistoryUpdateLogic;
import com.example.fitnessapp.Network.updateRequests;

import org.json.JSONException;

/**
 * Adding work out history UI class.
 */
//Edit the exercises "name", "rep" and "max" and send it to the backend.
public class workoutHistoryAdd extends AppCompatActivity implements IView{
    EditText exName;
    EditText exRep;
    EditText exMax;
    EditText exSets;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_edit);
        exName = findViewById(R.id.exciseNameTxt);
        exRep = findViewById(R.id.exciseRepTxt);
        exMax = findViewById(R.id.exciseMaxTxt);
        exSets = findViewById(R.id.exciseSetsTxt);
        saveBtn = findViewById(R.id.saveExBtn);

        updateRequests upDateStatsRQ = new updateRequests();
        final workoutHistoryUpdateLogic statsLogic = new workoutHistoryUpdateLogic(workoutHistoryAdd.this,upDateStatsRQ);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = exName.getText().toString();
                String sets = exSets.getText().toString();
                String rep = exRep.getText().toString();
                String weight = exMax.getText().toString();
                try {
                    statsLogic.addExercise(name,sets,rep,weight);
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