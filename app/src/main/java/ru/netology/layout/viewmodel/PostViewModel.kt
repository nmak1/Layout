package ru.netology.layout.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.layout.dto.Post
import ru.netology.layout.model.FeedModel
import ru.netology.layout.repository.PostRepository
import ru.netology.layout.repository.PostRepositoryImpl
import ru.netology.layout.until.SingleLiveEvent
import java.io.IOException
import kotlin.concurrent.thread


private val emptyPost = Post(

     0,
     "",
    "",
     "",
     false,
     0,
    0,
     0,
     "0",
     "0"
)


class PostViewModel(application: Application) : AndroidViewModel(application) {
    // упрощённый вариант
    private val repository: PostRepository = PostRepositoryImpl()
    private val _data = MutableLiveData(FeedModel())
    val data: LiveData<FeedModel>
        get() = _data
    val edited = MutableLiveData(emptyPost)
    private val _postCreated = SingleLiveEvent<Unit>()
    val postCreated: LiveData<Unit>
        get() = _postCreated

    init {
        loadPosts()
    }

    fun loadPosts() {
        thread {
            // Начинаем загрузку
            _data.postValue(FeedModel(loading = true, refreshing = true))
            try {
                // Данные успешно получены
                val posts = repository.getAll()
                FeedModel(posts = posts, empty = posts.isEmpty())
            } catch (e: IOException) {
                // Получена ошибка
                FeedModel(error = true)
            }.also(_data::postValue)
        }
    }

    fun save() {
        edited.value?.let {
            thread {
                repository.save(it)
                _postCreated.postValue(Unit)
            }
        }
        edited.value = emptyPost
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun likeById(id: Long) {
        thread {
            _data.postValue(FeedModel(loading = true))
            val oldPost = _data.value?.posts.orEmpty().find { it.id == id }
            try {
                if (oldPost != null) {
                    if (!oldPost.likeByMe) {
                        repository.likeById(id)
                    } else {
                        repository.unlikeById(id)
                    }
                }
                loadPosts()
                _data.postValue(FeedModel(loading = false))
            } catch (e: Exception) {
                _data.postValue(FeedModel(error = true))
            }
        }
    }

    fun unlikeById(id: Long) {
        thread { repository.unlikeById(id) }
    }

    fun shareById(id: Long) {
        thread { repository.shareById(id) }
    }

    fun viewById(id: Long) {
        thread { repository.viewById(id) }
    }

    fun removeById(id: Long) {
        thread {
            //Оптимистичная модель
            val old = _data.value?.posts.orEmpty()
            _data.postValue(
                _data.value?.copy(posts = _data.value?.posts.orEmpty()
                    .filter { it.id != id }
                )
            )
            try {
                repository.removeById(id)
            } catch (e: IOException) {
                _data.postValue(_data.value?.copy(posts = old))
            }
        }
    }

}






