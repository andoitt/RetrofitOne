package com.example.retrofitone.load.di

import com.example.retrofitone.core.data.CacheDataSource
import com.example.retrofitone.core.data.CloudDataSource
import com.example.retrofitone.core.data.MockService
import com.example.retrofitone.core.data.UserInfoService
import com.example.retrofitone.core.di.Core
import com.example.retrofitone.core.di.Module
import com.example.retrofitone.core.di.ProvideAbstract
import com.example.retrofitone.core.di.ProvideViewModel
import com.example.retrofitone.load.data.LoadRepository
import com.example.retrofitone.load.presentation.LoadViewModel
import com.example.retrofitone.load.presentation.UiObservable
import com.example.retrofitone.main.presentation.MyViewModel
import com.example.retrofitone.main.presentation.RunAsync
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoadModule(private val core: Core) : Module<LoadViewModel> {
    override fun viewModel(): LoadViewModel {

        val cacheDataSource = CacheDataSource.Base(core.userInfoSaved, Gson())

        val cloudDataSource = CloudDataSource.Base(
            if (core.runUiTest)
            MockService(core.mockIndex)
            else
                Retrofit.Builder().baseUrl("https://randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .addInterceptor(HttpLoggingInterceptor().apply {
                                setLevel(HttpLoggingInterceptor.Level.BODY)
                            })
                            .build()
                    )
                    .build()
                    .create(UserInfoService::class.java)
        )

        return LoadViewModel(
            UiObservable.Base(),
            LoadRepository.Base(core.lastScreenSaved, cloudDataSource, cacheDataSource),
            RunAsync.Base(),
        )
    }
}

class ProvideLoadViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, LoadViewModel::class.java){
    override fun module(): Module<out MyViewModel> {
      return LoadModule(core)
    }


}