package com.walterfabianberchialla.trabajointegrador.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gustavosds.trabajointegrador.R
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {
    @get:Rule
    var rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun compareInitialValues() {
        Espresso.onView(
            ViewMatchers.withId(R.id.button_compare)
        ).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.text1))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText("Texto 1")
                )
            )
        Espresso.onView(
            ViewMatchers.withId(R.id.text2)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Texto 2")
            )
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.result)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    "Son diferentes"
                )
            )
        )
    }

    @Test
    fun checkText1andText2isEquals() {
        Espresso.onView(
            ViewMatchers.withId(R.id.button_compare)
        ).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.text1)).perform(ViewActions.clearText())
        Espresso.onView(ViewMatchers.withId(R.id.text2)).perform(ViewActions.clearText())

        Espresso.onView(
            ViewMatchers.withId(R.id.text1)
        ).perform(ViewActions.typeText("Hola mundo!"))
        Espresso.onView(
            ViewMatchers.withId(R.id.text2)
        ).perform(ViewActions.typeText("Hola mundo!"))

        // Check result text
        Espresso.onView(ViewMatchers.withId(R.id.result))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        "Son diferentes"
                    )
                )
            )
    }

    @Test
    fun checkText1isEmpty() {
        // Clear texts
        Espresso.onView(
            ViewMatchers.withId(R.id.text1)
        ).perform(ViewActions.clearText())
        Espresso.onView(
            ViewMatchers.withId(R.id.text2)
        ).perform(ViewActions.clearText())

        // Click button
        Espresso.onView(
            ViewMatchers.withId(R.id.button_compare)
        ).perform(ViewActions.click())

        // Check result text
        Espresso.onView(
            ViewMatchers.withId(R.id.result)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    "No pueden ser vacios"
                )
            )
        )

    }
}