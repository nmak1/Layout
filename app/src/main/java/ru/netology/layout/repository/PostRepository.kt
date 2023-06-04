package ru.netology.layout.repository

import androidx.lifecycle.LiveData
import ru.netology.layout.dto.Post
import ru.netology.layout.entity.PostEntity

interface PostRepository {

    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun viewById(id: Long)
    fun removeById(id: Long)
    fun clearEdit(post: Post)
    fun save(post: Post)


}