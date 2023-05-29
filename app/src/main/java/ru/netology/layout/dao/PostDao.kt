package ru.netology.layout.dao

import ru.netology.layout.dto.Post


interface PostDao {
    fun get(): List<Post>
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
    fun save(post: Post): Post

}