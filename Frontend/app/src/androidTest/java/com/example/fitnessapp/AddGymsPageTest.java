package com.example.fitnessapp;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AddGymsPageTest {

    @Rule
    public ActivityScenarioRule<addgymsPage> activityRule = new ActivityScenarioRule<>(addgymsPage.class);

    @Test
    public void testInputEditTextAndClickAddButton() {

        onView(withId(R.id.gym_name)).perform(typeText("Test Gym"), closeSoftKeyboard());
        onView(withId(R.id.gym_description)).perform(typeText("This is a test gym"), closeSoftKeyboard());
        onView(withId(R.id.gym_location)).perform(typeText("Test Street"), closeSoftKeyboard());
        onView(withId(R.id.gym_phoneNumber)).perform(typeText("515-123-4567"), closeSoftKeyboard());
        onView(withId(R.id.gym_hours)).perform(typeText("9:00 AM - 5:00 PM"), closeSoftKeyboard());

        onView(withId(R.id.addgymsBtn)).perform(click());

    }

    @Test
    public void testNavigateToAddGymsAndDisplayGymsList() {

        onView(withId(R.id.addgymsBtn)).perform(click());

        // Navigate to gymsListPage
        onView(withId(R.id.addgyms_list)).perform(click());

        // Check if the RecyclerView is displayed
        onView(withId(R.id.gymsListRecyclerView)).check(matches(isDisplayed()));

    }

}
