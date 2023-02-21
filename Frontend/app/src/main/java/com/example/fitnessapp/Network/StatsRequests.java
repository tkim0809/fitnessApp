package com.example.fitnessapp.Network;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.fitnessapp.Logic.IVolleyListener;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitnessapp.AppController;

import org.json.JSONObject;

public class StatsRequests implements IServerRequest{
    private IVolleyListener l;
    @Override
    public void sendToServer(String url, JSONObject jsObject, String method) {
        int meth;
        if(method == "Post"){
            meth = Request.Method.POST;
        }
        else {
            meth =Request.Method.GET;
        }
        JsonObjectRequest newExerciseRequest = new JsonObjectRequest(meth, url, jsObject,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    //todo
                        //depend on the string send back from the back end

                    }},

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    //todo
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(newExerciseRequest);


    }

    @Override
    public void addVolleyListener(IVolleyListener listener) {
        l=listener;
    }
}
