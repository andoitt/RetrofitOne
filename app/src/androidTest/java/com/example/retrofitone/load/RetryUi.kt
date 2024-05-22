package com.example.retrofitone.load

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.retrofitone.R
import com.example.retrofitone.WaitAction
import org.hamcrest.Matcher
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not

class RetryUi(rootId: Matcher<View>?, rootClass: Matcher<View>?) {


    private val id: Int = R.id.retryButton


    private val interaction = onView(
        allOf(
            withId(id),
            isAssignableFrom(TextView::class.java),
            (rootId),
            rootClass
        )
    )

    fun checkNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun checkVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }

}
