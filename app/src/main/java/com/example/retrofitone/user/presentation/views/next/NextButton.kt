package com.example.retrofitone.user.presentation.views.next

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class NextButton : MaterialButton, UpdateNextButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    )

    override fun onSaveInstanceState(): Parcelable {
        super.onSaveInstanceState().let {
            val state = NextButtonSavedState(it)
            state.save(this)
            return state
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as NextButtonSavedState
        super.onRestoreInstanceState(restoredState.superState)
        restoredState.restore(this)
    }

    override fun changeEnabled(enabled: Boolean) {
        isEnabled = enabled
    }
}

interface UpdateNextButton {

    fun changeEnabled(enabled: Boolean)

}