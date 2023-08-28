package ru.netology.layout.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import ru.netology.layout.api.PostsApi
import ru.netology.layout.auth.AppAuth
import ru.netology.layout.db.AppDb
import ru.netology.layout.dto.MediaUpload
import ru.netology.layout.dto.Post
import ru.netology.layout.model.FeedModel
import ru.netology.layout.model.FeedModelState
import ru.netology.layout.model.PhotoModel
import ru.netology.layout.repository.PostRepository
import ru.netology.layout.repository.PostRepositoryImpl
import ru.netology.layout.until.RetryTypes
import ru.netology.layout.until.SingleLiveEvent
import java.io.File


private val emptyPost = Post(

    0,
    "",
    0,
    "",
    "",
    false,
    0,
    0,
    false,
    "0",
    "",
    attachment = null
)

@ExperimentalCoroutinesApi
class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository =
        PostRepositoryImpl(AppDb.getInstance(application).postDao())
    val data: LiveData<FeedModel> = AppAuth.getInstance()
        .authStateFlow
        .flatMapLatest { (myId, _) ->
            repository.data
                .map { posts ->
                    FeedModel(
                        posts.map { it.copy(ownedByMe = it.authorId == myId) },
                        posts.isEmpty()
                    )
                }
        }.asLiveData(Dispatchers.Default)
    private val edited = MutableLiveData(emptyPost)

    private val _state = MutableLiveData<FeedModelState>()
    val state: LiveData<FeedModelState>
        get() = _state

    val newerCount: LiveData<Int> = data.switchMap {
        repository.getNewerCount(it.posts.firstOrNull()?.id ?: 0L)
            .asLiveData(Dispatchers.Default)
    }

    private val _postCreated = SingleLiveEvent<Unit>()
    val postCreated: LiveData<Unit>
        get() = _postCreated
    private val scope = MainScope()

    private val withoutPhoto = PhotoModel()
    private val _photo = MutableLiveData(withoutPhoto)
    val photo: LiveData<PhotoModel>
        get() = _photo

    init {
        loadPosts()
    }

    fun loadPosts() = viewModelScope.launch {
        try {
            _state.value = FeedModelState(loading = true)
            repository.getNewPosts()
            _state.value = FeedModelState()
        } catch (e: Exception) {
            _state.value = FeedModelState(error = true)
        }
    }


    fun save() {
        edited.value?.let {
            viewModelScope.launch {
                try {
                    when (_photo.value) {
                        withoutPhoto -> repository.save(it)
                        else -> _photo.value?.file?.let { file ->
                            repository.saveWithAttachment(it, MediaUpload(file))
                        }
                    }
                    _state.value = FeedModelState()
                } catch (e: Exception) {
                    _state.value = FeedModelState(error = true)
                }
                _postCreated.value = Unit
            }
        }
        edited.value = emptyPost
        _photo.value = withoutPhoto
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
    fun changePhoto(uri: Uri?, file: File?) {
        _photo.value = if (uri != null && file != null) {
            PhotoModel(uri, file)
        } else {
            null
        }
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

    fun loadNewPosts() = viewModelScope.launch {
        try {
            _state.value = FeedModelState(loading = true)
            repository.getNewPosts()
            _state.value = FeedModelState()
        } catch (e: Exception) {
            _state.value = FeedModelState(error = true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}








