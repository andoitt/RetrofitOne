package com.example.retrofitone.main.data

import com.example.retrofitone.core.data.StringCache
import com.example.retrofitone.main.Screen

interface MainRepository {

    fun lastSavedScreen(): Screen

    class Base(
        private val lastScreen: StringCache
    ) : MainRepository {
        override fun lastSavedScreen(): Screen {
            val string = lastScreen.read()
            return Class.forName(string).getDeclaredConstructor().newInstance() as Screen
        }
    }
}