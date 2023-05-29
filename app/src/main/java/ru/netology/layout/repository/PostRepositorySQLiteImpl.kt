package ru.netology.layout.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.layout.dao.PostDao
import ru.netology.layout.dto.Post

class PostRepositorySQLiteImpl(private val dao:PostDao) : PostRepository {

    //
//    private val gson = Gson()
//    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
//    private val filename = "posts.json"
    private var posts = emptyList<Post>()
        set(value) {
            field = value
            data.value = value

        }
    private val data = MutableLiveData(posts)

    init {
        posts = dao.get()
        data.value = posts
    }


    override fun getAll(): LiveData<List<Post>> = data


    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id == id) it.copy(
                likes = if (it.likeByMe) it.likes - 1 else it.likes + 1,
                likeByMe = !it.likeByMe
            )
            else it

        }

    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id == id) it.copy(shares = it.shares + 1)
            else it

        }


    }

    override fun viewById(id: Long) {
        posts = posts.map {
            if (it.id == id) it.copy(views = it.views + 199)
            else it

        }


    }

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        posts = if (id == 0L) {
            listOf(saved) + posts
        } else {
            posts.map {
                if (it.id != id) it else saved

            }
}

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }

    }

    override fun video() {

    }

    override fun removeById(id: Long) {
        posts=posts.filter {
            it.id!=id
        }

    }

    override fun clearEdit(post: Post) {
       
    }


}