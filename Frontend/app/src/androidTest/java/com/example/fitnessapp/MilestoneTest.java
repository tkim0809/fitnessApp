package com.example.fitnessapp;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.RootMatchers;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

import android.os.SystemClock;


@RunWith(AndroidJUnit4.class)
public class MilestoneTest {

    @Rule
    public ActivityScenarioRule<milestone> activityScenarioRule = new ActivityScenarioRule<>(milestone.class);

    @Test
    public void inputTextAndAddMilestone() {
        // Input text in all 4 EditText fields
        onView(withId(R.id.workoutName)).perform(clearText(), typeText("Test Workout"));
        onView(withId(R.id.weights)).perform(clearText(), typeText("50"));
        onView(withId(R.id.reps)).perform(clearText(), typeText("10"));
        onView(withId(R.id.sets)).perform(clearText(), typeText("3"));

        // Close soft keyboard
        Espresso.closeSoftKeyboard();

        // Click the "Add" button

        onView(withId(R.id.addButton)).perform(click());

        SystemClock.sleep(50000);

        // Check if a "Successfully added" toast message is displayed
        // This is to check if Toast message is equal to add successful because if it does, it means that the json request was successful, but then the Toast
        // message will disappear before this test was able to run and check the message.
        onView(withText("add successful")).inRoot(new ToastMatchCheck()).check(matches(isDisplayed()));


    }
}
