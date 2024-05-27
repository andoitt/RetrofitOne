package com.example.retrofitone

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class TextUi(id: Int, rootId: Matcher<View>, rootClass: Matcher<View>) {

    private val interaction = onView(
        allOf(
            withId(id),
            isAssignableFrom(TextView::class.java),
            rootId,
            rootClass,
        )
    )

    fun checkUserInfoState(text: String) {
        interaction.check(matches(withText(text)))
    }
}
