package com.example.retrofitone.load.presentation

import androidx.fragment.app.Fragment
import com.example.retrofitone.main.Screen

object LoadScreen : Screen.Replace() {
    override fun fragment(): Fragment {
        return LoadFragment()
    }
}