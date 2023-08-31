package ru.netology.layout.service.notifications

data class Push(
    val content: String,
    val recipientId: Long?,
)