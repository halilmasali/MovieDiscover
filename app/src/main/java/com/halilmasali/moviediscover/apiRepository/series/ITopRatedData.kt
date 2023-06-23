package com.halilmasali.moviediscover.apiRepository.series

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ITopRatedData {
    @GET("tv/top_rated")
    fun createGet(@Header("Authorization") apiKey: String): Call<SeriesModelRoot>
}