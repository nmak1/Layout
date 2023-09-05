package ru.netology.layout.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.netology.layout.auth.AppAuth
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var auth: AppAuth

    private val appScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        setupAuth()
    }

    private fun setupAuth() {
        appScope.launch {
            auth.sendPushToken()
        }
    }
}