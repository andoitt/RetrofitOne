package com.example.retrofitone.load.presentation

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.example.retrofitone.load.data.LoadRepository
import com.example.retrofitone.main.presentation.MyViewModel
import com.example.retrofitone.main.presentation.RunAsync

class LoadViewModel(
    private val uiObservable: UiObservable,
    private val repository: LoadRepository,
    runAsync: RunAsync,
    ) : MyViewModel.Abstract(runAsync) {

    fun init(firstRun: Boolean) {
        if (firstRun) {
            repository.lastScreenSaved()
            uiObservable.updateUiState(LoadUiState.Progress)
            runAsync(repository::load) { loadResult ->
                val uiState = if (loadResult.isSuccessful())
                   LoadUiState.Success
                else
                    LoadUiState.Error(loadResult.message())
                uiObservable.updateUiState(uiState)
            }
        }
    }

    fun retry() = init(true)

    fun startGetUpdates(showUi: (LoadUiState) -> Unit) {
        uiObservable.updateObserver(showUi)
    }

    fun stopGetUpdates() {
        uiObservable.clearObserver()
    }
}

abstract class AbstractViewModel(
    private val runAsync: RunAsync,
) : ViewModel(), RunAsync {

    override fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit) {
        runAsync.runAsync(background, ui)
    }
}

/*
interface RunAsync {

    fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit)

    class Base : RunAsync {
        override fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit) {
            Thread {
                val result: T = background.invoke()
                Handler(Looper.getMainLooper()).post {
                    ui.invoke(result)
                }
            }.start()
        }
    }
}*/
