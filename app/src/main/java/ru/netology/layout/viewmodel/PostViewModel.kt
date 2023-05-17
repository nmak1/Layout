package ru.netology.layout.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.layout.dto.Post
import ru.netology.layout.repository.PostRepository
import ru.netology.layout.repository.PostRepositoryFileImpl

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likeByMe = false,
    published = ""
)
class PostViewModel(application: Application) :  AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryFileImpl(application)
    val edited = MutableLiveData(empty)
    val data = repository.getAll()

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }
    fun clearEdit(){
        edited.value= empty
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

    fun  video ()= repository.video()


}