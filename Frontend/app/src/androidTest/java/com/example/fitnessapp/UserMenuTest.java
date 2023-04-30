package com.example.fitnessapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class UserMenuTest {

    @Rule
    public ActivityScenario<NewUserMenu> activityScenario =
            ActivityScenario.launch(NewUserMenu.class);
    @Test
    public void testBtnProfile(){
            onView(withId(R.id.profileBtn)).perform(click());
            onView(withId(R.id.profile_page)).check(matches(isDisplayed()));


    }
    @Test
    public void testBtnLogout(){
        onView(withId(R.id.logOutBtn)).perform(click());
        onView(withId(R.id.login_page)).check(matches(isDisplayed()));
    }
    public void testBtnRecord(){
        onView(withId(R.id.recordBtn)).perform(click());
        onView(withId(R.id.workoutHistory_page)).check(matches(isDisplayed()));
    }

}
