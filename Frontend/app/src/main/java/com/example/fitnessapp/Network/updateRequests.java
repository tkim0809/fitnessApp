package com.example.fitnessapp.Network;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.example.fitnessapp.Logic.IVolleyListener;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fitnessapp.AppController;

import org.json.JSONObject;

public class updateRequests implements IServerRequest{
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
        JsonObjectRequest request = new JsonObjectRequest(meth, url, jsObject,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(AppController.TAG,response.toString());
                        //depend on the string send back from the back end
                        System.out.println("Request went through");
                    }},

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("sent user data error",error);
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(request);




    }

    @Override
    public void addVolleyListener(IVolleyListener listener) {
        l=listener;
    }




}
