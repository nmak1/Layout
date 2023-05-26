package ru.netology.layout.dto


import android.os.Parcel
import android.os.Parcelable




data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likeByMe: Boolean = false,
    val likes: Long = 0,
    val shares:Long = 0,
    val views: Long = 0,
    val videoUrl: String?=null
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}


