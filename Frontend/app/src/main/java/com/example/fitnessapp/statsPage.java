package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitnessapp.Logic.StatsUpdateLogic;
import com.example.fitnessapp.Logic.getStatsLogic;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.Network.getStatsRequests;

import org.json.JSONException;
import org.json.JSONObject;

public class statsPage extends AppCompatActivity implements IView{
    JSONObject exercise;
    Button editBtn;
    TextView ex1Name;
    TextView ex1Rep;
    TextView ex1Max;
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
        ex1Rep = findViewById(R.id.ex1RepTxt);
        ex1Max = findViewById(R.id.ex1MaxTxt);
      //  makeJsonObjReq();


 getStatsRequests getStatsRQ = new getStatsRequests();
 getStatsRQ.sendToServer("https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/stats", null,"Get");
 ex1Name.setText(getStatsRQ.getName().toString());
 ex1Rep.setText(getStatsRQ.getRep().toString());
 ex1Max.setText(getStatsRQ.getMax().toString());

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
