package com.example.fitnessapp;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.TypeTextAction;
import androidx.test.espresso.contrib.RecyclerViewActions;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.fitnessapp.chat_function.chatPage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4ClassRunner.class)
public class ChatPageTest {
    private static final int SIMULATED_DELAY_MS = 500;
    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule<>(chatPage.class);

    @Before
    public void setUp() {
        onView(withId(R.id.chatingMessage)).perform(typeText("Hi"), closeSoftKeyboard());
    }

    @Test
    public void sendMessageTest() {
        ActivityScenario<chatPage> scenario = activityScenarioRule.getScenario();


        // Run the test on a background thread
        scenario.onActivity(activity -> {
            RecyclerView recyclerView = activity.findViewById(R.id.chatRecyclerView);
            int initialItemCount = recyclerView.getAdapter().getItemCount();

            onView(withId(R.id.sendChatBtn)).perform(click());

            try {
                Thread.sleep(SIMULATED_DELAY_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            RecyclerView resultRecyclerView = activity.findViewById(R.id.chatRecyclerView);
            int resultItemCount = resultRecyclerView.getAdapter().getItemCount();
            assertEquals(initialItemCount, resultItemCount);
        });
    }

}
