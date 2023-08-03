package ru.netology.layout.model

import ru.netology.layout.dto.Post


data class FeedModel(
    val posts: List<Post> = emptyList(),
    val empty: Boolean = false
)