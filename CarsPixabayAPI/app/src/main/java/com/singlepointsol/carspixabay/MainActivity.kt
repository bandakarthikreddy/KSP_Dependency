package com.singlepointsol.carspixabay

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.singlepointsol.carspixabay.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var carsArrayList: ArrayList<Cars>
    lateinit var carsAdapter: CarsAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the RecyclerView
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        carsArrayList = arrayListOf()
        carsAdapter = CarsAdapter(carsArrayList)
        recyclerView.adapter = carsAdapter

        // Fetch flowers data from API
        fetchFlowersData()
    }

    // Coroutine scope to fetch data
    private fun fetchFlowersData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.getRetrofitInstance()
                    .create(ApiService::class.java)
                    .getCars() // Calling suspend function

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val flowersList = response.body()?.hits ?: emptyList()
                        carsArrayList.clear()
                        carsArrayList.addAll(flowersList)
                        carsAdapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }
}
