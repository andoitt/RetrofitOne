package com.example.retrofitone.core.data

import android.content.SharedPreferences

interface IntCache {

    fun save(value: Int)

    fun read(): Int

    class Base(
        private val key: String,
        private val permanentStorage: IntPermanentStorage,
        private val defValue: Int,
    ) : IntCache {
        override fun save(value: Int) {
            permanentStorage.save(value, key)
        }

        override fun read(): Int {
            return permanentStorage.read(key, defValue)
        }
    }
}

interface IntPermanentStorage {

    fun save(value: Int, key: String)

    fun read(key: String, defValue: Int): Int

    class Base(
        private val sharedPreferences: SharedPreferences
    ) : IntPermanentStorage {
        override fun save(value: Int, key: String) {
            sharedPreferences.edit().putInt(key, value).apply()
        }

        override fun read(key: String, defValue: Int): Int {
            return sharedPreferences.getInt(key, defValue)
        }
    }
}