package com.example.laba99

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.show_text) {
            val textView = findViewById<TextView>(R.id.text1)
            if (item.isChecked) {
                textView.visibility = View.VISIBLE
                item.isChecked = false
            } else {
                textView.visibility = View.INVISIBLE
                item.isChecked = true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
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
        if (id == R.id.color_red) {
            textView.setTextColor(Color.parseColor("red"))
        }
        if (id == R.id.color_black) {
            textView.setTextColor(Color.parseColor("blue"))
        }
        return super.onContextItemSelected(item)
    }
}