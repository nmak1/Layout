package ru.netology.layout.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.layout.db.AppDb
import ru.netology.layout.dto.Post
import ru.netology.layout.repository.PostRepository
import ru.netology.layout.repository.PostRepositoryImpl


private val emptyPost = Post(

    0,
    "Somebody",
    "",
    "Once upon a time",
    false,
    0,
    0,
    0,
    "0",
    "0"
)
class PostViewModel(application: Application) :  AndroidViewModel(application) {
    private val repository: PostRepository =
        PostRepositoryImpl(AppDb.getInstance(context = application).postDao())
    val edited = MutableLiveData(emptyPost)
    val data = repository.getAll()

    fun save() {
        edited.value?.let {
            repository.save(it)
            edited.value = emptyPost
        }

    }

    fun edit(post: Post) {
        edited.value = post
    }
    fun clearEdit(){
        edited.value= emptyPost
    }

    fun changeContent(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content==text) {
                return
            }
            edited.value = it.copy(content = text)
        }



    }
    fun likeById(id: Long) = repository.likeById(id)
    fun shareById(id: Long) = repository.shareById(id)
    fun viewById(id: Long) = repository.viewById(id)
    fun removeById(id: Long) = repository.removeById(id)




}