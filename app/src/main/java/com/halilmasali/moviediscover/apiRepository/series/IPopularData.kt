package com.halilmasali.moviediscover.apiRepository.series

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IPopularData {
    @GET("tv/popular")
    fun createGet(@Header("Authorization") apiKey: String): Call<SeriesModelRoot>
}