package com.singlepointsol.wallpaperpixabay

import android.os.Bundle
import android.widget.Adapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.singlepointsol.wallpaperpixabay.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var wallpaperArrayList: ArrayList<Wallpaper>
    lateinit var wallpaperAdapter: WallpaperAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the Recycler View
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        wallpaperArrayList = arrayListOf()
        wallpaperAdapter = WallpaperAdapter(wallpaperArrayList)
        recyclerView.adapter = wallpaperAdapter

        fetchWallpaperData()
    }
        // Coroutine scope to fetch data
        private fun fetchWallpaperData() {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = RetrofitInstance.getRetrofitInstance()
                        .create(APIService::class.java)
                        .getWallpaper() // Calling suspend function

                    if (response.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            val wallpaperList = response.body()?.hits ?: emptyList()
                            wallpaperArrayList.clear()
                            wallpaperArrayList.addAll(wallpaperList)
                            wallpaperAdapter.notifyDataSetChanged()
                        }
                    }
                } catch (e: Exception) {
                    // Handle errors
                }
            }
        }
    }