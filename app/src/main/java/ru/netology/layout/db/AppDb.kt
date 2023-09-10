package ru.netology.layout.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.netology.layout.dao.PostDao
import ru.netology.layout.dao.PostRemoteKeyDao
import ru.netology.layout.entity.PostEntity
import ru.netology.layout.entity.PostRemoteKeyEntity



@Database(entities = [PostEntity::class, PostRemoteKeyEntity::class],
    version = 2,
    exportSchema = false)

abstract class AppDb : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun postRemoteKeyDao(): PostRemoteKeyDao
}