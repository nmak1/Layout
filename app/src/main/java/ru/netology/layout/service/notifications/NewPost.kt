package ru.netology.layout.service.notifications

data class NewPost(
    val postId: Long,
    val postAuthor: String,
    val postContent: String
)