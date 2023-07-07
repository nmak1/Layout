package ru.netology.layout.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import ru.netology.layout.dto.Post
import java.io.IOException
import java.util.concurrent.TimeUnit

class PostRepositoryImpl : PostRepository {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
    private val gson = Gson()
    private val typeToken = object : TypeToken<List<Post>>() {}

    companion object {
        private const val BASE_URL = "http://10.0.2.2:9999"
        private val jsonType = "application/json".toMediaType()
    }

    override fun getAllAsync(callback: PostRepository.Callback<List<Post>>) {
        val request: Request = Request.Builder()
            .url("${BASE_URL}/api/slow/posts")
            .build()
        client.newCall(request)
            .enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    try {
                        val body = response.body?.string() ?: throw RuntimeException("Null body")
                        callback.onSuccess(gson.fromJson(body, typeToken.type))
                    } catch (e: Exception) {
                        callback.onError(e)
                    }
                }
                override fun onFailure(call: Call, e: IOException) {
                    callback.onError(e)
                }
            })
    }




    override fun likeByIdAsync(id: Long, callback: PostRepository.Callback<Post>) {
        val request: Request = Request.Builder()
            .post("".toRequestBody(jsonType))
            .url("${BASE_URL}/api/posts/$id/likes")
            .build()

        return client.newCall(request)
            .enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    callback.onSuccess(gson.fromJson(response.body?.string(), Post::class.java))
                }

                override fun onFailure(call: Call, e: IOException) {
                    callback.onError(e)
                }
            })
    }

    override fun unlikeByIdAsync(id: Long, callback: PostRepository.Callback<Post>) {
        val request: Request = Request.Builder()
            .delete()
            .url("${BASE_URL}/api/posts/$id/likes")
            .build()

        return client.newCall(request)
            .enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    callback.onSuccess(gson.fromJson(response.body?.string(), Post::class.java))
                }

                override fun onFailure(call: Call, e: IOException) {
                    callback.onError(e)
                }
            })
    }

    override fun shareById(id: Long) {
//        dao.shareById(id)

    }

    override fun viewById(id: Long) {
//        dao.viewById(id)
    }

    override fun removeByIdAsync(id: Long, callback: PostRepository.Callback<Post>) {
        val request: Request = Request.Builder()
            .delete()
            .url("${BASE_URL}/api/slow/posts/$id")
            .build()

        client.newCall(request)
            .enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(Post(0, "", "", "", authorAvatar = "", likeByMe = false))
            }

            override fun onFailure(call: Call, e: IOException) {
                callback.onError(e)
            }
        })
    }

    override fun clearEdit(post: Post) {

    }

    override fun saveAsync(post: Post, callback: PostRepository.Callback<Post>) {
        val request: Request = Request.Builder()
            .post(gson.toJson(post).toRequestBody(jsonType))
            .url("${BASE_URL}/api/slow/posts")
            .build()

        client.newCall(request)
            .enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    callback.onSuccess(post)
                }

                override fun onFailure(call: Call, e: IOException) {
                    callback.onError(e)
                }
            })
    }




}

