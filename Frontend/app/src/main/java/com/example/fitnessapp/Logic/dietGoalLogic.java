package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class dietGoalLogic implements IVolleyListener{
    IView i;
    IServerRequest serverRequest;

    public dietGoalLogic(IView i, IServerRequest serverRequest) {
        this.i = i;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }
    public void setGoal(int dailyCal, String weeklyCal) throws JSONException {
        final String url ="TBD";
        JSONObject goal = new JSONObject();
        goal.put("dietGoal",dailyCal);
        serverRequest.sendToServer(url,goal,"Put");
    }
    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
