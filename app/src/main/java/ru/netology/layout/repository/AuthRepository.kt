package ru.netology.layout.repository

import ru.netology.layout.api.ApiService
import ru.netology.layout.dto.User
import ru.netology.layout.errors.ApiException
import ru.netology.layout.errors.NetworkException
import ru.netology.layout.errors.UnknownException
import java.io.IOException
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: ApiService,
) {

    suspend fun authUser(login: String?, password: String?): User {
        try {
            val response = api.updateUser(login, password)
            if (!response.isSuccessful) {
                throw ApiException(response.code(), response.message())
            }
            return response.body() ?: throw Exception()
        } catch (e: IOException) {
            throw NetworkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

    suspend fun registrationUser(login: String?, password: String?, name: String?): User {
        try {
            val response = api.registrationUser(login, password, name)
            if (!response.isSuccessful) {
                throw ApiException(response.code(), response.message())
            }
            return response.body() ?: throw Exception()
        } catch (e: IOException) {
            throw NetworkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }
}