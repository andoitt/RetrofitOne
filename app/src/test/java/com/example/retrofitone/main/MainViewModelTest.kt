package com.example.retrofitone.main

import com.example.retrofitone.main.data.MainRepository
import org.junit.Assert
import org.junit.Test

class MainViewModelTest {

    @Test
    fun test() {
        val viewModel = MainViewModel(FakeRepository())
        var actual = viewModel.init(true)
        Assert.assertEquals(FakeScreen, actual)

        actual = viewModel.init(false)
        Assert.assertEquals(Screen.Empty, actual)

    }
}

private class FakeRepository : MainRepository {

    override fun lastSavedScreen(): Screen {
        return FakeScreen
    }
}

private object FakeScreen : Screen