package com.example.fitnessapp.Network;

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
    String name;
    String rep;
    String max;

    public String getName() {
        return name;
    }

    public String getRep() {
        return rep;
    }

    public String getMax() {
        return max;
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
        /**JsonObjectRequest request = new JsonObjectRequest(meth, url, jsObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Request went through");
                try {
                    name = response.get("name").toString();
                    rep = response.get("rep").toString();
                    max = response.get("max").toString();
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
         **/
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(meth,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            name = response.get("name").toString();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            max = response.get("max").toString();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            rep = response.get("rep").toString();
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

    @Override
    public void addVolleyListener(IVolleyListener listener) {
    l = listener;
    }
}
