package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.UserInfo;

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
        String meth;
        if (UserInfo.getHasSetDietGoal()){
            meth = "Put";

        }else {
            meth ="Post";
        }
        final String url ="http://coms-309-004.class.las.iastate.edu:8080/dietgoal/"+UserInfo.getUserID();
        JSONObject goal = new JSONObject();
        goal.put("dietGoalValue",dailyCal);
        serverRequest.sendToServer(url,goal,meth);
    }
    @Override
    public void onSuccess() {
        i.showText("saved");
        //UserInfo.setHasSetDietGoal(true);
    }

    @Override
    public void onError() {
        i.showText("error");
    }
}
