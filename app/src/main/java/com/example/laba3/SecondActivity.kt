package com.example.laba3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        // SecondActivity
        val nameView = findViewById<TextView>(R.id.textView6)
        nameView.text = intent.getStringExtra("name")

        val groupView = findViewById<TextView>(R.id.textView3)
        groupView.text = intent.getStringExtra("group")
        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            finish()
        }

    }
}