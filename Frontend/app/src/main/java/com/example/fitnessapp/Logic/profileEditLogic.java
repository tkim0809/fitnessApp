package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class profileEditLogic implements IVolleyListener{
    IView i;
    IServerRequest serverRequest;

    public profileEditLogic(IView i, IServerRequest serverRequest) {
        this.i = i;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }




    public void editProfile(String username,String gender, String age, String weight, String email) throws JSONException {
        final String url = "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/profile";
        JSONObject newProfile = new JSONObject();
        newProfile.put("userName",username);

        newProfile.put("gender",gender);
        newProfile.put("age",age);
        newProfile.put("weight",weight);
        newProfile.put("email",email);
        serverRequest.sendToServer(url,newProfile,"Put");


    }

    @Override
    public void onSuccess() {
        i.showText("saved");
    }

    @Override
    public void onError() {

    }
}
