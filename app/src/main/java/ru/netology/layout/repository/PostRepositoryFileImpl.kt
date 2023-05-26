package ru.netology.layout.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.layout.dto.Post

class PostRepositoryFileImpl(private val context: Context) : PostRepository {

//
private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filename = "posts.json"
    private var posts = emptyList<Post>()
        set(value) {
            field = value
            data.value = value
            sync()
        }
    private val data = MutableLiveData(posts)
    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                data.value = posts
            }
        } else {
            sync()
        }
    }

    private fun sync() {
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }
    override fun getAll(): LiveData<List<Post>> = data


    override fun likeById(id:Long) {
        posts = posts.map {
            if (it.id==id) it.copy(
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
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id =(posts.firstOrNull()?.id ?:0L)+1,
                    author = "Me",
                    published = "now",
                    likeByMe = false

                )
            ) + posts

            return
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