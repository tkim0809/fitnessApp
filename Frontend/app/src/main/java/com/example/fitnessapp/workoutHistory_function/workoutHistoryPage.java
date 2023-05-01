package com.example.fitnessapp.workoutHistory_function;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.fitnessapp.AppController;
import com.example.fitnessapp.IView;
import com.example.fitnessapp.NewUserMenu;
import com.example.fitnessapp.R;
import com.example.fitnessapp.chat_function.ChatItem_RecyclerViewAdapter;
import com.example.fitnessapp.chat_function.chatItemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Viewing workout history class
 */
public class workoutHistoryPage extends AppCompatActivity implements IView {
    Button addBtn;
    RecyclerView recyclerView;
    ArrayList<workoutHistoryItemModel> Items = new ArrayList<>();
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
        Button back = findViewById(R.id.WHBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(workoutHistoryPage.this, NewUserMenu.class);
                startActivity(intent);
            }
        });


        makeRequest();
        recyclerView = findViewById(R.id.workoutItemRView);




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
                System.out.println(response.toString());
                for (int i = 0; i<response.length();++i){
                    try {
                        String workout = response.getJSONObject(i).get("workoutName").toString();
                        String reps = response.getJSONObject(i).get("workoutReps").toString();
                        String weight = response.getJSONObject(i).get("workoutWeight").toString();
                        String sets = response.getJSONObject(i).get("workoutSets").toString();
                        Items.add(new workoutHistoryItemModel(workout,sets,reps,weight));
                        System.out.println(Items.size());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(workoutHistoryPage.this));
                workoutItem_RecyclerViewAdapter adapter = new workoutItem_RecyclerViewAdapter(Items,workoutHistoryPage.this);
                recyclerView.setAdapter(adapter);


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
