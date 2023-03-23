package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class addDietLogic implements IVolleyListener{
    IView i;
    IServerRequest serverRequest;

    public addDietLogic(IView i, IServerRequest serverRequest) {
        this.i = i;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }
    public void addDiet(String name, String calories, String date) throws JSONException {
        final String url = "TBD";
        JSONObject newFood = new JSONObject();
        newFood.put("name", name);
        newFood.put("calories", calories);
        newFood.put("date", date);
        serverRequest.sendToServer(url,newFood,"Post");

    }
    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
