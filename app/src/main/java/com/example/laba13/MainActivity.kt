package com.example.laba13

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val videoView = findViewById<VideoView>(R.id.videoView)

        // Путь к видео из res/raw
        videoView.setVideoURI(
            Uri.parse("android.resource://" + packageName + "/" + R.raw.catdog)
        )

        // Элементы управления (пауза, перемотка и т.д.)
        videoView.setMediaController(MediaController(this))
        videoView.start()
        videoView.requestFocus()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            val dialog = AlertDialog.Builder(this)
            try {
                dialog.setMessage(
                    title.toString() + " версия " +
                            packageManager.getPackageInfo(packageName, 0).versionName +
                            "\r\n\nПрограмма с примером работы с видео" +
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