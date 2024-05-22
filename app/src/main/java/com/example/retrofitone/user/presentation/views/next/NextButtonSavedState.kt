package com.example.retrofitone.user.presentation.views.next

import android.os.Parcel
import android.os.Parcelable
import android.view.View

class NextButtonSavedState : View.BaseSavedState {

    private var enabled: Boolean = false
    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        enabled = parcelIn.readInt() == 1
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeInt(if (enabled) 1 else 0)
    }

    fun save(view: View) {
        enabled = view.isEnabled
    }

    fun restore(view: View) {
        view.isEnabled = enabled
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<NextButtonSavedState> {
        override fun createFromParcel(parcel: Parcel): NextButtonSavedState =
            NextButtonSavedState(parcel)

        override fun newArray(size: Int): Array<NextButtonSavedState?> =
            arrayOfNulls(size)
    }
}