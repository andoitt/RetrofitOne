package com.example.retrofitone.user.di

import com.example.retrofitone.core.data.CacheDataSource
import com.example.retrofitone.core.di.Core
import com.example.retrofitone.core.di.Module
import com.example.retrofitone.core.di.ProvideAbstract
import com.example.retrofitone.core.di.ProvideViewModel
import com.example.retrofitone.main.presentation.MyViewModel
import com.example.retrofitone.user.data.UserRepository
import com.example.retrofitone.user.presentation.UserViewModel
import com.google.gson.Gson

class UserModule(
    private val core: Core
) : Module<UserViewModel> {
    override fun viewModel(): UserViewModel {
        val cacheDataSource = CacheDataSource.Base(core.userInfoSaved, Gson())
        return UserViewModel(UserRepository.Base(core.lastScreenSaved, cacheDataSource))
    }
}

class ProvideUserViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core,provideOther, UserViewModel::class.java) {
    override fun module(): Module<out MyViewModel> {
        return UserModule(core)
    }

}