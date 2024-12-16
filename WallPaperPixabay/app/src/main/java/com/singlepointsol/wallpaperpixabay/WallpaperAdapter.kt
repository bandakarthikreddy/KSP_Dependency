package com.singlepointsol.wallpaperpixabay

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WallpaperAdapter(private val wallpaperArrayList: ArrayList<Wallpaper>) :
    RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wallpaperImage: ImageView = itemView.findViewById(R.id.wallpaperlist_image)
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val viewCount: TextView = itemView.findViewById(R.id.view_count)
        val downloadCount: TextView = itemView.findViewById(R.id.download_count)
        val likesCount: TextView = itemView.findViewById(R.id.likes_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = wallpaperArrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallpaper = wallpaperArrayList[position]

        // Load the image using Glide
        Glide.with(holder.itemView.context).load(wallpaper.wallpaperImage).into(holder.wallpaperImage)

        // Set text for user, views, downloads, and likes
        holder.userName.text = "User: ${wallpaper.user}"
        holder.viewCount.text = "Views: ${wallpaper.views}"
        holder.downloadCount.text = "Downloads: ${wallpaper.downloads}"
        holder.likesCount.text = "Likes: ${wallpaper.likes}"

        // Handle item click to navigate to WallPaperActivity with correct data
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, WallPaperActivity::class.java)
            intent.putExtra("wallpaperImage", wallpaper.wallpaperImage)
            intent.putExtra("user", wallpaper.user)
            intent.putExtra("views", wallpaper.views)
            intent.putExtra("downloads", wallpaper.downloads)
            intent.putExtra("likes", wallpaper.likes)
            holder.itemView.context.startActivity(intent)
        }
    }
}
