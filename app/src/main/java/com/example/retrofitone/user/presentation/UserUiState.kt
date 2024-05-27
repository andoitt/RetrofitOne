package com.example.retrofitone.user.presentation
import android.widget.TextView
import com.example.retrofitone.user.presentation.views.Info.UpdateText
import java.io.Serializable

interface UserUiState : Serializable {

    fun update(
        genderView: UpdateText,
        cityView: UpdateText
   ) = Unit


    data class NewInfo(
        val gender: String,
        val city : String
    ) : UserUiState {

        override fun update(
            genderView: UpdateText,
            cityView: UpdateText
        ) {
            genderView.updateNewText(gender)
            cityView.updateNewText(city)
        }
    }

    object Empty : UserUiState

}