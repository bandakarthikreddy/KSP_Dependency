package com.singlepointsol.carspixabay

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class CarsActivity : AppCompatActivity() {
    lateinit var carsImageView: ImageView
    lateinit var userName: TextView
    lateinit var viewCount: TextView
    lateinit var downloadCount: TextView
    lateinit var likesCount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cars)

        // Initialize the views
        carsImageView = findViewById(R.id.cars_imageView)
        userName = findViewById(R.id.user_name)
        viewCount = findViewById(R.id.view_count)
        downloadCount = findViewById(R.id.download_count)
        likesCount = findViewById(R.id.likes_count)

        // Get the data from the intent
        val imageUrl = intent.getStringExtra("carsImage")
        val user = intent.getStringExtra("user")
        val views = intent.getIntExtra("views", 0)
        val downloads = intent.getIntExtra("downloads", 0)
        val likes = intent.getIntExtra("likes", 0)

        // Load the image using Glide
        Glide.with(this).load(imageUrl).into(carsImageView)

        // Set the text views
        userName.text = "User: $user"
        viewCount.text = "Views: $views"
        downloadCount.text = "Downloads: $downloads"
        likesCount.text = "Likes:$likes"
        }
}
