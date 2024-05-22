package com.example.retrofitone

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers

class UserPage(
    private val gender: String = "",
    private val city: String = "",
) {


    private val rootId: Int = R.id.userLayout
    private val rootClass =
        ViewMatchers.withParent(ViewMatchers.isAssignableFrom(LinearLayout::class.java))

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
