package com.example.fitnessapp.Network;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitnessapp.AppController;
import com.example.fitnessapp.Logic.IVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getStatsRequests implements IServerRequest{
    IVolleyListener l;
    TextView name;



    TextView sets;
    TextView rep;
    TextView weight;

    public void setName(TextView name) {
        this.name = name;
    }
    public void setSets(TextView sets) {
        this.sets = sets;
    }

    public void setRep(TextView rep) {
        this.rep = rep;
    }

    public void setWeight(TextView weight) {
        this.weight = weight;
    }

    @Override
    public void sendToServer(String url, JSONObject jsObject, String method) {
        int meth;

        if(method == "Post"){
            meth = Request.Method.POST;
        }
        else {
            meth =Request.Method.GET;
        }
        JsonObjectRequest request = new JsonObjectRequest(meth, url, jsObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Request went through");
                try {
                    name.setText(response.get("workoutName").toString());
                    rep.setText(response.get("workoutReps").toString());
                    sets.setText(response.get("workoutSets").toString());
                    weight.setText(response.get("workoutWeight").toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Get user data error",error.toString());
            }

        });
        AppController.getInstance().addToRequestQueue(request);



        }

    @Override
    public void sendToServer(String url, JSONArray array, String method) {

    }


    @Override
    public void addVolleyListener(IVolleyListener listener) {
    l = listener;
    }
}
