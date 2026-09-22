package com.example.laba5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val images = ArrayList<Image>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setData()
        val recyclerView = findViewById<RecyclerView>(R.id.review1)
        val adapter = CustomRecyclerAdapter(this, images)
        recyclerView.adapter = adapter
    }
    private fun setData() {
        images.add(Image("cat1", R.drawable.image1))
        images.add(Image("cat2", R.drawable.image2))
        images.add(Image("dog1", R.drawable.image3))
        images.add(Image("dog2", R.drawable.image4))
    }
}