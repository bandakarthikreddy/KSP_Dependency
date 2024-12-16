package com.singlepointsol.carspixabay

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("?key=47653851-be8efc99633a4a71764951148&q=cars&image_type=\"photo\", \"illustration\"&pretty=true")
    suspend fun getCars() : Response<CarsResponse>
}