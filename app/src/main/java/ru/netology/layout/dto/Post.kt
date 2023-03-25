package ru.netology.layout.dto



data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var LikeByMt: Boolean = true,
    var likes: Long = 0,
    var sheres:Long = 0,
    var views: Long = 0

)

