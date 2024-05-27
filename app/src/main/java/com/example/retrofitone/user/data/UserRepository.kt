package com.example.retrofitone.user.data

import com.example.retrofitone.core.data.StringCache
import com.example.retrofitone.core.data.CacheDataSource
import com.example.retrofitone.user.presentation.UserScreen


interface UserRepository {

    fun userInfo(): UserInfo

    fun saveLastScreenUser()

    class Base(
        private val lastSavedScreen: StringCache,
        private val cacheDataSource: CacheDataSource
    ) : UserRepository {
        override fun userInfo(): UserInfo {
            val cloudResponse = cacheDataSource.read().results[0]
            with(cloudResponse) {
                return UserInfo(
                    gender = gender ?: "",
                    city = location ?.city ?: "",
                )
            }
        }

        override fun saveLastScreenUser() {
            UserScreen::class.java.canonicalName?.let { lastSavedScreen.save(it) }
        }
    }
}