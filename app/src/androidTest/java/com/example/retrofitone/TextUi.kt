package com.example.retrofitone

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher


class TextUi(id: Any, rootId: Int, rootClass: Matcher<View>) {

    private val id: Int = R.id.userInfo

    private val interaction = Espresso.onView(
        CoreMatchers.allOf(
            ViewMatchers.withId(id),
            ViewMatchers.isAssignableFrom(TextView::class.java),
            ViewMatchers.withParent(rootId),
            rootClass

        )
    )

    fun checkUserInfoState(text: String) {
        interaction.check(ViewAssertions.matches(ViewMatchers.withText(text)))
    }

}
