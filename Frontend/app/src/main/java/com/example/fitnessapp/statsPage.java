package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitnessapp.Network.getStatsRequests;

import org.json.JSONObject;

public class statsPage extends AppCompatActivity implements IView{
    JSONObject exercise;
    Button editBtn;
    TextView ex1Name;
    TextView ex1Sets;
    TextView ex1Rep;
    TextView ex1Weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        editBtn = findViewById(R.id.editBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent(statsPage.this, statsEdit.class);
                startActivity(toEdit);
            }
        });

        ex1Name = findViewById(R.id.ex1Txt);
        ex1Sets =findViewById(R.id.ex1Sets);
        ex1Rep = findViewById(R.id.ex1RepTxt);
        ex1Weight = findViewById(R.id.ex1MaxTxt);
      //  makeJsonObjReq();


        getStatsRequests getStatsRQ = new getStatsRequests();
        getStatsRQ.setName(ex1Name);
        getStatsRQ.setSets(ex1Sets);
        getStatsRQ.setRep(ex1Rep);
        getStatsRQ.setWeight(ex1Weight);
        getStatsRQ.sendToServer("http://coms-309-004.class.las.iastate.edu:8080/Workouts", null,"Get");


    }

    @Override
    public void showText(String s) {

    }
/**
 private void makeJsonObjReq() {

 JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
 "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/stats", null,
 new Response.Listener<JSONObject>() {

@Override
public void onResponse(JSONObject response) {
try {
ex1Name.setText(response.get("name").toString());
ex1Rep.setText(response.get("rep").toString());
ex1Max.setText(response.get("max").toString());
} catch (JSONException e) {
throw new RuntimeException(e);
}
}
}, new Response.ErrorListener() {

@Override
public void onErrorResponse(VolleyError error) {

}
});
 AppController.getInstance().getRequestQueue().add(jsonObjReq);
    }
*/
}
