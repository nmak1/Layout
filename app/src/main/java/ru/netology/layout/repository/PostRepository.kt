package ru.netology.layout.repository

import ru.netology.layout.dto.Post

interface PostRepository {

    fun getAll(): List<Post>
    fun likeById(id: Long) : Post
    fun shareById(id: Long)
    fun viewById(id: Long)
    fun removeById(id: Long)
    fun clearEdit(post: Post)
    fun save(post: Post)
    fun unlikeById(id: Long) : Post
}