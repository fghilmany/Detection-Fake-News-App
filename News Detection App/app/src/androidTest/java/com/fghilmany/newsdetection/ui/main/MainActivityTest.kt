package com.fghilmany.newsdetection.ui.main

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.fghilmany.newsdetection.R
import com.fghilmany.newsdetection.helper.EspressoIdlingResource
import com.google.android.material.textfield.TextInputEditText
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    private val dummyNews = "Test"

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun testSplash(){
        onView(withId(R.id.lottieAnimationView)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
    }
    @Test
    fun testDashboard(){
        onView(isRoot()).perform(waitFor())
        onView(withId(R.id.tv_header)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.edt_news)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_confirm)).perform(click())
        onView(withId(R.id.edt_news)).check(matches(hasErrorText("Must be Filled")))
        onView((isAssignableFrom(TextInputEditText::class.java))).perform(typeText(dummyNews), closeSoftKeyboard())
        onView(withId(R.id.btn_confirm)).perform(click())
        // if want to test loading view, you must setiing EsspressoIdlingResource
        // but this effect is testResult is sure to fail
//        onView(withId(R.id.loading_view)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_fact_indication)).check(matches(isDisplayed()))
    }

    @Test
    fun testResult(){
        onView(isRoot()).perform(waitFor())
        onView((isAssignableFrom(TextInputEditText::class.java))).perform(typeText(dummyNews), closeSoftKeyboard())
        onView(withId(R.id.btn_confirm)).perform(click())
        onView(withId(R.id.fake_view_animation)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_fake)).check(matches(isDisplayed()))
        onView(withId(R.id.title_indication)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_fact_indication)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_fake_indication)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.tv_header)).check(matches(isDisplayed()))
    }

    private fun waitFor(delay: Long = 2000): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }

}