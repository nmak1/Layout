package ru.netology.layout.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.layout.repository.PostRepository
import ru.netology.layout.repository.PostRepositoryInMemoryImpl

class PostViewModel:ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun viewById(id: Long) = repository.viewById(id)

}