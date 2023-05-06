package com.example.fitnessapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class FriendListTest {

    @Rule
    public ActivityScenarioRule<friendList> activityScenarioRule =
            new ActivityScenarioRule<>(friendList.class);

    @Test
    public void isFriendListTitleDisplayed() {
        onView(withId(R.id.friendslistTitle)).check(matches(isDisplayed()));
    }

    @Test
    public void isFriendListRecyclerViewDisplayed() {
        onView(withId(R.id.friendlistRecyclerV)).check(matches(isDisplayed()));
    }
}
