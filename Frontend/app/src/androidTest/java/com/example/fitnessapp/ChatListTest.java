package com.example.fitnessapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.contrib.RecyclerViewActions;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.fitnessapp.chat_function.ChatList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4ClassRunner.class)
public class ChatListTest {
    @Rule
    public ActivityScenarioRule<ChatList> activityScenarioRule = new
            ActivityScenarioRule<>(ChatList.class);
    @Test
    public void clickOnItemTest(){
        onView(withId(R.id.chatListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.CchatRecyclerView)).check(matches(isDisplayed()));

    }
}
