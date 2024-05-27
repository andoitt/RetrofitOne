package com.example.retrofitone

import android.view.View
import android.widget.Button

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.CoreMatchers.allOf

class LoadNextButtonUi(rootId: Matcher<View>, rootClass: Matcher<View>?) {

    private val id: Int = R.id.loadNextButton


    private val interaction = onView(
        allOf(
            withId(R.id.loadNextButton),
            withText(R.string.nextButton),
            isAssignableFrom(Button::class.java),
            rootId ,
            rootClass,
        )
    )

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }


}
