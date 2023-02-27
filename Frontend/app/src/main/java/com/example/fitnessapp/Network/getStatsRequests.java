package com.example.fitnessapp.Network;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitnessapp.AppController;
import com.example.fitnessapp.Logic.IVolleyListener;

import org.json.JSONException;
import org.json.JSONObject;

public class getStatsRequests implements IServerRequest{
    IVolleyListener l;
    TextView name;
    TextView rep;
    TextView max;

    public void setName(TextView name) {
        this.name = name;
    }

    public void setRep(TextView rep) {
        this.rep = rep;
    }

    public void setMax(TextView max) {
        this.max = max;
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
                    name.setText(response.get("name").toString());
                    rep.setText(response.get("rep").toString());
                    max.setText(response.get("max").toString());
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
    public void addVolleyListener(IVolleyListener listener) {
    l = listener;
    }
}
