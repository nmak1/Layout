package ru.netology.layout.model

import ru.netology.layout.dto.Post
import ru.netology.layout.until.RetryTypes


data class FeedModelState(
    val refreshing: Boolean = false,
    val error: Boolean = false,
    val loading: Boolean = false,
    val loginError: Boolean = false,
    val registrationError: Boolean = false,
    val retryId: Long = 0,
    val retryType: RetryTypes? = null,
    val retryPost: Post? = null
)