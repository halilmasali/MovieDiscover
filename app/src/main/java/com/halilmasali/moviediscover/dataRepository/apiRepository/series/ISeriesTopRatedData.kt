package com.halilmasali.moviediscover.dataRepository.apiRepository.series

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ISeriesTopRatedData {
    @GET("tv/top_rated")
    fun createGet(@Header("Authorization") apiKey: String): Call<SeriesModelRoot>
}