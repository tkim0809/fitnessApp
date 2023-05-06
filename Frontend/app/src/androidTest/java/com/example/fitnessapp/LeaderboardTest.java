package com.example.fitnessapp;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class LeaderboardTest {

    @Rule
    public ActivityScenarioRule<leaderboard> activityRule = new ActivityScenarioRule<>(leaderboard.class);

    @Test
    public void testLeaderboardViewsDisplayed() {

        
        onView(withId(R.id.leaderboard_recyclerview)).check(matches(isDisplayed()));
        onView(withId(R.id.addpushupsButton)).check(matches(isDisplayed()));
        onView(withText("Leaderboard")).check(matches(isDisplayed()));
    }

    @Test
    public void testAddPushupsButtonClick() {
        onView(withId(R.id.addpushupsButton)).perform(click());

        // Add leaderboardAdd tests here
        onView(withId(R.id.addpushupsEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.addpushupsBut)).check(matches(isDisplayed()));
        onView(withId(R.id.backToLeaderboardBtn)).check(matches(isDisplayed()));
        onView(withText("add pushups")).check(matches(isDisplayed()));
    }

    @Test
    public void testAddPushupsButtonInLeaderboardAdd() {
        onView(withId(R.id.addpushupsButton)).perform(click());
        onView(withId(R.id.addpushupsEditText)).perform(typeText("10"));
        onView(withId(R.id.addpushupsBut)).check(matches(allOf(isDisplayed(), isEnabled()))).perform(click());
    }
}
