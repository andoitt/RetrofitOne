package com.example.retrofitone.core

import android.app.Application
import com.example.retrofitone.core.di.Core
import com.example.retrofitone.core.di.ManageViewModels
import com.example.retrofitone.core.di.ProvideViewModel
import com.example.retrofitone.main.presentation.MyViewModel

class App : Application(), ManageViewModels {

    private lateinit var factory: ManageViewModels
    private lateinit var core: Core


    override fun onCreate() {
        super.onCreate()
        core = Core(this)
        factory = ProvideViewModel.Factory(
            ProvideViewModel.Make(core)
        )
    }

    override fun clear(clazz: Class<out MyViewModel>) {
        factory.clear(clazz)
    }

    override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
        return factory.viewModel(clazz)
    }

}