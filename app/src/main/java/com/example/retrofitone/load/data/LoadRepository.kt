package com.example.retrofitone.load.data

import com.example.retrofitone.core.data.CacheDataSource
import com.example.retrofitone.core.data.CloudDataSource
import com.example.retrofitone.core.data.CloudResponse
import com.example.retrofitone.core.data.StringCache
import com.example.retrofitone.load.presentation.LoadScreen
import java.lang.IllegalStateException

interface LoadRepository {

   fun load(): LoadResult
   fun lastScreenSaved()

    class Base(
        private val lastSavedScreen: StringCache,
        private val cloudDataSource: CloudDataSource,
        private val cacheDataSource: CacheDataSource
    ) : LoadRepository {
        override fun load(): LoadResult {
            return try {
                val data: CloudResponse = cloudDataSource.data()
                cacheDataSource.save(data)
                LoadResult.Success
            } catch (e: Exception) {
                LoadResult.Error(e.message ?: "LoadRepository Error")
            }
        }

        override fun lastScreenSaved() {
           LoadScreen::class.java.canonicalName?.let { lastSavedScreen.save(it)}
        }
    }
}

interface LoadResult {

    fun isSuccessful(): Boolean

    fun message(): String

    object Success : LoadResult {
        override fun isSuccessful(): Boolean {
            return true
        }

        override fun message(): String {
            throw IllegalStateException()
        }
    }

    data class Error(private val message: String) : LoadResult {
        override fun isSuccessful(): Boolean {
            return false
        }

        override fun message(): String {
            return message
        }
    }
}