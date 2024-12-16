package com.singlepointsol.wallpaperpixabay

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class WallPaperActivity : AppCompatActivity() {
    lateinit var wallpaperImageView: ImageView
    lateinit var userName: TextView
    lateinit var viewCount: TextView
    lateinit var downloadCount: TextView
    lateinit var likesCount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_paper)

// Initialize the views
        wallpaperImageView = findViewById(R.id.wallpaper_imageView)
        userName = findViewById(R.id.user_name)
        viewCount = findViewById(R.id.view_count)
        downloadCount = findViewById(R.id.download_count)
        likesCount = findViewById(R.id.likes_count)

        // Get the data from the intent
        val imageUrl = intent.getStringExtra("wallpaperImage")
        val user = intent.getStringExtra("user")
        val views = intent.getIntExtra("views", 0)
        val downloads = intent.getIntExtra("downloads", 0)
        val likes = intent.getIntExtra("likes", 0)

        // Load the image using Glide
        Glide.with(this).load(imageUrl).into(wallpaperImageView)

        // Set the text views
        userName.text = "User: $user"
        viewCount.text = "Views: $views"
        downloadCount.text = "Downloads: $downloads"
        likesCount.text = "Likes: $likes"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}