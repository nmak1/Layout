package ru.netology.layout.dto

data class Attachment(
    val url: String,
    val description: String,
    val type: TypeAttachment
)

enum class TypeAttachment {
    IMAGE
}
