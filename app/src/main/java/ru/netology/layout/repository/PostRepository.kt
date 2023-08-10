package ru.netology.layout.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.netology.layout.dto.Post

interface PostRepository {

    val data: Flow<List<Post>>
    fun getNewerCount(firstId: Long): Flow<Int>
    suspend fun getAll()
    suspend fun likeById(id: Long)
    suspend fun unlikeById(id: Long)
    suspend fun save(post: Post)
    suspend fun removeById(id: Long)
}