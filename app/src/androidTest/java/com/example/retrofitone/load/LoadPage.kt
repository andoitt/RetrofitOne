package com.example.retrofitone.load

import android.widget.LinearLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.retrofitone.R
import com.example.retrofitone.Wait

class LoadPage {

    private val rootId  = withParent(withId(R.id.loadLayout))

    private val rootClass = withParent(isAssignableFrom(LinearLayout::class.java))

    private val progressUi = ProgressUi(rootId,rootClass)
    private val errorUi = ErrorUi(rootId,rootClass)
    private val retryUi = RetryUi(rootId,rootClass)

    fun checkProgressState() {
        progressUi.checkVisible()
        errorUi.checkNotVisible()
        retryUi.checkNotVisible()

    }

    fun waitUntilError() {
      errorUi.waitUntilVisible()
    }

    fun checkErrorState(message: String) {
        progressUi.checkNotVisible()
        errorUi.checkVisible(message = message)
        retryUi.checkVisible()
    }

    fun clickRetry() {
        retryUi.click()
    }

    fun waitUntilDisappear() {
        Espresso.onView(ViewMatchers.isRoot()).perform(Wait(1100))
    }
}
