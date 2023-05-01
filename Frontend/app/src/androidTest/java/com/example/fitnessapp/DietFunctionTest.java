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
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertEquals;

import android.support.test.InstrumentationRegistry;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.fitnessapp.diet_function.dietPage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class DietFunctionTest {
    private static final int SIMULATED_DELAY_MS = 500;
    @Rule
    public ActivityScenarioRule<dietPage> activityScenario =
            new ActivityScenarioRule<>(dietPage.class);
    @Test
    public void setDietGoalTest(){
        String goal = "2000";
        onView(withId(R.id.setGoalBtn)).perform(click());
        onView(withId(R.id.goalCalTxt)).perform(typeText(goal),closeSoftKeyboard());
        onView(withId(R.id.setBtn)).perform(click());
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }
        onView(withId(R.id.BackBtn)).perform(click());
        onView(withId(R.id.dailyCalTxt)).check(matches(withText(goal+"Cal")));
    }
    @Test
    public void addMealTest(){
        String food = "Beef";
        String Cal = "500";
        ActivityScenario<dietPage> scenario = activityScenario.getScenario();

        // Run the test on a background thread
        scenario.onActivity(activity -> {
                onView(withId(R.id.TodayBtn)).perform(click());
                // Perform checks or actions on the RecyclerView here
                RecyclerView recyclerView = activity.findViewById(R.id.dailyDietRV);
                int initialItemCount = recyclerView.getAdapter().getItemCount();
                onView(withId(R.id.backToDietBtn)).perform(click());
                onView(withId(R.id.addMealBtn)).perform(click());
                onView(withId(R.id.FoodTxt)).perform(typeText(food),closeSoftKeyboard());
                onView(withId(R.id.CaloriesTxt)).perform(typeText(Cal),closeSoftKeyboard());
                onView(withId(R.id.MealDD)).perform(click());

                // Use a DataInteraction to select the desired item
                ViewInteraction item = onData(allOf(is(instanceOf(String.class)), is("Dinner")))
                        .inAdapterView(withId(R.id.MealDD))
                        .check(matches(isDisplayed()));
                item.perform(click());
                onView(withId(R.id.addBtn)).perform(click());
                onView(withId(R.id.TodayBtn)).perform(click());
                // Perform checks or actions on the RecyclerView here
                RecyclerView resultRecyclerView = activity.findViewById(R.id.dailyDietRV);
                int resultItemCount = recyclerView.getAdapter().getItemCount();
                assertEquals(initialItemCount,resultItemCount);

        });
    }

}
