package com.example.fitnessapp.Network;

import com.example.fitnessapp.Logic.IVolleyListener;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IServerRequest {
    public void sendToServer(String url, JSONObject jsObject, String method);
    public void sendToServer(String url, JSONArray array, String method);
    public void addVolleyListener(IVolleyListener listener);
    

}
