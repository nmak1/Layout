package ru.netology.layout.repository

import androidx.lifecycle.LiveData
import ru.netology.layout.dto.Post

interface PostRepository  {
    fun get(): LiveData<Post>
    fun like()
    fun share()
    fun view()
}