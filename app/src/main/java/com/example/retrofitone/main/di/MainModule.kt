package com.example.retrofitone.main.di

import com.example.retrofitone.core.di.Core
import com.example.retrofitone.core.di.Module
import com.example.retrofitone.core.di.ProvideAbstract
import com.example.retrofitone.core.di.ProvideViewModel
import com.example.retrofitone.main.presentation.MainViewModel
import com.example.retrofitone.main.presentation.MyViewModel
import com.example.retrofitone.main.data.MainRepository

class MainModule(
    private val core: Core
) : Module<MainViewModel> {
    override fun viewModel(): MainViewModel {
        return MainViewModel(MainRepository.Base(core.lastScreenSaved))
    }
}

class ProvideMainViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, MainViewModel::class.java) {


    override fun module(): Module<out MyViewModel> {
        return MainModule(core)
    }
}
