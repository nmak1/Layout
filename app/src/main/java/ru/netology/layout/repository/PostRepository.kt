package ru.netology.layout.repository

import ru.netology.layout.dto.Post

interface PostRepository {

    fun getAllAsync(callback: Callback<List<Post>>)
    fun likeByIdAsync(id: Long, callback: Callback<Post>)
    fun shareById(id: Long)
    fun viewById(id: Long)
    fun clearEdit(post: Post)
    fun saveAsync(post: Post, callback: Callback<Post>)
    fun removeByIdAsync(id: Long, callback: Callback<Post>)
    fun unlikeByIdAsync(id: Long, callback: Callback<Post>)

    interface Callback<T> {
        fun onSuccess(posts: T)
        fun onError(e: Exception)
    }
}