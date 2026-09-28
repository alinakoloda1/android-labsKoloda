package com.example.laba11

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonNotify = findViewById<Button>(R.id.buttonNotify)

        buttonNotify.setOnClickListener {
            requestMultiplePermissions()
            showNotification()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showNotification() {
        val context: Context = this@MainActivity
        var newnotchan: NotificationChannel? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            newnotchan = NotificationChannel(
                "mychannel1", "mychannel", NotificationManager.IMPORTANCE_HIGH
            )
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
            newnotchan.setSound(
                Uri.parse(
                    ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName +
                            "/" + R.raw.roblox8
                ),
                audioAttributes
            )
        }

        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(newnotchan!!)
        }

        var notification: Notification? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = Notification.Builder(context, "mychannel1")
                .setContentTitle("Привет от Алины")
                .setContentText("Hey, listen!")
                .setTicker("Новое уведомление!")
                .setChannelId("mychannel1")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .build()
        }
        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notification)
    }

    fun requestMultiplePermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                return
            } else finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            val dialog = AlertDialog.Builder(this)
            try {
                dialog.setMessage(
                    title.toString() + " версия " +
                            packageManager.getPackageInfo(packageName, 0).versionName +
                            "\r\n\nПрограмма с примером уведомлений" +
                            "\r\n\nАвтор - Колода Алина, гр. БОМ41ПРИ"
                )
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            dialog.setTitle("О программе")
            dialog.setNeutralButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            dialog.setIcon(R.mipmap.ic_launcher_round)
            val alertDialog = dialog.create()
            alertDialog.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}