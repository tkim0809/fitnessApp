package com.example.fitnessapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4ClassRunner.class)
public class ProfileTest {
    private static final int SIMULATED_DELAY_MS = 500;
    @Rule
    public ActivityScenarioRule<profilePage> activityScenario =
            new ActivityScenarioRule<>(profilePage.class);
    @Test
    public void editProfileTest(){
        String username = "eddylty";
        String gender = "M";
        String weight = "144lb";
        String age = "28";
        String email = "lty@gmail.com";
        onView(withId(R.id.editPBtn)).perform(click());
        onView(withId(R.id.edUNTxt)).perform(typeText(username),closeSoftKeyboard());
        onView(withId(R.id.edGenderTxt)).perform(typeText(gender),closeSoftKeyboard());
        onView(withId(R.id.edWeightTxt)).perform(typeText(weight),closeSoftKeyboard());
        onView(withId(R.id.edAgeTxt)).perform(typeText(age),closeSoftKeyboard());
        onView(withId(R.id.edEmailTxt)).perform(typeText(email),closeSoftKeyboard());
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }
        onView(withId(R.id.savePFBtn)).perform(click());
        onView(withId(R.id.userNameTxt)).check(matches(withText(username)));
        onView(withId(R.id.genderTxt)).check(matches(withText(gender)));
        onView(withId(R.id.ageTxt)).check(matches(withText(age)));
        onView(withId(R.id.weightTxt)).check(matches(withText(weight)));
        onView(withId(R.id.emailTxt)).check(matches(withText(email)));
    }
}
