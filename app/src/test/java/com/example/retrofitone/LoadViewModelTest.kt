package com.example.retrofitone

import com.example.retrofitone.load.data.LoadRepository
import com.example.retrofitone.load.data.LoadResult
import com.example.retrofitone.load.presentation.LoadUiState
import com.example.retrofitone.load.presentation.LoadViewModel
import com.example.retrofitone.load.presentation.UiObservable
import com.example.retrofitone.main.presentation.RunAsync
import org.junit.Test

import org.junit.Assert.*

/**
 * error after progress
 * retry
 * progress and then success
 */
class LoadViewModelTest {
    @Test

    fun testCase1() {
        val repository = FakeRepository()
        val runAsync = FakeRunAsync()
        val showUi = FakeObservable()
        val viewModel = LoadViewModel(
            repository = repository,
            runAsync = runAsync,
            uiObservable = showUi)

        // first run
        viewModel.init(firstRun = true)

        assertEquals(true, repository.saveLastScreenIsCalled)

        assertEquals(LoadUiState.Progress, showUi.uiStateList[0])
        assertEquals(LoadUiState.Error(message = "failed to fetch data"), showUi.uiStateList[1])


        // retry -
        viewModel.retry()
        assertEquals(LoadUiState.Progress, showUi.uiStateList[2])
        assertEquals(LoadUiState.Success, showUi.uiStateList[3])

        // change configuration or screen rotate
        assertEquals(4, showUi.uiStateList.size)
        viewModel.init(firstRun = false)
        assertEquals(4, showUi.uiStateList.size)
    }
}

class FakeRepository : LoadRepository {

    private var returnSuccess = false
    var saveLastScreenIsCalled = false

    override fun load(): LoadResult {
        return if (returnSuccess)
            LoadResult.Success
        else {
            returnSuccess = true
            LoadResult.Error(message = "failed to fetch data")
        }
    }

    override fun lastScreenSaved() {
        saveLastScreenIsCalled = true
    }
}

class FakeObservable : UiObservable {

    val uiStateList = mutableListOf<LoadUiState>()

    override fun updateObserver(observer: (LoadUiState) -> Unit) {

    }

    override fun clearObserver() {

    }

    override fun updateUiState(uiState: LoadUiState) {
        uiStateList.add(uiState)
    }
}

class FakeRunAsync : RunAsync {

    override fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit) {
        val result = background.invoke()
        ui.invoke(result)
    }
}
