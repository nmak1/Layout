package ru.netology.layout.repository

import androidx.lifecycle.LiveData
import ru.netology.layout.dto.Post

interface PostRepository {

    val data: LiveData<List<Post>>
    suspend fun getAll()
    suspend fun likeById(id: Long)
    suspend fun unlikeById(id: Long)
    suspend fun save(post: Post)
    suspend fun removeById(id: Long)
}