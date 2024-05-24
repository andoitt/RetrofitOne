package com.example.retrofitone.load.data

import com.example.retrofitone.core.data.StringCache
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
            TODO("Not yet implemented")
        }

        override fun lastScreenSaved() {
            LoadResult::class.java.canonicalName?.let { lastSavedScreen.save(it)}
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