package com.example.retrofitone.main.data

import com.example.retrofitone.core.data.StringCache
import com.example.retrofitone.main.presentation.Screen

interface MainRepository {

    fun lastSavedScreen(): Screen

    class Base(
        private val lastSavedScreen: StringCache
    ) : MainRepository {

        override fun lastSavedScreen(): Screen {
            return Class.forName(lastSavedScreen.read()).getDeclaredConstructor().newInstance() as Screen
        }
    }
}