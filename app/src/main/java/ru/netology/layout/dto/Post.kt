package ru.netology.layout.dto



data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val LikeByMe: Boolean = true,
    val likes: Long = 0,
    val sheres:Long = 0,
    val views: Long = 0

)

