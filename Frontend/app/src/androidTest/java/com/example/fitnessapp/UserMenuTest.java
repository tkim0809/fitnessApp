package com.example.fitnessapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import android.support.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserMenuTest {

    @Rule
    public ActivityTestRule<NewUserMenu> activityRule =
            new ActivityTestRule<>(NewUserMenu.class);
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
