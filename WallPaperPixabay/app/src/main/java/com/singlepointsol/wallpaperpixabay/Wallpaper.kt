package com.singlepointsol.wallpaperpixabay

import com.google.gson.annotations.SerializedName

data class Wallpaper(
    @SerializedName("webformatURL")
    val wallpaperImage: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("views")
    val views: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("likes")
    val likes: Int
)

// Wrapper class for the entire response
data class WallpaperResponse(
    @SerializedName("hits")
    val hits: List<Wallpaper>
)
