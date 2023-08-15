package ru.netology.layout.model

import ru.netology.layout.dto.Post
import ru.netology.layout.until.RetryTypes


data class FeedModelState(
    val refreshing: Boolean = false,
//    val messageOfCodeError: String = "",
    val error: Boolean = false,
    val loading: Boolean = false,

    val retryId: Long = 0,
    val retryType: RetryTypes? = null,
    val retryPost: Post? = null
)