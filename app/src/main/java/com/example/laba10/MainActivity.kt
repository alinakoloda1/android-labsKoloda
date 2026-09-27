package com.example.laba10

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonExit = findViewById<Button>(R.id.buttonExit)

        buttonExit.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("Вы действительно хотите выйти?")
            dialog.setCancelable(false)
            dialog.setPositiveButton("Да") { _, _ ->
                this.finish()
            }
            dialog.setNegativeButton("Нет") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            val alertDialog = dialog.create()
            alertDialog.show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_about) {
            val dialog = AlertDialog.Builder(this)
            try {
                dialog.setMessage(
                    title.toString() + " версия " +
                            packageManager.getPackageInfo(packageName, 0).versionName +
                            "\r\n\nПрограмма с примером выполнения диалогового окна" +
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