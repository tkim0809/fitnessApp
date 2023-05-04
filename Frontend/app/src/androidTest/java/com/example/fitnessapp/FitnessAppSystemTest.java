package com.example.fitnessapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class FitnessAppSystemTest {

    private UiDevice device;

    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void test_navigationBetweenActivities() throws UiObjectNotFoundException {



        // Launch the FitnessApp
        UiObject fitnessApp = device.findObject(new UiSelector().description("FitnessApp"));
        fitnessApp.click();

        // Fill in email and password fields
        UiObject emailField = device.findObject(new UiSelector().resourceId("com.example.fitnessapp:id/username"));
        emailField.setText("thisisme@iastate.edu");

        UiObject passwordField = device.findObject(new UiSelector().resourceId("com.example.fitnessapp:id/password"));
        passwordField.setText("981998199819");

        // Click the sign-in button and wait for the new window
        UiObject signInButton = device.findObject(new UiSelector().resourceId("com.example.fitnessapp:id/loginButton"));
        signInButton.clickAndWaitForNewWindow();

        // Wait for the NewUserMenu activity to be displayed
        UiObject newUserMenuLayout = device.findObject(new UiSelector().resourceId("com.example.fitnessapp:id/new_user_menu_layout"));
        assertTrue(newUserMenuLayout.waitForExists(5000));


    }

}
