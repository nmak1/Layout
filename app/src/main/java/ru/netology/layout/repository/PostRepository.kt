package ru.netology.layout.repository

import androidx.lifecycle.LiveData
import ru.netology.layout.dto.Post

interface PostRepository  {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id : Long)
    fun shareById(id : Long)
    fun viewById(id : Long)

}