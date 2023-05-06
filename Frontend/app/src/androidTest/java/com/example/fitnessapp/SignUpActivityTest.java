package com.example.fitnessapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.app.Instrumentation;

@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {

    @Rule
    public ActivityScenarioRule<signUpActivity> activityRule = new ActivityScenarioRule<>(signUpActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testSignUpButtonValid() {
        // Set up a stub response for the expected intent
        Intents.intending(IntentMatchers.hasComponent(LoginPage.class.getName()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

        // Enter first name
        onView(withId(R.id.firstName)).perform(typeText("first"), closeSoftKeyboard());

        // Enter last name
        onView(withId(R.id.lastName)).perform(typeText("last"), closeSoftKeyboard());

        // Enter email
        onView(withId(R.id.email)).perform(typeText("email@example.com"), closeSoftKeyboard());

        // Enter password
        onView(withId(R.id.password)).perform(typeText("StrongPassword123"), closeSoftKeyboard());

        // Click sign up button
        onView(withId(R.id.signUpButton)).perform(click());

        // Check if LoginPage has been started
        Intents.intended(IntentMatchers.hasComponent(LoginPage.class.getName()));
        Intents.assertNoUnverifiedIntents();
    }

    @Test
    public void testSignUpButtonInvalid() {
        // Set up a stub response for the expected intent
        Intents.intending(IntentMatchers.hasComponent(LoginPage.class.getName()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

        // Enter first name
        onView(withId(R.id.firstName)).perform(typeText("first2"), closeSoftKeyboard());

        // Enter last name
        onView(withId(R.id.lastName)).perform(typeText("last2"), closeSoftKeyboard());

        // Enter email
        onView(withId(R.id.email)).perform(typeText("test2@example.com"), closeSoftKeyboard());

        // Enter short password
        onView(withId(R.id.password)).perform(typeText("invalid"), closeSoftKeyboard());

        // Click sign up button
        onView(withId(R.id.signUpButton)).perform(click());

        // Check if LoginPage has not been started
        Intents.assertNoUnverifiedIntents();
    }
}
