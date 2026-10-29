package com.example.laba10

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text1)
        registerForContextMenu(textView)

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
    }


    override fun onCreateContextMenu(
        menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.contextmenu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        val textView = findViewById<TextView>(R.id.text1)
        if (id == R.id.color_blue) {
            textView.setTextColor(Color.parseColor("blue"))
        }
        if (id == R.id.color_green) {
            textView.setTextColor(Color.parseColor("green"))
        }
        return super.onContextItemSelected(item)
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
        if (id == R.id.show_text) {
            val textView = findViewById<TextView>(R.id.text1)
            if (item.isChecked) {
                textView.visibility = View.VISIBLE
                item.isChecked = false
            } else {
                textView.visibility = View.INVISIBLE
                item.isChecked = true
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}