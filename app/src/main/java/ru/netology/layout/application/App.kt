package ru.netology.layout.application

import android.app.Application
import ru.netology.layout.auth.AppAuth

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppAuth.initApp(this)
    }
}