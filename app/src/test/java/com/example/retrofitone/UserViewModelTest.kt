package com.example.retrofitone

import com.example.retrofitone.user.data.UserInfo
import com.example.retrofitone.user.data.UserRepository
import com.example.retrofitone.user.presentation.UserUiState
import com.example.retrofitone.user.presentation.UserViewModel
import org.junit.Test
import org.junit.Assert.*

class UserViewModelTest {

    @Test
    fun testCaseNumberOne() {
        val repository = FakeUserRepository()
        val viewModel = UserViewModel(repository = repository)

        // User info state
        var actualUiState: UserUiState = viewModel.init(firstRun = true)
        var expectedUiState: UserUiState = UserUiState.NewInfo(
            gender = "male",
            city = "Portsmouth",
        )

        assertEquals(expectedUiState, actualUiState)
        assertEquals(true, repository.lastScreenSaved)

        // change config or rotate screen
        actualUiState = viewModel.init(firstRun = false)
        expectedUiState = UserUiState.Empty
        assertEquals(expectedUiState, actualUiState)
    }
}

class FakeUserRepository : UserRepository {

    private val userInfoList = listOf(
        UserInfo(
            gender = "male",
            city = "Portsmouth",
        ),
        UserInfo(
            gender = "female",
            city = "Senden"
        )
    )

    private var currentIndex = 0

    var lastScreenSaved: Boolean = false
    override fun userInfo(): UserInfo {
       if (currentIndex == 2) currentIndex = 0
       return userInfoList[currentIndex++]
    }

    override fun saveLastScreenUser() {
        lastScreenSaved = true
    }
}


