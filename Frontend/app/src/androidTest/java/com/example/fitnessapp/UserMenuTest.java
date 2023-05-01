package com.example.fitnessapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class UserMenuTest {

    @Rule
    public ActivityScenarioRule<NewUserMenu> activityScenario =
           new ActivityScenarioRule<>(NewUserMenu.class);
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
    @Test
    public void testBtnRecord(){
        onView(withId(R.id.recordBtn)).perform(click());
        onView(withId(R.id.workoutHistory_page)).check(matches(isDisplayed()));
    }
    @Test
    public void testDietBtn(){
        onView(withId(R.id.dietBtn)).perform(click());
        onView(withId(R.id.dietOL)).check(matches(isDisplayed()));
    }
    @Test
    public void testLeaderboardBtn(){
        onView(withId(R.id.leaderboardBtn)).perform(click());
        onView(withId(R.id.leader_board)).check(matches(isDisplayed()));
    }
    @Test
    public void testChatBtn(){
        onView(withId(R.id.chatBtn)).perform(click());
        onView(withId(R.id.chat_list)).check(matches(isDisplayed()));
    }
    @Test
    public void testFindFriendBtn(){
        onView(withId(R.id.findFriendButton)).perform(click());
        onView(withId(R.id.find_friend)).check(matches(isDisplayed()));
    }
    @Test
    public void testFriendBtn(){
        onView(withId(R.id.FriendListButtonXML)).perform(click());
        onView(withId(R.id.friend_list)).check(matches(isDisplayed()));
    }
    @Test
    public void testMilestoneBtn(){
        onView(withId(R.id.milestoneBtn)).perform(click());
        onView(withId(R.id.mile_stone)).check(matches(isDisplayed()));
    }

}
