package ru.netology.layout.repository

import ru.netology.layout.api.UserApi
import ru.netology.layout.dto.User

import ru.netology.layout.errors.ApiException
import ru.netology.layout.errors.NetworkException
import ru.netology.layout.errors.UnknownException
import java.io.IOException

class AuthRepository {

    suspend fun authUser(login: String?, password: String?): User {
        try {
            val response = UserApi.service.updateUser(login, password)
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
            val response = UserApi.service.registrationUser(login, password, name)
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