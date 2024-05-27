package com.example.retrofitone.user.presentation.views.Info

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView

class CustomTextView : MaterialTextView, UpdateText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    )

    override fun updateNewText(text: String) {
        setText(text)
    }

    override fun getFreezesText(): Boolean {
        return true
    }
}

interface UpdateText {

    fun updateNewText(text: String)
}