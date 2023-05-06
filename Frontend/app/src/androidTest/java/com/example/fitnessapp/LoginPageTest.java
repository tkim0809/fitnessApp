package com.example.fitnessapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LoginPageTest {

    @Rule
    public ActivityScenarioRule<LoginPage> activityRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void testLoginButton() {
        // Enter email
        onView(withId(R.id.username)).perform(typeText("testEspresso@gmail.com"), closeSoftKeyboard());

        // Enter password
        onView(withId(R.id.password)).perform(typeText("1234567890"), closeSoftKeyboard());

        // Click login button
        onView(withId(R.id.loginButton)).perform(click());

    }

    @Test
    public void testLoginButtonInvalidCredentials() {

        // Enter incorrect email
        onView(withId(R.id.username)).perform(typeText("wrong@example.com"), closeSoftKeyboard());

        // Enter incorrect password
        onView(withId(R.id.password)).perform(typeText("wrongPassword"), closeSoftKeyboard());

        // Click login button
        onView(withId(R.id.loginButton)).perform(click());

    }
}
