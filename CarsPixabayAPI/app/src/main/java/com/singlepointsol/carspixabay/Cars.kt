package com.singlepointsol.carspixabay

import com.google.gson.annotations.SerializedName

data class Cars(
    @SerializedName("webformatURL")
    val carsImage: String,
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
data class CarsResponse(
    @SerializedName("hits")
    val hits: List<Cars>
)
