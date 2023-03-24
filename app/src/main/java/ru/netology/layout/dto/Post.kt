package ru.netology.layout.dto



data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var LikeByMt: Boolean = true,
    var likes: Int = 0,
    var sheres: Int = 0,
    var views: Int = 0

)

