package ru.netology.layout.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import ru.netology.layout.service.notifications.Action
import ru.netology.layout.service.notifications.Like
import ru.netology.layout.service.notifications.NewPost
import ru.netology.layout.R
import kotlin.random.Random
import ru.netology.layout.service.notifications.Notification

class FCMService : FirebaseMessagingService() {
    private val action = "action"
    private val content = "content"
    private val channelId = "remote"
    private val gson = Gson()

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_remote_name)
            val descriptionText = getString(R.string.channel_remote_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {

        try {
            message.data[action]?.let {
                when (Action.valueOf(it)) {
                    Action.LIKE -> handleLike(
                        gson.fromJson(
                            message.data[content],
                            Like::class.java
                        )
                    )
                    Action.POST -> handleNewPost(
                        gson.fromJson(
                            message.data[content],
                            NewPost::class.java
                        )
                    )
                }
            }
        } catch (error: IllegalArgumentException) {
            errorNotification(gson.fromJson(message.data[content], Notification::class.java))
        }
    }

    override fun onNewToken(token: String) {
        println(token)
    }

    private fun errorNotification(content: Notification) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setShowWhen(false)
            .setContentTitle(
                getString(
                    R.string.error_notification_title
                )
            )
            .setContentText(
                getString(
                    R.string.error_notification_text
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }

    private fun handleLike(content: Like) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(
                getString(
                    R.string.notification_user_liked,
                    content.userName,
                    content.postAuthor,
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }

    @SuppressLint("StringFormatInvalid")
    private fun handleNewPost(content: NewPost) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(
                getString(
                    R.string.notification_user_new_posted,
                    content.postAuthor
                )
            )
            .setStyle(NotificationCompat.BigTextStyle().bigText(content.postContent))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }
}