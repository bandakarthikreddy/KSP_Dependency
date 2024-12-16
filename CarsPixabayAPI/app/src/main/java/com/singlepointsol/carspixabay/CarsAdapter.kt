package com.singlepointsol.carspixabay

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarsAdapter(private val carsArrayList: ArrayList<Cars>) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carsImage: ImageView = itemView.findViewById(R.id.carslist_image)
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val viewCount: TextView = itemView.findViewById(R.id.view_count)
        val downloadCount: TextView = itemView.findViewById(R.id.download_count)
        val likesCount: TextView = itemView.findViewById(R.id.likes_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cars_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = carsArrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cars = carsArrayList[position]

        // Load the image using Glide
        Glide.with(holder.itemView.context).load(cars.carsImage).into(holder.carsImage)

        // Set the text for the user, views, downloads, and likes
        holder.userName.text = "User: ${cars.user}"
        holder.viewCount.text = "Views: ${cars.views}"
        holder.downloadCount.text = "Downloads: ${cars.downloads}"
        holder.likesCount.text = "Likes: ${cars.likes}"

        // Handle item click event to pass the data to the next activity
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CarsActivity::class.java).apply {
                putExtra("carsImage", cars.carsImage)
                putExtra("user", cars.user)
                putExtra("views", cars.views)
                putExtra("downloads", cars.downloads)
                putExtra("likes", cars.likes)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
}
