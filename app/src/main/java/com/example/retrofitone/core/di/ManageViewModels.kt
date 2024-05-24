package com.example.retrofitone.core.di

import com.example.retrofitone.load.presentation.LoadViewModel
import com.example.retrofitone.main.MyViewModel

interface ManageViewModels : ClearViewModel, ProvideViewModel

interface ClearViewModel {

    fun clear(clazz: Class<out MyViewModel>)
}


