package ru.netology.layout.repository

import androidx.lifecycle.map
import ru.netology.layout.dao.PostDao
import ru.netology.layout.dto.Post

class PostRepositoryImpl(private val dao:PostDao) : PostRepository {



    override fun getAll()= dao.getAll().map { list ->
        list.map { it.toDto() }
    }


    override fun likeById(id: Long) {
           dao.likeById(id)

    }

    override fun shareById(id: Long) {
        dao.shareById(id)

        }

    override fun viewById(id: Long) {
        dao.viewById(id)
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }

    override fun clearEdit(post: Post) {

    }

    override fun save(post: Post) {
        dao.save(post)
    }




}

