package com.example.retrofitone.core.di

import android.content.Context
import com.example.retrofitone.BuildConfig
import com.example.retrofitone.R
import com.example.retrofitone.core.data.CloudResponse
import com.example.retrofitone.core.data.IntCache
import com.example.retrofitone.core.data.IntPermanentStorage
import com.example.retrofitone.core.data.StringCache
import com.example.retrofitone.core.data.StringPermanentStorage
import com.example.retrofitone.load.presentation.LoadScreen
import com.google.gson.Gson

class Core(context: Context) {

    var runUiTest: Boolean = true
    private val gson = Gson()

    val lastScreenSaved: StringCache
    val userInfoSaved: StringCache
    val mockIndex: IntCache

    init {
      //  runUiTest = BuildConfig.DEBUG

        val sharedPreferencesInfo =
            if (runUiTest) "ui_test"
            else context.getString(R.string.app_name)

        val sharedPreferences = context.getSharedPreferences(
            sharedPreferencesInfo,
            Context.MODE_PRIVATE
        )

        val stringPermanentStorage = StringPermanentStorage.Base(sharedPreferences)
        val intPermanentStorage = IntPermanentStorage.Base(sharedPreferences)

        lastScreenSaved = StringCache.Base(
            LAST_SCREEN_SAVED,
            stringPermanentStorage,
            LoadScreen::class.java.canonicalName
        )

        userInfoSaved = StringCache.Base(
            USER_INFO_KEY,
            stringPermanentStorage,
            gson.toJson(CloudResponse(emptyList()))
        )

        mockIndex = IntCache.Base(
            MOCK_INDEX_KEY,
            intPermanentStorage,
            0
        )
    }

    companion object {

        private const val LAST_SCREEN_SAVED = "LAST_SAVED_SCREEN_KEY"
        private const val USER_INFO_KEY = "USER_INFO_KEY"
        private const val MOCK_INDEX_KEY = "MOCK_INDEX_KEY"
    }
}