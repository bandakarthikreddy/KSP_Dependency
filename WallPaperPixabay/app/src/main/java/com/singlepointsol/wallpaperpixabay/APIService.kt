package com.singlepointsol.wallpaperpixabay

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("?key=47626086-cb873d7b992d88489e8f9d0ad&q=wallpapers&image_type=photo&pretty=true")
    suspend fun getWallpaper(): Response<WallpaperResponse>
}