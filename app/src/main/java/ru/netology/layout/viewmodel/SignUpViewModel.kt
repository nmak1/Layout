package ru.netology.layout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.netology.layout.dto.User
import ru.netology.layout.model.FeedModelState
import ru.netology.layout.repository.AuthRepository

class SignUpViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _data = MutableLiveData<User>()
    val data: LiveData<User>
        get() = _data

    private val _state = MutableLiveData<FeedModelState>()
    val state: LiveData<FeedModelState>
        get() = _state

    fun registrationUser(login: String?, password: String?, name: String?) {
        viewModelScope.launch {
            try {
                val user = repository.registrationUser(login, password, name)
                _data.value = user
            } catch (e: Exception) {
                _state.postValue(FeedModelState(registrationError = true))
            }
        }
    }
}