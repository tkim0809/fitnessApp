package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.UserInfo;

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
        final String url = "http://coms-309-004.class.las.iastate.edu:8080/profile/37";
        String meth;
        if (UserInfo.getHasProfile()){
            meth = "Put";
        }
        else {
            meth = "Post";
        }
        JSONObject newProfile = new JSONObject();
        newProfile.put("userName",username);

        newProfile.put("gender",gender);
        newProfile.put("age",age);
        newProfile.put("weight",weight);
        newProfile.put("email",email);
        serverRequest.sendToServer(url,newProfile,meth);


    }

    @Override
    public void onSuccess() {
        i.showText("saved");
    }

    @Override
    public void onError() { i.showText("error");

    }
}
