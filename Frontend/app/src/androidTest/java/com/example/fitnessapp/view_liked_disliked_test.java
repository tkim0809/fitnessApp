package com.example.fitnessapp;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class view_liked_disliked_test {

    @Rule
    public ActivityScenarioRule<LoginPage> activityRule = new ActivityScenarioRule<>(LoginPage.class);

    @Test
    public void testLikedByButtonClick() {

        onView(withId(R.id.username)).perform(typeText("thisisme@iastate.edu"), closeSoftKeyboard());


        onView(withId(R.id.password)).perform(typeText("981998199819"), closeSoftKeyboard());


        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.gymsBtn)).perform(click());
        onView(withId(R.id.addgyms_list)).perform(click());

        onView(allOf(withId(R.id.liked), isDescendantOfA(withId(R.id.gymslist_recycler_view_id))))
                .perform(click());
        onView(withId(R.id.likedgymsRecyclerview)).check(matches(isDisplayed()));

    }

    @Test
    public void testDislikedByButtonClick() {

        onView(withId(R.id.username)).perform(typeText("testEspresso@gmail.com"), closeSoftKeyboard());


        onView(withId(R.id.password)).perform(typeText("1234567890"), closeSoftKeyboard());


        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.disliked)).perform(click());
        onView(withId(R.id.dislikedgymsRecyclerview)).check(matches(isDisplayed()));

    }



}
