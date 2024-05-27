package com.example.retrofitone.core.di

import com.example.retrofitone.main.presentation.MyViewModel

interface Module <T : MyViewModel> {

    fun viewModel(): T
}