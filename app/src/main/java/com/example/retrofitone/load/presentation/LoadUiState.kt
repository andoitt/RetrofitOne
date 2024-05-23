package com.example.retrofitone.load.presentation

import com.example.retrofitone.load.presentation.views.error.ErrorUiState
import com.example.retrofitone.load.presentation.views.error.UpdateError
import com.example.retrofitone.load.presentation.views.progress.ProgressUiState
import com.example.retrofitone.load.presentation.views.progress.UpdateProgress
import com.example.retrofitone.load.presentation.views.retry.RetryUiState
import com.example.retrofitone.load.presentation.views.retry.UpdateRetry


interface LoadUiState {

    fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) = Unit

    fun navigate(navigate: () -> Unit ) = Unit

    data class Error(private val message: String) : LoadUiState {
        override fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) {
            progress.updateUiState(ProgressUiState.Hide)
            error.updateUiState(ErrorUiState.Show(message))
            retry.updateUiState(RetryUiState.Show)
        }
    }

    object Progress : LoadUiState {
        override fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) {
            progress.updateUiState(ProgressUiState.Show)
            error.updateUiState(ErrorUiState.Hide)
            retry.updateUiState(RetryUiState.Hide)
        }
    }

    object Success : LoadUiState {
        override fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) = Unit

    }
}