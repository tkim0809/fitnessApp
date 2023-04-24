package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Viewing workout history class
 */
public class workoutHistoryPage extends AppCompatActivity implements IView{
    JSONObject exercise;
    Button addBtn;
    TextView ex1Name;
    TextView ex1Sets;
    TextView ex1Rep;
    TextView ex1Weight;
    TextView ex2Name;
    TextView ex2Sets;
    TextView ex2Rep;
    TextView ex2Weight;
    TextView ex3Name;
    TextView ex3Sets;
    TextView ex3Rep;
    TextView ex3Weight;
    final String url = "http://coms-309-004.class.las.iastate.edu:8080/Workouts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        addBtn = findViewById(R.id.addExBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent(workoutHistoryPage.this, workoutHistoryAdd.class);
                startActivity(toEdit);
            }
        });

        ex1Name = findViewById(R.id.ex1Txt);
        ex1Sets =findViewById(R.id.ex1Sets);
        ex1Rep = findViewById(R.id.ex1RepTxt);
        ex1Weight = findViewById(R.id.ex1MaxTxt);
        ex2Name = findViewById(R.id.ex2Txt);
        ex2Sets =findViewById(R.id.ex2Sets);
        ex2Rep = findViewById(R.id.ex2RepTxt);
        ex2Weight = findViewById(R.id.ex2MaxTxt);
        ex3Name = findViewById(R.id.ex3Txt);
        ex3Sets =findViewById(R.id.ex3Sets);
        ex3Rep = findViewById(R.id.ex3RepTxt);
        ex3Weight = findViewById(R.id.ex3MaxTxt);
        makeRequest();



    }

    @Override
    public void showText(String s) {

    }

    /**
     * This method requests and display the user workout history.
     */
    private void makeRequest(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    ex1Name.setText(response.getJSONObject(0).get("workoutName").toString());
                    ex1Rep.setText(response.getJSONObject(0).get("workoutReps").toString());
                    ex1Sets.setText(response.getJSONObject(0).get("workoutSets").toString());
                    ex1Weight.setText(response.getJSONObject(0).get("workoutWeight").toString());
                }catch (Exception e){

                }
                try {

                    ex2Name.setText(response.getJSONObject(1).get("workoutName").toString());
                    ex2Rep.setText(response.getJSONObject(1).get("workoutReps").toString());
                    ex2Sets.setText(response.getJSONObject(1).get("workoutSets").toString());
                    ex2Weight.setText(response.getJSONObject(1).get("workoutWeight").toString());
                }catch (Exception e){

                }
                try {
                    ex3Name.setText(response.getJSONObject(2).get("workoutName").toString());
                    ex3Rep.setText(response.getJSONObject(2).get("workoutReps").toString());
                    ex3Sets.setText(response.getJSONObject(2).get("workoutSets").toString());
                    ex3Weight.setText(response.getJSONObject(2).get("workoutWeight").toString());
                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }
}
