package com.example.retrofitone.core.di

import com.example.retrofitone.load.di.ProvideLoadViewModel
import com.example.retrofitone.main.presentation.MyViewModel
import com.example.retrofitone.main.di.ProvideMainViewModel
import com.example.retrofitone.user.di.ProvideUserViewModel

interface ManageViewModels : ClearViewModel, ProvideViewModel

interface ClearViewModel {

    fun clear(clazz: Class<out MyViewModel>)
}

interface ProvideViewModel {
    fun <T : MyViewModel> viewModel(clazz: Class<T>): T

    class Factory(
        private val provideViewModel: ProvideViewModel,
    ) : ManageViewModels {

        private val mutableMap = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()

        override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
            return if (mutableMap[clazz] == null) {
                val viewModel = provideViewModel.viewModel(clazz)
                mutableMap[clazz] = viewModel
                return viewModel
            } else {
                mutableMap[clazz] as T
            }
        }

        override fun clear(clazz: Class<out MyViewModel>) {
            mutableMap[clazz] = null
        }
    }

    class Make(
        core: Core,
    ) : ProvideViewModel {

        private val chain: ProvideViewModel

        init {
            var temp: ProvideViewModel = Error()
            temp = ProvideLoadViewModel(core, temp)
            temp = ProvideUserViewModel(core, temp)
            chain = ProvideMainViewModel(core, temp)
        }

        override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
            return chain.viewModel(clazz)
        }
    }
}
