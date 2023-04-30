package com.example.fitnessapp;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

@RunWith(AndroidJUnit4.class)
public class LoginPageSystemTest {

    private Context appContext;
    private ActivityScenario<LoginPage> scenario;

    @Before
    public void setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(appContext, LoginPage.class);
        scenario = ActivityScenario.launch(intent);
    }

    @Test
    public void testValidLogin() throws JSONException {
        scenario.onActivity(activity -> {
            RequestQueue queue = Volley.newRequestQueue(activity);

            JSONObject obj = new JSONObject();

            try {
                obj.put("email", "test@test.com");
                obj.put("password", "testpassword");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            String url = "http://coms-309-004.class.las.iastate.edu:8080/login";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj,
                    response -> {
                        try {
                            Assert.assertTrue(response.getBoolean("success"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        throw new RuntimeException("Error occurred during login request");
                    });

            queue.add(request);
        });
    }

    @Test
    public void testInvalidLogin() throws JSONException {
        scenario.onActivity(activity -> {
            RequestQueue queue = Volley.newRequestQueue(activity);

            JSONObject obj = new JSONObject();
            try {
                obj.put("email", "invalid@test.com");
                obj.put("password", "invalidpassword");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            String url = "http://coms-309-004.class.las.iastate.edu:8080/login";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, obj,
                    response -> {
                        try {
                            Assert.assertFalse(response.getBoolean("success"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        throw new RuntimeException("Error occurred during login request");
                    });

            queue.add(request);
        });
    }
}
