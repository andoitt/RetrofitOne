package com.example.retrofitone.user.presentation

import com.example.retrofitone.main.MyViewModel
import com.example.retrofitone.user.data.UserRepository

class UserViewModel(
    private var repository: UserRepository
) : MyViewModel {

    fun init(firstRun: Boolean): UserUiState {
        return if (firstRun) {
            repository.saveLastScreenUser()
            val userData = repository.userInfo()
            UserUiState.NewInfo(
                gender = userData.gender,
                city = userData.city,
            )
        } else {
            UserUiState.Empty
        }
    }
}


