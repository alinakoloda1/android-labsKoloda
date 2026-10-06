package com.example.laba5

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val images = ArrayList<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setData()

        val recyclerView = findViewById<RecyclerView>(R.id.review1)

        // Определяем количество колонок в зависимости от ориентации и размера экрана
        val orientation = resources.configuration.orientation
        val screenSize = resources.configuration.screenLayout and
                Configuration.SCREENLAYOUT_SIZE_MASK

        val columns = when {
            screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE -> 3  // планшет
            orientation == Configuration.ORIENTATION_LANDSCAPE -> 2   // телефон горизонтально
            else -> 1                                                  // телефон вертикально
        }

        recyclerView.layoutManager = GridLayoutManager(this, columns)
        recyclerView.adapter = CustomRecyclerAdapter(this, images)
    }

    private fun setData() {
        images.add(Image("cat1", R.drawable.image1))
        images.add(Image("cat2", R.drawable.image2))
        images.add(Image("dog1", R.drawable.image3))
        images.add(Image("dog2", R.drawable.image4))
        images.add(Image("cat3", R.drawable.image1))
        images.add(Image("cat4", R.drawable.image2))
        images.add(Image("dog3", R.drawable.image3))
        images.add(Image("dog4", R.drawable.image4))
        images.add(Image("cat5", R.drawable.image1))
        images.add(Image("cat6", R.drawable.image2))
        images.add(Image("dog5", R.drawable.image3))
        images.add(Image("dog6", R.drawable.image4))
        images.add(Image("cat7", R.drawable.image1))
        images.add(Image("cat8", R.drawable.image2))
        images.add(Image("dog7", R.drawable.image3))
        images.add(Image("cat9", R.drawable.image1))
        images.add(Image("cat10", R.drawable.image2))
        images.add(Image("dog8", R.drawable.image3))
        images.add(Image("dog9", R.drawable.image4))
        images.add(Image("cat11", R.drawable.image1))
        images.add(Image("cat12", R.drawable.image2))
        images.add(Image("dog10", R.drawable.image3))
        images.add(Image("dog11", R.drawable.image4))
        images.add(Image("cat13", R.drawable.image1))
        images.add(Image("cat14", R.drawable.image2))
        images.add(Image("dog12", R.drawable.image3))
        images.add(Image("dog13", R.drawable.image4))
        images.add(Image("cat15", R.drawable.image1))
        images.add(Image("cat16", R.drawable.image2))
        images.add(Image("dog14", R.drawable.image3))
        images.add(Image("dog15", R.drawable.image4))
    }
}