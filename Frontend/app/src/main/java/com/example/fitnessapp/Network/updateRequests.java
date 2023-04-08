package com.example.fitnessapp.Network;

import android.util.Log;

        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.fitnessapp.Logic.IVolleyListener;
        import com.android.volley.Request;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.example.fitnessapp.AppController;

import org.json.JSONArray;
import org.json.JSONException;
        import org.json.JSONObject;

public class updateRequests implements IServerRequest {
    private IVolleyListener l;

    @Override
    public void sendToServer(String url, JSONObject jsObject, String method) {
        int meth;

        if (method == "Post") {
            meth = Request.Method.POST;
        } else if (method =="Get"){
            meth = Request.Method.GET;
        } else {
            meth = Request.Method.PUT;
        }
        JsonObjectRequest request = new JsonObjectRequest(meth, url, jsObject,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        l.onSuccess();
                        try {
                            String status = response.getString("message");
                            if (status.equals("success")) {
                                VolleyLog.d("Exercise added successfully");
                            } else {
                                VolleyLog.d("Failed to add exercise");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        l.onError();
                        //VolleyLog.d("sent user data error",error);
                        // Handle the error
                        error.printStackTrace();
                        String errorMessage = error.getMessage() != null ? error.getMessage() : "Unknown error";
                        VolleyLog.d("Request error: " + errorMessage);
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(request);


    }

    @Override
    public void sendToServer(String url, JSONArray array, String method) {
        int meth;

        if (method == "Post") {
            meth = Request.Method.POST;
        } else {
            meth = Request.Method.GET;
        }
        JsonArrayRequest request = new JsonArrayRequest(meth, url, array, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("responding");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                String errorMessage = error.getMessage() != null ? error.getMessage() : "Unknown error";
                VolleyLog.d("Request error: " + errorMessage);
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }


    @Override
    public void addVolleyListener(IVolleyListener listener) {
        l = listener;
    }
}
