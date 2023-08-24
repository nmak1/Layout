package ru.netology.layout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import ru.netology.layout.auth.AppAuth
import ru.netology.layout.dto.Token

class AuthViewModel { val data: LiveData<Token?> = AppAuth.getInstance().data
    .asLiveData(Dispatchers.Default)

    val authorized: Boolean
        get() = AppAuth.getInstance().data.value?.token != null
}