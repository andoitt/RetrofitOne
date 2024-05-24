package com.example.retrofitone.core.di

import com.example.retrofitone.main.MyViewModel

interface Module <T : MyViewModel> {

    fun viewModel(): T
}