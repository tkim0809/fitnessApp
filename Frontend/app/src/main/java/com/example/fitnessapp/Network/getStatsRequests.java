package com.example.fitnessapp.Network;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitnessapp.AppController;
import com.example.fitnessapp.Logic.IVolleyListener;

import org.json.JSONObject;

public class getStatsRequests implements IServerRequest{
    IVolleyListener l;
    JSONObject object;
    @Override
    public JSONObject getObject() {
        return object;
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
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            object= response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public void addVolleyListener(IVolleyListener listener) {
    l = listener;
    }
}
