package ru.netology.layout.dto



data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likeByMe: Boolean = false,
    val likes: Long = 0,
    val shares:Long = 0,
    val views: Long = 0

)

