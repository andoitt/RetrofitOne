package com.example.retrofitone.load

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.retrofitone.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not

class ProgressUi(rootId: Matcher<View>, rootClass: Matcher<View>) {

    private val id: Int = R.id.progressBar


    private val interaction = onView(
        Matchers.allOf(
            withId(id),
            ViewMatchers.isAssignableFrom(ProgressBar::class.java),
            (rootId),
            rootClass
        )
    )

    fun checkVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun checkNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }
}
