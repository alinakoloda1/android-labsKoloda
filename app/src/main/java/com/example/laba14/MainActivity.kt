package com.example.laba14

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            if (checkSelfPermission(android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 0)
            } else {
                requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 100)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val bitmap = data?.extras?.get("data") as? Bitmap
        imageView.setImageBitmap(bitmap)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 0)
            }
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
                            "\r\n\nПрограмма с примером работы с камерой" +
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