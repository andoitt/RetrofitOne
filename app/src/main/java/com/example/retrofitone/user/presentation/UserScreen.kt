package com.example.retrofitone.user.presentation

import androidx.fragment.app.Fragment
import com.example.retrofitone.main.presentation.Screen

object UserScreen : Screen.Replace() {
    override fun fragment(): Fragment {
        return UserFragment()
    }
}