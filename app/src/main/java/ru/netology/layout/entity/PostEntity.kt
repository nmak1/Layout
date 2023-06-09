package ru.netology.layout.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.layout.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likeByMe: Boolean ,
    val likes: Int=0,
    val shares:Int=0 ,
    val views: Int=0,
    val videoUrl: String?=null ,
    val authorAvatar: String?=null
) {
    fun toDto() =
        Post(
            id,author, published, content, likeByMe, likes, shares, views
        )

    companion object {
        fun fromDto(dto: Post) = with(dto) {
            PostEntity(
                dto.id,dto.author,dto.published,dto.content,dto.likeByMe,dto.likes,dto.shares,dto.views
            )
        }
    }
}