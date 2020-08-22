package com.genxmultiplexer.notificationbuilder.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.genxmultiplexer.notificationbuilder.R

class NotificationService : Service() {

    val channelId: String = "notification"
    val notificationlId: Int = 1
    val obj=Constarnts()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();


        showNotification()


        when(intent?.action){
            obj.play ->{
                Toast.makeText(this,"Playing Music",Toast.LENGTH_SHORT).show()
            }
            obj.cancel ->{
                Toast.makeText(this,"Cancelled Music",Toast.LENGTH_SHORT).show()

            }
        }


        return START_NOT_STICKY
    }


    override fun onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    fun showNotification() {

        val playIntent = Intent(this, NotificationService::class.java)
        playIntent.action = obj.play
        val playPendingIntent = PendingIntent.getService(this, 100, playIntent, 0)


        val cancelIntent = Intent(this, NotificationService::class.java)
        cancelIntent.action = obj.cancel
        val cacelPendingIntent = PendingIntent.getService(this, 100, cancelIntent, 0)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val charSquacne: CharSequence = "Notification"
            val message: String = "Notification"
            val importenc = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(channelId, message, importenc)
            notificationChannel.description = message

            val notificationManager =
                getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notiBuilder = NotificationCompat.Builder(this, channelId)
        notiBuilder.setSmallIcon(R.mipmap.ic_launcher)

        notiBuilder.setContentTitle("My Notification")
        notiBuilder.setContentText("My Notification Message")


        notiBuilder.priority = NotificationCompat.PRIORITY_DEFAULT
        notiBuilder.setAutoCancel(true)

        notiBuilder.addAction(R.mipmap.ic_launcher, obj.play, playPendingIntent)
        notiBuilder.addAction(R.mipmap.ic_launcher, obj.cancel, cacelPendingIntent)




        startForeground(1, notiBuilder.build())
    }
}