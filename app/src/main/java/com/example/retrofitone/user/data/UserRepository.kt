package com.example.retrofitone.user.data

import com.example.retrofitone.core.data.StringCache
import com.example.retrofitone.load.data.CacheDataSource
import com.example.retrofitone.user.presentation.UserScreen


interface UserRepository {

    fun userInfo() : UserInfo

    fun saveLastScreenUser()

   class Base(
        private val lastSavedScreen: StringCache,
        private val cacheDataSource: CacheDataSource
    ) : UserRepository {
       override fun userInfo(): UserInfo {
           TODO("Not yet implemented")
       }

       override fun saveLastScreenUser() {
            UserScreen::class.java.canonicalName?.let { lastSavedScreen.save(it) }
        }


    }

}