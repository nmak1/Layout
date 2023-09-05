package ru.netology.layout.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.netology.layout.dao.PostDao
import ru.netology.layout.entity.PostEntity


@Database(entities = [PostEntity::class], version = 2, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun postDao(): PostDao

}