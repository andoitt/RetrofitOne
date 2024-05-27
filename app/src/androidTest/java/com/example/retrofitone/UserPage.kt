package com.example.retrofitone

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher

class UserPage(
    private val gender: String ,
    private val city: String ,
) {


    private val rootId: Matcher<View> = withParent(withId(R.id.userLayout))
    private val rootClass: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))

    private val loadNextButton = LoadNextButtonUi(rootId,rootClass)


    private val genderInfo =
        TextUi(id = R.id.genderTextView, rootId = rootId, rootClass = rootClass)

    private val cityInfo =
        TextUi(id = R.id.cityTextView, rootId = rootId, rootClass = rootClass)

    fun checkUserInfoState() {
        genderInfo.checkUserInfoState(text = gender)
        cityInfo.checkUserInfoState(text = city)
    }

    fun clickNextUser() {
        loadNextButton.click()
    }
}
