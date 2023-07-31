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
    ""
)


class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository =
        PostRepositoryImpl(AppDb.getInstance(application).postDao())
    val data: LiveData<FeedModel> = repository.data.map { FeedModel(it, it.isEmpty()) }
    private val edited = MutableLiveData(empty)

    private val _state = MutableLiveData(FeedModelState())
    val state: LiveData<FeedModelState>
        get() = _state

    private val _postCreated = SingleLiveEvent<Unit>()
    val postCreated: LiveData<Unit>
        get() = _postCreated
    private val scope = MainScope()

    init {
        loadPosts()
    }

    fun loadPosts() = viewModelScope.launch {
        try {
            _state.value = FeedModelState(loading = true)
            repository.getAll()
            _state.value = FeedModelState()
        } catch (e: Exception) {
            _state.value = FeedModelState(error = true)
        }
    }


    fun save() {
        edited.value?.let {
            viewModelScope.launch {
                try {
                    _postCreated.value = Unit
                    repository.save(it)
                    _state.value = FeedModelState()
                } catch (e: Exception) {
                    _state.value = FeedModelState(error = true)
                }
            }
        }
        edited.value = empty
    }


    fun retrySave(post: Post?) {
        viewModelScope.launch {
            try {
                if (post != null) {
                    PostsApi.service.save(post)
                    loadPosts()
                }
            } catch (e: Exception) {
                _state.value =
                    FeedModelState(error = true, retryType = RetryTypes.SAVE, retryPost = post)
            }
        }
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

    fun likeById(id: Long) = viewModelScope.launch {
        try {
            repository.likeById(id)
        } catch (e: Exception) {
            _state.value = FeedModelState(error = true)
        }
    }

    fun unlikeById(id: Long) = viewModelScope.launch {
        try {
            repository.unlikeById(id)
        } catch (e: Exception) {
            _state.value = FeedModelState(error = true, retryId = id)
        }
    }


    fun removeById(id: Long) = viewModelScope.launch {
        try {
            repository.removeById(id)
        } catch (e: Exception) {
            _state.value = FeedModelState(error = true, retryType = RetryTypes.REMOVE, retryId = id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}








