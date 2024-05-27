package com.example.retrofitone.core.di

import com.example.retrofitone.main.presentation.MyViewModel

class Error : ProvideViewModel {
    override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
        throw IllegalStateException("unknown viewModel $clazz  add it to ProvideViewModel.Make")
    }
}