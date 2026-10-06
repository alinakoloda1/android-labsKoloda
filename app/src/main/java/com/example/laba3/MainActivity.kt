package com.example.laba3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // MainActivity
        val nameField = findViewById<EditText>(R.id.editTextText)
        val groupField = findViewById<EditText>(R.id.editTextText2)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("name", nameField.text.toString())
            intent.putExtra("group", groupField.text.toString())
            startActivity(intent)
        }

    }
}