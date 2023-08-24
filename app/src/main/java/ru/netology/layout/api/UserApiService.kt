package ru.netology.layout.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.netology.layout.BuildConfig
import ru.netology.layout.auth.AppAuth
import ru.netology.layout.dto.User


private const val BASE_URL = "${BuildConfig.BASE_URL}/api/users/"

private val logging = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private val authInterceptor = Interceptor { chain ->
    val request = AppAuth.getInstance().data.value?.token?.let {
        chain.request()
            .newBuilder()
            .addHeader("Authorization", it)
            .build()
    } ?: chain.request()

    chain.proceed(request)
}

private val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .addInterceptor(authInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .baseUrl(BASE_URL)
    .build()

interface UserApiService {
    @FormUrlEncoded
    @POST("users/authentication")
    suspend fun updateUser(
        @Field("login") login: String?,
        @Field("pass") pass: String?,
    ): Response<User>

    @FormUrlEncoded
    @POST("users/registration")
    suspend fun registrationUser(
        @Field("login") login: String?,
        @Field("pass") pass: String?,
        @Field("name") name: String?,
    ): Response<User>
}

object UserApi {
    val service: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}