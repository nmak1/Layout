package ru.netology.layout.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.layout.repository.PostRepository
import ru.netology.layout.repository.PostRepositoryInMemoryImpl

class PostViewModel:ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun shere() = repository.share()
    fun view() = repository.view()
}