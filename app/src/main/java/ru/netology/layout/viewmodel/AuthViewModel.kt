package ru.netology.layout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ru.netology.layout.auth.AppAuth
import ru.netology.layout.auth.AuthState


class AuthViewModel : ViewModel() {
    val data: LiveData<AuthState> = AppAuth.getInstance()
    .authStateFlow
    .asLiveData(Dispatchers.Default)

    val authorized: Boolean
        get() = AppAuth.getInstance().authStateFlow.value.id != 0L
}