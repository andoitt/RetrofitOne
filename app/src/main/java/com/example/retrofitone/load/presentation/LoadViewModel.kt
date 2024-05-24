package com.example.retrofitone.load.presentation

import com.example.retrofitone.load.data.LoadRepository
import com.example.retrofitone.main.MyViewModel
import com.example.retrofitone.main.RunAsync

class LoadViewModel(
    private val repository: LoadRepository,
    private val uiObservable: UiObservable,
    private val runAsync: RunAsync,

    ) : MyViewModel.Abstract(runAsync) {

    fun init(firstRun: Boolean) {
        if (firstRun) {
            repository.lastScreenSaved()
            uiObservable.updateUiState(LoadUiState.Progress)
            runAsync.runAsync(repository::load) { loadResult ->
                val uiState = if (loadResult.isSuccessful())
                   LoadUiState.Success
                else
                    LoadUiState.Error(loadResult.message())
                uiObservable.updateUiState(uiState)
            }
        }
    }

    fun retry() = init(true)
}