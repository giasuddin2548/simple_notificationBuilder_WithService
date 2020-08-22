package com.genxmultiplexer.notificationbuilder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.genxmultiplexer.notificationbuilder.Service.NotificationService

class MainActivity : AppCompatActivity() {

    val channelId: String = "notification"
    val notificationlId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showNotification(view: View) {
        startService(Intent(this,NotificationService::class.java))

    }


}