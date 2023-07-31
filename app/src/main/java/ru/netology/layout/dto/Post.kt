package ru.netology.layout.dto


data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likeByMe: Boolean ,
    val likes: Int =0 ,
    val shares: Int =0 ,
    val views: Int =0 ,
    val videoUrl: String?= null ,
    val authorAvatar: String?=null,
    val attachment: Attachment? = null,
)


