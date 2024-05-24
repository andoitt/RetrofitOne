package com.example.retrofitone.core.di

import com.example.retrofitone.main.MyViewModel

class Error : ProvideViewModel {
    override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
        throw IllegalStateException("unknown viewModel $clazz go and add it to ProvideViewModel.Make")
    }
}